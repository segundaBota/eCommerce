package org.example.jle.ecommerce.service;

import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;

import java.time.OffsetDateTime;

public interface PriceService {
    PriceResponseDTO getProductPrice(Integer productId, OffsetDateTime applicationDate, Integer priceList);
}
