import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RoomPage from './pages/RoomPage';
import ReservationPage from './pages/ReservationPage';
import ShoppingPage from './pages/ShoppingPage';
import ProfilePage from './pages/ProfilePage';
import ConfirmReservationPage from './pages/ConfirmReservationPage'
import ClerkRooms from './pages/ClerkRoomPage'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/rooms" element={<RoomPage />} />
        <Route path="/reservation" element={<ReservationPage />} />
        <Route path="/confirmReservationPage" element ={<ConfirmReservationPage />} />
        <Route path="/shop" element={<ShoppingPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/clerkRooms" element={<ClerkRooms />} />
      </Routes>
    </Router>
  );
}

export default App;