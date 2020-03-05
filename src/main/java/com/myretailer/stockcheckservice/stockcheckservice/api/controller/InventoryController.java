package com.myretailer.stockcheckservice.stockcheckservice.api.controller;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.InventoryRequest;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InventoryController {

    private InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PutMapping(path = "inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@Valid @RequestBody InventoryRequest request) {
        service.updateInventory(request);
    }
}
