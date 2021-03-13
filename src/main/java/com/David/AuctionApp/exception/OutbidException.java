package com.David.AuctionApp.exception;

import java.math.BigDecimal;

public class OutbidException extends Exception {
    public OutbidException(BigDecimal currentBid) {
        super("You have been outbid :( Outbid amount: $" + currentBid);
    }
}
