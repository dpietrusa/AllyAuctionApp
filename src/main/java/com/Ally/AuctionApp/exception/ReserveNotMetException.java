package com.Ally.AuctionApp.exception;

import java.math.BigDecimal;

public class ReserveNotMetException extends Exception {
    public ReserveNotMetException(BigDecimal reservePrice) {
        super("Bid did not meet reserve price: $" + reservePrice);
    }
}
