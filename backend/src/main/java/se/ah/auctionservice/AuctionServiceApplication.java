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
        };
    }
}
