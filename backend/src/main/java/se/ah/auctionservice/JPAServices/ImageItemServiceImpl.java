package se.ah.auctionservice.JPAServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ah.auctionservice.JPAEntities.ImageItem;
import se.ah.auctionservice.Repositories.ImageItemRepository;

@Service
public class ImageItemServiceImpl implements ImageItemService {

    ImageItemRepository repository;

    public ImageItemServiceImpl(ImageItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addImageItem(ImageItem imageItem) {
        repository.save(imageItem);
    }
}
