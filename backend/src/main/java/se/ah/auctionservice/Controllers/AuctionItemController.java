package se.ah.auctionservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.JPAServices.AuctionItemService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuctionItemController {
    AuctionItemService service;

    @Autowired
    public AuctionItemController(AuctionItemService service) {
        this.service = service;
    }

    @GetMapping("/auctionItems")
    public ResponseEntity<List<AuctionItem>> getAllActiveAuctionItems(){
        return new ResponseEntity<>(service.getAllAuctionItems(), HttpStatus.OK);
    }

    @GetMapping("/auctionItems/{id}")
    public ResponseEntity<AuctionItem> getAuctionItemByID(@PathVariable long id){
        return new ResponseEntity<>(service.getAuctionItemById(id), HttpStatus.OK);
    }

    @PostMapping("/auctionItems")
    public ResponseEntity<AuctionItem> addAuctionItem(@RequestBody AuctionItem item){
        service.addAuctionItem(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/auctionItems/{id}")
    public ResponseEntity<AuctionItem> deleteAuctionItemByID(@PathVariable long id){
        service.deleteAuctionItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
