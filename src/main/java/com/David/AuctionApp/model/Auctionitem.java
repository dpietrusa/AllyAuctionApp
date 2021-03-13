package com.David.AuctionApp.model;

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
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne(mappedBy = "auctionitem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Item item;
    @OneToMany(mappedBy = "auctionitem")
    Set<Bid> bids;
    private boolean isReservePriceMet;
}
