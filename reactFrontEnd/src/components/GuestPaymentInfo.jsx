function GuestPayment() {
    return (
        <div className="box">
                <div className="row">
                    <p>Start night: 12/03/2023</p>
                    <p>End night: 12/06/2023</p>
                </div>
                <div className="row">
                    <label htmlFor="name">Name: </label>
                    <input id="name" type="text" />
                </div>
                <div className="row">
                    <label htmlFor="address">Address: </label>
                    <input id="address" type="text" />
                </div>
                <div className="row">
                    <p>Payment Method: </p>
                    <div>
                        <label htmlFor="credit">credit</label>
                        <input id="credit" type="radio" name="payment_method" value="credit"/>
                    
                        <label htmlFor="debit">debit</label>
                        <input id="debit" type="radio" name="payment_method" value="debit"/>
                    </div>
                </div>
        </div>
    );
};

export default GuestPayment;