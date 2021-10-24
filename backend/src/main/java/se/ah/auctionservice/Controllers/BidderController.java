package se.ah.auctionservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.JPAEntities.Bidder;
import se.ah.auctionservice.JPAServices.BidderService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BidderController {
    BidderService service;

    @Autowired
    public BidderController(BidderService service) {
        this.service = service;
    }

    @GetMapping("/bidders/{id}")
    public ResponseEntity<List<Bidder>> getBiddersByAuctionID(@PathVariable long id){
        return new ResponseEntity<>(service.getBiddersByAuctionID(id), HttpStatus.OK);
    }

    @GetMapping("/bidders/bid/max/{id}")
    public ResponseEntity<Double> getMaxBidFromAuctionID(@PathVariable long id){
        return new ResponseEntity<>(service.findMaxBidByAuctionID(id), HttpStatus.OK);
    }

    @PostMapping("/bidders")
    public ResponseEntity<Bidder> getBiddersByAuctionID(@RequestBody Bidder bidder){
        service.addBid(bidder);
        return new ResponseEntity<>(bidder, HttpStatus.OK);
    }


}
