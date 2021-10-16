package se.ah.auctionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.JPAServices.AuctionItemService;
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
    public CommandLineRunner demo(AuctionItemService service) {
        return (args) -> {



            service.addAuctionItem(new AuctionItem("item 1", "description of item 1", 100,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(60).toLocalDateTime())));
            service.addAuctionItem(new AuctionItem("item 2", "description of item 2", 51,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(30).toLocalDateTime())));
            service.addAuctionItem(new AuctionItem("item 3", "description of item 3", 32,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(10).toLocalDateTime())));
            service.addAuctionItem(new AuctionItem("item 4", "description of item 4", 2000,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(180).toLocalDateTime())));


            // fetch all customers
            log.info("items found with findAll():");
            log.info("-------------------------------");
            for (AuctionItem auctionItem : service.getAllAuctionItems()) {
                log.info(auctionItem.toString());
            }
            log.info("");

            AuctionItem a = new AuctionItem("item 1", "description of item 1", 100,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(60).toLocalDateTime()));

            AuctionItem b = new AuctionItem("item 1", "description of item 1", 100,
                    "USD", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()),
                    Timestamp.valueOf(ZonedDateTime.now().plusMinutes(60).toLocalDateTime()));

            service.addAuctionItem(a);



            for (AuctionItem item : service.getAllAuctionItems()){
                service.deleteAuctionItemById(item.getId());
            }
        };
    }
}
