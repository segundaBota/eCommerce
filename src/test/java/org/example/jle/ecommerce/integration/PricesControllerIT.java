package org.example.jle.ecommerce.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerIT {

    private static final String GET_ENDPOINT = "/prices";
    private static final String PARAM_DATE = "applicationDate";
    private static final String PARAM_PRODUCT_ID = "productId";
    private static final String PARAM_BRAND_ID = "brandId";
    private static final Integer PRODUCT_ID = 35455;
    private static final Integer BRAND_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPrice_WhenRequestIsCorrect() throws Exception {

        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, "2020-06-14T10:00:00")
                        .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID)))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void shouldReturn404_WhenPriceNotFound() throws Exception {
        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, "2029-01-01T00:00:00")
                        .param(PARAM_PRODUCT_ID, "99999")
                        .param(PARAM_BRAND_ID, "1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void proposedTest1() throws Exception {
        performAndAssert("2020-06-14T10:00:00", 35.50);
    }

    @Test
    void proposedTest2() throws Exception {
        performAndAssert("2020-06-14T16:00:00", 25.45);
    }

    @Test
    void proposedTest3() throws Exception {
        performAndAssert("2020-06-14T21:00:00", 35.50);
    }

    @Test
    void proposedTest4() throws Exception {
        performAndAssert("2020-06-15T10:00:00", 30.50);
    }

    @Test
    void proposedTest5() throws Exception {
        performAndAssert("2020-06-16T21:00:00", 38.95);
    }

    private void performAndAssert(String date, double expectedPrice) throws Exception {
        mockMvc.perform(get(GET_ENDPOINT)
                        .param(PARAM_DATE, date)
                        .param(PARAM_PRODUCT_ID, PRODUCT_ID.toString())
                        .param(PARAM_BRAND_ID, BRAND_ID.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

}
