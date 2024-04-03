import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
    return (
        <div>
            <h1>Welcome to Stay & Shop Reservation System</h1>
            <p>Experience the unique combination of comfortable stay and exclusive shopping experience.</p>
            <Link to="/login">Login </Link>
            <Link to="/shop"> Shop Now </Link>
            <Link to="/clerkRooms"> Clerk Rooms </Link>
            <Link to="/registerGuest"> Create Guest Account</Link>
        </div>
    );
}

export default HomePage;