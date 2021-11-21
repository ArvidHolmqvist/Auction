import React, { useState, useEffect } from 'react';
import AuctionDataService from "../api/AuctionDataService";
import AuctionItemModal from "./AuctionItemModal";
import {
    MDBBtn,
    MDBCard,
    MDBCardBody,
    MDBCardFooter,
    MDBCardImage,
    MDBCardText,
    MDBCardTitle,
    MDBRipple,
    MDBInput,
    MDBModal,
    MDBModalDialog,
    MDBModalContent,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
    MDBTable, MDBTableHead, MDBTableBody
} from "mdb-react-ui-kit";

export default function AuctionCardComponent(props) {
    const [data, setData] = useState([]);
    let eventSource = undefined;

    const [currentPrice,setCurrentPrice] = useState();
    const [bid, setBid] = useState(0);
    const [numberOfBidders,setNumberOfBidders] = useState(0);
    const [timeLeft,setTimeLeft] = useState(0.0);
    const [modalOpen,setModalOpen] = useState(false);

    useEffect(() => {
        updateNumberOfBidders();
        updateCurrentPrice();
    }, []);

    useEffect(() => {
        setTimer();
    });

    useEffect(() => {
        eventSource = new EventSource(`http://localhost:4567/emitter/bidder/${props.id}`);
        console.log(eventSource)
        eventSource.onopen = (event) => {
            console.log("connection opened")
        }

        eventSource.onmessage = (event) => {
            updateNumberOfBidders();
            updateCurrentPrice();
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

    const setNewBid = (event) => {
        if(!isNaN(event.target.value)){
            setBid(Number(event.target.value))
        }

    };

    const WindowFocusHandler = () => {
        useEffect(() => {
            window.addEventListener('focus', onFocus);
            window.addEventListener('blur', onFocus);

            return () => {
                window.removeEventListener('focus', onFocus);
                window.removeEventListener('blur', onFocus);
            };
        });

        return <></>;
    };

    const onFocus = () => {
        updateNumberOfBidders();
        updateCurrentPrice();
    };

    const updateNumberOfBidders = () => {
        //console.log(props.id);
        AuctionDataService.getBiddersFromAuctionID(props.id)
            .then(
                (response) => {
                    setNumberOfBidders(response.data.length)
                }
            );
    }

    const updateCurrentPrice = () => {
        AuctionDataService.getMaxBidFromAuctionID(props.id)
            .then(
                (response) => {
                    setCurrentPrice(response.data);
                }
            );
    }

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

    const createBid = () => {
        let bidder = {
            'auctionID' : props.id,
            'name' : 'name 1',
            'bid' : bid,
            'date' : Date.now()
        }
        AuctionDataService.getMaxBidFromAuctionID(props.id).then(
            (response) => {

            }
        );
        AuctionDataService.getMaxBidFromAuctionID(props.id).then(
            (response) => {
                console.log("hello2")
                if (response.data < bid){
                    AuctionDataService.createBid(bidder).then(
                        (response) => {
                            setCurrentPrice(response.data.bid)
                            setNumberOfBidders(numberOfBidders+1)
                        }
                    )
                } else {
                    updateNumberOfBidders()
                    updateCurrentPrice()
                }
            })
    }

    const deleteBid = () => {
        AuctionDataService.deleteAuctionItem(props.id).then(
            () => {
                //TODO
            }
        )
    }

    const toggleModal = () => {
        setModalOpen(!modalOpen)
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
                    {currentPrice + " " + props.currency + " - " + numberOfBidders + " bids"}
                </MDBCardText>
                <MDBBtn color="primary" size="md" onClick={createBid}>
                    Bid
                </MDBBtn>
                <MDBInput label='Bid' id='typeNumber' type='float' onChange={setNewBid}></MDBInput>
                <MDBBtn color="primary" size="md" onClick={deleteBid}>
                    Remove
                </MDBBtn>
                <MDBBtn color="primary" size="md" onClick={toggleModal}>
                    modal
                </MDBBtn>
                <MDBModal staticBackdrop show={modalOpen} tabIndex='-1'>
                    <MDBModalDialog centered size="lg">
                        <AuctionItemModal id={props.id} name={props.name} endTime={props.endTime} description={props.description} startPrice={props.startPrice} currency={props.currency} toggleModal={toggleModal}></AuctionItemModal>
                    </MDBModalDialog>
                </MDBModal>
            </MDBCardBody>
            <MDBCardFooter>{timeLeft}</MDBCardFooter>
        </MDBCard>
    )
}