package se.ah.auctionservice.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("emitterWrapper")
public class EmitterWrapper {
    private static SseEmitter auctionEmitter;
    private static Map<Long, SseEmitter> bidderEmitterMap = new ConcurrentHashMap<>();

    public static SseEmitter getBidderEmitter(long id){
        if (bidderEmitterMap.containsKey(id)) {
            return bidderEmitterMap.get(id);
        } else {
            return null;
        }
    }
    public static void addBidderEmitter(long id, SseEmitter sseEmitter){
        bidderEmitterMap.put(id, sseEmitter);
    }

    public static void sendBiddersMessage(long id){
        var emitter = getBidderEmitter(id);
        try {
            if (emitter != null) {
                emitter.send(emitter.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            emitter.completeWithError(e);
        }
    }

    public static SseEmitter getAuctionEmitter(){
        return auctionEmitter;
    }
    public static void addAuctionEmitter(SseEmitter sseEmitter){
        auctionEmitter = sseEmitter;
    }

    public static void sendAuctionsMessage(){
        var emitter = getAuctionEmitter();
        try {
            if (emitter != null) {
                emitter.send(emitter.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            emitter.completeWithError(e);
        }
    }
}
