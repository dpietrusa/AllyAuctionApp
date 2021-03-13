package com.Ally.AuctionApp.service;

import com.Ally.AuctionApp.exception.InvalidBidAmountException;
import com.Ally.AuctionApp.exception.OutbidException;
import com.Ally.AuctionApp.exception.ReserveNotMetException;
import com.Ally.AuctionApp.model.Auctionitem;
import com.Ally.AuctionApp.model.Bid;
import com.Ally.AuctionApp.model.User;
import com.Ally.AuctionApp.model.request.SubmitBidRequest;
import com.Ally.AuctionApp.repository.AuctionitemRepository;
import com.Ally.AuctionApp.repository.BidRepository;
import com.Ally.AuctionApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BidService {
    @Autowired
    AuctionitemRepository auctionitemRepository;
    @Autowired
    BidRepository bidRepository;
    @Autowired
    UserRepository userRepository;

    public String submitBid(SubmitBidRequest currentBidRequest) throws ReserveNotMetException, OutbidException, InvalidBidAmountException {
        Auctionitem auctionItem = auctionitemRepository.getOne(currentBidRequest.getAuctionItemId());
        User user = checkIfUserExistsAndAddIfNotFound(currentBidRequest.getBidderName());
        validateBidAmountIsGreaterThanCurrentBid(auctionItem, currentBidRequest.getMaxAutoBidAmount());
        if (!auctionItem.isReservePriceMet()) {
            if (currentBidRequest.getMaxAutoBidAmount().compareTo(auctionItem.getReservePrice()) == -1) {
                auctionItem.setCurrentBid(currentBidRequest.getMaxAutoBidAmount());
                auctionItem.setUser(user);
                saveBid(currentBidRequest, auctionItem, user);
                throw new ReserveNotMetException(auctionItem.getReservePrice());
            } else {
                auctionItem.setReservePriceMet(true);
                auctionItem.setCurrentBid(auctionItem.getReservePrice());
                auctionItem.setUser(user);
                saveBid(currentBidRequest, auctionItem, user);
                return "You are the highest bidder. Current bid: $" + auctionItem.getCurrentBid();
            }
        } else {
            BigDecimal highestBid = bidRepository.findMaxAutoBidForAuctionItem(auctionItem.getId());
            if (currentBidRequest.getMaxAutoBidAmount().compareTo(highestBid) == 1) {
                auctionItem.setCurrentBid(highestBid.add(new BigDecimal("1")));
                auctionItem.setUser(user);

                saveBid(currentBidRequest, auctionItem, user);
                return "You are the highest bidder. Current bid: $" + auctionItem.getCurrentBid();
            } else {
                auctionItem.setCurrentBid(currentBidRequest.getMaxAutoBidAmount().add(new BigDecimal("1")));
                saveBid(currentBidRequest, auctionItem, user);
                throw new OutbidException(auctionItem.getCurrentBid());
            }
        }
    }

    private void saveBid(SubmitBidRequest bidRequest, Auctionitem auctionItem, User user) {
        bidRepository.save(Bid.builder()
                .auctionitem(auctionItem)
                .user(user)
                .maxAutoBidAmount(bidRequest.getMaxAutoBidAmount())
                .build());
    }

    private void validateBidAmountIsGreaterThanCurrentBid(Auctionitem auctionItem, BigDecimal bidAmount) throws InvalidBidAmountException {
        if (bidAmount.compareTo(auctionItem.getCurrentBid()) == -1 || bidAmount.compareTo(auctionItem.getCurrentBid()) == 0) {
            throw new InvalidBidAmountException(auctionItem.getCurrentBid());
        }
    }

    private User checkIfUserExistsAndAddIfNotFound(String bidderName) {
        User foundUser = userRepository.findUserByName(bidderName);
        if (foundUser != null) {
            return foundUser;
        } else {
            return userRepository.save(User.builder()
                    .name(bidderName)
                    .build());
        }
    }
}
