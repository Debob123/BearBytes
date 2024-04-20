import GuestHeader from '../components/GuestHeader';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import ShopHeader from '../shopComponents/ShopHeader';
import './styles/purchaseConfirmationPage.css';

function PurchaseConfirmationPage() {

    return (
        <div>
            <GuestHeader />
            <ShopHeader />
            <h1 className="content-start"></h1>
            <div className="check"><FontAwesomeIcon className="check-icon" icon={faCheck}/></div>
            <p className="thank-you-message">Thank you for your purchase!</p>
        </div>
    );
}

export default PurchaseConfirmationPage;