package bearbytes.dev.hotel.billing;

import java.util.Date;
import java.util.List;

/**
 * This class holds all information in order to charge an account
 * for a purchase that was made.
 */
public class Bill {
    // The unique id for this bill, that seperates it from other purchases.
    private int billId;

    // The id of the account that is being charged.
    private int customerId;

    // The amount that the account is being charged.
    private Double amount;

    // The date that the bill should be paid by.
    private Date date;

    // List of items/services.
    private List<Object> items;

    // The status of the bill.
    private String status;

    /**
     * The Default Constructor for a Bill: stores a unique id, customer info, and
     * payment details
     * 
     * @param billId     The unique id of this bill.
     * @param customerId The id of the customer being charged.
     * @param amount     The amount that the customer is being charged.
     * @param date       The date that the bill is due by.
     * @param status     The status of the bill.
     */
    public Bill(int billId, int customerId, Double amount, Date date, String status) {
        this.billId = billId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    /**
     * Returns the unique id of this Bill.
     * 
     * @return The id of this Bill.
     */
    public int getBillId() {
        return billId;
    }

    /**
     * Sets the unique id of this Bill.
     * 
     * @param billId The value to set the Bill's id to.
     */
    public void setBillId(int billId) {
        this.billId = billId;
    }

    /**
     * Returns the id of the customer responsible for paying this Bill.
     * 
     * @return The id of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the id of the customer to the given value.
     * 
     * @param customerId The id of the customer.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the payment amount on the Bill.
     * 
     * @return The amount the customer needs to pay.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount the customer will need to pay.
     * 
     * @param amount The new payment amount on the Bill.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date that the Bill is supposed to be paid by.
     * 
     * @return The due date of the Bill payment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date that the Bill should be paid by.
     * 
     * @param date The new due date of the Bill.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the payment status of the Bill.
     * 
     * @return The status of the Bill.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the payment status of the Bill.
     * 
     * @param status The new status of the Bill.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of items that the customer is paying for.
     * 
     * @return A list of all objects the customer is paying for.
     */
    public List<Object> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the Bill to the incoming list.
     * 
     * @param items The new list that the Bill consists of.
     */
    public void setItems(List<Object> items) {
        this.items = items;
    }

    /**
     * Adds an item to the list of items the Bill consists of.
     * 
     * @param item The item to add to the Bill.
     */
    public void addItem(Object item) {
        this.items.add(item);
    }

}
