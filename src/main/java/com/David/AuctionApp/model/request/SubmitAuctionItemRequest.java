package com.David.AuctionApp.model.request;

import com.David.AuctionApp.model.Item;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitAuctionItemRequest {
    private BigDecimal reservePrice;
    private Item item;
}
