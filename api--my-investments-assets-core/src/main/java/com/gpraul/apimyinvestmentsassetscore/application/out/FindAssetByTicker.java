package com.gpraul.apimyinvestmentsassetscore.application.out;

import com.gpraul.apimyinvestmentsassetscore.domain.Asset;

import java.util.Optional;

public interface FindAssetByTicker {

    Optional<Asset> findByTicker(String ticker);
}
