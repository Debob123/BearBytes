import React, { useState } from 'react';
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation';
import ReservationBill from '../billComponents/ReservationBill';
import CancelationBill from '../billComponents/CancelationBill';
import ShoppingBill from '../billComponents/ShoppingBill';
import '../pages/styles/clerkMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { useEffect } from 'react';
import { faFileInvoiceDollar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useNavigate } from 'react-router-dom';

function ClerkMyBillPage() {
  const [username, setUsername] = useState('');
  const user = getSessionStorage('user', null);
  const [bill, setBill] = useState([]);
  const [cancelledReservations, setCancelledReservations] = useState([]);
  const [billedReservations, setBilledReservations] = useState([]);
  const [billedOrders, setBilledOrders] = useState([]);
  const [error, setError] = useState('');
  const [showBill, setShowBill] = useState(false);

  const navigate = useNavigate();

  const generateBill = () => {
    let body = JSON.stringify(username);

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
        console.log("data: ", data);
        setBill(data);
        setCancelledReservations(data.cancelledReservations);
        setBilledReservations(data.reservations);
        setBilledOrders(data.orders);
        setError('');
        setShowBill(true); // Show the bill after successful fetch
        sessionStorage.setItem('clerkBillTotal', data.billTotal);
      })
      .catch(error => setError('Error fetching bill information.'));
  }

  const handleUsernameSubmit = () => {
    // Verify if the username has a reservation
    fetch('http://localhost:8080/reservation/getReservations', {
      mode: 'cors',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(username)
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        if (data.length > 0) {
          generateBill(); // Generate bill if the username has a reservation
        } else {
          setError('This guest does not have a reservation');
          setShowBill(false); // Hide the bill if username is invalid
        }
      })
      .catch(error => setError('Error verifying reservation.'));
  };

  const payBillRedirect = () => {
    navigate("/clerkBill/paybill")
  }

  return (
    <div>
      <ClerkNavigation />
      <div className="white-space-fix">ignore</div>
      <div className="my-bill-container">
        <p className="your-bill-text">Your Bill</p>
        <div className="username-input">
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter guest username"
          />
          <button onClick={handleUsernameSubmit}>Submit</button>
        </div>
        {error && <p className="error-message">{error}</p>}
        {user !== null && username !== '' && error === '' && showBill && (
          <>
            <ReservationBill billedReservations={billedReservations} bill={bill} />
            <CancelationBill cancelledReservations={cancelledReservations} bill={bill} />
            <ShoppingBill billedOrders={billedOrders} bill={bill} />
            <div className="pay-container">
              <p className="total-bill-text">Total Bill: ${(Math.round(bill.billTotal * 100) / 100).toFixed(2)}</p>
              <button className="pay-bill-button" onClick={payBillRedirect}>
                <FontAwesomeIcon className="invoice-icon" icon={faFileInvoiceDollar} /> Pay Bill
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  )
}

export default ClerkMyBillPage;
