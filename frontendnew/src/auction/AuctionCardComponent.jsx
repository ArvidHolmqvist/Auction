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
    const [id,setId] = useState(0);
    const [name,setName] = useState("name");
    const [desc,setDesc] = useState("desc");
    const [endDate,setEndDate] = useState(new Date());

    useEffect(() => {
    }, []);

    const updateAuctionItem = () => {
        let auctionItem = {
            id: 0,
            name: "test",
            description: "desc",
            start_price: 1.0,
            currency: "USD",
            start_time: Date.now(),
            end_time: new Date("12/12/2021 16:00:00").getTime()
        }

        AuctionDataService.createAuctionItem(auctionItem).then(r =>
            AuctionDataService.getAllActiveAuctionItems()
        );
    }

    return (
        <MDBCard style={{ maxWidth: '20rem', maxHeight: '40rem' }}>
            <MDBRipple rippleColor='light' rippleTag='div' className='bg-image hover-overlay'>
                <MDBCardImage src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="MDBCard image cap" top hover
                              overlay="white-slight" />
                <a>
                    <div className='mask' style={{ backgroundColor: 'rgba(251, 251, 251, 0.15)' }}></div>
                </a>
            </MDBRipple>
            <MDBCardBody>
                <MDBCardTitle tag="h5">{props.name}</MDBCardTitle>
                <MDBCardText className='text-left'>
                    {props.currentPrice + " " + props.currency}
                </MDBCardText>
                <MDBBtn color="primary" size="md">
                    Bid
                </MDBBtn>
            </MDBCardBody>
        </MDBCard>
    )
}