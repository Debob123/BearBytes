import React, { useState } from 'react';

function ReservationPage() {
    const [date, setDate] = useState('');
    const [roomId, setRoomId] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        // Handle reservation logic here
    };

    return (
        <div>
            <h1>Reservation Page</h1>
            <form onSubmit={handleSubmit}>
                <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
                <input type="number" value={roomId} onChange={(e) => setRoomId(e.target.value)} placeholder="Room ID" />
                <button type="submit">Reserve</button>
            </form>
        </div>
    );
}

export default ReservationPage;