package com.myretailer.stockcheckservice.stockcheckservice.api.controller;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAuditRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.StockAuditResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.StockAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockAuditController {

    private StockAuditService service;

    public StockAuditController(StockAuditService service) {
        this.service = service;
    }

    @PostMapping(path = "stock-audit")
    @ResponseStatus(HttpStatus.CREATED)
    public StockAuditResponse performAudit(@RequestBody StockAuditRequest request) {
        return service.performStockAudit(request);
    }
}
