import React, { useState, useEffect } from 'react';
import GuestHeader from '../components/GuestHeader';
import ProductDisplay from '../shopComponents/ProductDisplay';
import ShopHeader from '../shopComponents/ShopHeader';

function ShoppingPage() {

    return (
        <div>
            <GuestHeader />
            <ShopHeader />
            <h1 className="content-start"></h1>
            <ProductDisplay />
        </div>
    );
}

export default ShoppingPage;