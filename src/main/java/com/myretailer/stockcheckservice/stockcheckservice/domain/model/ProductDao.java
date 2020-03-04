package com.myretailer.stockcheckservice.stockcheckservice.domain.model;

import javax.persistence.*;

@Entity(name = "product")
public class ProductDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double unitPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private InventoryDao inventory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public InventoryDao getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDao inventory) {
        this.inventory = inventory;
    }
}
