package org.example.jle.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jle.ecommerce.application.exception.PriceNotFoundException;
import org.example.jle.ecommerce.application.exception.utils.ErrorCode;
import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.ext.domain.entity.converter.PriceEntityToDtoConverter;
import org.example.jle.ecommerce.ext.repository.PriceRepository;
import org.example.jle.ecommerce.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityToDtoConverter converter;

    @Override
    public PriceResponseDTO getProductPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId).stream()
            .map(converter::convert)
            .max(Comparator.comparingInt(PriceResponseDTO::getPriority))
            .orElseThrow(() -> {
                log.error("No price found with given productId {} and brandId {} for the date {}",
                        productId, brandId, applicationDate);
                return new PriceNotFoundException(ErrorCode.PRICE_NOT_FOUND.getDescription());
            });
    }

    private Optional<PriceResponseDTO> getPriceByPriority(List<PriceResponseDTO> priceResponseDTOList) {
        return priceResponseDTOList.stream()
                .max(Comparator.comparingInt(PriceResponseDTO::getPriority));
    }
}