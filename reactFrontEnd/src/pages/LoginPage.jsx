import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './styles/loginPage.css'

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginFailed, setLoginFailed] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        const accountType = document.querySelector('input[name="account_type"]:checked').value;
        let nextPage = "";
        // construct endpoint based on the selected account type
        switch (accountType) {
            case "guest":
                nextPage = "/guestReservations";
                break;
            case "clerk":
                nextPage = "/clerkHome";
                break;
            case "manager":
                nextPage = "/managerHome";
                break;
            default:
                break;
        }
        const endpoint = `http://localhost:8080/accounts/${accountType.toLowerCase()}login`;
        fetch(endpoint, {
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
            sessionStorage.setItem('account_type', JSON.stringify({account_type: accountType}))
            navigate(nextPage);
        } else {
            setLoginFailed("Invalid username or password");
        }
      })
      .catch(error => console.error('Error logging in user:', error));
    }

    return (
        <div className="login-container">
            <h1 className="center-text main-title">Stay and Shop</h1>
            <form className="login-form" onSubmit={handleSubmit}>
                <div className="login-row">
                    <div>
                        <input type="radio" id="guest" name="account_type" value="guest" defaultChecked/>
                        <label htmlFor="guest">Guest</label>
                    </div>
                    <div>
                        <input type="radio" id="staff" name="account_type" value="clerk" />
                        <label htmlFor="clerk">Clerk</label>
                    </div>
                    <div>
                        <input type="radio" id="admin" name="account_type" value="manager" />
                        <label htmlFor="manager">Manager</label>
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
            {loginFailed && <div className="message" style={{ fontWeight: 'bold', color: "red" }}>{loginFailed}</div>}
        </div>
    );
}

export default LoginPage;