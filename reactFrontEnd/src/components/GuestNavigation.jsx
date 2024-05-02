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
    const myPointsRedirect = () => {
        navigate("/")
    }

    const logOut = () => {
        if (window.confirm("Are you sure you want to log out?")) {
            sessionStorage.removeItem('user');
            navigate("/");
        }
    }




    return (
        <div className="nav">
            <p className="profile">{user ? "Welcome " + user.username : "Error loading profile"}!</p>
            <Button className="button" text="Log Out" onClick={logOut} height="4vh" />
            <Button className="button" text="Shop" onClick={shopRedirect} height="4vh" />
            <Button className="button" text="Reserve Room" onClick={reservationRedirect} height="6vh" />
            <Button className="button" text="My Reservations" onClick={myReservationsRedirect} height="6vh" />
            <Button className="button" text="My Bill" onClick={myBillRedirect} height="4vh" />

        </div>
    )
}

export default GuestNavigation;