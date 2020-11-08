package com.Ally.AuctionApp.exception;

import java.math.BigDecimal;

public class InvalidBidAmountException extends Exception {
    public InvalidBidAmountException(BigDecimal currentBid) {
        super("Bid amount must be greater than current bid of: $" + currentBid);
    }
}
