package com.David.AuctionApp.service;

import com.David.AuctionApp.model.Auctionitem;
import com.David.AuctionApp.model.Item;
import com.David.AuctionApp.model.request.SubmitAuctionItemRequest;
import com.David.AuctionApp.model.response.CreateAuctionItemResponse;
import com.David.AuctionApp.repository.AuctionitemRepository;
import com.David.AuctionApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuctionItemService {
    @Autowired
    AuctionitemRepository auctionitemRepository;
    @Autowired
    ItemRepository itemRepository;

    public CreateAuctionItemResponse saveAuctionItem(SubmitAuctionItemRequest submitAuctionItemRequest) {

        Auctionitem auctionItemToSave = Auctionitem.builder()
                .currentBid(new BigDecimal("0"))
                .reservePrice(submitAuctionItemRequest.getReservePrice())
                .isReservePriceMet(false)
                .build();
        Auctionitem savedAuctionItem = auctionitemRepository.save(auctionItemToSave);
        Item itemToSave = Item.builder()
                .auctionitem(savedAuctionItem)
                .itemId(submitAuctionItemRequest.getItem().getItemId())
                .description(submitAuctionItemRequest.getItem().getDescription())
                .build();
        Item savedItem = itemRepository.save(itemToSave);
        return CreateAuctionItemResponse.builder()
                .auctionItemId(String.valueOf(savedAuctionItem.getId()))
                .build();
    }
}
