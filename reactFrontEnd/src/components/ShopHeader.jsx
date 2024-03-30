import './ShopHeader.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping} from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

function ShopHeader()  {
  return(

    <div className="container-shop-header">
      <p>Shop</p>
      <ul>
        <li>
          <form action="#" >
          <input type="text" placeholder="Search Products" name="search" className="search-bar" ></input>
          </form>
        </li>
        <li>
          <button className="cart-button"><FontAwesomeIcon className="cart-icon" icon={faCartShopping} /></button>
        </li>
      </ul>
    </div>

    
  );
}

export default ShopHeader