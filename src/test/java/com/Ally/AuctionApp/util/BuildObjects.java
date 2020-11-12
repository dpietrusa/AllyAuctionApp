package com.Ally.AuctionApp.util;

import com.Ally.AuctionApp.model.Auctionitem;
import com.Ally.AuctionApp.model.Bid;
import com.Ally.AuctionApp.model.Item;
import com.Ally.AuctionApp.model.request.SubmitAuctionItemRequest;
import com.Ally.AuctionApp.model.request.SubmitBidRequest;
import com.Ally.AuctionApp.model.response.CreateAuctionItemResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildObjects {
    public static SubmitAuctionItemRequest buildSubmitAuctionItemRequest() {
        Item item = Item.builder()
                .id(1l)
                .itemId("DMC Delorean")
                .description("Excellent Condition. Newly replaced flux capacitor.")
                .build();
        return SubmitAuctionItemRequest.builder()
                .item(item)
                .reservePrice(new BigDecimal(45000.00))
                .build();
    }

    public static Auctionitem buildAuctionItem() {
        Item item = Item.builder()
                .id(1l)
                .itemId("DMC Delorean")
                .description("Excellent Condition. Newly replaced flux capacitor.")
                .build();
        Set<Bid> bids = new HashSet<>();
        return Auctionitem.builder()
                .id(1l)
                .isReservePriceMet(false)
                .currentBid(new BigDecimal(0))
                .item(item)
                .reservePrice(new BigDecimal(45000.00))
                .bids(bids)
                .build();
    }
    public static Item buildItem() {
     return Item.builder()
                .id(1l)
                .itemId("DMC Delorean")
                .description("Excellent Condition. Newly replaced flux capacitor.")
                .build();
    }

    public static SubmitBidRequest buildSubmitBidRequest() {
        return SubmitBidRequest.builder()
                .auctionItemId(1l)
                .bidderName("David")
                .maxAutoBidAmount(new BigDecimal(60000))
                .build();
    }

    public static Bid buildBid() {
        return Bid.builder()
                .bidderName("David")
                .auctionitem(buildAuctionItem())
                .maxAutoBidAmount(new BigDecimal(60000))
                .id(1l)
                .build();
    }

    public static CreateAuctionItemResponse buildCreateAuctionItemResponse() {
        return CreateAuctionItemResponse.builder()
                .auctionItemId("2")
                .build();
    }

    public static List<Auctionitem> buildListOfAuctionItems() {
        List<Auctionitem> auctionItemList = new ArrayList<>();
        Item item1 = Item.builder()
                .id(1l)
                .itemId("honda accord")
                .description("great condition")
                .build();
        Item item2 = Item.builder()
                .id(1l)
                .itemId("ford probe")
                .description("great condition")
                .build();
        Auctionitem auctionItem1 = Auctionitem.builder()
                .id(1l)
                .currentBid(new BigDecimal(1500))
                .reservePrice(new BigDecimal(1000))
                .isReservePriceMet(true)
                .item(item1)
                .build();
        Auctionitem auctionItem2 = Auctionitem.builder()
                .id(2l)
                .currentBid(new BigDecimal(1500))
                .reservePrice(new BigDecimal(1000))
                .isReservePriceMet(true)
                .item(item2)
                .build();
        auctionItemList.add(auctionItem1);
        auctionItemList.add(auctionItem2);
        return auctionItemList;
    }
}
