package customer;

import magazine.Magazine;
import magazine.Supplement;
import java.util.ArrayList;

/**
 * The PayingCustomer class represents a customer who subscribes to a magazine and makes payments.
 * A PayingCustomer may also have a list of associate customers, and can subscribe to optional supplements.
 *
 * This class extends the abstract Customer class and implements functionality specific to paying customers,
 * such as managing payment methods, generating invoices, and calculating monthly payments.
 */
public class PayingCustomer extends Customer {
    /**
     * The payment method used by the customer.
     */
    private String paymentMethod;

    /**
     * The payment details associated with the payment method.
     */
    private String paymentDetails;

    /**
     * A list of associate customers tied to this paying customer.
     */
    private ArrayList<AssociateCustomer> associateCustomerList;


    //------------------ CONSTRUCTORS ------------------//

    /**
     * Constructs a PayingCustomer without supplements and associate customers.
     *
     * @param customerType   The type of the customer.
     * @param name           The name of the customer.
     * @param address        The address of the customer.
     * @param emailAdd       The email address of the customer.
     * @param paymentMethod  The payment method for the subscription.
     * @param paymentDetails The details of the payment method.
     */
    public PayingCustomer(String customerType, String name, String address, String emailAdd, String paymentMethod,
                          String paymentDetails) {
        super(customerType, name, address, emailAdd);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.associateCustomerList = new ArrayList<>();
    }

    /**
     * Constructs a PayingCustomer with supplements but without associate customers.
     *
     * @param name           The name of the customer.
     * @param address        The address of the customer.
     * @param emailAdd       The email address of the customer.
     * @param supplements    The list of supplements subscribed by the customer.
     * @param paymentMethod  The payment method for the subscription.
     * @param paymentDetails The details of the payment method.
     */
    public PayingCustomer(String customerType, String name, String address, String emailAdd, ArrayList<Supplement> supplements,
                          String paymentMethod, String paymentDetails) {
        super(customerType, name, address, emailAdd, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.associateCustomerList = new ArrayList<>();
    }

    /**
     * Constructs a PayingCustomer without supplements, but with associate customers.
     *
     * @param name                 The name of the customer.
     * @param address              The address of the customer.
     * @param emailAdd             The email address of the customer.
     * @param paymentMethod        The payment method for the subscription.
     * @param paymentDetails       The details of the payment method.
     * @param associateCustomerList The list of associate customers.
     */
    public PayingCustomer(String customerType, String name, String address, String emailAdd, String paymentMethod,
                          String paymentDetails, ArrayList<AssociateCustomer> associateCustomerList) {
        super(customerType, name, address, emailAdd);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.associateCustomerList = new ArrayList<>();
    }

    /**
     * Constructs a PayingCustomer with both supplements and associate customers.
     */
    public PayingCustomer(String customerType, String name, String address, String emailAdd, ArrayList<Supplement> supplements,
                          String paymentMethod, String paymentDetails,
                          ArrayList<AssociateCustomer> associateCustomerList) {
        super(customerType, name, address, emailAdd, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.associateCustomerList = new ArrayList<>();
    }



    //------------------ GETTERS AND SETTERS ------------------//

    /**
     * Gets the payment method used by the customer.
     *
     * @return The payment method as a String.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method for the customer.
     *
     * @param paymentMethod The new payment method.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the payment details associated with the customer.
     *
     * @return The payment details as a String.
     */
    public String getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * Sets the payment details for the customer.
     *
     * @param paymentDetails The new payment details.
     */
    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * Gets the list of associate customers linked to this paying customer.
     *
     * @return A list of associate customers.
     */
    public ArrayList<AssociateCustomer> getAssociateCustomerList() {
        return associateCustomerList;
    }

    /**
     * Sets the list of associate customers for this paying customer.
     *
     * @param associateCustomerList The new list of associate customers.
     */
    public void setAssociateCustomerList(ArrayList<AssociateCustomer> associateCustomerList) {
        this.associateCustomerList = associateCustomerList;
    }



    //------------------ CLASS METHODS ------------------//

    /**
     * Adds an associate customer to the list of associate customers.
     *
     * @param associateCustomer The associate customer to add.
     */
    public void addAssociateCustomer(AssociateCustomer associateCustomer) {
        this.associateCustomerList.add(associateCustomer);
    }

    /**
     * Removes an associate customer from the list of associate customers.
     *
     * @param associateCustomer The associate customer to remove.
     */
    public void removeAssociateCustomer(AssociateCustomer associateCustomer) {
        associateCustomerList.remove(associateCustomer);
    }

    /**
     * Calculates the total weekly payment for the customer, including any supplements and associate customers.
     *
     * @param magazine The magazine to which the customer is subscribed.
     * @return The total weekly payment amount.
     */
    public double getWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();

        // Check if supplementList is not null before iterating
        if (getSupplementList() != null) {
            for (Supplement supp : getSupplementList()) {
                weeklyPayment += supp.getWeeklyCost();
            }
        }

        // Check if associateCustomerList is not null before iterating
        if (associateCustomerList != null) {
            for (AssociateCustomer assCust : associateCustomerList) {
                weeklyPayment += assCust.getWeeklyPayment(magazine);
            }
        }
        return weeklyPayment;

    }

    /**
     * Calculates the total monthly payment for the customer, including any supplements and associate customers.
     *
     * @param magazine The magazine to which the customer is subscribed.
     * @return The total monthly payment amount.
     */
    public double getMonthlyPayment(Magazine magazine) {
        return getWeeklyPayment(magazine) * 4;
    }


    public String getBillingHistory(Magazine magazine) {
        String billingHistory = "---------------------------- INVOICE ---------------------------\n" +
                "\n" +
                "------------------- Cost Breakdown -------------------\n\n" +
                "Paying Customer: " + this.getName() + "\n" +
                "Magazine Subscription:\n" + "     " + magazine.getName() + " - $" +
                magazine.getWeeklyCost() + "\n";

        billingHistory += "\nSupplements:\n";
        if (getSupplementList() != null && !getSupplementList().isEmpty()) {
            for (Supplement supp : this.getSupplementList()) {
                billingHistory += "     " + supp.getName() + " - $" + supp.getWeeklyCost() +
                        "\n";
            }
        } else {
            billingHistory += "     No supplements.\n";
        }

        billingHistory += "\nAssociate Customers:";

        if (getAssociateCustomerList() != null && !getAssociateCustomerList().isEmpty()) {
            int count = 1;
            for (AssociateCustomer assCust : this.getAssociateCustomerList()) {
                billingHistory += "\n" + count + ". " + assCust.getName() + "\n" +
                        "Magazine Subscription:\n" + "     " + magazine.getName() + " - $" +
                        magazine.getWeeklyCost() + "\n";
                if (assCust.getSupplementList() != null && !assCust.getSupplementList().isEmpty()) {
                    billingHistory += "Supplements:\n";
                    for (Supplement supp : assCust.getSupplementList()) {
                        billingHistory += "     " + supp.getName() + " - $" + supp.getWeeklyCost() +
                                "\n\n";
                    }
                } else {
                    billingHistory += "     No supplements.\n";
                }
                count++;
            }
        } else {
            billingHistory += "     No associate customers.\n\n";
        }

        billingHistory += "------------------------------------------------------------------\n" +
                "Total weekly cost: $" + this.getWeeklyPayment(magazine) +
                "\nTotal payment due for the month: $" + this.getMonthlyPayment(magazine) +
                "\n\nThank you for your subscription!" +
                "\n------------------------------------------------------------------";

        return billingHistory;
    }

} // END OF PAYINGCUSTOMER CLASS