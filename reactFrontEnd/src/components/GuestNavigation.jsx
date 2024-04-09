import getSessionStorage from '../authentication/GetSessionStorage';
import { useNavigate } from 'react-router-dom';
import Button from "./Button"
import './navigation.css'

function GuestNavigation() {
    const user = getSessionStorage('user', null);
    const navigate = useNavigate();

    const shopRedirect = () => {
        navigate("/shop")
    }

    const reservationRedirect = () => {
        navigate("/roomSearch")
    }

    const myReservationsRedirect = () => {
        navigate("/guestReservations")
    }

    const myBillRedirect = () => {
        navigate("/bill")
    }

    return (
        <div>
            <div className="nav">
                <p className="profile">{user ? "Welcome " + user.username : "Error loading profile"}!</p>
                <Button text="Shop" onClick={shopRedirect} height="4vh"/>
                <Button text="Reserve Room" onClick={reservationRedirect} height="4vh"/>
                <Button text="My Reservations" onClick={myReservationsRedirect} height="4vh"/>
                <Button text="My Bill" onClick={myBillRedirect} height="4vh"/>
            </div>
            <div class="reservations-display">
                <h1 className="center-text content-start">My Reservations</h1>
                <div>
                    hello
                </div>
            </div>
        </div>
    )
}

export default GuestNavigation;