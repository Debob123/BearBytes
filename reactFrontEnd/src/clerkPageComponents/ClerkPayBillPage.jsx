import React, { useState } from 'react';
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation';
import '../pages/styles/clerkMyBill.css';
import getSessionStorage from '../authentication/GetSessionStorage';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCreditCard } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';

function ClerkPayBillPage() {
  const billTotal = getSessionStorage('clerkBillTotal', 0.0);
  const [nameOnCard, setNameOnCard] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [cvv, setCVV] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [paymentMethod, setPaymentMethod] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!nameOnCard || !cardNumber || !cvv || !expirationDate || !paymentMethod) {
      setError('Please provide all payment information.');
    } else {
      setError('');
      alert('Payment submitted successfully!');
      navigate('/clerkHome');
    }
  };

  return (
    <div>
      <ClerkNavigation />
      <div className="white-space-fix">ignore</div>
      <div className="pay-bill-container">
        <p className="bill-total-text">Bill Total: ${billTotal.toFixed(2)}</p>
        <p className="pay-with-card-text">Pay with card <FontAwesomeIcon icon={faCreditCard} /></p> 
        <form className="bill-form">
          <div className="payment-method-container">
            <input type="radio" id="credit" name="payment" value="Credit" onChange={() => setPaymentMethod('Credit')} />
            <label htmlFor="credit">Credit</label>
            <input type="radio" id="debit" name="payment" value="Debit" onChange={() => setPaymentMethod('Debit')} />
            <label htmlFor="debit">Debit</label>
          </div>
          <label> Name on card:</label><br />
          <input type="text" value={nameOnCard} onChange={(e) => setNameOnCard(e.target.value)} placeholder="name on card"></input><br />
          <label> Card number: </label><br />
          <input type="text" value={cardNumber} onChange={(e) => setCardNumber(e.target.value)} placeholder="**** **** **** ****"></input><br />
          <label> CVV: </label><br />
          <input type="text" value={cvv} onChange={(e) => setCVV(e.target.value)} placeholder="***"></input><br />
          <label> Expiration date: </label><br />
          <input type="text" value={expirationDate} onChange={(e) => setExpirationDate(e.target.value)} placeholder="MM/YY"></input><br />
          <div>
            <button className="pay-bill-button" onClick={handleSubmit}>Submit</button>
          </div>
        </form>
      </div>
      {error && <p className="error-message">{error}</p>}
    </div>
  )
}

export default ClerkPayBillPage;
