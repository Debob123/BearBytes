import ReservationBill from '../billComponents/ReservationBill';
import ShoppingBill from '../billComponents/ShoppingBill';
import GuestHeader from '../components/GuestHeader';
import GuestNavigation from '../components/GuestNavigation';

import './styles/guestMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { useState, useEffect } from 'react';
import { faFileInvoiceDollar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useNavigate } from 'react-router-dom';


function GuestMyBillPage() {
  const user = getSessionStorage('user', null);
  const [bill, setBill] = useState([]);
  const [cancelledReservations, setCancelledReservations] = useState([]);

  useEffect(() => {
    if (user !== null) {
      generateBill();
    }
  }, []);

  function generateBill() {
    let body = JSON.stringify(user.username);

    fetch('http://localhost:8080/bill/generateBill', {
      mode: 'cors',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: body
    })
      .then(response => response.json())
      .then(data => {
        setBill(data);
        setCancelledReservations(data.cancelledReservations);
      })
  }

  const navigate = useNavigate();
  const payBillRedirect = () => {
    navigate("/bill/paybill")
  }

  sessionStorage.setItem('billTotal', bill.billTotal);
  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <GuestNavigation />
      <p className="your-bill-text">Your Bill</p>
<<<<<<< HEAD
      {user !== null ? 
      <>
        <ReservationBill bill={bill}/>
        <ShoppingBill />
        <p className="total-bill-text">Total Bill: {(Math.round(bill.billTotal * 100) / 100).toFixed(2)}</p>
        <button className="pay-bill-button" onClick={payBillRedirect}>
        <FontAwesomeIcon className="cart-icon" icon={faFileInvoiceDollar} /> Pay Bill
        </button>
      </> : 
      <p className="sign-in-text">Please sign in <a href="/login"n>here</a> to see bill</p>}
=======
      {user !== null ?
        <>
          <ReservationBill />
          <ShoppingBill />
          <p className="total-bill-text">Total Bill: {(Math.round(bill.billTotal * 100) / 100).toFixed(2)}</p>
          <button className="pay-bill-button" onClick={payBillRedirect}>
            <FontAwesomeIcon className="cart-icon" icon={faFileInvoiceDollar} /> Pay Bill
          </button>
        </> :
        <p className="sign-in-text">Please sign in <a href="/login" n>here</a> to see bill</p>}
>>>>>>> b72aac86419750cbf2ad1f70976d1aa4dd8bb302
    </div>
  )

}

export default GuestMyBillPage;