package com.gpraul.apimyinvestmentsassetscore.application.in;

import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.Order;
import com.gpraul.apimyinvestmentsassetscore.domain.PurchaseOrder;

public interface ExecutePurchaseOrder {

    Asset execute(PurchaseOrder purchaseOrder);
}
