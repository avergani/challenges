package com.afvergani.getprice.controller;

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
public class PriceServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testGetPriceOK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getPrice")
                        .queryParam("productId", "35456")
                        .queryParam("brandId", "1")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);

        String responseContent = mvcResult.getResponse().getContentAsString();
        Assertions.assertFalse(responseContent.isEmpty());
    }

    @Test
    public void testGetPriceBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getPrice")
                        .queryParam("productId", "35456")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(400, statusCode);

        String responseContent = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(responseContent.isEmpty());
    }
}
