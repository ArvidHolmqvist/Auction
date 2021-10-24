package se.ah.auctionservice.JPAServices;

import se.ah.auctionservice.JPAEntities.Bidder;

import java.util.List;

public interface BidderService {
    void addBid(Bidder bidder);
    List<Bidder> getBiddersByAuctionID(long id);
    double findMaxBidByAuctionID(long id);
}
