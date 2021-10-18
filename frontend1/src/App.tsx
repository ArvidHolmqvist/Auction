import React from 'react';
import logo from './logo.svg';
import './App.css';
import Card from 'react-bootstrap/Card'
import { MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImage, MDBBtn } from 'mdb-react-ui-kit';
import AuctionApp from "./auction/AuctionApp";


function App() {
  return (
    <div className="App">
        <AuctionApp/>
    </div>
  );
}

export default App;
