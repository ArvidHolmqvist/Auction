import axios from 'axios';

class AuctionDataService {
    getAllActiveAuctionItems() {
        return axios.get(`http://localhost:4567/auctionItems`,{ params: { isActive: true } });
    }

    createAuctionItem(auctionItem) {
        return axios.post(`http://localhost:4567/auctionItems`, auctionItem);
    }

    getBiddersFromAuctionID(id) {
        return axios.get(`http://localhost:4567/bidders/${id}`);
    }

    createBid(bidder) {
        return axios.post(`http://localhost:4567/bidders`, bidder);
    }

    getMaxBidFromAuctionID(id) {
        return axios.get(`http://localhost:4567/bidders/bid/max/${id}`);
    }

}

export default new AuctionDataService();