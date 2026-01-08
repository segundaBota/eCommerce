package org.example.jle.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.jle.ecommerce.api.PricesApi;
import org.example.jle.ecommerce.controller.converter.RestPriceConverter;
import org.example.jle.ecommerce.model.PriceResponse;
import org.example.jle.ecommerce.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PricesController implements PricesApi{

    private final PriceService priceService;
    private final RestPriceConverter restPriceConverter;

    @Override
    public ResponseEntity<PriceResponse> getPrice(String applicationDate, Integer productId, Integer brandId) {
        return ResponseEntity.ok(
                restPriceConverter.asPriceResponse(
                        priceService.getProductPrice(LocalDateTime.parse(applicationDate), productId, brandId)));
    }
}
