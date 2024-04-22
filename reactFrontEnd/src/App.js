import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import ReservationPage from './pages/ReservationPage';
import ShoppingPage from './pages/ShoppingPage';
import ProfilePage from './pages/ProfilePage';
import ConfirmReservationPage from './pages/ConfirmReservationPage'
import ClerkRooms from './pages/ClerkRoomPage'
import Checkout from './pages/CheckoutPage'
import RoomSearch from './pages/RoomSearch'
import PurchaseConfirmationPage from './pages/PurchaseConfirmationPage';
import RegisterGuest from './pages/RegisterGuest';
import RegistrationSuccess from './pages/RegistrationSuccess';
import RegistrationFailed from './pages/RegistrationFailed';
import GuestReservations from './pages/GuestReservations';
import GuestMyBillPage from './pages/GuestMyBillPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path='/roomSearch' element={<RoomSearch />} />
        <Route path="/reservation" element={<ReservationPage />} />
        <Route path="/confirmReservationPage" element ={<ConfirmReservationPage />} />
        <Route path="/shop" element={<ShoppingPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/clerkRooms" element={<ClerkRooms />} />
        <Route path="/cart" element={<Checkout/>} />
        <Route path="/purchaseConfirmation" element={<PurchaseConfirmationPage/>} />
        <Route path="/registerGuest" element={<RegisterGuest />} />
        <Route path="/registrationSuccess" element={<RegistrationSuccess />} />
        <Route path="/registrationFailed" element={<RegistrationFailed/>}/>
        <Route path="/guestReservations" element={<GuestReservations />}/>
        <Route path="/bill" element={<GuestMyBillPage />}/>
      </Routes>
    </Router>
  );
}

export default App;