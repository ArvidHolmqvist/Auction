package se.ah.auctionservice.JPAServices;

import org.springframework.stereotype.Service;
import se.ah.auctionservice.JPAEntities.Bidder;
import se.ah.auctionservice.Repositories.BidderRepository;

import java.util.List;

@Service
public class BidderServiceImpl implements BidderService {
    BidderRepository repository;

    public BidderServiceImpl(BidderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addBid(Bidder bidder) {
        repository.save(bidder);
    }

    @Override
    public List<Bidder> getBiddersByAuctionID(long id) {
        return repository.findByAuctionID(id);
    }

    @Override
    public double findMaxBidByAuctionID(long id) {
        return repository.findMaxBidByAuctionID(id);
    }
}
