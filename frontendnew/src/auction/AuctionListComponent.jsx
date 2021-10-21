import React, {useState,useEffect } from 'react';
import AuctionDataService from "../api/AuctionDataService";
import {MDBCardGroup} from "mdb-react-ui-kit";
import AuctionCardComponent from "./AuctionCardComponent";

export default function AuctionListComponent(props) {
    const [auctionItems,setAuctionItems] = useState([]);

    useEffect(() => {
        refreshAuctionItems()
    }, []);

    const refreshAuctionItems = () => {
        AuctionDataService.getAllActiveAuctionItems()
            .then(
                (response) => {
                    setAuctionItems(response.data)
                }
            )
    }

    return (
        <MDBCardGroup>
            {auctionItems.map(
                auctionItem =>
                    <AuctionCardComponent name={auctionItem.name} endTime={auctionItem.end_time} description={auctionItem.description} currentPrice={auctionItem.current_price} currency={auctionItem.currency}/>
            )}
        </MDBCardGroup>
    )
}