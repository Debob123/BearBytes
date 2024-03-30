import { v4 as uuidv4 } from 'uuid';
import ProductBoxDisplay from './ProductBoxDisplay.jsx';
import './display.css';
import vase from '../images/vase.jpg'
import beachHat from '../images/beach-hat.jpg'
import beachTowels from '../images/beach-towels.jpg'
import sunglasses from '../images/sunglasses.jpg'
import tropicalShirt from '../images/tropical-shirt.jpg'
import sharkNecklace from '../images/shark-necklace.jpg'
import shellBracelet from '../images/seashell-bracelet.jpg'
import umbrella from '../images/beach-umbrella.jpg'
import Button from './Button';
const button = <Button text="Add to Cart"/>

const product = [
    {
        imgLink: tropicalShirt,
        name: "Tropical Shirt",
        price: "$25",
        id: uuidv4()
    },
    {
        imgLink: sharkNecklace,
        name: "Shark Necklace",
        price: "$15",
        id: uuidv4()
    },
    {
        imgLink: vase,
        name: "Locally Made Vases",
        price: "$50",
        id: uuidv4()
    },
    {
        imgLink: beachHat,
        name: "Beach Hat",
        price: "$25",
        id: uuidv4()
    },
    {
        imgLink: sunglasses,
        name: "Sunglasses",
        price: "$10",
        id: uuidv4()
    },
    {
        imgLink: beachTowels,
        name: "Beach Towels",
        price: "$30",
        id: uuidv4()
    },
    {
        imgLink: shellBracelet,
        name: "Locally Crafted Seashell Bracelet",
        price: "$20",
        id: uuidv4()
    },
    {
        imgLink: umbrella,
        name: "Beach Umbrella",
        price: "$25",
        id: uuidv4()
    },

]


function ProductDisplay() {
    return (
        <div className="display">
            {product.map((product) =>(
                <ProductBoxDisplay className="box-display" key={product.id} imgLink={product.imgLink} title={product.name} cost={product.price} btn={button}/>
            ))}
        </div>
    );
}

export default ProductDisplay;