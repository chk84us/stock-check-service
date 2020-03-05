package com.myretailer.stockcheckservice.stockcheckservice.api.controller;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.ProductRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.ProductResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = "product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @PostMapping(path = "product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return service.createProduct(request);
    }
}
