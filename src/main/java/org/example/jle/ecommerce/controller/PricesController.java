package org.example.jle.ecommerce.controller;

import org.example.jle.ecommerce.api.PricesApi;
import org.example.jle.ecommerce.model.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PricesController implements PricesApi{

    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer productId, OffsetDateTime applicationDate, Integer priceList) {
        return ResponseEntity.ok(new PriceResponse());
    }
}
