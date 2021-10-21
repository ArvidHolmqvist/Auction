package se.ah.auctionservice.JPAServices;

import se.ah.auctionservice.JPAEntities.Bidder;
import se.ah.auctionservice.Repositories.BidderRepository;

public class BidderServiceImpl implements BidderService {
    BidderRepository repository;

    public BidderServiceImpl(BidderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addBid(Bidder bidder) {
        repository.save(bidder);
    }
}
