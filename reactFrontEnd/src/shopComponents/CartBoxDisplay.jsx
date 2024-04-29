import './cartDisplay.css';

function CartBoxDisplay({ id, image, title, cost}) {

    function removeFromCart() {

        let cart = JSON.parse(sessionStorage.getItem('cart'));
        var ndx = cart.findIndex(x => x.id === id);
        if(ndx !== -1)  {
            cart.splice(ndx, 1);
        }
        sessionStorage.setItem('cart', JSON.stringify(cart));
        document.location.reload();
        
    }

    return (
        <div className="cart-box-display">
            <img src={require('../images/' + `${image}`)} alt={title} />
            <h2>{title}</h2>
            <p>${cost}</p>
            <button onClick={removeFromCart}>Remove</button>
        </div>
    );
}

export default CartBoxDisplay;