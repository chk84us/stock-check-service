package com.myretailer.stockcheckservice.stockcheckservice.api.controller;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAdviceRequest;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.StockAdviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class StockAdviceController {

    private StockAdviceService service;

    public StockAdviceController(StockAdviceService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        createAdvice(getRequest(StockAdviceType.DO_NOT_REORDER));
        createAdvice(getRequest(StockAdviceType.ONE_OFF_ORDER));
        createAdvice(getRequest(StockAdviceType.REORDER));
    }

    private StockAdviceRequest getRequest(StockAdviceType adviceType) {
        return new StockAdviceRequest(adviceType);
    }

    @PostMapping(path = "stock-advice")
    @ResponseStatus(HttpStatus.CREATED)
    private void createAdvice(@RequestBody StockAdviceRequest request) {
        service.createAdvice(request);
    }

}
