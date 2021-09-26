package se.ah.auctionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.ah.auctionservice.JPAEntities.AuctionItem;
import se.ah.auctionservice.Repositories.AuctionItemRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@SpringBootApplication
public class AuctionServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(AuctionServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AuctionItemRepository repository) {
        return (args) -> {

            repository.save(new AuctionItem("item 1", "description of item 1", 100,
                    "USD", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + 3600000),
                    new Date(System.currentTimeMillis())));
            repository.save(new AuctionItem("item 2", "description of item 2", 12,
                    "USD", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + 3600000),
                    new Date(System.currentTimeMillis())));
            repository.save(new AuctionItem("item 3", "description of item 3", 50,
                    "USD", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + 3600000),
                    new Date(System.currentTimeMillis())));
            repository.save(new AuctionItem("item 4", "description of item 4", 250,
                    "USD", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + 3600000),
                    new Date(System.currentTimeMillis())));
            repository.save(new AuctionItem("item 5", "description of item 5", 3,
                    "USD", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis() + 3600000),
                    new Date(System.currentTimeMillis())));

            // fetch all customers
            log.info("items found with findAll():");
            log.info("-------------------------------");
            for (AuctionItem auctionItem : repository.findAll()) {
                log.info(auctionItem.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<AuctionItem> auctionItem = repository.findById(1L);
            log.info("item found with findById(1L):");
            log.info("--------------------------------");
            log.info(auctionItem.toString());
            log.info("");
        };
    }
}
