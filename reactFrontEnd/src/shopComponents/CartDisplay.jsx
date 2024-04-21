import CartBoxDisplay from './CartBoxDisplay.jsx';
import '../components/display.css';
import './cart.css';
import './modal.css'
import Button from '../components/Button.jsx';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react'
const button = <Button text="Remove from Cart"/>

function CartDisplay() {

  const cart = JSON.parse(sessionStorage.getItem('cart'));
  let cartSubtotal = 0.0;
  let cartTax = 0.0;
  let cartTotal = 0.0;

  if(cart !== null)  {
    cart.forEach((p) =>  {
      cartSubtotal += p.price;
    })
    cartTax = cartSubtotal * 0.08;
    cartTotal = cartSubtotal + cartTax;
  }

  const navigate = useNavigate();

  const confirmationRedirect = () => {
    navigate("/purchaseConfirmation")
  }

  const handleSubmit = (e) => {
    let body = JSON.stringify({
            orderId: 1111,
            purchaseDate: "",
            purchasedProducts: cart,
    })
    console.log(body);
    e.preventDefault();
    // Request validation of the guest account through API
    fetch('http://localhost:8080/shop/add', {
    mode: 'cors',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: body
    },
  )
  .then(response => response.json())
  .then(data => {
    // Check if the guest was authenticated
    if(data) {
        // If the guest was authenticated, save the username to the session info
        // so it is accessible on other pages (this will be needed for associating
        // reservations and similar items)
    }
  })
  .catch(error => console.error('Error adding order:', error));

  
  sessionStorage.setItem('cart', JSON.stringify([]));
  confirmationRedirect();
}

  // Confirmation pop-up
  const [modal, setModal] = useState(false);
  
  const toggleModal = () => {
    setModal(!modal);
  };

  return(
    <>
    <div className="container-cart">
      <div className="cart-products">
        {cart !== null ? cart.map((product) =>(
          <CartBoxDisplay key={product.id} id={product.id} image={product.image} title={product.name} cost={product.price} btn={button}/>
          )) : true}
      </div>
      <div className="order-summary">
        <p className="subtotal-text">Subtotal:       ${cartSubtotal.toFixed(2)}</p>
        <p className="tax-test">+ Tax:               ${cartTax.toFixed(2)}</p>
        <p className="order-total-text">Order Total: ${cartTotal.toFixed(2)}</p>
        <p>Purchase will be added to your bill and delivered to your room</p>
        { (cart !== null) && (cart.length !== 0) ?
          <button onClick={toggleModal} className="btn-modal">Checkout</button> :
         <button className="btn-modal-unavailable">Checkout</button>}
      </div>
    </div>

    {modal && (
      <div className="modal">
        <div onClick={toggleModal} className="overlay"></div>
        <div className="modal-content">
          <div className="modal-title">Purchase Confirmation</div>
          <div className="modal-body">Purchase will be added to your bill to be payed after your stay, confirm?</div>
          <div className="buttons">
            <button onClick={toggleModal} className="close-modal-button">cancel</button>
            <button className="confirm-button" onClick={handleSubmit}>confirm</button>
          </div>
        </div>
      </div>
    )}
    </>
  );
}


export default CartDisplay;