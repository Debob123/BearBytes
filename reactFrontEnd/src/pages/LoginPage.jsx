import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './styles/loginPage.css'

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        // Request validation of the guest account through API
        fetch('http://localhost:8080/accounts/guestlogin', {
        mode: 'cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({username: username, password: password}),
      })
      .then(response => response.json())
      .then(data => {
        // Check if the guest was authenticated
        if(data) {
            // If the guest was authenticated, save the username to the session info
            // so it is accessible on other pages (this will be needed for associating
            // reservations and similar items)
            sessionStorage.setItem('user', JSON.stringify({username: username}));
            navigate("/roomSearch");
        }
      })
      .catch(error => console.error('Error logging in user:', error));
    }

    return (
        <div className="login-container">
            <h1 className="center-text main-title">Stay and Shop</h1>
            <form className="login-form" onSubmit={handleSubmit}>
                <p className="center-text">login</p>
                <div className="login-row">
                    <div>
                        <input type="radio" id="guest" name="account_type" value="guest" defaultChecked/>
                        <label htmlFor="guest">Guest</label>
                    </div>
                    <div>
                        <input type="radio" id="staff" name="account_type" value="staff" />
                        <label htmlFor="staff">Staff</label>
                    </div>
                    <div>
                        <input type="radio" id="admin" name="account_type" value="admin" />
                        <label htmlFor="admin">Admin</label>
                    </div>
                </div>
                <div className="login-row">
                    <label htmlFor="username">Username:</label>
                    <input type="text" 
                    id="username" 
                    name="username" 
                    value={username}
                    onChange={(e) => setUsername(e.target.value)} 
                    placeholder="Username" />
                </div>
                <div className="login-row">
                    <label htmlFor="pwd">Password:</label>
                    <input type="password" 
                    id="pwd" 
                    name="pwd" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    placeholder="Password" />
                </div>
                <button className="login-submit" type="submit">Login</button>
                <p>New Guest? <Link to="/registerGuest">Create Account</Link></p>
            </form>
        </div>
    );
}

export default LoginPage;