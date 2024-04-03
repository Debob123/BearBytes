import {useState} from 'react'
import singleRoom from '../images/hotelRoom1.jpg'
import GuestHeader from "../components/GuestHeader.jsx";
import BoxDisplay from '../components/BoxDisplay.jsx';
import getSessionStorage from '../authentication/GetSessionStorage.js';
import './styles/confirmReservationPage.css';

function ConfirmReservationPage() {
    const [checkbox, setCheckbox] = useState(false);
    
    const dates = getSessionStorage('dates', null);
    const rooms = getSessionStorage('rooms', null);
    const user = getSessionStorage('user', null);
    const nightsStayed = (Date.parse(dates[1]) - Date.parse(dates[0])) / (1000 * 3600 * 24);
    let nightlyCost = 0;

    rooms.forEach((room) => {
        nightlyCost += room.dailyRate;
    })

    const displayRooms = (rooms) => {
        return (
            <div className="display">
                {
                rooms.map((room) =>(
                    <BoxDisplay className="box-display" 
                        key={room.number} 
                        imgLink={singleRoom} 
                        type={room.type} 
                        cost={room.dailyRate} 
                        numBeds={room.numBeds}
                        bedSize={room.bedSize}
                        smokingAllowed={room.smokingAllowed}
                        roomNum={room.number}
                        quality={room.quality}
                        btnAct={null}/>
                ))}
            </div>
        )
    }

    const handleSubmit = (e) => {
        let body = JSON.stringify({
                username: user.username,
                rooms: rooms,
                startDate: dates[0],
                endDate: dates[1]
        })
        e.preventDefault();
        // Request validation of the guest account through API
        fetch('http://localhost:8080/reservation/add', {
        mode: 'cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: body
        },
      )
      .then(response => response.json())
      .then(data => {
        // Check if the guest was authenticated
        if(data) {
            // If the guest was authenticated, save the username to the session info
            // so it is accessible on other pages (this will be needed for associating
            // reservations and similar items)
        }
      })
      .catch(error => console.error('Error creating booking:', error));
    }
    return (
        <div>
            <GuestHeader className="header"/>
            <h2 className="content-begin center-text">Reserve Room</h2>
            <div className="container">
                {displayRooms(rooms)}
                <form className="confirm-input" onSubmit={handleSubmit}>
                    {/* Payment information display */}
                    <div className="box">
                        <div className="row">
                            <p>Start night: {dates[0]}</p>
                            <p>End night: {dates[1]}</p>
                        </div>
                        { /*<div className="row">
                            <label htmlFor="cardNum">Card number: </label>
                            <input id="cardNum" 
                                   type="tel"
                                   value={cardNum} 
                                   onChange={(e) => setCardNum(e.target.value)}
                                   pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"
                                   placeholder="1234-5678-9098-7654"
                                   minLength={19}
                                   maxLength={19}
                                   required/>
                        </div> 
                        <div className="row">
                            <p>Payment Method: </p>
                            <div onChange={(e) => setCardType(e.target.value)}>
                                <label htmlFor="credit">credit</label>
                                <input id="credit" type="radio" name="payment_method" value="credit" required/>
                        
                                <label htmlFor="debit">debit</label>
                                <input id="debit" type="radio" name="payment_method" value="debit" required defaultChecked/>
                            </div>
                        </div> */}
                    </div>
                    {/* Rules and policies display */}
                    <div className="box">
                        <h3 className="center-text">Rules and Policies</h3>
                        <div >
                            <ul className="center-list">
                                <li>If you cancel within 2 days of reservation start, you will be charged 80% of one night's stay</li>
                                <li>You may modify your reservation at any time</li>
                                <li>Check room policy for smoking</li>
                            </ul>
                        </div>
                        <div className="center-text">
                            <input id="agreement" 
                                   type="checkbox" 
                                   name="agreement" 
                                   value="agreement"
                                   onChange={(e) => setCheckbox(e.target.checked)} 
                                   required/>
                            <label htmlFor="agree"> I have read and agree to the above policies</label>
                        </div>
                    </div>
                    {/* Summary of price and stay */}
                    <div className="box">
                       <div className="row">
                           <p>Nights stayed: {nightsStayed}</p>
                           <p>${nightlyCost} per night</p>
                        </div>
                        <div className="row">
                            <p>Total Stay Cost:</p>
                            <p>${nightlyCost * nightsStayed}</p>
                        </div>
                    </div>
                    <input type="submit" 
                           value="Confirm" 
                           className="cnf-btn" 
                           disabled={!checkbox}/>
                </form>
            </div>
        </div>
    );
}

export default ConfirmReservationPage;