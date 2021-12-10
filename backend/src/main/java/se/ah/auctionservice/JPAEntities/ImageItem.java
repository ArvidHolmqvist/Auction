package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images" )
public class ImageItem {
    @Id
    @Column(name="imageID")
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageID;

    @Column(name="auctionID")
    @Getter @Setter
    private long auctionID;

    @Column
    @Getter @Setter
    @Lob
    private byte[] image;

    public ImageItem(long auctionID, byte[] image) {
        this.auctionID = auctionID;
        this.image = image;
    }
}
