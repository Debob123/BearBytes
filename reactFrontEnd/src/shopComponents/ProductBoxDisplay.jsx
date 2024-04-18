import React, { useState, useEffect } from 'react';

// Define custom styles for the modal
const customStyles = {
    content: {
        width: '30%', // make the modal 3 times smaller
        height: '30%',
        margin: 'auto',
    },
};


function ProductBoxDisplay({ id, image, title, cost, btnAct="y"}) {

    function addToCart() {
        const product = {
            "id": id,
            "name": title,
            "price": cost,
            "image": image
        }

        if(sessionStorage.getItem('cart') !== null)  {
            let cart = JSON.parse(sessionStorage.getItem('cart'));
            cart.push(product);
            sessionStorage.setItem('cart', JSON.stringify(cart));
        }
        else  {
            let cart = [product];
            sessionStorage.setItem('cart', JSON.stringify(cart));
        }
        
    }


    return (
        <div className="box-display">
            <img src={image} alt={title} />
            <h2>{title}</h2>
            <p>${cost}</p>
            <button onClick={addToCart}>Add to Cart</button>
        </div>
    );
}

export default ProductBoxDisplay;