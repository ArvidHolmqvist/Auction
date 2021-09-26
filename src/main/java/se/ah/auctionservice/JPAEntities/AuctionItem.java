package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
public class AuctionItem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    long id;

    @Column
    @Getter @Setter
    String name;

    @Column
    @Getter @Setter
    String description;

    @Column
    @Getter @Setter
    double startPrice;

    @Column
    @Getter @Setter
    String currency;

    @Column
    @Getter @Setter
    Time startTime;

    @Column
    @Getter @Setter
    Time endTime;

    @Column
    @Getter @Setter
    Date date;

    boolean isActive() {
        return Time.valueOf(LocalTime.now()).before(endTime);
    }


}
