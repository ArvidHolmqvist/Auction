package se.ah.auctionservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ah.auctionservice.JPAEntities.AuctionItem;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {

}
