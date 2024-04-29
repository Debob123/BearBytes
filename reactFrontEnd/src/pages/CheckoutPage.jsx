import GuestNavigation from '../components/GuestNavigation';
import ShopHeader from '../shopComponents/ShopHeader';
import CartDisplay from '../shopComponents/CartDisplay';

function CheckoutPage() {

    return (
        <div>
            <GuestNavigation/>
            <ShopHeader/>
            <p className="cart-title">Your Cart</p>
            <CartDisplay/>
        </div>
    );
}

export default CheckoutPage;