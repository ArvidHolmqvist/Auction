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

    const getTimeLeft = (endTime) => {
        let timeLeft = endTime - Date.now();
        let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
        let hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

        if (timeLeft < 0) {
            return "Expired";
        }

        return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
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