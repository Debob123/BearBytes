import { v4 as uuidv4 } from 'uuid';
import CartBoxDisplay from './CartBoxDisplay.jsx';
import '../components/display.css';
import './cart.css';
import Button from '../components/Button.jsx';
import { useNavigate } from 'react-router-dom';
const button = <Button text="Remove from Cart"/>

function CartDisplay() {

  const roomNum = getRoomNum();
  const cart = JSON.parse(sessionStorage.getItem('cart'));
  let cartSubtotal = 0.0;
  let cartTax = 0.0;
  let cartTotal = 0.0;

    cart.forEach((p) =>  {
      cartSubtotal += p.price;
    })
    cartTax = cartSubtotal * 0.08;
    cartTotal = cartSubtotal + cartTax;
    
  function getRoomNum()  {
    return 111;
  }

  const navigate = useNavigate();

  const confirmationRedirect = () => {
    navigate("/purchaseConfirmation")
  }

  const handleSubmit = (e) => {
    let body = JSON.stringify({
            orderId: 1111,
            purchaseDate: "2012-04-05",
            purchasedProducts: cart,
    })
    e.preventDefault();
    // Request validation of the guest account through API
    fetch('http://localhost:8080/shop/addOrder', {
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


  return(
    <div className="container-cart">
      <div className="cart-products">
        {cart !== null ? cart.map((product) =>(
          <CartBoxDisplay key={product.id} id={product.id} image={product.image} title={product.name} cost={product.price} btn={button}/>
          )) : true}
      </div>
      <div className="order-summary">
        <p>Subtotal:    ${cartSubtotal.toFixed(2)}</p>
        <p>Tax:         ${cartTax.toFixed(2)}</p>
        <p>Order Total: ${cartTotal.toFixed(2)}</p>
        <p>Purchase will be added to your bill and be delivered to Room {roomNum}</p>
        <button onClick={handleSubmit}>Checkout</button>
      </div>
    </div>
  );

}

export default CartDisplay;