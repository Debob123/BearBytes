import React, { useState } from 'react';
import getSessionStorage from "../authentication/GetSessionStorage";
import ManagerNavigation from "../managerPageComponents/ManagerNavigation";

function ChangeCredentials() {
    let username = getSessionStorage('user').username;
    const account_type = getSessionStorage('account_type').account_type;
    const [password, setPassword] = useState('p');
    const [newUsername, setNewUsername] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage] = useState('');



    const handleChangeUsername = (e) => {
        e.preventDefault();
        let type = "";

        switch (account_type) {
            case "guest":
                type = "Guest";
                break;
            case "clerk":
                type = "Clerk";
                break;
            case "manager":
                type = "Manager";
                break;
            default:
                type = "account_type_error"
                break;
        }

        fetch('http://localhost:8080/accounts/change'+type+'Username?newUsername=' + newUsername, {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: username, password: password }),
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    sessionStorage.setItem('user', JSON.stringify({username: newUsername}));
                    username = newUsername;
                    setMessage('Username changed successfully');
                } else {
                    setMessage('Username change failed');
                }
            })
            .catch(error => { console.error('Error changing username'); });
    };

    const handleChangePassword = (e) => {
        e.preventDefault();
        let type = "";

        switch (account_type) {
            case "guest":
                type = "Guest";
                break;
            case "clerk":
                type = "Clerk";
                break;
            case "manager":
                type = "Manager";
                break;
            default:
                type = "account_type_error"
                break;
        }

        fetch('http://localhost:8080/accounts/change'+type+'Password?newPassword=' + newPassword, {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: username, password: password }),
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    setMessage('Password changed successfully');
                } else {
                    setMessage('Password change failed');
                }
            })
            .catch(error => { console.error('Error changing Password'); });
    };

    return (
        <div>
            <ManagerNavigation />
            <h1>Change Credentials</h1>
            <div>
                <h2>Change Username</h2>
                <input
                    type="text"
                    placeholder="New Username"
                    value={newUsername}
                    onChange={(e) => setNewUsername(e.target.value)}
                />
                <button onClick={handleChangeUsername}>Change Username</button>
            </div>
            <div>
                <h2>Change Password</h2>
                <input
                    type="text"
                    placeholder="New Password"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                />
                <button onClick={handleChangePassword}>Change Password</button>
            </div>
            {message && <p>{message}</p>}
        </div>
    );
}

export default ChangeCredentials;
