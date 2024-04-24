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

    return (
        <div className="nav">
            <p className="profile">{user ? "Welcome " + user.username : "Error loading profile"}!</p>
            <Button text="Manager Home" onClick={managerHomeRedirect} height="6vh"/>
        </div>
    )
}

export default ManagerNavigation;