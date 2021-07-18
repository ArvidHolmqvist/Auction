package se.ah.auctionservice;

import java.util.List;

public interface AuctionItemService {
    void addAuctionItem(AuctionItem item);
    AuctionItem getAuctionItemById(long id);
    void removeAuctionItemById(long id);
    void updateAuctionItemById(long id, AuctionItem item);
    List<AuctionItem> getAllAuctionItems();
    List<AuctionItem> getAllActiveAuctionItems();
    List<AuctionItem> getAllInactiveAuctionItems();
}
