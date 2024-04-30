import './bill.css'

function ShoppingBill({billedOrders, bill})  {  

  return (
    <div>
      <div className="bill-container">
        <p className="category-text">Shopping Purchases</p>
        
        {billedOrders.map((order) => (
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
        
          <p>Subtotal: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${(Math.round(bill.shoppingSubTotal * 100) / 100).toFixed(2)}</p>
          <p>Tax: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + ${(Math.round(bill.shoppingTax * 100) / 100).toFixed(2)}</p>
          <p>Shopping Total: ${(Math.round(bill.shoppingTotal * 100) / 100).toFixed(2)}</p>
      
      </div>
    </div>
  )
}

export default ShoppingBill;