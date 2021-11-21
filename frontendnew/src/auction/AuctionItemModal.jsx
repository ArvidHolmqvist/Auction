import React, {useState,useEffect } from 'react';
import AuctionDataService from "../api/AuctionDataService";
import missingImage from '../resources/images/missing-image.png';
import {
    MDBBtn,
    MDBCardGroup, MDBModal,
    MDBModalBody,
    MDBModalContent,
    MDBModalDialog, MDBModalFooter,
    MDBModalHeader,
    MDBModalTitle, MDBTable, MDBTableBody, MDBTableHead,
    MDBCarousel,
    MDBCarouselInner,
    MDBCarouselItem,
    MDBCarouselElement,
    MDBCarouselCaption,
} from "mdb-react-ui-kit";
import AuctionCardComponent from "./AuctionCardComponent";
import Carousel from "react-bootstrap/Carousel";

export default function AuctionListModalComponent(props) {

    const [timeLeft,setTimeLeft] = useState(0.0);
    const [bidList,setBidList] = useState([]);
    let eventSource = undefined

    useEffect(() => {
        updateBidList()
    }, []);

    useEffect(() => {
        eventSource = new EventSource(`http://localhost:4567/emitter/bidder/${props.id}`);
        console.log(eventSource)
        eventSource.onopen = (event) => {
            console.log("connection opened")
        }

        eventSource.onmessage = (event) => {
            updateBidList()
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

    const setTimer = () => {
        const timer = setTimeout(() => {
            setTimeLeft(getTimeLeft());
        },1000);
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

    const updateBidList = () => {
        AuctionDataService.getBiddersFromAuctionID(props.id)
            .then(
                (response) => {
                    setBidList(response.data.reverse());
                    console.log(response.data)
                }
            );
    }

    return (
        <MDBModalContent>
            <MDBModalHeader>
                <MDBModalTitle>{props.name}</MDBModalTitle>
                <MDBBtn className='btn-close' color='none' onClick={props.toggleModal}></MDBBtn>
            </MDBModalHeader>
            <MDBModalBody>
                <Carousel indicators={false}>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={missingImage}
                            alt="First slide"
                        />
                        <Carousel.Caption>
                            <h3>First slide label</h3>
                            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://mdbcdn.b-cdn.net/img/new/slides/042.jpg"
                            alt="Second slide"
                        />

                        <Carousel.Caption>
                            <h3>Second slide label</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://mdbcdn.b-cdn.net/img/new/slides/043.jpg"
                            alt="Third slide"
                        />

                        <Carousel.Caption>
                            <h3>Third slide label</h3>
                            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                </Carousel>
                <MDBTable hover>
                    <MDBTableHead>
                        <tr>
                            <th scope='col'>Name</th>
                            <th scope='col'>Bid</th>
                            <th scope='col'>Date</th>
                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        {bidList.map((item) => {
                            return [
                                <tr key={item}>
                                    <td>{item.name}</td>
                                    <td>{item.bid + " " + props.currency}</td>
                                    <td>{new Date(item.date).toLocaleString()}</td>
                                </tr>,
                            ];
                        })}
                    </MDBTableBody>
                </MDBTable>
            </MDBModalBody>
            <MDBModalFooter>
                <MDBBtn color='secondary' onClick={props.toggleModal}>
                    Close
                </MDBBtn>
            </MDBModalFooter>
        </MDBModalContent>
    )
}