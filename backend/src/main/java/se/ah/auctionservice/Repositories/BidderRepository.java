package se.ah.auctionservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.ah.auctionservice.JPAEntities.Bidder;

import java.util.List;

public interface BidderRepository extends JpaRepository<Bidder, Long> {
    List<Bidder> findByAuctionID(long id);
    @Query(value = "select max(bid) from Bidders where auctionid = ?1", nativeQuery = true)
    double findMaxBidByAuctionID(long id);
}
