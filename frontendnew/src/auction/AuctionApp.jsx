import React from 'react';
import { MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBRow, MDBCol } from 'mdb-react-ui-kit';
import AuctionCardComponent from "./AuctionCardComponent";
import AuctionListComponent from "./AuctionListComponent";

export default function AuctionApp() {
    return (
        <div>
            <AuctionListComponent></AuctionListComponent>
            <AuctionCardComponent></AuctionCardComponent>
        </div>
    );
}