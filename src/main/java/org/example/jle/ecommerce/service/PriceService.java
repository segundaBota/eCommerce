package org.example.jle.ecommerce.service;

import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponseDTO getProductPrice(Integer productId, LocalDateTime applicationDate, Integer priceList);
}
