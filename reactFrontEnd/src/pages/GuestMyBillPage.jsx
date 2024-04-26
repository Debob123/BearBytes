import ReservationBill from '../billComponents/ReservationBill';
import ShoppingBill from '../billComponents/ShoppingBill';
import GuestHeader from '../components/GuestHeader';
import './styles/guestMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { faFileInvoiceDollar} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useNavigate } from 'react-router-dom';


function GuestMyBillPage()  {
  const user = getSessionStorage('user', null);

  function calculateBillTotal()  {
    let billTotal = getSessionStorage('shoppingTotal', 0.0) + getSessionStorage('reservationTotal', 0.0);
    sessionStorage.setItem('billTotal', billTotal);
    return billTotal;
  }

  const navigate = useNavigate();
  const payBillRedirect = () => {
    navigate("/bill/paybill")
  }

  return(
    <div>
      <GuestHeader />
      <p className="your-bill-text">Your Bill</p>
      {user !== null ? 
      <>
        <ReservationBill />
        <ShoppingBill />
        <p className="total-bill-text">Total Bill: {calculateBillTotal().toFixed(2)}</p>
        <button className="pay-bill-button" onClick={payBillRedirect}>
          <FontAwesomeIcon className="cart-icon" icon={faFileInvoiceDollar} /> Pay Bill
        </button>
      </> : 
      <p className="sign-in-text">Please sign in <a href="/login"n>here</a> to see bill</p>}
    </div>
  )
  
}

export default GuestMyBillPage;