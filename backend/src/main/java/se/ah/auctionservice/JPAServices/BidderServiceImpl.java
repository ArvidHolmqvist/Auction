package se.ah.auctionservice.JPAServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ah.auctionservice.JPAEntities.Bidder;
import se.ah.auctionservice.Repositories.BidderRepository;

import java.util.List;

@Service
public class BidderServiceImpl implements BidderService {
    BidderRepository repository;

    AuctionItemService auctionItemService;

    public BidderServiceImpl(BidderRepository repository, AuctionItemService auctionItemService) {
        this.repository = repository;
        this.auctionItemService = auctionItemService;

    }

    @Override
    public void addBid(Bidder bidder) {
        var highestBid = findMaxBidByAuctionID(bidder.getAuctionID());
        if (highestBid == null && bidder.getBid() > auctionItemService.getAuctionItemById(bidder.getAuctionID()).getStartPrice() || highestBid != null && bidder.getBid() > highestBid){
            repository.save(bidder);
        }
    }

    @Override
    public List<Bidder> getBiddersByAuctionID(long id) {
        return repository.findByAuctionID(id);
    }

    @Override
    public Double findMaxBidByAuctionID(long id) {
        var maxBid = repository.findMaxBidByAuctionID(id);
        if (maxBid == null){
            System.out.println("findMaxBidByAuctionID");
            return auctionItemService.getAuctionItemById(id).getStartPrice();
        }
        return maxBid;
    }
}
