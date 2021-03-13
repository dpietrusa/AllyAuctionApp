package com.Ally.AuctionApp.model.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitBidRequest {
    private long auctionItemId;
    private BigDecimal maxAutoBidAmount;
    private String bidderName;
}
