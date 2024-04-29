import GuestHeader from '../components/GuestHeader';
import './styles/guestMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCreditCard} from '@fortawesome/free-solid-svg-icons';

function GuestMyBillPage()  {
  const billTotal = getSessionStorage('billTotal', 0.0);

  return(
    <div>
      <GuestHeader />
      <div className="pay-bill-container">
        <p className="bill-total-text">Bill Total: {billTotal}</p>
        <p className="pay-with-card-text">Pay with card <FontAwesomeIcon icon={faCreditCard} /></p> 
        <form className="bill-form">
          <div className="payment-method-container">
            <input type="radio" id="credit" name="payment" value="Credit"/>
            <label for="credit">Credit</label>
            <input type="radio" id="debit" name="payment" value="Debit"/>
            <label for="debit">Debit</label>
          </div>
          <label> Name on card:</label><br/>
          <input type="text" placeholder="name on card"></input><br/>
          <label> Card number: </label><br/>
          <input type="text" placeholder="**** **** **** ****"></input><br/>
          <label> CVV: </label><br/>
          <input type="text" placeholder="***"></input><br/>
          <label> Expiration date: </label><br/>
          <input type="text" placeholder="MM/YY"></input><br/>
          <div>
          </div>
        </form>
      </div>
    </div>
  )
  
}

export default GuestMyBillPage;