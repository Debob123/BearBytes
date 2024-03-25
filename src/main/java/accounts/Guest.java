package accounts;

public class Guest extends Account {
    String name, address, creditCardExpiration;
    long creditCardNumber;

    public Guest(String username, String password) {
        super(username, password);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setCreditCardNumber(long number){
        creditCardNumber = number;
    }

    public long getCreditCardNumber(){
        return creditCardNumber;
    }

    public void setCreditCardExpiration(String exp){
        creditCardExpiration = exp;
    }

    public String getCreditCardExpiration(){
        return creditCardExpiration;
    }
}
