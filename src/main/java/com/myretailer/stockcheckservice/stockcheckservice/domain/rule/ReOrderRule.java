package com.myretailer.stockcheckservice.stockcheckservice.domain.rule;

import com.myretailer.stockcheckservice.stockcheckservice.domain.model.InventoryDao;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Set;

import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.INVENTORY;
import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.RE_ORDER_ADVICE_LIST;

@Rule(name = "reorder-rule")
public class ReOrderRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReOrderRule.class);

    @Condition
    public boolean whenInventoryLessThanMinimum(@Fact(INVENTORY) InventoryDao inventory) {
        return inventory.getDoNotReorder() == null ?
                isProductInventorySufficient(inventory) :
                !inventory.getDoNotReorder() && isProductInventorySufficient(inventory);
    }

    @Action
    public void adviceReorder(@Fact(INVENTORY) InventoryDao inventory,
                              @Fact(RE_ORDER_ADVICE_LIST) Set<Long> productsToReOrder) {
        productsToReOrder.add(inventory.getId());
        LOGGER.info(MessageFormat.format("Re-order product id: {0}. stock level ({1}) <= minimum stock level ({2})",
                inventory.getId(), inventory.getCurrentStockLevel(), inventory.getMinimumStockLevel()));
    }

    private boolean isProductInventorySufficient(InventoryDao inventory) {
        return inventory.getCurrentStockLevel() <= inventory.getMinimumStockLevel();
    }
}
