package com.springboot.brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.brewery.services.CustomerService;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.springboot.brewery.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validaCustomer;


    @BeforeEach
    void setUp() {
       validaCustomer = CustomerDto.builder().id(UUID.randomUUID())
            .customerName("BlackFriday")
            .build();
    }

    @Test
    void getCustomer() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(validaCustomer);

        mockMvc.perform(get("/api/v1/customer/" + validaCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validaCustomer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is("BlackFriday")));
    }

    @Test
    void handlePost() throws Exception {

        CustomerDto customerDto = validaCustomer;
        customerDto.setId(null);
        CustomerDto savedCustomer = CustomerDto.builder().id(UUID.randomUUID()).customerName("New Customer Name").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveNewCustomer(any())).willReturn(savedCustomer);

        mockMvc.perform(post("/api/v1/customer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerDtoJson))
            .andExpect(status().isCreated());

    }

    @Test
    void handleUpdate() throws Exception {

        CustomerDto customerDto = validaCustomer;
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(put("/api/v1/customer/" + validaCustomer.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerDtoJson))
            .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());

    }

}