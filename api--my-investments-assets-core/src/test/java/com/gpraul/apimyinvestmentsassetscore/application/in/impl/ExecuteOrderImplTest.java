package com.gpraul.apimyinvestmentsassetscore.application.in.impl;

import com.gpraul.apimyinvestmentsassetscore.application.in.ExecutePurchaseOrder;
import com.gpraul.apimyinvestmentsassetscore.application.out.FindAssetByTicker;
import com.gpraul.apimyinvestmentsassetscore.application.out.SaveAsset;
import com.gpraul.apimyinvestmentsassetscore.domain.Asset;
import com.gpraul.apimyinvestmentsassetscore.domain.Order;
import com.gpraul.apimyinvestmentsassetscore.domain.OrderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ExecuteOrderImplTest {

    @Mock
    private FindAssetByTicker findAssetByTicker;

    @Mock
    private SaveAsset saveAsset;

    @Spy
    private ExecutePurchaseOrder executePurchaseOrder;

    @InjectMocks
    private ExecuteOrderImpl executeOrder;

    private static Order purchaseOrder;

    private static Asset itsa4Asset;

    private static Asset itsa4AssetUpdated;

    @BeforeAll
    public static void setUp() {
        purchaseOrder = Order.builder()
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

        itsa4AssetUpdated = Asset.builder()
                .ticker("ITSA4")
                .amount(115)
                .totalValue(new BigDecimal(1564.5))
                .build();
    }

    @Test
    public void eee() {
        when(findAssetByTicker.findByTicker(any())).thenReturn(Optional.of(itsa4Asset));

        //doAnswer(itsa4AssetUpdated).when(executePurchaseOrder.execute(any(), any()));

        final Asset assetUpdated = executeOrder.execute(purchaseOrder);

        BigDecimal expectedValue = new BigDecimal(1564.50);
        expectedValue = expectedValue.setScale(2, RoundingMode.HALF_EVEN);

        Assertions.assertEquals(expectedValue, assetUpdated.getTotalValue());

    }
}
