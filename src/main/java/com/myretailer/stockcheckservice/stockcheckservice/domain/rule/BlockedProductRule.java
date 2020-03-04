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

import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.BLOCKED_ADVICE_LIST;
import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.INVENTORY;

@Rule(name = "blocked-rule")
public class BlockedProductRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockedProductRule.class);

    @Condition
    public boolean whenProductIsMarkedAsBlocked(@Fact(INVENTORY) InventoryDao inventory) {
        return inventory.getDoNotReorder() != null && inventory.getDoNotReorder();
    }

    @Action
    public void adviceToNotReOrder(@Fact(INVENTORY) InventoryDao inventory,
                                   @Fact(BLOCKED_ADVICE_LIST) Set<Long> blockedAdviceList) {
        blockedAdviceList.add(inventory.getId());
        LOGGER.info(MessageFormat.format("product id: {0} is marked as blocked. Do not re-order", inventory.getId()));
    }

}
