package bearbytes.dev.hotel.billing;

import java.util.Date;

/**
 * The Payment class stores all information of a sale.
 */
public class Payment {
    // The unique id of this Payment.
    private int paymentId;

    // The id of the invoice.
    private int invoiceId;

    // The price of this payment.
    private Double amount;

    // The date this payment is due by.
    private Date date;

    // The payment method of the payment.
    private String paymentMethod;

    // The current completion status of the payment.
    private String status;

    /**
     * The Default Constructor for a Payment: creates a unique paymentId, and stores
     * all information about an individual payment.
     * 
     * @param paymentId     The value of the payment id.
     * @param invoiceId     The value of the invoice id.
     * @param amount        The monetary value of the payment.
     * @param date          The due date of the payment.
     * @param paymentMethod The way that the payment is being processed.
     * @param status        The current completion status of the payment.
     */
    public Payment(int paymentId, int invoiceId, Double amount, Date date, String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    /**
     * Gets the unique id of this payment.
     * 
     * @return The paymentId of this payment.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique id of this payment.
     * 
     * @param paymentId The new unique id of this payment.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the invoiceId for this payment.
     * 
     * @return The invoiceId of this payment.
     */
    public int getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the invoiceId for this payment.
     * 
     * @param invoiceId The new invoice id of this payment.
     */
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Gets the price of this payment.
     * 
     * @return The price of this payment.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the price of this payment.
     * 
     * @param amount The new price of this payment.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the due date of the payment.
     * 
     * @return The due date of this payment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the due date of this payment.
     * 
     * @param date The new due date of this payment.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the payment method for this payment.
     * 
     * @return The payment type.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method for this payment.
     * 
     * @param paymentMethod The new payment method.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the completion status of this payment.
     * 
     * @return The completion status of this payment.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the completion status of this payment.
     * 
     * @param status The new status of this payment.
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
