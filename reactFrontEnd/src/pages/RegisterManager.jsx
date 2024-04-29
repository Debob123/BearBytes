import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "./styles/registerManager.css"
import ManagerNavigation from "../managerPageComponents/ManagerNavigation";

function RegisterManager() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [color, setColor] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        // Request guest account creation through API
        fetch('http://localhost:8080/accounts/createManager', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        })
            .then(response => response.json())
            .then(data => {
                // Check if the guest account was added
                if(data) {
                    setMessage("Account created successfully");
                    setColor("green");
                    setUsername("")
                    setPassword("")
                } else {
                    setMessage("Account creation failed");
                    setColor("red");
                }
            })
            .catch(error => console.error('Error creating guest account:', error));
    }

    return (
        <div className="create-container">
            <ManagerNavigation />
            <h1 className="center-text main-title">Register Manager</h1>
            <form className="create-form" onSubmit={handleSubmit}>
                <div className="create-row">
                    <label htmlFor="username">Username: </label>
                    <input type="text"
                           id="username"
                           name="username"
                           value={username}
                           onChange={(e) => setUsername(e.target.value)}
                           placeholder="Username"
                           required />
                </div>
                <div className="create-row">
                    <label htmlFor="pwd">Password: </label>
                    <input type="password"
                           id="pwd"
                           name="pwd"
                           value={password}
                           onChange={(e) => setPassword(e.target.value)}
                           placeholder="Password"
                           required />
                </div>
                <button
                    className="create-submit"
                    type="submit"
                    disabled={!password || !username}>Create Account</button>
            </form>
            {message && <div className="message" style={{ fontWeight: 'bold', color: color }}>{message}</div>}
        </div>
    );
}

export default RegisterManager;