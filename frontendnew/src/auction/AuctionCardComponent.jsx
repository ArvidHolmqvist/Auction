import React, { useState, useEffect } from 'react';
import AuctionDataService from '../api/AuctionDataService.jsx'
import {
    MDBBtn,
    MDBCard,
    MDBCardBody,
    MDBCardFooter,
    MDBCardImage,
    MDBCardText,
    MDBCardTitle,
    MDBRipple
} from "mdb-react-ui-kit";

export default function AuctionCardComponent(props) {
    const [currentPrice,setCurrentPrice] = useState(0.0);
    const [timeLeft,setTimeLeft] = useState(0.0);

    useEffect(() => {
        const timer = setTimeout(() => {
            setTimeLeft(getTimeLeft());
        },1000);
    });

    const updateAuctionItem = () => {

    }

    const getTimeLeft = () => {
        let timeLeft = props.endTime - Date.now();
        let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
        let hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

        if (timeLeft < 0) {
            return "Expired";
        }

        if(days > 0){
            return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
        }

        if(hours > 0){
            return hours + "h " + minutes + "m " + seconds + "s ";
        }

        if(minutes > 0){
            return minutes + "m " + seconds + "s ";
        }

        return seconds + "s ";

    }



    return (
        <MDBCard style={{ maxWidth: '20rem', maxHeight: '40rem' }}>
            <MDBCardImage src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="MDBCard image cap" top hover
                          overlay="white-slight" />
            <a>
                <div className='mask' style={{ backgroundColor: 'rgba(251, 251, 251, 0.15)' }}></div>
            </a>
            <MDBCardBody>
                <MDBCardTitle tag="h5">{props.name}</MDBCardTitle>
                <MDBCardText className='text-left'>
                    {currentPrice + " " + props.currency}
                </MDBCardText>
                <MDBBtn color="primary" size="md">
                    Bid
                </MDBBtn>
            </MDBCardBody>
            <MDBCardFooter>{timeLeft}</MDBCardFooter>
        </MDBCard>
    )
}