import '../pages/styles/guestMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { faFileInvoiceDollar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

function BillTotal() {
  let billTotal = 0.0;

  const navigate = useNavigate();
  const payBillRedirect = () => {
    navigate("/bill/paybill")
  }

  let bill = JSON.parse(sessionStorage.getItem('bill'));
  billTotal = bill.billTotal;

  return (
    <div>
      <p className="total-bill-text">Total Bill: {billTotal.toFixed(2)}</p>
      <button className="pay-bill-button" onClick={payBillRedirect}>
        <FontAwesomeIcon className="cart-icon" icon={faFileInvoiceDollar} /> Pay Bill
      </button>
    </div>
  )

}

export default BillTotal;