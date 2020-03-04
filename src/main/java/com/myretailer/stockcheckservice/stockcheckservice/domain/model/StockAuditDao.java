package com.myretailer.stockcheckservice.stockcheckservice.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity(name = "stock_check")
public class StockAuditDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductStockingAdvice")
    private Map<ProductDao, StockAdviceDao> adviceDaoMap;

    private Date createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<ProductDao, StockAdviceDao> getAdviceDaoMap() {
        return adviceDaoMap;
    }

    public void setAdviceDaoMap(
            Map<ProductDao, StockAdviceDao> adviceDaoMap) {
        this.adviceDaoMap = adviceDaoMap;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @PrePersist
    public void setCreatedOn() {
        createdOn = new Date();
    }
}
