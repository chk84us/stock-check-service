package com.myretailer.stockcheckservice.stockcheckservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class StockCheckServiceRequestException extends HttpClientErrorException {

    public StockCheckServiceRequestException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
