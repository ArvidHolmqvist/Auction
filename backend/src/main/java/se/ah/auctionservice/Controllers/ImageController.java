package se.ah.auctionservice.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import se.ah.auctionservice.JPAEntities.ImageItem;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    @PostMapping("/uploadImage")
    public ResponseEntity<ImageItem> updloadImage(@RequestBody MultipartFile image){
        return null;
    }
}
