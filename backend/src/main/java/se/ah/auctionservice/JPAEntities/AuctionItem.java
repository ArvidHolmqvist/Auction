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
    private double start_Price;

    @Column
    @Getter @Setter
    private String currency;

    @Column
    @Getter @Setter
    private Timestamp start_Time;

    @Column
    @Getter @Setter
    private Timestamp end_Time;

    public AuctionItem(String name, String description, double startPrice, String currency, Timestamp startTime,
                       Timestamp endTime) {
        this.name = name;
        this.description = description;
        this.start_Price = startPrice;
        this.currency = currency;
        this.start_Time = startTime;
        this.end_Time = endTime;
    }

    public AuctionItem() {
    }

    public boolean isActive() {
        return Time.valueOf(LocalTime.now()).before(end_Time);
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + start_Price +
                ", currency='" + currency + '\'' +
                ", startTime=" + start_Time +
                ", endTime=" + end_Time +
                '}';
    }
}
