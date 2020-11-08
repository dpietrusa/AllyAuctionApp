package com.Ally.AuctionApp.model.request;

import com.Ally.AuctionApp.model.Item;
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
