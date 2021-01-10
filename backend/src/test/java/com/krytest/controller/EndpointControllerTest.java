package com.krytest.controller;

import com.krytest.AbstractTest;
import com.krytest.entity.Endpoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndpointControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createEndpointTest() throws Exception {
        String uri = "/endpoints";
        Endpoint endpoint = new Endpoint("https://www.kry.se/");

        String inputJson = super.mapToJson(endpoint);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    // TODO Implement tests for all controller methods
}
