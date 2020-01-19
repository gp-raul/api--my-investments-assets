package com.gpraul.apimyinvestmentsassetscore.application.in.impl;

import com.gpraul.apimyinvestmentsassetscore.application.in.ExecutePurchaseOrder;
import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.Order;
import com.gpraul.apimyinvestmentsassetscore.domain.OrderType;
import com.gpraul.apimyinvestmentsassetscore.application.in.ExecuteOrder;
import com.gpraul.apimyinvestmentsassetscore.application.out.FindAssetByTicker;
import com.gpraul.apimyinvestmentsassetscore.application.out.SaveAsset;
import com.gpraul.apimyinvestmentsassetscore.domain.PurchaseOrder;

import java.math.BigDecimal;
import java.util.Optional;

public class ExecuteOrderImpl implements ExecuteOrder {

    private final FindAssetByTicker findAssetByTicker;

    private final SaveAsset saveAsset;

    private final ExecutePurchaseOrder executePurchaseOrder;

    public ExecuteOrderImpl(final FindAssetByTicker findAssetByTicker, final SaveAsset saveAsset, ExecutePurchaseOrder executePurchaseOrder) {
        this.findAssetByTicker = findAssetByTicker;
        this.saveAsset = saveAsset;
        this.executePurchaseOrder = executePurchaseOrder;
    }

    @Override
    public Asset execute(final Order order) {
        Optional<Asset> foundAsset = findAssetByTicker.findByTicker(order.getTicker());

        if(OrderType.PURCHASE.equals(order.getOrderType())) {
            final Asset asset = foundAsset.orElse(Asset.builder().ticker(order.getTicker()).build());

            final PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .asset(asset)
                    .order(order)
                    .build();

            foundAsset = Optional.of(executePurchaseOrder.execute(purchaseOrder));
        } else {
            // Ativo não disponivel na carteira
            foundAsset.orElseThrow(() -> new RuntimeException());

            Asset asset = foundAsset.get();
            executeSaleOrder(order, foundAsset, asset);
        }

        return foundAsset.get();
    }

    private void executeSaleOrder(Order order, Optional<Asset> foundAsset, Asset asset) {
        if(order.getAmount() > foundAsset.get().getAmount() ) {
            // Quantidade não disponivel na carteira
            throw new RuntimeException();
        } else if(order.getAmount() == foundAsset.get().getAmount()) {
            asset.setAmount(0);
            asset.setTotalValue(new BigDecimal(0));
        } else {
            final int newAmount = asset.getAmount() - order.getAmount();
            asset.setAmount(newAmount);

            final BigDecimal newValue = asset.getTotalValue().subtract(order.getValue()).abs();
            asset.setTotalValue(newValue);
        }
    }
}
