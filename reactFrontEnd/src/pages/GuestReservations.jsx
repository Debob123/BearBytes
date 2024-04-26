import GuestNavigation from "../components/GuestNavigation";
import getSessionStorage from '../authentication/GetSessionStorage';
import singleRoom from '../images/hotelRoom1.jpg'
import ReservationDisplay from "../components/ReservationDisplay";
import {useState, useEffect} from 'react';
import "./styles/guestReservations.css"

function GuestReservations() {
    const [reservations, setReservations] = useState([]);
    const user = getSessionStorage('user', null);

    useEffect(() => {
        renderReservations();
    }, []);

    const displayReservations = () => {
        return (
            user != null ? 
            reservations.length !== 0 ? 
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
            <p className="center-text">You have no reservations</p> :
            <p className="center-text">You are not logged in</p>
        )
    }

    const [isLoading, setLoading] = useState(true);
    // Fetch all of the available rooms
    function renderReservations() {
        if(user) {
            fetch('http://localhost:8080/reservation/getReservations', {
                mode: 'cors',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user.username)
            })
            .then(response => response.json())
            .then(data => {
                // Set the array of reservations
                setReservations(data);
            })
            .catch(error => console.error('Error creating room array:', error));
        }
        setLoading(false);
    }

    return (
        <div>
            <GuestNavigation />
            <div className="content">
                <h1 className="center-text content-start">My Reservations</h1>
                <div>
                    {isLoading ? <p className="center-text">Loading...</p> : displayReservations()}
                </div>
            </div>
        </div>
    );

    
}

export default GuestReservations;