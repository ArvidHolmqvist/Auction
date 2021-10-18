import axios from 'axios';

class AuctionDataService {
    getAllActiveAuctionItems() {
        return axios.get(`http://localhost:4567/AuctionItems`,{ params: { isActive: true } });
    }

    createAuctionItem(auctionItem) {
        console.log(auctionItem)
        return axios.post(`http://localhost:4567/AuctionItems`, auctionItem);
    }

}

export default new AuctionDataService();