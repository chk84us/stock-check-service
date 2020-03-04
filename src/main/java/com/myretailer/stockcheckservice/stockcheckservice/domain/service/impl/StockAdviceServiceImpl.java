package com.myretailer.stockcheckservice.stockcheckservice.domain.service.impl;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAdviceRequest;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAdviceDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.StockAdviceRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.StockAdviceService;
import org.springframework.stereotype.Service;

@Service
public class StockAdviceServiceImpl implements StockAdviceService {

    private StockAdviceRepository repository;

    public StockAdviceServiceImpl(StockAdviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAdvice(StockAdviceRequest request) {
        StockAdviceDao stockAdviceDao = new StockAdviceDao();
        stockAdviceDao.setAdviceType(request.getAdviceType());
        repository.save(stockAdviceDao);
    }
}
