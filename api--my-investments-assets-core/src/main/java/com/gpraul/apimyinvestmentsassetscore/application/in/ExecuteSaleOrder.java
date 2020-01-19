package com.gpraul.apimyinvestmentsassetscore.application.in;

import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.Order;

public interface ExecuteSaleOrder {

    Asset execute(Order order);
}
