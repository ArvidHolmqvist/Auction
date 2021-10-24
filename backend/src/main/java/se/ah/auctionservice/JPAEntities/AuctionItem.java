package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="startPrice")
    @Getter @Setter
    private double startPrice;

    @Column(name="currentPrice")
    @Getter @Setter
    private double currentPrice;

    @Column
    @Getter @Setter
    private String currency;

    @Column(name="startTime")
    @Getter @Setter
    private long startTime;

    @Column(name="endTime")
    @Getter @Setter
    private long endTime;

    public AuctionItem(String name, String description, double startPrice, double currentPrice, String currency, long startTime,
                       long endTime) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.currency = currency;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AuctionItem() {
    }

    public boolean isActive() {
        //return Time.valueOf(LocalTime.now()).before(end_time);
        return ZonedDateTime.now().toInstant().toEpochMilli() < endTime;
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", currency='" + currency + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
