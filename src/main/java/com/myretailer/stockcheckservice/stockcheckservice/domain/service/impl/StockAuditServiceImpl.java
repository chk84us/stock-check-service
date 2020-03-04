package com.myretailer.stockcheckservice.stockcheckservice.domain.service.impl;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAuditRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.StockAuditResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.InventoryDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.ProductDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAdviceDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAuditDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.InventoryRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.ProductRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.StockAuditRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.rule.BlockedProductRule;
import com.myretailer.stockcheckservice.stockcheckservice.domain.rule.OneOffOrderRule;
import com.myretailer.stockcheckservice.stockcheckservice.domain.rule.ReOrderRule;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.StockAdviceService;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.StockAuditService;
import com.myretailer.stockcheckservice.stockcheckservice.exception.StockCheckServiceException;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.*;

@Service
public class StockAuditServiceImpl implements StockAuditService {

    private StockAuditRepository repository;
    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;

    private StockAdviceService stockAdviceService;

    private Facts facts;
    private Rules rules;
    private RulesEngine rulesEngine;

    public StockAuditServiceImpl(
            StockAuditRepository repository,
            StockAdviceService stockAdviceService, ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.repository = repository;
        this.stockAdviceService = stockAdviceService;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.rules = new Rules();
        this.facts = new Facts();
        this.rulesEngine = new DefaultRulesEngine();
    }

    @Override
    public StockAuditResponse performStockAudit(StockAuditRequest request) {
        Set<Long> reorderList = new HashSet<>();
        Set<Long> blockedList = new HashSet<>();
        Set<Long> oneOffOrderList = new HashSet<>();

        facts.put(RE_ORDER_ADVICE_LIST, reorderList);
        facts.put(BLOCKED_ADVICE_LIST, blockedList);
        facts.put(ONE_OFF_ORDER_ADVICE_LIST, oneOffOrderList);

        registerRules();
        applyRules(request, facts);

        StockAuditResponse response = createResponse(reorderList, blockedList, oneOffOrderList);

        Long auditId = saveAdviceForAuditPurposes(response);
        response.setId(auditId);
        return response;
    }

    private Long saveAdviceForAuditPurposes(StockAuditResponse response) {
        StockAuditDao stockAuditDao = new StockAuditDao();
        Map<ProductDao, StockAdviceDao> adviceDaoMap = mapAdvice(response);
        stockAuditDao.setAdviceDaoMap(adviceDaoMap);
        return repository.save(stockAuditDao).getId();
    }

    private Map<ProductDao, StockAdviceDao> mapAdvice(StockAuditResponse response) {
        Map<ProductDao, StockAdviceDao> adviceDaoMap = new HashMap<>();
        response.getProductsToReorder().forEach(id -> adviceDaoMap.put(productRepository.findById(id).get(),
                stockAdviceService.getAdvice(StockAdviceType.REORDER)));

        response.getDoNotReorder().forEach(id -> adviceDaoMap.put(productRepository.findById(id).get(),
                stockAdviceService.getAdvice(StockAdviceType.DO_NOT_REORDER)));

        response.getProductsForOneOffOrder().forEach(id -> adviceDaoMap.put(productRepository.findById(id).get(),
                stockAdviceService.getAdvice(StockAdviceType.ONE_OFF_ORDER)));
        return adviceDaoMap;
    }

    private StockAuditResponse createResponse(Set<Long> reorderList, Set<Long> blockedList, Set<Long> oneOffOrderList) {
        StockAuditResponse response = new StockAuditResponse();
        response.setProductsToReorder(reorderList);
        response.setDoNotReorder(blockedList);
        response.setProductsForOneOffOrder(oneOffOrderList);
        return response;
    }

    private void registerRules() {
        rules.register(new ReOrderRule());
        rules.register(new BlockedProductRule());
        rules.register(new OneOffOrderRule());
    }

    private void applyRules(StockAuditRequest request, Facts facts) {
        for (Long productId: request.getProductIds()) {
            Optional<ProductDao> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Optional<InventoryDao> inventoryOptional = inventoryRepository.findById(productId);
                if (!inventoryOptional.isPresent()) {
                    throw new StockCheckServiceException(MessageFormat.format("no inventory record for product with " +
                            "id {0}", productId));
                }

                facts.put(INVENTORY, inventoryOptional.get());
                rulesEngine.fire(rules, facts);
            }
        }
    }
}
