package com.gpraul.apimyinvestmentsassetscore.domain;

import com.gpraul.apimyinvestmentsassetscore.domain.exception.AmountCannotBeLessThanOneException;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PurchaseOrder {

    private final Asset asset;

    private final Order order;

    public PurchaseOrder(final Asset asset, final Order order) {
        this.asset = asset;
        this.order = order;
    }

    public void addOrderAmountToAsset() {
        if(order.getAmount() < 1) {
            throw new AmountCannotBeLessThanOneException();
        }

        final int newAmount = asset.getAmount() + order.getAmount();
        asset.setAmount(newAmount);
    }

    public void addOrderTotalValueToAsset() {
        final BigDecimal totalValueFromAsset = asset.getTotalValue();
        asset.setTotalValue(totalValueFromAsset.add(order.getTotalValue()));
    }
}
