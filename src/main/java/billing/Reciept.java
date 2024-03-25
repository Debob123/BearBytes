package billing;

public class Reciept { // Reservation details
private String confirmationNumber;
private String checkInDate;
private String checkOutDate;
private String roomType;
private int numberOfRooms;

// Guest Information
private String guestName;
private String contactInformation;

// Transaction details
private double totalAmount;
private String paymentMethod;
private String billingAddress;

// Cancellation and refund policy
private String cancellationPolicy;

// Loyalty points earned
private int loyaltyPointsEarned;

// Additional information
private String promotionalOffers;

    public void setConfirmationNumber(String s){confirmationNumber = s;}
    public void setCheckInDate(String s){checkInDate = s;}
    public void setCheckOutDate(String s){checkOutDate = s;}
    public void setRoomType(String s){roomType = s;}
    public void setNumberOfRooms(int n){numberOfRooms = n;}
public void setTotalAmount(double d){totalAmount = d;}
public void setPaymentMethod(String s){paymentMethod = s;}
public void setBillingAddress(String s){ billingAddress = s;}

public void setCancellationPolicy(String s){cancellationPolicy = s;}
public void setLoyaltyPointsEarned(int n){loyaltyPointsEarned = n;}
public void setPromotionalOffers(String s){promotionalOffers = s;}
public String getConfirmationNumber(){return confirmationNumber;}
public String getCheckInDate(){return checkInDate;}
public String getCheckOutDate(){return checkOutDate;}
public String getRoomType(){return roomType;}
public int getNumberOfRooms(){return numberOfRooms;}
public String getGuestName(){return guestName;}
public String getContactInformation(){return contactInformation;}
public double getTotalAmount(){return totalAmount;}
public String getPaymentMethod(){return paymentMethod;}
public String getBillingAddress(){return billingAddress;}
public int getLoyaltyPointsEarned(){return loyaltyPointsEarned;}
public String getPromotionalOffers(){return promotionalOffers;}
}