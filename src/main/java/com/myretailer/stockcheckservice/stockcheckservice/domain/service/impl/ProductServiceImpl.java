package com.myretailer.stockcheckservice.stockcheckservice.domain.service.impl;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.request.ProductRequest;
import com.myretailer.stockcheckservice.stockcheckservice.api.model.response.ProductResponse;
import com.myretailer.stockcheckservice.stockcheckservice.domain.mapper.ProductMapper;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.InventoryDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.ProductDao;
import com.myretailer.stockcheckservice.stockcheckservice.domain.repo.ProductRepository;
import com.myretailer.stockcheckservice.stockcheckservice.domain.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Optional<ProductDao> productOptional = repository.findById(id);
        if (!productOptional.isPresent()) {
            throw new RuntimeException("not found");
        }
        return mapper.map(productOptional.get());
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductDao productDAO = repository.save(mapper.map(request));
        repository.save(updateInventory(productDAO));
        return mapper.map(productDAO);
    }

    private ProductDao updateInventory(ProductDao productDao) {
        InventoryDao inventoryDao = new InventoryDao();
        inventoryDao.setId(productDao.getId());
        inventoryDao.setProduct(productDao);
        productDao.setInventory(inventoryDao);
        return productDao;
    }
}
