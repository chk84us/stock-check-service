package com.myretailer.stockcheckservice.stockcheckservice.domain.repo;

import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAuditDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAuditRepository extends JpaRepository<StockAuditDao, Long> {
}
