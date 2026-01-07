package org.example.jle.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.jle.ecommerce.api.PricesApi;
import org.example.jle.ecommerce.controller.converter.RestPriceConverter;
import org.example.jle.ecommerce.model.PriceResponse;
import org.example.jle.ecommerce.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PricesController implements PricesApi{

    private PriceService priceService;
    private RestPriceConverter restPriceConverter;

    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer productId, OffsetDateTime applicationDate, Integer priceList) {
        return ResponseEntity.ok(
                restPriceConverter.asPriceResponse(
                        priceService.getProductPrice(productId, applicationDate, priceList)));
    }
}
