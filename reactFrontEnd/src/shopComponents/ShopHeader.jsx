import './ShopHeader.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping} from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import { useState } from 'react'

function ShopHeader()  {

  const [inputValue, setInputValue] = useState('');

  const handleEnterChange = (e) =>  {
    if(e.key === 'Enter')  {
      sessionStorage.setItem('searchBarString', inputValue);
      window.location.reload();
    }
  }

  const handleInputChange = (e) =>  {
    setInputValue(e.target.value);
  }

  return(

    <div className="container-shop-header">
      <p className="shop-text">Shop</p>
      <ul>
        <li>
            <input type="text" placeholder="Search Products" className="search-bar" value={inputValue} onKeyDown={handleEnterChange} onChange={handleInputChange}></input>
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