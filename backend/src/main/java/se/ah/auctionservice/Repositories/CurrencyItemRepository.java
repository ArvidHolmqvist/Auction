package se.ah.auctionservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ah.auctionservice.JPAEntities.CurrencyItem;

public interface CurrencyItemRepository extends JpaRepository<CurrencyItem, Long> {

}
