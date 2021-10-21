package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Bidder implements Serializable {
    @EmbeddedId
    @Column
    @Getter @Setter
    private BidderID bidderID;

    @Column
    @Getter @Setter
    private double bid;

    @Column
    @Getter @Setter
    private long date;
}
