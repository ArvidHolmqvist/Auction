package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ImageItem {
    @Id
    @Column
    @Getter
    @Setter
    private long itemID;

    @Id
    @Column
    @Getter @Setter
    private long imageID;

    @Column
    @Getter @Setter
    @Lob
    private byte[] image;
}
