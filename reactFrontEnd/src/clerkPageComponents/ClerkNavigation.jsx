import Button from '../components/Button';
import { useNavigate } from 'react-router-dom';
import '../components/navigation.css'
import getSessionStorage from "../authentication/GetSessionStorage";

function ClerkNavigation() {
    const user = getSessionStorage('user', null);
    const navigate = useNavigate();

    const homeRedirect = () => {
        navigate("/clerkHome")
    }

    const roomsRedirect = () => {
        navigate("/clerkRooms")
    }

    const checkInOutRedirect = () => {
        navigate("/clerkCheckInAndOut")
    }

    const guestReservationsRedirect = () => {
        navigate("/clerkModify");
    }

    const billingRedirect = () => {
        navigate("/clerkBill");
    }

    const makeReservationRedirect = () => {
        navigate("/clerkMakeReservation")
    }

    const registerGuestRedirect = () => {
        navigate("/clerkRegisterGuest")
    }

    const profileRedirect = () => {
        navigate("/clerkProfile")
    }

    const guestPasswordResetRedirect = () => {
        navigate("/guestPasswordReset")
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
            <Button className="button" text="View Rooms" onClick={roomsRedirect} height="4vh"/>
            <Button className="button" text="Check In/Out" onClick={checkInOutRedirect} height="4vh"/>
            <Button className="button" text="Modify Reservations" onClick={guestReservationsRedirect} height="6vh"/>
            <Button className="button" text="Guest Billing" onClick={billingRedirect} height="4vh"/>
            <Button className="button" text="Reserve Room" onClick={makeReservationRedirect} height="6vh"/>
            <Button className="button" text="Register Guest" onClick={registerGuestRedirect} height="6vh"/>
            <Button className="button" text="Edit Profile" onClick={profileRedirect} height="4vh"/>
            <Button className="button" text="Guest Password" onClick={guestPasswordResetRedirect} height="4vh"/>
            <Button className="button" text="Log Out" onClick={logOut} height="4vh"/>
        </div>
    )
}

export default ClerkNavigation;