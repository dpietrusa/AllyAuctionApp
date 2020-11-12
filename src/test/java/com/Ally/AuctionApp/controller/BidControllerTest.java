package com.Ally.AuctionApp.controller;


import com.Ally.AuctionApp.service.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.Ally.AuctionApp.util.BuildObjects.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BidControllerTest {
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    @Spy
    BidController bidController;
    @Mock
    BidService bidService;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void submitBidShouldReturnStringFromSubmitBidService() throws Exception {
        String request = mapper.writeValueAsString(buildSubmitBidRequest());
        Mockito.when(bidService.submitBid(Mockito.any())).thenReturn("response from bid service");
        mockMvc.perform(post("/bid")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("response from bid service"));
    }
}
