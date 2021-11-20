package se.ah.auctionservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuctionEmitter {

    EmitterWrapper emitterWrapper;

    @Autowired
    public AuctionEmitter(EmitterWrapper emitterWrapper) {
        this.emitterWrapper = emitterWrapper;
    }

    @GetMapping("/emitter/bidder/{id}")
    public SseEmitter createBidderEmitter(@PathVariable long id){
        var emitter = new SseEmitter(Long.MAX_VALUE);
        emitterWrapper.addBidderEmitter(id,emitter);
        return emitter;
    }

    @GetMapping("/emitter/auctionItem")
    public SseEmitter createAuctionEmitter(){
        var emitter = new SseEmitter(Long.MAX_VALUE);
        emitterWrapper.addAuctionEmitter(emitter);
        return emitter;
    }

}
