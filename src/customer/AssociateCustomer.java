package customer;

import magazine.*;
import java.util.ArrayList;

/**
 * The AssociateCustomer class represents a customer who subscribes to a magazine
 * under the sponsorship of a paying customer. Associate customers do not pay directly
 * for their subscriptions but may subscribe to supplements.
 *
 * This class extends the Customer class, inheriting the common attributes and behaviors
 * of a customer, while adding specific features like linking to a paying customer and
 * handling supplement subscriptions.
 *
 * There are two constructors provided, allowing the creation of an associate customer
 * either with or without supplements.
 *
 * The class also provides functionality to:
 * - Calculate the monthly payment based on the supplements subscribed.
 * - Send weekly emails to the associate customer about their subscription.
 *
 * @see Customer
 * @see PayingCustomer
 * @see Supplement
 */
public class AssociateCustomer extends Customer {
    /**
     * The paying customer who sponsors the associate customer's subscription.
     */
    private PayingCustomer payingCustomer;

    /**
     * Constructs an AssociateCustomer without supplements.
     *
     * @param name          The name of the associate customer.
     * @param emailAdd      The email address of the associate customer.
     * @param payingCustomer The paying customer associated with this associate customer.
     */    public AssociateCustomer(String customerType, String name, String address, String emailAdd, PayingCustomer payingCustomer) {
        super(customerType, name, address, emailAdd);
        this.payingCustomer = payingCustomer;
    }

    /**
     * Constructs an AssociateCustomer with supplements.
     *
     * @param name          The name of the associate customer.
     * @param emailAdd      The email address of the associate customer.
     * @param supplements   The list of supplements that the associate customer subscribes to.
     * @param payingCustomer The paying customer associated with this associate customer.
     */
    public AssociateCustomer(String customerType, String name, String address, String emailAdd, ArrayList<Supplement> supplements, PayingCustomer payingCustomer) {
        super(customerType, name, address, emailAdd, supplements);
        this.payingCustomer = payingCustomer;
    }

    /**
     * Gets the paying customer associated with this associate customer.
     *
     * @return The paying customer linked to this associate customer.
     */
    public PayingCustomer getPayingCustomer() {
        return payingCustomer;
    }

    /**
     * Sets the paying customer for this associate customer.
     *
     * @param payingCustomer The paying customer to be linked with this associate customer.
     */
    public void setPayingCustomer(PayingCustomer payingCustomer) {
        this.payingCustomer = payingCustomer;
    }

    /**
     * Calculates the weekly payment for the associate customer based on the magazine
     * subscription and the supplements subscribed to.
     *
     * @param magazine The magazine that the associate customer subscribes to.
     * @return The total weekly payment including supplements.
     */
    public double getWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();

        // Check if supplementList is not null before iterating
        if (getSupplementList() != null) {
            for (Supplement supp : getSupplementList()) {
                weeklyPayment += supp.getWeeklyCost();
            }
        }
        return weeklyPayment;
    }

    /**
     * Calculates the monthly payment for the associate customer based on the magazine
     * subscription and the supplements subscribed to.
     *
     * @param magazine The magazine that the associate customer subscribes to.
     * @return The total monthly payment including supplements.
     */
    public double getMonthlyPayment(Magazine magazine) {
        return getWeeklyPayment(magazine) * 4;
    }

} // END OF ASSOCIATECUSTOMER CLASS
