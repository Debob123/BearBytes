import React, { useState } from 'react';
import GuestHeader from '../components/GuestHeader';
import RoomDisplay from '../components/RoomDisplay';
import './reservationPage.css'

function ReservationPage() {
    const [date, setDate] = useState('');
    const [roomId, setRoomId] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        // Handle reservation logic here
    };

    return (
        <div>
            <GuestHeader />
            <h1 className="content-start">Available rooms</h1>
            <RoomDisplay />
        </div>
    );
}

export default ReservationPage;