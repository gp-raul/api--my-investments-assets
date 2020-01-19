package com.gpraul.apimyinvestmentsassetscore.application.in.impl;

import com.gpraul.apimyinvestmentsassetscore.application.in.ExecutePurchaseOrder;
import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.PurchaseOrder;

public class ExecutePurchaseOrderImpl implements ExecutePurchaseOrder {


    @Override
    public Asset execute(final PurchaseOrder purchaseOrder) {
        purchaseOrder.addOrderAmountToAsset();
        purchaseOrder.addOrderTotalValueToAsset();

        return purchaseOrder.getAsset();
    }
}
