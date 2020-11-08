package com.Ally.AuctionApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Auctionitem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private BigDecimal currentBid;
    private BigDecimal reservePrice;
    @OneToOne(mappedBy = "auctionitem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Item item;
    @JsonBackReference
    @OneToMany(mappedBy = "auctionitem")
    Set<Bid> bids;
    private boolean isReservePriceMet;
}
