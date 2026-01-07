package org.example.jle.ecommerce.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PricesController.class)
public class PriceControllerTest {

    private static final String GET_ENDPOINT = "/prices";
    private static final String PARAM_DATE = "applicationDate";
    private static final String PARAM_PRODUCT_ID = "productId";
    private static final String PARAM_BRAND_ID = "brandId";

    @Autowired
    private MockMvc mockMvc;

/*    @Test
    void test1() throws Exception {
        performAndAssert("2020-06-14Y10:00:00", 35.50);
    }

    private void performAndAssert(String date, double expectedPrice) {
         mockMvc.perform(get(GET_ENDPOINT))
                .param(PARAM_DATE, date)
                .param(PARAM_PRODUCT_ID, "35455")
                .param(PARAM_BRAND_ID, "1"))
        .andExpect(status.isOk()));
      //  .andExpect(jsonPath("$.price").value(expectedPrice));
    }
*/

}
