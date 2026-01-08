package org.example.jle.ecommerce.service;

import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponseDTO getProductPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
