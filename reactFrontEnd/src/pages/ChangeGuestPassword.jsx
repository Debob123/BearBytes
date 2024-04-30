import React, { useState } from "react";
import ClerkNavigation from "../clerkPageComponents/ClerkNavigation";

function ChangeGuestPassword() {

    const [username, setUsername] = useState('');
    const [message, setMessage] = useState('');
    const [messageType, setMessageType] = useState('');

    const handleChangePassword = (e) => {
        e.preventDefault();
        setUsername()

        fetch('http://localhost:8080/accounts/changeGuestPassword?newPassword=password', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: username, password: '' }),
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    setMessage('Password successfully changed to: password');
                    setMessageType('success');
                } else {
                    setMessage('Password reset failed. Please try again.');
                    setMessageType('danger');
                }
            })
            .catch(error => { console.error('Error resetting Password'); });
    };

    return (
        <div>
            <ClerkNavigation />
            <div>
                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <h1 style={{ fontSize: '4em' }}>Change Guest Password</h1>
                </div>
                <div>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                        <p>Enter a guest username and press the button to reset their password to: 'password'</p>
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                        <input
                            type="text"
                            placeholder="Guest Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            style={{ width: '200px' }}
                        />
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                        <button style={{ width: '150px' }} onClick={handleChangePassword}>Reset Password</button>
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                        {message && <div className={`alert alert-${messageType}`} role="alert">{message}</div>}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ChangeGuestPassword;