function GuestPayment() {
    return (
        <div class="box">
                <div class="row">
                    <p>Start night: 12/03/2023</p>
                    <p>End night: 12/06/2023</p>
                </div>
                <div class="row">
                    <label for="name">Name: </label>
                    <input id="name" type="text" />
                </div>
                <div class="row">
                    <label for="address">Address: </label>
                    <input id="address" type="text" />
                </div>
                <div class="row">
                    <p>Payment Method: </p>
                    <div>
                        <label for="credit">credit</label>
                        <input id="credit" type="radio" name="payment_method" value="credit"/>
                    
                        <label for="debit">debit</label>
                        <input id="debit" type="radio" name="payment_method" value="debit"/>
                    </div>
                </div>
        </div>
    );
};

export default GuestPayment;