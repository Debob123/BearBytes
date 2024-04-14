import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
    return (
        <div>
            <h1>Welcome to Stay & Shop Reservation System</h1>
            <p>Experience the unique combination of comfortable stay and exclusive shopping experience.</p>
            <p><Link to="/login">Login </Link></p>
            <p><Link to="/clerkRooms"> Clerk Rooms </Link></p>
            <p><Link to="/registerGuest"> Create Guest Account</Link></p>
            <p><Link to="/guestReservations">Guest page</Link></p>
        </div>
    );
}

export default HomePage;