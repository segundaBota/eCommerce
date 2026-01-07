package org.example.jle.ecommerce.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPrice_WhenRequestIsCorrect() throws Exception {

        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14-10.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void shouldReturn404_WhenPriceNotFound() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "99999")
                        .param("applicationDate", "2029-01-01T00.00.00")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound());
    }
}
