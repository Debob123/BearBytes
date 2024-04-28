import Icon from '@mdi/react';
import { mdiCheckCircleOutline } from '@mdi/js';
import './styles/accountRegistration.css'
import {useNavigate } from 'react-router-dom';

function ManagerRegistrationSuccess() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/managerHome");
    }

    return (
        <div className="result-container">
            <div className="flex-result">
                <Icon path={mdiCheckCircleOutline} size={10} color="green" className="center-item"/>
                <h1 className="green center-item">Success</h1>
            </div>
            <div className="flex-result">
                <p className="success-text green center-item">Congratulations, your account has been successfully created</p>
                <button onClick={handleClick} className="continue-login">Continue</button>
            </div>
        </div>
    )
}

export default ManagerRegistrationSuccess;