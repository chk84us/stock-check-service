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

import static com.myretailer.stockcheckservice.stockcheckservice.constants.Constants.*;

@Rule(name = "one-off-order")
public class OneOffOrderRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneOffOrderRule.class);

    @Condition
    public boolean whenProductIsMarkedAsOneOffOrder(@Fact(INVENTORY) InventoryDao inventory) {
        return inventory.getOneOffOrder() != null && inventory.getOneOffOrder();
    }

    @Action
    public void adviceOneOffOrder(@Fact(INVENTORY) InventoryDao inventory,
                                  @Fact(ONE_OFF_ORDER_ADVICE_LIST) Set<Long> oneOffOrderAdviceList,
                                  @Fact(RE_ORDER_ADVICE_LIST) Set<Long> reOrderAdviceList) {
        Long id = inventory.getId();
        oneOffOrderAdviceList.add(id);
        LOGGER.info(MessageFormat.format("product id: {0} is marked for on-off order ", id));
    }
}
