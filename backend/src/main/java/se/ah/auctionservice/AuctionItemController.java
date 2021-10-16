package se.ah.auctionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.JPAServices.AuctionItemService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuctionItemController {
    AuctionItemService service;

    @Autowired
    public AuctionItemController(AuctionItemService service) {
        this.service = service;
    }

    @GetMapping("/AuctionItems/all")
    public ResponseEntity<List<AuctionItem>> getAllAuctionItems(){
        return new ResponseEntity<>(service.getAllAuctionItems(), HttpStatus.OK) ;
    }

    @GetMapping("/AuctionItems/boolean")
    public ResponseEntity<List<AuctionItem>> getAllActiveAuctionItems(@RequestParam boolean isActive){
        if (isActive){
            return new ResponseEntity<>(service.getAllActiveAuctionItems(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getAllInactiveAuctionItems(), HttpStatus.OK);
        }
    }

    @GetMapping("/AuctionItems/{id}")
    public ResponseEntity<AuctionItem> getAuctionItemByID(@PathVariable long id){
        return new ResponseEntity<>(service.getAuctionItemById(id), HttpStatus.OK);
    }

    @PostMapping("/AuctionItems")
    public ResponseEntity<AuctionItem> addAuctionItem(@RequestBody AuctionItem item){
        service.addAuctionItem(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/AuctionItems")
    public ResponseEntity<AuctionItem> deleteAuctionItemByID(@RequestParam long id){
        service.deleteAuctionItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }












}
