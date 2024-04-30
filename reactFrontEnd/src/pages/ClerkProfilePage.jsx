import React, { useState } from 'react';
import getSessionStorage from "../authentication/GetSessionStorage";
import ClerkNavigation from "../clerkPageComponents/ClerkNavigation";

function ChangeCredentials() {
    let username = getSessionStorage('user').username;
    const account_type = getSessionStorage('account_type').account_type;
    const [password, setPassword] = useState('p');
    const [newUsername, setNewUsername] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage] = useState('');
    const [color, setColor] = useState('');



    const handleChangeUsername = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/accounts/changeClerkUsername?newUsername=' + newUsername, {
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
                    setColor("green");
                } else {
                    setMessage('Username change failed');
                    setColor("red");
                }
            })
            .catch(error => { console.error('Error changing username'); });
    };

    const handleChangePassword = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/accounts/changeClerkPassword?newPassword=' + newPassword, {
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
            <ClerkNavigation />
            <div style={{display: 'flex', justifyContent: 'center'}}>
                <h1 style={{ fontSize: '4em' }}>Change Credentials</h1>
            </div>
            <div>
                <div style={{display: 'flex', justifyContent: 'center', marginTop: '20px'}}>
                    <p>Change your profile's username/password</p>
                </div>
                <div style={{display: 'flex', justifyContent: 'center', marginTop: '20px'}}>
                    <h1>Change Username</h1>
                </div>
                <div style={{display: 'flex', justifyContent: 'center', marginTop: '5px'}}>
                    <input
                        type="text"
                        placeholder="New Username"
                        value={newUsername}
                        onChange={(e) => setNewUsername(e.target.value)}
                        style={{width: '200px'}}
                    />
                </div>
                <div style={{display: 'flex', justifyContent: 'center'}}>
                    <button style={{width: '150px'}} onClick={handleChangeUsername}>Change Username</button>
                </div>
            </div>
            <div>
                <div style={{display: 'flex', justifyContent: 'center', marginTop: '20px'}}>
                    <h1>Change Password</h1>
                </div>
                <div style={{display: 'flex', justifyContent: 'center', marginTop: '5px'}}>
                    <input
                        type="text"
                        placeholder="New Password"
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                        style={{ width: '200px' }}
                    />
                </div>
                <div style={{display: 'flex', justifyContent: 'center'}}>
                    <button style={{ width: '150px' }} onClick={handleChangePassword}>Change Password</button>
                </div>
            </div>
            <div style={{ display: 'flex', color: color, justifyContent: 'center', marginTop: '20px'}}>
                {message && <p>{message}</p>}
            </div>
        </div>
    );
}

export default ChangeCredentials;
