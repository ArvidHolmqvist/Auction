import React from 'react';
import { MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBRow, MDBCol } from 'mdb-react-ui-kit';
import AuctionCardComponent from "./AuctionCardComponent";
import AuctionListComponent from "./AuctionListComponent";
import Menu from "./Menu";

import {
    MDBCarousel,
    MDBCarouselInner,
    MDBCarouselItem,
    MDBCarouselElement,
} from 'mdb-react-ui-kit';

export default function AuctionApp() {
    return (
        <div>
            <Menu></Menu>
            <AuctionListComponent></AuctionListComponent>
        </div>
    );
}