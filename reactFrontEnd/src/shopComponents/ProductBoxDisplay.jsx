import './shopDisplay.css'

function ProductBoxDisplay({ id, image, title, cost}) {

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
        <div className="product-box-display">
            <img src={require('../images/' + `${image}`)} alt={title} />
            <h2>{title}</h2>
            <p>${cost}</p>
            <button className="add-button" onClick={addToCart}>Add to Cart</button>
        </div>
    );
}

export default ProductBoxDisplay;