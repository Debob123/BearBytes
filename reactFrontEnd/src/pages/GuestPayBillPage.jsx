import GuestNavigation from '../components/GuestNavigation';
import './styles/guestMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCreditCard } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';

function GuestMyBillPage() {
  const billTotal = getSessionStorage('billTotal', 0.0);

  function validateCard()  {
    const regex = /^\d+$/;
    let name = document.forms["card_form"]["card_name"].value;
    let number = document.forms["card_form"]["card_num"].value;
    let cvv = document.forms["card_form"]["card_cvv"].value;
    let exp = document.forms["card_form"]["card_exp"].value;

    number = number.replace(/\s/g, '');
    cvv = cvv.replace(/\s/g, '');

    if(name == null || name == "")  {
      setWarning(true);
    }

    if(number == null || number.length != 16 || !(regex.test(number)))  {
      setWarning(true);
    }

    if(cvv == null || cvv.length != 3 || !(regex.test(cvv)))  {
      setWarning(true);
    }

    if(exp == null || exp.length != 5 || !(regex.test(exp)))  {
      setWarning(true);
    }


    if(!warning)  {
      // continue to confirmation page
    }
  }

  const [warning, setWarning] = useState(false);
  
  const toggleWarning = () => {
    setWarning(!warning);
  };

  return (
    <div>
      <GuestNavigation />
      <div className="white-space-fix">ignore</div>
      <div className="pay-bill-container">
        <p className="bill-total-text">Bill Total: ${billTotal.toFixed(2)}</p>
        <p className="pay-with-card-text">Pay with card <FontAwesomeIcon icon={faCreditCard} /></p> 
        <form className="bill-form" name="card_form">
          <div className="payment-method-container">
            <input type="radio" id="credit" name="payment" value="Credit" />
            <label for="credit">Credit</label>
            <input type="radio" id="debit" name="payment" value="Debit" />
            <label for="debit">Debit</label>
          </div>
          <div className="text-fields">
            <label> Name on card:</label><br />
            <input type="text" placeholder="name on card" name="card_name"></input><br />
            <label> Card number: </label><br />
            <input type="text" placeholder="**** **** **** ****" name="card_num"></input><br />
            <label> CVV: </label><br />
            <input type="text" placeholder="***" name="card_cvv"></input><br />
            <label> Expiration date: </label><br />
            <input type="text" placeholder="MM/YY" name="card_exp"></input><br />
          </div>
        </form>
        <button className="pay-button" onClick={validateCard}>Pay</button>
        {warning ? 
        <p className="valid-info-warning">Please enter valid info</p>
        : <></>}
        
      </div>
    </div>
  )
}

export default GuestMyBillPage;