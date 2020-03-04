package com.myretailer.stockcheckservice.stockcheckservice.domain.repo;

import com.myretailer.stockcheckservice.stockcheckservice.domain.model.InventoryDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryDao, Long> {
}
