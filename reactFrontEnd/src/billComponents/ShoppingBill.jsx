import './bill.css'
import { useState } from 'react'
import { useEffect } from 'react'
import getSessionStorage from '../authentication/GetSessionStorage';

function ShoppingBill()  {

  const [orders, setOrders] = useState([]);
  const user = getSessionStorage('user', null);

  useEffect(() => {
    if(user !== null)  {
      renderOrders();
    }
  }, []);

  function renderOrders() {
      let body = JSON.stringify(user.username);

      fetch('http://localhost:8080/shop/getOrders', {
      mode: 'cors',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
        body:body
      })
      .then(response => response.json())
      .then(data => {
        setOrders(data);
      })
  }

  const shoppingSubtotal = orders.reduce((sum, {subtotal}) => sum + subtotal, 0);
  const shoppingTax = shoppingSubtotal * 0.08;
  const shoppingTotal = shoppingSubtotal + shoppingTax;
  //sessionStorage.setItem('shoppingTotal', shoppingTotal);


  return (
    <div>
      <div className="bill-container">
        <p className="category-text">Shopping Purchases</p>
        
        {orders.map((order) => (
          <>
            <p>Purchase Date: {order.purchaseDate}</p>
            <ul>
              {order.purchasedProducts.map((product) =>(
                <li className="purchased-products">
                  <p className="product-name-text">-{product.name}</p>
                  <p className="product-price-text">${product.price.toFixed(2)}</p>
                </li>
              ))}
            </ul>
          </>
        ))}
        <p>Subtotal: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${shoppingSubtotal.toFixed(2)}</p>
        <p>Tax: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + ${shoppingTax.toFixed(2)}</p>
        <p>Shopping Total: ${shoppingTotal.toFixed(2)}</p>
      </div>
    </div>
  )
}

export default ShoppingBill;