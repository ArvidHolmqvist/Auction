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
                    console.log(auctionItems)
                }
            )
    }

    const renderCard = (auctionItem) => {
        if(auctionItem.endTime - Date.now() >= 0){
            return <AuctionCardComponent id={auctionItem.id} name={auctionItem.name} endTime={auctionItem.endTime} description={auctionItem.description} currentPrice={auctionItem.currentPrice} currency={auctionItem.currency}/>
        }
        return null
    }

    return (
        <MDBCardGroup>
            {auctionItems.map((auctionItem) =>
                renderCard(auctionItem)
            )}
        </MDBCardGroup>
    )
}