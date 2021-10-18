import React, {useState,useEffect } from 'react';
import AuctionDataService from "../api/AuctionDataService";
import {MDBCard, MDBCardBody, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBRow, MDBRipple, MDBBtn, MDBCardFooter} from "mdb-react-ui-kit";

export default function CardAuctionComponent(props) {
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
        <MDBRow className='row-cols-1 row-cols-md-3 g-4'>
            {auctionItems.map(
                auctionItem =>
                    <MDBCard style={{ maxWidth: '22rem' }}>
                        <MDBRipple rippleColor='light' rippleTag='div' className='bg-image hover-overlay'>
                            <MDBCardImage src='https://mdbcdn.b-cdn.net/img/new/standard/nature/111.jpg' fluid alt='...' />
                            <a>
                                <div className='mask' style={{ backgroundColor: 'rgba(251, 251, 251, 0.15)' }}></div>
                            </a>
                            <MDBCardBody>
                                <MDBCardTitle>{auctionItem.name}</MDBCardTitle>
                                <MDBCardText>
                                    {auctionItem.description}
                                </MDBCardText>
                                <MDBBtn href='#'>Button</MDBBtn>
                            </MDBCardBody>
                            <MDBCardFooter className='text-muted'>{getTimeLeft(auctionItem.end_time)}</MDBCardFooter>
                        </MDBRipple>
                    </MDBCard>
            )}
        </MDBRow>
    )
}