package com.myretailer.stockcheckservice.stockcheckservice.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity(name = "inventory")
public class InventoryDao {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private ProductDao product;

    private Integer minimumStockLevel;

    private Integer currentStockLevel;

    private Boolean doNotReorder;

    private Boolean oneOffOrder;

    private Integer oneOffOrderQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDao getProduct() {
        return product;
    }

    public void setProduct(ProductDao product) {
        this.product = product;
    }

    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(Integer minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }

    public Integer getCurrentStockLevel() {
        return currentStockLevel;
    }

    public void setCurrentStockLevel(Integer currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }

    public Boolean getDoNotReorder() {
        return doNotReorder;
    }

    public void setDoNotReorder(Boolean doNotReorder) {
        this.doNotReorder = doNotReorder;
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
}
