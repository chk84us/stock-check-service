package com.myretailer.stockcheckservice.stockcheckservice.domain.service;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.ProductRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.ProductResponse;

public interface ProductService {
    ProductResponse getProduct(Long id);

    ProductResponse createProduct(ProductRequest request);
}
