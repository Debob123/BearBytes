import React, { useState, useEffect } from 'react';

// Define custom styles for the modal
const customStyles = {
    content: {
        width: '30%', // make the modal 3 times smaller
        height: '30%',
        margin: 'auto',
    },
};

function CartBoxDisplay({ imgLink, title, cost, availability, btnAct="y"}) {

    function removeFromCart() {
        // Add your logic to add the product to the cart
    }

    return (
        <div className="box-display">
            <img src={imgLink} alt={title} />
            <h2>{title}</h2>
            <p>{cost}</p>
            <button onClick={removeFromCart}>Remove</button>
        </div>
    );
}

export default CartBoxDisplay;