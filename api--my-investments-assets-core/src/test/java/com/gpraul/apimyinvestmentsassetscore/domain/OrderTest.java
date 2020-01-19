package com.gpraul.apimyinvestmentsassetscore.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderTest {

    private static Order itsa4Order;

    private static Order itub3Order;

    private static Order tiet11Order;

    private static Order itub4Order;

    private static Order egie3Order;

    @BeforeAll
    public static void setUp() {
        itsa4Order = Order.builder()
                .amount(99)
                .value(new BigDecimal(13.68))
                .build();

        itub3Order = Order.builder()
                .amount(67)
                .value(new BigDecimal(36.99))
                .build();

        tiet11Order = Order.builder()
                .amount(100)
                .value(new BigDecimal(27.10))
                .build();

        itub4Order = Order.builder()
                .amount(0)
                .value(new BigDecimal(24.10))
                .build();

        egie3Order = Order.builder()
                .amount(10)
                .value(new BigDecimal(0))
                .build();
    }

    @Test
    public void shouldReturnTheRightPrice() {
        BigDecimal expectedToTalValue = new BigDecimal(1354.32);
        expectedToTalValue = expectedToTalValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedToTalValue, itsa4Order.getTotalValue());
    }

    @Test
    public void shouldCalcTheRightTotalValue() {
        BigDecimal expectedToTalValue = new BigDecimal(2478.33);
        expectedToTalValue = expectedToTalValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedToTalValue, itub3Order.getTotalValue());
    }

    @Test
    public void shouldCalcTheRightTotalValue2() {
        BigDecimal expectedToTalValue = new BigDecimal(2710.00);
        expectedToTalValue = expectedToTalValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedToTalValue, tiet11Order.getTotalValue());
    }

    @Test
    public void shouldCalcTheRightTotalValue3() {
        BigDecimal expectedToTalValue = new BigDecimal(0.00);
        expectedToTalValue = expectedToTalValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedToTalValue, itub4Order.getTotalValue());
    }

    @Test
    public void shouldCalcTheRightTotalValue4() {
        BigDecimal expectedToTalValue = new BigDecimal(0.00);
        expectedToTalValue = expectedToTalValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedToTalValue, egie3Order.getTotalValue());
    }
}
