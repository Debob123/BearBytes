import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';

// Define custom styles for the modal
const customStyles = {
    content: {
        width: '30%', // make the modal 3 times smaller
        height: '30%',
        margin: 'auto',
    },
};

Modal.setAppElement('#root'); // This line is needed for accessibility reasons

function ProductBoxDisplay({ imgLink, title, cost, availability, btnAct="y"}) {
    const [isAvailable, setIsAvailable] = useState(true);
    const inStock = true;

    function addToCart() {
        // Add your logic to add the product to the cart
    }

    function checkAvailability() {
        // Check if product is in stock
        if (inStock) {
            setIsAvailable(true);
        } else {
            setIsAvailable(false);
        }
    }

    useEffect(() => {
        checkAvailability();
    },);

    return (
        <div className="box-display">
            <img src={imgLink} alt={title} />
            <h2>{title}</h2>
            <p>{cost}</p>
            {isAvailable ? <button onClick={addToCart}>Add to Cart</button> : <p>Product is out of stock.</p>}
        </div>
    );
}

export default ProductBoxDisplay;