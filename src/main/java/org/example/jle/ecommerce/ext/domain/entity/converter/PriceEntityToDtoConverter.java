package org.example.jle.ecommerce.ext.domain.entity.converter;

import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;

public class PriceEntityToDtoConverter {

    public PriceResponseDTO convert (PriceEntity entity){
        return PriceResponseDTO.builder()
                .brandId(entity.getBrandId())
                .productId(entity.getProductId())
                .priceList(entity.getPriceList())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}
