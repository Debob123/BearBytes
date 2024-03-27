import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './loginPage.css'

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        // Handle login logic here
    };

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
                    <input type="text" id="username" name="username" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" />
                </div>
                <div className="login-row">
                    <label htmlFor="pwd">Password:</label>
                    <input type="password" id="pwd" name="pwd" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
                </div>
                <button className="login-submit" type="submit">Login</button>
                <p>New Guest? <Link>Create Account</Link></p>
            </form>
        </div>
    );
}

export default LoginPage;