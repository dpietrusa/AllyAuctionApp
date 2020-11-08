package com.Ally.AuctionApp.repository;

import com.Ally.AuctionApp.model.Auctionitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionitemRepository extends JpaRepository<Auctionitem, Long> {
}
