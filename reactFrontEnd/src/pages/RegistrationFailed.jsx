import Icon from '@mdi/react';
import { mdiAlphaXCircleOutline } from '@mdi/js';
import './styles/accountRegistration.css'
import {useNavigate } from 'react-router-dom';

function RegistrationFailed() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate('/registerGuest');
    }

    return (
        <div className="result-container">
            <div className="flex-result">
                <Icon path={mdiAlphaXCircleOutline} size={10} color="red" className="center-item"/>
                <h1 className="red center-item">Failed</h1>
            </div>
            <div className="flex-result">
                <p className="success-text red center-item">Sorry, the username you entered is already taken, please try another</p>
                <button onClick={handleClick} className="retry-registration">Retry</button>
            </div>
        </div>
    )
}

export default RegistrationFailed;