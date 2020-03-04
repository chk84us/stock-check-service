package com.myretailer.stockcheckservice.stockcheckservice.api.model.response;

import java.util.Set;

public class StockAuditResponse {

    private Long id;

    private Set<Long> productsToReorder;

    private Set<Long> doNotReorder;

    private Set<Long> productsForOneOffOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getProductsToReorder() {
        return productsToReorder;
    }

    public void setProductsToReorder(Set<Long> productsToReorder) {
        this.productsToReorder = productsToReorder;
    }

    public Set<Long> getDoNotReorder() {
        return doNotReorder;
    }

    public void setDoNotReorder(Set<Long> doNotReorder) {
        this.doNotReorder = doNotReorder;
    }

    public Set<Long> getProductsForOneOffOrder() {
        return productsForOneOffOrder;
    }

    public void setProductsForOneOffOrder(Set<Long> productsForOneOffOrder) {
        this.productsForOneOffOrder = productsForOneOffOrder;
    }
}
