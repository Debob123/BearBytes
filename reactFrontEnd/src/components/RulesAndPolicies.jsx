function RulesAndPolicies() {
    return (
        <div className="box">
            <h3 className="center-text">Rules and Policies</h3>
            <div className="center-text">
                <p>Description of rules and policies</p>
            </div>
            <div classNamee="center-text">
                <label for="agree"> I agree</label>
                <input id="agreement" type="radio" name="agreement" value="agreement"/>
            </div>
        </div>
    );
};

export default RulesAndPolicies;