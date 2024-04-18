import { v4 as uuidv4 } from 'uuid';
import ProductBoxDisplay from './ProductBoxDisplay.jsx';
import '../components/display.css';
import vase from '../images/vase.jpg'
import beachHat from '../images/beach-hat.jpg'
import beachTowels from '../images/beach-towels.jpg'
import sunglasses from '../images/sunglasses.jpg'
import tropicalShirt from '../images/tropical-shirt.jpg'
import sharkNecklace from '../images/shark-necklace.jpg'
import shellBracelet from '../images/seashell-bracelet.jpg'
import umbrella from '../images/beach-umbrella.jpg'
import Button from '../components/Button.jsx';
const button = <Button text="Add to Cart"/>

const product = [
    {
        id: 1,
        image: tropicalShirt,
        name: "Tropical Shirt",
        price: 25.00
        
    },
    {
        id: 2,
        image: sharkNecklace,
        name: "Shark Necklace",
        price: 15.00
        
    },
    {
        id: 3,
        image: vase,
        name: "Locally Made Vases",
        price: 50.00
    },
    {
        id: 4,
        image: beachHat,
        name: "Beach Hat",
        price: 20.00
    },
    {
        id: 5,
        image: sunglasses,
        name: "Sunglasses",
        price: 10.00
    },
    {
        id: 6,
        image: beachTowels,
        name: "Beach Towels",
        price: 30.00
    },
    {
        id: 7,
        image: shellBracelet,
        name: "Locally Crafted Seashell Bracelet",
        price: 15.00
    },
    {
        id: 8,
        image: umbrella,
        name: "Beach Umbrella",
        price: 25.00
    },

]


function ProductDisplay() {
    
    return (
        <div className="display">
            {product.map((product) =>(
                <ProductBoxDisplay className="box-display" key={product.id} id={product.id} image={product.image} title={product.name} cost={product.price} btn={button}/>
            ))}
        </div>
    );
}

export default ProductDisplay;