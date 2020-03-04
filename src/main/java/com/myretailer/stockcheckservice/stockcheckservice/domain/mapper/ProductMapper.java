package com.myretailer.stockcheckservice.stockcheckservice.domain.mapper;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.ProductRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.ProductResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.ProductDAO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse map(ProductDAO productDAO) {
        ProductResponse response = new ProductResponse();
        response.setId(productDAO.getId());
        response.setName(productDAO.getName());
        response.setUnitPrice(productDAO.getUnitPrice());
        return response;
    }

    public ProductDAO map(ProductRequest request) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.setName(request.getName());
        productDAO.setUnitPrice(request.getUnitPrice());
        return productDAO;
    }

}
