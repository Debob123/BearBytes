import getSessionStorage from '../authentication/GetSessionStorage';
import { useNavigate } from 'react-router-dom';
import Button from "../components/Button"
import '../components/navigation.css'

function ManagerNavigation() {
    const user = getSessionStorage('user', null);
    const navigate = useNavigate();

    const managerHomeRedirect = () => {
        navigate("/managerHome")
    }

    const profileRedirect = () => {
        navigate("/managerProfile")
    }

    const registerManagerRedirect = () => {
        navigate("/registerManager")
    }

    const logoutManager = () => {
        sessionStorage.removeItem('user');
        navigate("/");
    }

    return (
        <div className="nav">
            <p className="profile">{user ? "Welcome " + user.username : "Error loading profile"}!</p>
            <Button text="Manager Home" onClick={managerHomeRedirect} height="6vh" />
            <Button text="Edit Profile" onClick={profileRedirect} height="4vh" />
            <Button text="Register Manager" onClick={registerManagerRedirect} height="4vh" />
            <Button text="Logout" onClick={logoutManager} height="4vh" />
        </div>
    )
}

export default ManagerNavigation;