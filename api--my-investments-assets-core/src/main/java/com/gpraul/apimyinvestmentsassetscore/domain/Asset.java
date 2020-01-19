package com.gpraul.apimyinvestmentsassetscore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Builder
@ToString
public class Asset {

    private Optional<Integer> id;

    private final String ticker;

    @Setter
    private int amount;

    @Setter
    private BigDecimal totalValue = new BigDecimal(0);

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime editedAt = LocalDateTime.now();

    public BigDecimal getTotalValue() {
        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }
}
