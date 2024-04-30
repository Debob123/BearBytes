import ReservationBill from '../billComponents/ReservationBill';
import ShoppingBill from '../billComponents/ShoppingBill';
import CancelationBill from '../billComponents/CancelationBill';
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
  const [billedReservations, setBilledReservations] = useState([]);
  const [billedOrders, setBilledOrders] = useState([]);

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
        setBilledReservations(data.reservations);
        setBilledOrders(data.orders)
      })
  }

  const navigate = useNavigate();
  const payBillRedirect = () => {
    navigate("/bill/paybill")
  }

  sessionStorage.setItem('billTotal', bill.billTotal);

  return(
    <div>
      <GuestNavigation />
      <div className="white-space-fix">ignore</div>
      <div className="my-bill-container">
        <p className="your-bill-text">Your Bill</p>
        {user != null ? 
        <>
          <ReservationBill billedReservations={billedReservations} bill={bill}/>
          <CancelationBill cancelledReservations={cancelledReservations} bill={bill} />
          <ShoppingBill billedOrders={billedOrders} bill={bill} />
          <div className="pay-container">
            <p className="total-bill-text">Total Bill: ${(Math.round(bill.billTotal * 100) / 100).toFixed(2)}</p>
            <button className="pay-bill-button" onClick={payBillRedirect}>
            <FontAwesomeIcon className="invoice-icon" icon={faFileInvoiceDollar} /> Pay Bill
            </button>
          </div>
        </> : 
        <p className="sign-in-text">Please sign in <a href="/login"n>here</a> to see bill</p>}
      </div>
    </div>
  )
}

export default GuestMyBillPage;