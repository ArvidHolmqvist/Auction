import React, {useState,useEffect } from 'react';
import AuctionDataService from "../api/AuctionDataService";
import {MDBCardGroup} from "mdb-react-ui-kit";
import AuctionCardComponent from "./AuctionCardComponent";

export default function AuctionListComponent(props) {
    const [data, setData] = useState([]);
    let eventSource = undefined;

    const [auctionItems,setAuctionItems] = useState([]);

    useEffect(() => {
        refreshAuctionItems()
    }, []);

    useEffect(() => {
        eventSource = new EventSource(`http://localhost:4567/emitter/auctionItem`);
        console.log(eventSource)
        eventSource.onopen = (event) => {
            console.log("connection opened")
        }

        eventSource.onmessage = (event) => {
            console.log("result", event.data);
            refreshAuctionItems()
        }

        eventSource.onerror = (event) => {
            console.log(event.target.readyState)
            if (event.target.readyState === EventSource.CLOSED) {
                console.log('eventsource closed (' + event.target.readyState + ')')
            }
            eventSource.close();
        }

        return () => {
            eventSource.close();
            console.log("eventsource closed")
        }
    },[]);

    const refreshAuctionItems = () => {
        AuctionDataService.getAllActiveAuctionItems()
            .then(
                (response) => {
                    setAuctionItems(response.data)
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