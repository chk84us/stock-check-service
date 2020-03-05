package com.myretailer.stockcheckservice.stockcheckservice.domain.service.impl;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.InventoryRequest;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.InventoryDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.InventoryRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateInventory(InventoryRequest request) {
        validateRequest(request);
        InventoryDao inventory = repository.findById(request.getProductId()).get();

        if (request.getCurrentStockLevel() != null) {
            inventory.setCurrentStockLevel(request.getCurrentStockLevel());
        }

        if (request.getOneOffOrder() != null) {
            inventory.setOneOffOrder(request.getOneOffOrder());
            inventory.setOneOffOrderQuantity(request.getOneOffOrderQuantity());
        }

        if (request.getBlocked() != null) {
            inventory.setDoNotReorder(request.getBlocked());
        }

        if (request.getMinimumStockLevel() != null) {
            inventory.setMinimumStockLevel(request.getMinimumStockLevel());
        }

        repository.save(inventory);
    }

    private void validateRequest(InventoryRequest request) {
        if (request.getBlocked() == null && request.getOneOffOrder() == null &&
                request.getCurrentStockLevel() == null && request.getMinimumStockLevel() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "at least one of the following fields are required: blocked, oneOffOrder, currentStockLevel, " +
                            "minimumStockLevel");
        }

        if (request.getOneOffOrder() != null && request.getOneOffOrderQuantity() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "oneOffOrderQuantity missing");
        }

        Optional<InventoryDao> inventoryOptional = repository.findById(request.getProductId());
        if (!inventoryOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    MessageFormat.format("No inventory found for product with id {0}", request.getProductId()));
        }
    }
}
