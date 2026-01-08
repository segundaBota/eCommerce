package org.example.jle.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.jle.ecommerce.api.PricesApi;
import org.example.jle.ecommerce.controller.converter.RestPriceConverter;
import org.example.jle.ecommerce.model.PriceResponse;
import org.example.jle.ecommerce.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PricesController implements PricesApi{

    private final PriceService priceService;
    private final RestPriceConverter restPriceConverter;

    @Override
    @Validated
    public ResponseEntity<PriceResponse> getPrice(
            @DateTimeFormat String applicationDate, Integer productId, Integer brandId) {
        return ResponseEntity.ok(
                restPriceConverter.asPriceResponse(
                        priceService.getProductPrice(LocalDateTime.parse(applicationDate), productId, brandId)));
    }
}
