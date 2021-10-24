package se.ah.auctionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.JPAEntities.Bidder;
import se.ah.auctionservice.JPAServices.AuctionItemService;
import se.ah.auctionservice.JPAServices.BidderService;
import se.ah.auctionservice.Repositories.AuctionItemRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootApplication
public class AuctionServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(AuctionServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AuctionItemService auctionItemService, BidderService bidderService) {
        return (args) -> {


            /*
            service.addAuctionItem(new AuctionItem("item 1", "description of item 1", 100,
                    "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(30).toInstant().toEpochMilli()));
            service.addAuctionItem(new AuctionItem("item 2", "description of item 2", 51,
                    "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(10).toInstant().toEpochMilli()));
            service.addAuctionItem(new AuctionItem("item 3", "description of item 3", 32,
                    "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(60).toInstant().toEpochMilli()));
             */
            auctionItemService.addAuctionItem(new AuctionItem("item 3", "description of item 4", 2000, 2000,
                    "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(5).toInstant().toEpochMilli()));


            // fetch all customers
            log.info("items found with findAll():");
            log.info("-------------------------------");
            for (AuctionItem auctionItem : auctionItemService.getAllAuctionItems()) {
                log.info(auctionItem.toString());
            }
            log.info("");

            AuctionItem a = new AuctionItem("item 1", "description of item 1", 100,
                    100, "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(40).toInstant().toEpochMilli());

            AuctionItem b = new AuctionItem("item 2", "description of item 1", 100,
                    100, "USD", ZonedDateTime.now().toInstant().toEpochMilli(),
                    ZonedDateTime.now().plusMinutes(1000).toInstant().toEpochMilli());

            auctionItemService.addAuctionItem(a);
            auctionItemService.addAuctionItem(b);

            bidderService.addBid(new Bidder(a.getId(),"bidder 1",101,
                    ZonedDateTime.now().toInstant().toEpochMilli()));
            bidderService.addBid(new Bidder(a.getId(),"bidder 2",102,
                    ZonedDateTime.now().toInstant().toEpochMilli()));
            bidderService.addBid(new Bidder(a.getId(),"bidder 1",103,
                    ZonedDateTime.now().toInstant().toEpochMilli()));

            System.out.println(bidderService.getBiddersByAuctionID(a.getId()));
        };
    }
}
