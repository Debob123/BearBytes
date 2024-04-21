import { useState } from 'react';
import ReservationDisplay from '../clerkPageComponents/ReservationDisplay';
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation';
import singleRoom from '../images/hotelRoom1.jpg'
import "./styles/clerkModifyReservations.css"

function ClerkModifyReservation() {
    const [username, setUsername] = useState('');
    const [reservations, setReservations] = useState([]);
    const [submitted, setSubmitted] = useState(false);

    function renderReservations() {
        if(username) {
            fetch('http://localhost:8080/reservation/getReservations', {
                mode: 'cors',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(username)
            })
            .then(response => response.json())
            .then(data => {
                // Set the array of reservations
                setReservations(data);
            })
            .catch(error => console.error('Error creating room array:', error));
            setSubmitted(true);
        }
    }

    const displayReservations = () => {
        return (
            submitted && 
            (reservations.length !== 0 ? 
            <div className="display">
                {
                reservations.map((reservation) =>(
                    <ReservationDisplay className="box-display" 
                        key={reservation.reservationID} 
                        imgLink={singleRoom} 
                        reservation={reservation}
                        reservations={reservations}
                        setReservations={setReservations}
                        />
                ))}
            </div> : 
            <p className="center-text">This guest does not have a reservation</p> )
        )
    }

    return (
        <div>
            <ClerkNavigation/>
            <div className="content">
                <h1 className="center-text">Guest Reservation</h1>
                <div className="center-input top-space">
                    <label htmlFor="username">Enter guest username:</label>
                    <input type="text" 
                    id="username" 
                    name="username" 
                    value={username}
                    onChange={(e) => setUsername(e.target.value)} 
                    placeholder="Username" />
                    <button className="submit-btn" onClick={renderReservations}>Submit</button>
                </div>
                {displayReservations()}
            </div>
        </div>
    )
}

export default ClerkModifyReservation