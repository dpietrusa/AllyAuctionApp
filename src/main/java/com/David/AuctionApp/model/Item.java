package com.David.AuctionApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"auctionitem"})
public class Item {
    @Id
    private long id;
    private String itemId;
    private String description;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "auction_item_id", referencedColumnName = "auctionItemId")
    private Auctionitem auctionitem;
}
