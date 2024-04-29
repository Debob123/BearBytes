import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import ReservationPage from './pages/ReservationPage';
import ShoppingPage from './pages/ShoppingPage';
import ManagerProfilePage from './pages/ManagerProfilePage';
import ConfirmReservationPage from './pages/ConfirmReservationPage'
import ClerkRooms from './pages/ClerkRoomPage'
import Checkout from './pages/CheckoutPage'
import RoomSearch from './pages/RoomSearch'
import PurchaseConfirmationPage from './pages/PurchaseConfirmationPage';
import RegisterGuest from './pages/RegisterGuest';
import RegisterManager from './pages/RegisterManager';
import RegistrationSuccess from './pages/RegistrationSuccess';
import RegistrationFailed from './pages/RegistrationFailed';
import GuestReservations from './pages/GuestReservations';
import GuestMyBillPage from './pages/GuestMyBillPage';
import GuestPayBillPage from './pages/GuestPayBillPage';
import ClerkModifyReservation from './pages/ClerkModifyReservations';
import ClerkHome from "./pages/ClerkHome";
import ManagerHome from "./pages/ManagerHome";
import ClerkRegisterGuest from "./clerkPageComponents/ClerkRegisterGuest";
import ClerkMakeReservation from './pages/ClerkMakeReservation';
import ClerkConfirmReservation from './pages/ClerkConfirmReservation';
import ChangeGuestPassword from "./pages/ChangeGuestPassword";
import ClerkCheckInAndOut from "./pages/ClerkCheckInAndOut";
import ClerkProfilePage from "./pages/ClerkProfilePage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path='/roomSearch' element={<RoomSearch />} />
        <Route path="/reservation" element={<ReservationPage />} />
        <Route path="/confirmReservationPage" element ={<ConfirmReservationPage />} />
        <Route path="/shop" element={<ShoppingPage />} />
        <Route path="/managerProfile" element={<ManagerProfilePage />} />
        <Route path="/clerkProfile" element={<ClerkProfilePage />} />
        <Route path="/clerkRooms" element={<ClerkRooms />} />
        <Route path="/cart" element={<Checkout/>} />
        <Route path="/purchaseConfirmation" element={<PurchaseConfirmationPage/>} />
        <Route path="/registerGuest" element={<RegisterGuest />} />
        <Route path="/registerManager" element={<RegisterManager />} />
        <Route path="/registrationSuccess" element={<RegistrationSuccess />} />
        <Route path="/registrationFailed" element={<RegistrationFailed/>}/>
        <Route path="/guestReservations" element={<GuestReservations />}/>
        <Route path="/bill" element={<GuestMyBillPage />}/>
        <Route path="/bill/paybill" element={<GuestPayBillPage />}/>
        <Route path="/clerkModify" element={<ClerkModifyReservation/>}/>
        <Route path="/clerkHome" element={<ClerkHome/>}/>
        <Route path="/managerHome" element={<ManagerHome/>}/>
        <Route path="/clerkRegisterGuest" element={<ClerkRegisterGuest />} />
        <Route path="/guestPasswordReset" element={<ChangeGuestPassword />} />
        <Route path="/clerkMakeReservation" element={<ClerkMakeReservation/>}/>
        <Route path="/clerkConfirmReservation" element={<ClerkConfirmReservation/>}/>
        <Route path="/clerkCheckInAndOut" element={<ClerkCheckInAndOut/>}/>
      </Routes>
    </Router>
  );
}

export default App;