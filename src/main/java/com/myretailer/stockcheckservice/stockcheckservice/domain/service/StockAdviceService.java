package com.myretailer.stockcheckservice.stockcheckservice.domain.service;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAdviceRequest;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAdviceDao;

public interface StockAdviceService {

    void createAdvice(StockAdviceRequest request);

    StockAdviceDao getAdvice(StockAdviceType adviceType);
}
