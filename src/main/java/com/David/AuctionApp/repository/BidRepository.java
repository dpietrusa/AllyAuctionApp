package com.David.AuctionApp.repository;

import com.David.AuctionApp.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query(value = "select MAX(bid.max_auto_bid_amount) FROM bid WHERE auction_item_id = :auctionItemId", nativeQuery = true)
    BigDecimal findMaxAutoBidForAuctionItem(@Param("auctionItemId") long auctionItemId);

}
