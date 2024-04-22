import ShoppingBill from '../billComponents/ShoppingBill';
import GuestHeader from '../components/GuestHeader';
import './styles/guestMyBill.css';

function GuestMyBillPage()  {


  return(
    <div>
      <GuestHeader />
      <p className="your-bill-text">Your Bill</p>
      <ShoppingBill />

    </div>
  )
  
}

export default GuestMyBillPage;