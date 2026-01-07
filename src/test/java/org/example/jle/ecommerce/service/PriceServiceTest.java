package org.example.jle.ecommerce.service;

import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.example.jle.ecommerce.ext.repository.PriceRepository;
import org.example.jle.ecommerce.service.impl.PriceServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private static final Integer PPIORITY = 1;
    private static final Double PRICE = 10.0;
    private static final String CURRENCY = "EUR";

    @Mock
    PriceRepository repository;

    @InjectMocks
    PriceServiceImpl service;

    @Test
    void whenGetProductPriceOk_thenReturnPrice() {
        when(repository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.of(buildMockPrice()));

        var result = service.getProductPrice(35455, LocalDateTime.now(), PRICE_LIST);

        assertEquals(PRICE, result.getPrice());
    }

    @Test
    void whenGetProductPriceKo_thenThrowException() {
        when(repository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, ()-> {
            service.getProductPrice(35455, LocalDateTime.now(), PRICE_LIST);
        });
    }

    private PriceEntity buildMockPrice() {
        return PriceEntity.builder()
                .id(ID)
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .priceList(PRICE_LIST)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .priority(PPIORITY)
                .price(PRICE)
                .currency(CURRENCY)
                .build();
    }
}
