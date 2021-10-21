package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BidderID implements Serializable {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private long auction_id;

}
