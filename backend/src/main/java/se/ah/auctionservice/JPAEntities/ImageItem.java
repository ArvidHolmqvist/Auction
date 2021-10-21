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
    private long image_id;

    @Column
    @Getter @Setter
    private long auction_id;

    @Column
    @Getter @Setter
    @Lob
    private byte[] image;
}
