package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bidders")
public class Bidder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bidderID")
    @Getter @Setter
    private long bidderID;

    @Column(name="auctionID")
    @Getter @Setter
    private long auctionID;

    @Column
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private double bid;

    @Column
    @Getter @Setter
    private long date;

    public Bidder(long auctionID, String name, double bid, long date) {
        this.auctionID = auctionID;
        this.name = name;
        this.bid = bid;
        this.date = date;
    }

    public Bidder() {

    }
}
