package org.example.jle.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.example.jle.ecommerce.ext.domain.entity.converter.PriceEntityToDtoConverter;
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

    private static final String DATA_NOT_FOUND = "Data not found";

    private PriceRepository priceRepository;
    private PriceEntityToDtoConverter converter;

    @Override
    public PriceResponseDTO getProductPrice(Integer productId, LocalDateTime applicationDate, Integer priceList) {
        Optional<PriceEntity> entity =
                priceRepository.findApplicablePrice(productId, applicationDate, priceList);
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
            log.error("No price found with given productId {} and priceList {} for the date {}",
                    productId, priceList, applicationDate);
            throw new ServiceException(DATA_NOT_FOUND);
        }
    }

}