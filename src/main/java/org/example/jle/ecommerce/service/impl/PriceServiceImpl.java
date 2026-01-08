package org.example.jle.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jle.ecommerce.application.exception.PriceNotFoundException;
import org.example.jle.ecommerce.application.exception.utils.ErrorCode;
import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.example.jle.ecommerce.ext.repository.PriceRepository;
import org.example.jle.ecommerce.service.PriceService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponseDTO getProductPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        Optional<PriceEntity> entity =
                priceRepository.findApplicablePrice(applicationDate, productId, brandId);
        if (entity.isPresent()) {
            PriceEntity priceEntity = entity.get();
            return PriceResponseDTO.builder()
                    .brandId(priceEntity.getBrandId())
                    .productId(priceEntity.getProductId())
                    .priceList(priceEntity.getPriceList())
                    .startDate(priceEntity.getStartDate())
                    .endDate(priceEntity.getEndDate())
                    .priority(priceEntity.getPriority())
                    .price(priceEntity.getPrice())
                    .currency(priceEntity.getCurrency())
                    .build();
        } else{
            log.error("No price found with given productId {} and brandId {} for the date {}",
                    productId, brandId, applicationDate);
            throw new PriceNotFoundException(ErrorCode.PRICE_NOT_FOUND.getDescription());
        }
    }

}