package com.myretailer.stockcheckservice.stockcheckservice.domain.repo;

import com.myretailer.stockcheckservice.stockcheckservice.api.model.StockAdviceType;
import com.myretailer.stockcheckservice.stockcheckservice.domain.model.StockAdviceDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAdviceRepository extends JpaRepository<StockAdviceDao, Long> {

    StockAdviceDao findByAdviceType(StockAdviceType adviceType);
}
