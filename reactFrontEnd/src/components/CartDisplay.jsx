import { v4 as uuidv4 } from 'uuid';
import CartBoxDisplay from './CartBoxDisplay.jsx';
import './display.css';
import './cart.css';
import vase from '../images/vase.jpg'
import beachHat from '../images/beach-hat.jpg'
import beachTowels from '../images/beach-towels.jpg'
import sunglasses from '../images/sunglasses.jpg'
import tropicalShirt from '../images/tropical-shirt.jpg'
import sharkNecklace from '../images/shark-necklace.jpg'
import shellBracelet from '../images/seashell-bracelet.jpg'
import umbrella from '../images/beach-umbrella.jpg'
import Button from './Button';
const button = <Button text="Remove from Cart"/>

const test = [
  {
      imgLink: tropicalShirt,
      name: "Tropical Shirt",
      price: "$25",
      id: uuidv4()
  },
  {
      imgLink: sharkNecklace,
      name: "Shark Necklace",
      price: "$15",
      id: uuidv4()
  },
  {
      imgLink: vase,
      name: "Locally Made Vases",
      price: "$50",
      id: uuidv4()
  }]
  

  

function CartDisplay() {

  function getSubTotal()  {
    return 0;
  }
  function getTax()  {
    return 0;
  }
  function getTotal() {
    return 0;
  }
  function getRoomNum()  {
    return 111;
  }
  const cartSubTotal = getSubTotal();
  const cartTax = getTax();
  const cartTotal = getTotal();
  const roomNum = getRoomNum();
  const cart = JSON.parse(sessionStorage.getItem('cart'));

  return(
    <div className="container-cart">
      <div className="cart-products">
        {cart.map((product) =>(
          <CartBoxDisplay key={product.id} imgLink={product.imgLink} title={product.name} cost={product.price} btn={button}/>
          ))}
      </div>
      <div className="order-summary">
        <p>Subtotal:    ${cartSubTotal}</p>
        <p>Tax:         ${cartTax}</p>
        <p>Order Total: ${cartTotal}</p>
        <p>Purchase will be added to your bill and be delivered to Room {roomNum}</p>
        <button>Checkout</button>
      </div>
    </div>
  );

}

export default CartDisplay;