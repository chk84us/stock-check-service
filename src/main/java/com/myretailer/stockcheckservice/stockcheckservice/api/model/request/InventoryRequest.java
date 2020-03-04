package com.myretailer.stockcheckservice.stockcheckservice.api.model.request;

import javax.validation.constraints.NotNull;

public class InventoryRequest {

    @NotNull
    private Long productId;

    private Integer currentStockLevel;

    private Integer minimumStockLevel;

    private Boolean blocked;

    private Boolean oneOffOrder;

    private Integer oneOffOrderQuantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCurrentStockLevel() {
        return currentStockLevel;
    }

    public void setCurrentStockLevel(Integer currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getOneOffOrder() {
        return oneOffOrder;
    }

    public void setOneOffOrder(Boolean oneOffOrder) {
        this.oneOffOrder = oneOffOrder;
    }

    public Integer getOneOffOrderQuantity() {
        return oneOffOrderQuantity;
    }

    public void setOneOffOrderQuantity(Integer oneOffOrderQuantity) {
        this.oneOffOrderQuantity = oneOffOrderQuantity;
    }

    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(Integer minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }
}
