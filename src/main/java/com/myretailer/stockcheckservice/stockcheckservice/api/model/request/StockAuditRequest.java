package com.myretailer.stockcheckservice.stockcheckservice.api.model.request;

import java.util.List;

public class StockAuditRequest {

    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
