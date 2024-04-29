import GuestNavigation from '../components/GuestNavigation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import ShopHeader from '../shopComponents/ShopHeader';
import './styles/purchaseConfirmationPage.css';

function PurchaseConfirmationPage() {

    return (
        <div>
            <GuestNavigation />
            <ShopHeader />
            <div className="check">
                <FontAwesomeIcon className="check-icon" icon={faCheck}/>
                <p className="thank-you-message">Thank you for your purchase!</p>
            </div>
        </div>
    );
}

export default PurchaseConfirmationPage;