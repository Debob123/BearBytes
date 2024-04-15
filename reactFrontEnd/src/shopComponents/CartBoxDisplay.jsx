import React, { useState, useEffect } from 'react';

// Define custom styles for the modal
const customStyles = {
    content: {
        width: '30%', // make the modal 3 times smaller
        height: '30%',
        margin: 'auto',
    },
};

function CartBoxDisplay({ id, imgLink, title, cost, btnAct="y"}) {

    function removeFromCart() {
        let product = {
            "id" : id,
            "name": title,
            "price": cost,
            "image": imgLink
        }

        let cart = JSON.parse(sessionStorage.getItem('cart'));
        var ndx = cart.findIndex(x => x.name === product.name);
        if(ndx !== -1)  {
            cart.splice(ndx, 1);
        }
        sessionStorage.setItem('cart', JSON.stringify(cart));
        document.location.reload();
        
    }

    return (
        <div className="box-display">
            <img src={imgLink} alt={title} />
            <h2>{title}</h2>
            <p>${cost}</p>
            <button onClick={removeFromCart}>Remove</button>
        </div>
    );
}

export default CartBoxDisplay;