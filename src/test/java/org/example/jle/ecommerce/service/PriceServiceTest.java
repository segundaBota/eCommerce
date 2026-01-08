package org.example.jle.ecommerce.service;

import org.example.jle.ecommerce.application.exception.PriceNotFoundException;
import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.example.jle.ecommerce.ext.domain.entity.converter.PriceEntityToDtoConverter;
import org.example.jle.ecommerce.ext.repository.PriceRepository;
import org.example.jle.ecommerce.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private static final Long ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Integer PRICE_LIST = 1;
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = LocalDateTime.now();
    private static final Integer PRIORITY = 1;
    private static final Double PRICE = 10.0;
    private static final String CURRENCY = "EUR";

    @Mock
    PriceRepository repository;

    @Spy
    PriceEntityToDtoConverter converter;

    @InjectMocks
    PriceServiceImpl service;

    @Test
    void whenGetProductPriceOk_thenReturnPrice() {
        when(repository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.of(buildMockPrice()));

        var result = service.getProductPrice(LocalDateTime.now(), 35455, BRAND_ID);

        assertEquals(PRICE, result.getPrice());
    }

    @Test
    void whenGetProductPriceKo_thenThrowException() {
        when(repository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, ()->
                service.getProductPrice(LocalDateTime.now(), 35455, BRAND_ID));
    }

    private PriceEntity buildMockPrice() {
        return PriceEntity.builder()
                .id(ID)
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .priceList(PRICE_LIST)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .priority(PRIORITY)
                .price(PRICE)
                .currency(CURRENCY)
                .build();
    }
}
