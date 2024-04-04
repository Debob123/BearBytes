import React, { useState, useEffect } from 'react';
import GuestHeader from '../components/GuestHeader';
import CartDisplay from '../components/CartDisplay';

function CheckoutPage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products from API and setProducts
    }, []);

    return (
        <div>
            <GuestHeader />
            <h1>Your Cart</h1>
            <h1 className="content-start"></h1>
            <CartDisplay/>
            
        </div>
    );
}

export default CheckoutPage;