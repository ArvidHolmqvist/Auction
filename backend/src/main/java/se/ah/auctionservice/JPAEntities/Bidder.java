package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bidder {
    @Id
    @Column
    @Getter @Setter
    private String name;

    @Id
    @Column
    @Getter @Setter
    private long auctionID;

    @Column
    @Getter @Setter
    private double bid;
}
