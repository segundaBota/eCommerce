package org.example.jle.ecommerce.controller;

import org.example.jle.ecommerce.application.exception.PriceNotFoundException;
import org.example.jle.ecommerce.application.exception.RestExceptionHandler;
import org.example.jle.ecommerce.application.exception.utils.ErrorCode;
import org.example.jle.ecommerce.controller.converter.RestPriceConverter;
import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PricesController.class)
@Import(RestExceptionHandler.class)
@AutoConfigureMockMvc
public class PriceControllerTest {

    private static final Integer PRODUCT_ID = 123;
    private static final Integer BRAND_ID = 15;
    private static final Integer PRICE_LIST = 1;
    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final Integer PRIORITY = 1;
    private static final Double PRICE = 150.0;
    private static final String CURRENCY = "EUR";
    private static final String GET_ENDPOINT = "/api/ecommerce/prices";
    private static final String PARAM_DATE = "applicationDate";
    private static final String PARAM_PRODUCT_ID = "productId";
    private static final String PARAM_BRAND_ID = "brandId";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceService priceService;

    @MockitoBean
    private RestPriceConverter restPriceConverter;

    @Test
    void whenGetPriceFound_thenReturnPrice() throws Exception {
        when(priceService.getProductPrice(any(), any(), any()))
                .thenReturn(buildPriceResponse());

        mockMvc.perform(get(GET_ENDPOINT)
                .param(PARAM_DATE, DATE.toString())
                .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                .param(PARAM_BRAND_ID, BRAND_ID.toString()))
        .andExpect(status().isOk());
    }

    @Test
    void whenRequestIsWrong_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, DATE.toString())
                        .param("wrongProduct", PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenParameterIsWrong_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, "wrongDate")
                        .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGetPriceNotFound_thenReturnException() throws Exception {
        when(priceService.getProductPrice(any(), any(), any()))
                .thenThrow(new PriceNotFoundException(ErrorCode.UNDEFINED_ERROR.getDescription()));

        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, DATE.toString())
                        .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenInternalError_thenReturnException() throws Exception {
        when(priceService.getProductPrice(any(), any(), any()))
                .thenThrow(new InternalError(ErrorCode.UNDEFINED_ERROR.getDescription()));

        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, DATE.toString())
                        .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString()))
                .andExpect(status().is5xxServerError());
    }

    private PriceResponseDTO buildPriceResponse() {
        return PriceResponseDTO.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .priceList(PRICE_LIST)
                .startDate(DATE)
                .endDate(DATE)
                .priority(PRIORITY)
                .price(PRICE)
                .currency(CURRENCY)
                .build();
    }
}
