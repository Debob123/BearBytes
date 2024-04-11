import { v4 as uuidv4 } from 'uuid';
import CartBoxDisplay from './CartBoxDisplay.jsx';
import '../components/display.css';
import './cart.css';
import vase from '../images/vase.jpg'
import beachHat from '../images/beach-hat.jpg'
import beachTowels from '../images/beach-towels.jpg'
import sunglasses from '../images/sunglasses.jpg'
import tropicalShirt from '../images/tropical-shirt.jpg'
import sharkNecklace from '../images/shark-necklace.jpg'
import shellBracelet from '../images/seashell-bracelet.jpg'
import umbrella from '../images/beach-umbrella.jpg'
import Button from '../components/Button.jsx';
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


  return(
    <div className="container-cart">
      <div className="cart-products">
        {cart !== null ? cart.map((product) =>(
          <CartBoxDisplay key={product.id} imgLink={product.imgLink} title={product.name} cost={product.price} btn={button}/>
          )) : true}
      </div>
      <div className="order-summary">
        <p>Subtotal:    ${cartSubtotal.toFixed(2)}</p>
        <p>Tax:         ${cartTax.toFixed(2)}</p>
        <p>Order Total: ${cartTotal.toFixed(2)}</p>
        <p>Purchase will be added to your bill and be delivered to Room {roomNum}</p>
        <button>Checkout</button>
      </div>
    </div>
  );

}

export default CartDisplay;