import ProductBoxDisplay from './ProductBoxDisplay.jsx';
import Button from '../components/Button.jsx';
import {useState, useEffect} from 'react';

const button = <Button text="Add to Cart"/>


function isSearch(product)  {
    let search = "";
    if(sessionStorage.getItem('searchBarString') !== null)  {
        search = sessionStorage.getItem('searchBarString');
    }
    return (product.name).toLowerCase().includes(search.toLowerCase());
}


function ProductDisplay() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        renderProducts();
        console.log(JSON.stringify(products));
    }, []);

    function renderProducts() {
        fetch('http://localhost:8080/shop/getProducts', {
        mode: 'cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        })
        .then(response => response.json())
        .then(data => {
            setProducts(data);
        })
        .catch(error => console.error('Error creating product array:', error));
    }
    
    return (
        <div className="shop-display">
            {products.filter(isSearch).map((product) =>(
                <ProductBoxDisplay className="product-box-display" key={product.id} id={product.id} image={product.image} title={product.name} cost={product.price} btn={button}/>
            ))}
        </div>
    );
}

export default ProductDisplay;