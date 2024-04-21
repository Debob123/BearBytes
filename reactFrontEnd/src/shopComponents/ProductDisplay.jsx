import ProductBoxDisplay from './ProductBoxDisplay.jsx';
import '../components/display.css';
import Button from '../components/Button.jsx';
import {useState, useEffect} from 'react';

const images = require.context('../images', true); 

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
    }, []);

    const [isLoading, setLoading] = useState(true);
    // Fetch all of the available rooms
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
        setLoading(false);
        })
        .catch(error => console.error('Error creating product array:', error));
    }
    
    return (
        <div className="display">
            {products.filter(isSearch).map((product) =>(
                <ProductBoxDisplay className="box-display" key={product.id} id={product.id} image={product.image} title={product.name} cost={product.price} btn={button}/>
            ))}
        </div>
    );
}

export default ProductDisplay;