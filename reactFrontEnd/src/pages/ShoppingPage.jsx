import React, { useState, useEffect } from 'react';
import GuestHeader from '../components/GuestHeader';
import ProductDisplay from '../shopComponents/ProductDisplay';
import ShopHeader from '../shopComponents/ShopHeader';

function ShoppingPage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products from API and setProducts
    }, []);

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

/*
{products.map((product) => (
                <div key={product.id}>
                    <h2>{product.name}</h2>
                    <p>{product.description}</p>
                </div>
            ))}
*/


/*
import React, { useState, useEffect } from 'react';

function ShoppingPage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products from API and setProducts
    }, []);

    return (
        <div>
            <h1>Shopping Page</h1>
            {products.map((product) => (
                <div key={product.id}>
                    <h2>{product.name}</h2>
                    <p>{product.description}</p>
                </div>
            ))}
        </div>
    );
}

export default ShoppingPage;
*/