import React, {useState} from "react";

function ChangeGuestPassword() {

    const [username, setUsername] = useState('');
    const [message, setMessage] = useState('');

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
                    setMessage('Password changed to: password');
                } else {
                    setMessage('Password reset failed');
                }
            })
            .catch(error => { console.error('Error resetting Password'); });
    };

    return (
        <div>
            <h1>Change Guest Password</h1>
            <div>
                <input
                    type="text"
                    placeholder="Guest Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <button onClick={handleChangePassword}>Reset Password</button>
            </div>
            {message && <p>{message}</p>}
        </div>
    );
}

export default ChangeGuestPassword;