import './bill.css'
import { useState } from 'react'
import { useEffect } from 'react'

function ShoppingBill()  {

  const [orders, setOrders] = useState([]);

  useEffect(() => {
      renderOrders();
  }, []);

  const [isLoading, setLoading] = useState(true);
  function renderOrders() {
      fetch('http://localhost:8080/shop/getOrders', {
      mode: 'cors',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      })
      .then(response => response.json())
      .then(data => {
      
      setOrders(data);
      setLoading(false);
      })
      .catch(error => console.error('Error creating Order array:', error));
  }

  const shoppingSubtotal = orders.reduce((sum, {subtotal}) => sum + subtotal, 0);
  const shoppingTax = shoppingSubtotal * 0.08;
  const shoppingTotal = shoppingSubtotal + shoppingTax;

  return (
    <div>
      <div className="shopping-bill-container">
        <p className="shopping-purchases-text">Shopping Purchases</p>
        
        {orders.map((order) => (
          <>
            <p>Purchase Date: {order.purchaseDate}</p>
            <ul>
              {order.purchasedProducts.map((product) =>(
                <li className="purchased-products">
                  <p className="product-name-text">{product.name}</p>
                  <p className="product-price-text">${product.price.toFixed(2)}</p>
                </li>
              ))}
            </ul>
          </>
        ))}
        <p>Subtotal: ${shoppingSubtotal.toFixed(2)}</p>
        <p>Tax: + ${shoppingTax.toFixed(2)}</p>
        <p>Shopping Total: ${shoppingTotal.toFixed(2)}</p>
      </div>
    </div>
  )
}

export default ShoppingBill;