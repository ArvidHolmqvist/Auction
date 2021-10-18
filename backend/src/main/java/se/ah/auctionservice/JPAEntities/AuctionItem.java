package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "auction_items" )
public class AuctionItem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;

    @Column
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private String description;

    @Column
    @Getter @Setter
    private double start_price;

    @Column
    @Getter @Setter
    private String currency;

    @Column
    @Getter @Setter
    private Timestamp start_time;

    @Column
    @Getter @Setter
    private Timestamp end_time;

    public AuctionItem(String name, String description, double startPrice, String currency, Timestamp startTime,
                       Timestamp endTime) {
        this.name = name;
        this.description = description;
        this.start_price = startPrice;
        this.currency = currency;
        this.start_time = startTime;
        this.end_time = endTime;
    }

    public AuctionItem() {
    }

    public boolean isActive() {
        return Time.valueOf(LocalTime.now()).before(end_time);
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + start_price +
                ", currency='" + currency + '\'' +
                ", startTime=" + start_time +
                ", endTime=" + end_time +
                '}';
    }
}
