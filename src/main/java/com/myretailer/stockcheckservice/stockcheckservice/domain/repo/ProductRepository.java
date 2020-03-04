package com.myretailer.stockcheckservice.stockcheckservice.domain.repo;

import com.myretailer.stockcheckservice.stockcheckservice.domain.model.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDAO, Long> {
}
