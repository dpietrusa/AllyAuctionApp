package com.Ally.AuctionApp.service;

import com.Ally.AuctionApp.exception.InvalidBidAmountException;
import com.Ally.AuctionApp.exception.OutbidException;
import com.Ally.AuctionApp.exception.ReserveNotMetException;
import com.Ally.AuctionApp.model.Auctionitem;
import com.Ally.AuctionApp.model.Bid;
import com.Ally.AuctionApp.model.request.SubmitBidRequest;
import com.Ally.AuctionApp.repository.AuctionitemRepository;
import com.Ally.AuctionApp.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BidService {
    @Autowired
    AuctionitemRepository auctionitemRepository;

    @Autowired
    BidRepository bidRepository;

    public String submitBid(SubmitBidRequest bidRequest) throws ReserveNotMetException, OutbidException, InvalidBidAmountException {
        Auctionitem auctionItem = auctionitemRepository.getOne(bidRequest.getAuctionItemId());
        validateBidAmountIsGreaterThanCurrentBid(auctionItem, bidRequest.getMaxAutoBidAmount());
        if (!auctionItem.isReservePriceMet()) {
            if (bidRequest.getMaxAutoBidAmount().compareTo(auctionItem.getReservePrice()) == -1) {
                auctionItem.setCurrentBid(bidRequest.getMaxAutoBidAmount());
                saveBid(bidRequest, auctionItem);
                throw new ReserveNotMetException(auctionItem.getReservePrice());
            } else {
                auctionItem.setReservePriceMet(true);
                auctionItem.setCurrentBid(auctionItem.getReservePrice());
                saveBid(bidRequest, auctionItem);
                return "You are the highest bidder. Current bid: $" + auctionItem.getCurrentBid();
            }
        } else {
            BigDecimal highestBid = bidRepository.findMaxAutoBidForAuctionItem(auctionItem.getId());
            if (bidRequest.getMaxAutoBidAmount().compareTo(highestBid) == 1) {
                auctionItem.setCurrentBid(highestBid.add(new BigDecimal("1")));
                saveBid(bidRequest, auctionItem);
                return "You are the highest bidder. Current bid: $" + auctionItem.getCurrentBid();
            } else {
                auctionItem.setCurrentBid(bidRequest.getMaxAutoBidAmount().add(new BigDecimal("1")));
                saveBid(bidRequest, auctionItem);
                throw new OutbidException(auctionItem.getCurrentBid());
            }
        }
    }

    private void saveBid(SubmitBidRequest bidRequest, Auctionitem auctionItem) {
        bidRepository.save(Bid.builder()
                .auctionitem(auctionItem)
                .bidderName(bidRequest.getBidderName())
                .maxAutoBidAmount(bidRequest.getMaxAutoBidAmount())
                .build());
    }

    private void validateBidAmountIsGreaterThanCurrentBid(Auctionitem auctionItem, BigDecimal bidAmount) throws InvalidBidAmountException {
        if (bidAmount.compareTo(auctionItem.getCurrentBid()) == -1 || bidAmount.compareTo(auctionItem.getCurrentBid()) == 0) {
            throw new InvalidBidAmountException(auctionItem.getCurrentBid());
        }
    }
}
