package com.myretailer.stockcheckservice.stockcheckservice.api.model.request;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;

public class StockAdviceRequest {

    private StockAdviceType adviceType;

    public StockAdviceRequest(StockAdviceType adviceType) {
        this.adviceType = adviceType;
    }

    public StockAdviceType getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(StockAdviceType adviceType) {
        this.adviceType = adviceType;
    }
}
