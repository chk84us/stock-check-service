package com.myretailer.stockcheckservice.stockcheckservice.domain.service;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.InventoryRequest;

public interface InventoryService {
    void updateInventory(InventoryRequest request);
}
