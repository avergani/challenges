package com.afvergani.ecommerce.controller;

import com.afvergani.ecommerce.service.IProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityControllerTest {

    @Autowired
    AvailabilityController controller;

    @Autowired
    IProductsService productsService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testProductsAvailability() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/productsAvailability")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);

        String responseContent = mvcResult.getResponse().getContentAsString();

        Assertions.assertFalse(responseContent.isEmpty());
        Assertions.assertEquals("5,1,3", responseContent);
    }

    @Test
    public void testGetProductsAvailabilityTrue() {
        controller = new AvailabilityController(productsService);
        String expectedOutput = "5,1,3";
        String actualOutput = controller.getProductsAvailability();
        assertEquals(expectedOutput, actualOutput);
    }

}

