import React, { useState, useEffect } from 'react';

function RoomPage() {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        // Fetch rooms from API and setRooms
    }, []);

    return (
        <div>
            <h1>Room Page</h1>
            {rooms.map((room) => (
                <div key={room.id}>
                    <h2>{room.name}</h2>
                    <p>{room.description}</p>
                </div>
            ))}
        </div>
    );
}

export default RoomPage;