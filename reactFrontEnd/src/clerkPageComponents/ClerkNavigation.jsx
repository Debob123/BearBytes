import Button from '../components/Button';
import { useNavigate } from 'react-router-dom';
import './clerkNav.css'

function ClerkNavigation() {
    const navigate = useNavigate();

    const homeRedirect = () => {
        navigate("/clerkHome")
    }

    const roomsRedirect = () => {
        navigate("/clerkRooms")
    }

    const checkInOutRedirect = () => {
        
    }

    const guestReservationsRedirect = () => {
        navigate("/clerkModify");
    }

    const billingRedirect = () => {
        
    }

    const makeReservationRedirect = () => {

    }

    const registerGuestRedirect = () => {
        navigate("/clerkRegisterGuest")
    }

    return (
        <div className="clerk-nav">
            <Button text="Home" onClick={homeRedirect} height="4vh"/>
            <Button text="View Rooms" onClick={roomsRedirect} height="4vh"/>
            <Button text="Check In/Out" onClick={checkInOutRedirect} height="4vh"/>
            <Button text="Modify Reservations" onClick={guestReservationsRedirect} height="6vh"/>
            <Button text="Guest Billing" onClick={billingRedirect} height="4vh"/>
            <Button text="Reserve Room" onClick={makeReservationRedirect} height="6vh"/>
            <Button text="Register Guest" onClick={registerGuestRedirect} height="6vh"/>
        </div>
    )
}

export default ClerkNavigation;