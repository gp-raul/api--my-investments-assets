package com.gpraul.apimyinvestmentsassetscore.domain;

import com.gpraul.apimyinvestmentsassetscore.domain.exception.AmountCannotBeLessThanOneException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PurchaseOrderTest {

    private  Asset itub3Asset;

    private Order itub3Order;

    @BeforeEach
    public void setUp() {
        itub3Asset = Asset.builder()
                .ticker("ITUB3")
                .amount(16)
                .totalValue(new BigDecimal(554.55))
                .build();

        itub3Order = Order.builder()
                .orderType(OrderType.PURCHASE)
                .amount(93)
                .value(new BigDecimal(36.99))
                .build();
    }

    @Test
    public void shouldAddAmountFromOrderToTheAsset() {
        PurchaseOrder purchaseOrder = new PurchaseOrder(itub3Asset, itub3Order);
        purchaseOrder.addOrderAmountToAsset();

        final int updatedAmount = purchaseOrder.getAsset().getAmount();
        Assertions.assertEquals(109, updatedAmount);
    }

    @Test
    public void whenAmountFromOrderIsLessThanOneShouldThrowExeception() {
        itub3Order = Order.builder()
                .orderType(OrderType.PURCHASE)
                .amount(-1)
                .value(new BigDecimal(36.99))
                .build();

        final PurchaseOrder purchaseOrder = new PurchaseOrder(itub3Asset, itub3Order);

        AmountCannotBeLessThanOneException exception = Assertions.assertThrows(AmountCannotBeLessThanOneException.class, () -> {
            purchaseOrder.addOrderAmountToAsset();
        });

        final String expectedMessage = "Order's amount cannot be negative.";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldAddTotalValueToAsset() {
        final PurchaseOrder purchaseOrder = new PurchaseOrder(itub3Asset, itub3Order);
        purchaseOrder.addOrderTotalValueToAsset();

        BigDecimal expectedTotalValue = new BigDecimal(3994.62);
        expectedTotalValue = expectedTotalValue.setScale(2, RoundingMode.HALF_EVEN);

        final BigDecimal actualTotalValue = purchaseOrder.getAsset().getTotalValue();

        Assertions.assertEquals(expectedTotalValue, actualTotalValue);
    }
}
