import axios from 'axios';

class AuctionDataService {
    getAllAuctionItems() {
        return axios.get(`http://localhost:4567/AuctionItems`);
    }

    createAuctionItem(auctionItem) {
        return axios.post(`http://localhost:4567/AuctionItems`,auctionItem);
    }

}

export default new AuctionDataService();