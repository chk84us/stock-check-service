package com.myretailer.stockcheckservice.stockcheckservice.domain.model;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;

import javax.persistence.*;

@Entity(name = "stock_advice")
public class StockAdviceDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private StockAdviceType adviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockAdviceType getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(StockAdviceType adviceType) {
        this.adviceType = adviceType;
    }
}
