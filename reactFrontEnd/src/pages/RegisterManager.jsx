import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "./styles/registerManager.css"

function RegisterManager() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        // Request manager account creation through API
        fetch('http://localhost:8080/accounts/createManager', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
                address: address,
                name: name
            }),
        })
            .then(response => response.json())
            .then(data => {
                // Check if the manager account was added
                if(data) {
                    // If the manager was created, navigate back to login page
                    navigate("/registrationSuccess");
                } else {
                    navigate("/registrationFailed");
                }
            })
            .catch(error => console.error('Error creating manager account:', error));
    }

    return (
        <div className="create-container">
            <h1 className="center-text main-title">Stay and Shop</h1>
            <form className="create-form" onSubmit={handleSubmit}>
                <p className="center-text">Create account</p>
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
                <div className="create-row">
                    <label htmlFor="name">Name: </label>
                    <input type="text"
                           id="name"
                           name="name"
                           value={name}
                           onChange={(e) => setName(e.target.value)}
                           placeholder="Full name"
                           required />
                </div>
                <div className="create-row">
                    <label htmlFor="address">Address: </label>
                    <input type="text"
                           id="address"
                           name="address"
                           value={address}
                           onChange={(e) => setAddress(e.target.value)}
                           placeholder="Address"
                           required />
                </div>
                <button
                    className="create-submit"
                    type="submit"
                    disabled={!address || !password || !username || !name}>Create Account</button>
            </form>
        </div>
    );
}

export default RegisterManager;