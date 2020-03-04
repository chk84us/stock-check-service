package com.myretailer.stockcheckservice.stockcheckservice.domain.mapper;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.ProductRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.ProductResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.ProductDao;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse map(ProductDao productDAO) {
        ProductResponse response = new ProductResponse();
        response.setId(productDAO.getId());
        response.setName(productDAO.getName());
        response.setUnitPrice(productDAO.getUnitPrice());
        return response;
    }

    public ProductDao map(ProductRequest request) {
        ProductDao productDAO = new ProductDao();
        productDAO.setName(request.getName());
        productDAO.setUnitPrice(request.getUnitPrice());
        return productDAO;
    }

}
