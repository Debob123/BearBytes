import getSessionStorage from '../authentication/GetSessionStorage';
import { useNavigate } from 'react-router-dom';
import Button from "./Button"
import "./header.css"


function GuestHeader() {
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

    const logOut = () => {
        sessionStorage.removeItem('user');
        navigate("/");
    }

    return (
        <div className="header">
            <Button text="Shop" onClick={shopRedirect} />
            <Button text="Reserve Room" onClick={reservationRedirect} />
            <Button text="My Reservations" onClick={myReservationsRedirect} />
            <Button text="My Bill" onClick={myBillRedirect} />
            <Button text="Log Out" onClick={logOut} />
            <p className="profile">{user ? "Welcome " + user.username : "Error loading profile"}!</p>
        </div>
    )
}

export default GuestHeader
