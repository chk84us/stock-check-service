package com.myretailer.stockcheckservice.stockcheckservice.domain.service;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAdviceRequest;

public interface StockAdviceService {

    void createAdvice(StockAdviceRequest request);
}
