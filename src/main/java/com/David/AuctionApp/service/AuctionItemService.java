package com.Ally.AuctionApp.service;

import com.Ally.AuctionApp.model.Auctionitem;
import com.Ally.AuctionApp.model.Item;
import com.Ally.AuctionApp.model.request.SubmitAuctionItemRequest;
import com.Ally.AuctionApp.model.response.CreateAuctionItemResponse;
import com.Ally.AuctionApp.repository.AuctionitemRepository;
import com.Ally.AuctionApp.repository.ItemRepository;
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
