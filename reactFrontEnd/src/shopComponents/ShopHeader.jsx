import './ShopHeader.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping} from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

function ShopHeader()  {
  return(

    <div className="container-shop-header">
      <p className="shop-text">Shop</p>
      <ul>
        <li>
          <form action="#" >
            <input type="text" placeholder="Search Products" name="search" className="search-bar" ></input>
          </form>
        </li>
        <li>
          <Link to="/cart">
            <FontAwesomeIcon className="cart-icon" icon={faCartShopping} />
          </Link>
        </li>
      </ul>
    </div>

    
  );
}

export default ShopHeader