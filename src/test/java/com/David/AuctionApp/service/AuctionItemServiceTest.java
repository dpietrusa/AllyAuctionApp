package com.David.AuctionApp.service;

import com.David.AuctionApp.model.response.CreateAuctionItemResponse;
import com.David.AuctionApp.repository.AuctionitemRepository;
import com.David.AuctionApp.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static com.David.AuctionApp.util.BuildObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuctionItemServiceTest {
    @InjectMocks
    @Spy
    AuctionItemService auctionItemService;
    @Mock
    AuctionitemRepository auctionitemRepository;
    @Mock
    ItemRepository itemRepository;

    @Test
    public void saveAuctionItemShouldReturnCreateAuctionItemResponse() {
        Mockito.when(itemRepository.save(Mockito.any())).thenReturn(buildItem());
        Mockito.when(auctionitemRepository.save(Mockito.any())).thenReturn(buildAuctionItem());
        CreateAuctionItemResponse response = auctionItemService.saveAuctionItem(buildSubmitAuctionItemRequest());
        assertEquals(CreateAuctionItemResponse.class, response.getClass());
        assertEquals("1", response.getAuctionItemId());
    }
}
