package com.gpraul.apimyinvestmentsassetscore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Builder
@ToString
public class Order {

    private Optional<Integer> id;

    private final OrderType orderType;

    private final String ticker;

    private final int amount;

    private final BigDecimal value;

    @Builder.Default
    private LocalDate executionDate = LocalDate.now();

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime editedAt = LocalDateTime.now();

    public BigDecimal getTotalValue() {
        final BigDecimal amountConverted = new BigDecimal(amount);
        final BigDecimal totalValue = value.multiply(amountConverted);

        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }
}
