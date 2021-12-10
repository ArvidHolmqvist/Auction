package se.ah.auctionservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.ah.auctionservice.JPAEntities.ImageItem;
import se.ah.auctionservice.JPAServices.ImageItemService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
    ImageItemService service;

    @Autowired
    public ImageController(ImageItemService service) {
        this.service = service;
    }

    @PostMapping("/uploadImage/{auctionID}")
    public ResponseEntity<ImageItem> uploadImage(@RequestBody MultipartFile image, @PathVariable long auctionID) throws IOException {
        ImageItem imageItem = new ImageItem(auctionID, image.getInputStream().readAllBytes());
        service.addImageItem(imageItem);
        return new ResponseEntity<>(imageItem, HttpStatus.OK);
    }
}
