import {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import singleRoom from '../images/hotelRoom1.jpg'
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation.jsx';
import BoxDisplay from '../components/BoxDisplay.jsx';
import getSessionStorage from '../authentication/GetSessionStorage.js';
import Modal from 'react-modal';
import './styles/confirmReservationPage.css';

const customStyles = {
    content: {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      marginRight: '-50%',
      transform: 'translate(-50%, -50%)',
    },
};

function ClerkConfirmReservation() {
    const [checkbox, setCheckbox] = useState(false);
    const [isHidden, setIsHidden] = useState("hidden");
    const [content, setContent] = useState("");
    const [username, setUsername] = useState("");
    const [submitted, setSubmitted] = useState(false);
    const [modalIsOpen, setIsOpen] = useState(false);
    const navigate = useNavigate();
    
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
            <div className="rooms-display">
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

    function openModal() {
        setIsOpen(true);
    }
    
    function closeModal() {
        setIsOpen(false);
    }

    function homeRedirect() {
        navigate("/clerkHome");
    }

    const handleSubmit = (e) => {
        let body = JSON.stringify({
                rooms: rooms,
                startDate: dates[0],
                endDate: dates[1],
                username: username
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
        // Check if the room was added
        if(data) {
            openModal();
            setSubmitted(true);
        } else {
            setIsHidden('red');
            setContent("Error creating reservation, Are you sure the username is correct?");
        }
      })
      .catch(error => console.error('Error creating booking:', error));
    }
    return (
        <div>
             <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                style={customStyles}
                contentLabel="Reservation created"
            >
                <p className="green">Reservation successfully created</p>
                <button onClick={homeRedirect}>Continue</button>
            </Modal>
            <ClerkNavigation/>
            <div className="content">
            <h2 className="center-text">Reserve Guest Room</h2>
            <div className="center top-space">
                <label htmlFor="username">Guest Username: </label>
                <input 
                    type="text" 
                    id="username" 
                    name="username"
                    placeholder="username"
                    onChange={(e) => setUsername(e.target.value)}
                    required/>
            </div>
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
                    <div className={isHidden + " center-text bottom-gap"}>{content}</div>
                    {!submitted && <input type="submit" 
                           value="Confirm" 
                           className="cnf-btn" 
                           disabled={!checkbox || username === ""}/>}
                </form>
            </div>
            </div>
        </div>
    );
}

export default ClerkConfirmReservation;