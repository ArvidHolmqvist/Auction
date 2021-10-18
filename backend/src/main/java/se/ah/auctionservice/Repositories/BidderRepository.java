package se.ah.auctionservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ah.auctionservice.JPAEntities.Bidder;

public interface BidderRepository extends JpaRepository<Bidder, Long> {

}
