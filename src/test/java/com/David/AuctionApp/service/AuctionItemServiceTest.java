package com.Ally.AuctionApp.service;

import com.Ally.AuctionApp.model.response.CreateAuctionItemResponse;
import com.Ally.AuctionApp.repository.AuctionitemRepository;
import com.Ally.AuctionApp.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static com.Ally.AuctionApp.util.BuildObjects.*;
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