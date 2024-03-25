import singleRoom from '../images/hotelRoom1.jpg'
import GuestHeader from "../components/GuestHeader";
import GuestPaymentInfo from '../components/GuestPaymentInfo';
import RulesAndPolicies from '../components/RulesAndPolicies';
import ReservationSummary from '../components/ReservationSummary';
import './confirmReservationPage.css';
import BoxDisplay from '../components/BoxDisplay.jsx';

function ConfirmReservationPage() {
    return (
        <div>
            <GuestHeader className="header"/>
            <h2 className="content-begin center-text">Reserve Room</h2>
            <div className="container">
                <BoxDisplay imgLink={singleRoom} title="Single" cost="$130" btn={null}/>
                <form class="confirm-input">
                    <GuestPaymentInfo />
                    <RulesAndPolicies />
                    <ReservationSummary />
                    <input type="submit" value="Confirm" class="cnf-btn"/>
                </form>
            </div>
        </div>
    );
}

export default ConfirmReservationPage;