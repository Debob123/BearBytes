import React, { useState, useEffect } from 'react';
import GuestHeader from '../components/GuestHeader';
import ShopHeader from '../components/ShopHeader';
import CartDisplay from '../components/CartDisplay';

function CheckoutPage() {

    return (
        <div>
            <GuestHeader/>
            <ShopHeader/>
            <p className="cart-title">Your Cart</p>
            
            <CartDisplay/>
        </div>
    );
}

export default CheckoutPage;