package com.David.AuctionApp.repository;

import com.David.AuctionApp.model.Auctionitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionitemRepository extends JpaRepository<Auctionitem, Long> {
}
