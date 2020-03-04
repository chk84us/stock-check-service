package com.myretailer.stockcheckservice.stockcheckservice.domain.service;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.StockAuditRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.StockAuditResponse;

public interface StockAuditService {

    StockAuditResponse performStockAudit(StockAuditRequest request);
}
