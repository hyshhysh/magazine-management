package customer;

import magazine.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Customer class is an abstract class representing a magazine customer.
 * Customers can subscribe to a magazine and optional supplements.
 *
 * The class provides constructors to initialize a customer with or without supplements
 * and includes methods to manage the customer’s name, address, email address, and their supplement list.
 *
 * This class is meant to be extended by concrete customer types (e.g., AssociateCustomer, PayingCustomer)
 *
 * @see Supplement
 * @see Magazine
 */
public abstract class Customer implements Serializable {

    private String customerType;
    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The address of the customer.
     */
    private String address;

    /**
     * The email address of the customer.
     */
    private String emailAdd;

    /**
     * A list of supplements that the customer is interested in.
     */
    private ArrayList<Supplement> supplementList;



    //------------------ CONSTRUCTORS ------------------//

    /**
     * Constructs a Customer without subscribing to any supplements.
     *
     * @param name     The name of the customer.
     * @param emailAdd The email address of the customer.
     */
    public Customer(String customerType, String name,  String address, String emailAdd) {
        this.customerType = customerType;
        this.name = name;
        this.address = address;
        this.emailAdd = emailAdd;
    }

    /**
     * Constructs a Customer with a list of supplements.
     *
     * @param name        The name of the customer.
     * @param emailAdd    The email address of the customer.
     * @param supplements The list of supplements that the customer subscribes to.
     */
    public Customer(String customerType, String name,  String address, String emailAdd, ArrayList<Supplement> supplements) {
        this.customerType = customerType;
        this.name = name;
        this.address = address;
        this.emailAdd = emailAdd;
        this.supplementList = supplements;
    }



    //------------------ GETTERS AND SETTERS ------------------//


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return The email address of the customer.
     */
    public String getEmailAdd() {
        return emailAdd;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param emailAdd The new email address of the customer.
     */
    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The new address of the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the list of supplements the customer has subscribed to.
     *
     * @return The list of supplements, or null if no supplements have been subscribed to.
     */

    public ArrayList<Supplement> getSupplementList() {
        return supplementList;
    }

    /**
     * Sets the list of supplements the customer has subscribed to.
     *
     * @param supplementList The list of supplements.
     */
    public void setSupplementList(ArrayList<Supplement> supplementList) {
        this.supplementList = supplementList;
    }


} // END OF CUSTOMER CLASS