package com.gpraul.apimyinvestmentsassetscore.application.in.impl;

import com.gpraul.apimyinvestmentsassetscore.application.in.ExecutePurchaseOrder;
import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.Order;
import com.gpraul.apimyinvestmentsassetscore.domain.OrderType;
import com.gpraul.apimyinvestmentsassetscore.domain.PurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ExecutePurchaseOrderImplTest {

    private ExecutePurchaseOrder executePurchaseOrder = new ExecutePurchaseOrderImpl();

    private Order itsa4Order;

    private Asset itsa4Asset;

    private PurchaseOrder purchaseOrder;

    @BeforeEach
    public void setUp() {
        itsa4Order = Order.builder()
                .orderType(OrderType.PURCHASE)
                .ticker("ITSA4")
                .amount(100)
                .value(new BigDecimal(13.68))
                .executionDate(LocalDate.now())
                .build();

        itsa4Asset = Asset.builder()
                .ticker("ITSA4")
                .amount(15)
                .totalValue(new BigDecimal(196.5))
                .build();

        purchaseOrder = new PurchaseOrder(itsa4Asset, itsa4Order);
    }

    @Test
    public void shouldAddOrderValuesToAsset() {
        final Asset expectedAsset = Asset.builder()
                .amount(115)
                .totalValue(new BigDecimal(1564.5))
                .build();

        final Asset executedAsset = executePurchaseOrder.execute(purchaseOrder);

        Assertions.assertEquals(expectedAsset.getAmount(), executedAsset.getAmount());
        Assertions.assertEquals(expectedAsset.getTotalValue(), executedAsset.getTotalValue());
    }
}
