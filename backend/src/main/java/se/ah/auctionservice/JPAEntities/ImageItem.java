package se.ah.auctionservice.JPAEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images" )
public class ImageItem {
    @Id
    @Column
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageID;

    @Column
    @Getter @Setter
    private long itemID;

    @Column
    @Getter @Setter
    @Lob
    private byte[] image;
}
