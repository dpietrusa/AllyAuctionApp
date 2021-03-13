package com.David.AuctionApp.controller;

import com.David.AuctionApp.repository.AuctionitemRepository;
import com.David.AuctionApp.service.AuctionItemService;
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

import java.util.Optional;

import static com.David.AuctionApp.util.BuildObjects.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuctionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    @Spy
    AuctionController auctionController;
    @Mock
    AuctionItemService auctionItemService;
    @Mock
    AuctionitemRepository auctionitemRepository;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(auctionController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void createAuctionItemShouldReturnAuctionItemIdAnd200Status() throws Exception {
        String request = mapper.writeValueAsString(buildSubmitAuctionItemRequest());
        String response = mapper.writeValueAsString(buildCreateAuctionItemResponse());
        Mockito.when(auctionItemService.saveAuctionItem(Mockito.any())).thenReturn(buildCreateAuctionItemResponse());
        mockMvc.perform(post("/auctionItems")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }

    @Test
    public void getAllAuctionItemsShouldReturnlistOfAuctionitemsAnd200Status() throws Exception {
        String response = mapper.writeValueAsString(buildListOfAuctionItems());
        Mockito.when(auctionitemRepository.findAll()).thenReturn(buildListOfAuctionItems());
        mockMvc.perform(get("/auctionItems"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }

    @Test
    public void getAuctionItem() throws Exception {
        String response = mapper.writeValueAsString(buildAuctionItem());
        Mockito.when(auctionitemRepository.findById(1l)).thenReturn(Optional.of(buildAuctionItem()));
        mockMvc.perform(get("/auctionItems/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }
}
