package se.ah.auctionservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ah.auctionservice.JPAEntities.ImageItem;

public interface ImageItemRepository extends JpaRepository<ImageItem, Long> {

}
