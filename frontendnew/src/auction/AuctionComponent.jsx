import React, { useState, useEffect } from 'react';
import AuctionDataService from '../api/AuctionDataService.jsx'

export default function AuctionComponent() {
    const [id,setId] = useState(0);
    const [name,setName] = useState("name");
    const [desc,setDesc] = useState("desc");
    const [endDate,setEndDate] = useState(new Date());

    useEffect(() => {
        updateAuctionItem()
    }, []);

    const updateAuctionItem = () => {
        let auctionItem = {
            id: 1,
            name: "test",
            description: "desc",
            start_price: 1.0,
            currency: "USD",
            start_Time: new Date(),
            end_Time: new Date(Date.now() + (3600 * 1000 * 24))
        }

        AuctionDataService.createAuctionItem(auctionItem).then(r =>
            AuctionDataService.getAllAuctionItems()
        );
    }

    return (
        <button onClick={updateAuctionItem}>xxx</button>
    )
}