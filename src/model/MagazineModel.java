package model;

import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import magazine.Magazine;
import magazine.Supplement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MagazineModel implements Serializable {

    private Magazine magazine;
    private ArrayList<Supplement> supplements;
    private ArrayList<Customer> customers;
    private String fileName;

    public MagazineModel() {
        this.magazine = null;
        this.supplements = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.fileName = "";
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(ArrayList<Supplement> supplements) {
        this.supplements = supplements;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void addNewCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void deleteCustomer(Customer customer) {
        this.customers.remove(customer);

        //Remove all associate customers too if customer is a paying customer
        if (customer.getCustomerType().equalsIgnoreCase("Paying")) {
            PayingCustomer payingCustomer = (PayingCustomer) customer;
            for (AssociateCustomer assCust : payingCustomer.getAssociateCustomerList()) {
                this.customers.remove(assCust);
            }
        } else {
            //If associate customer, remove from its paying customer's list
            PayingCustomer payingCustomer = ((AssociateCustomer) customer).getPayingCustomer();
            payingCustomer.removeAssociateCustomer((AssociateCustomer) customer);
        }
    }

    public void addNewSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    public void deleteSupplementFromCust(Supplement supplement) {
        for (Customer cust : this.customers) {
            if (cust.getSupplementList() != null) {
                Iterator<Supplement> iterator = cust.getSupplementList().iterator();
                while (iterator.hasNext()) {
                    Supplement supp = iterator.next();
                    if (supp.getName().equalsIgnoreCase(supplement.getName())) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public void deleteSupplement(Supplement supplement) {
        //Delete supplement from all customer's supplement list
        deleteSupplementFromCust(supplement);
        // Then delete supplement from magazine model
        this.supplements.remove(supplement);
    }

    public Supplement getSupplementViaName(String name) {
        for (Supplement supp : this.getSupplements()) {
            if (supp.getName().equalsIgnoreCase(name)) {
                return supp;
            }
        }
        return null;
    }

    public Customer getCustomerViaEmail(String email) {
        for (Customer cust : this.getCustomers()) {
            if (cust.getEmailAdd().equalsIgnoreCase(email)) {
                return cust;
            }
        }
        return null;
    }

    public void editMagazineInfo(String name) {
        this.magazine.setName(name);
    }

    public void editMagazineInfo(double cost) {
        this.magazine.setWeeklyCost(cost);
    }

    public void editMagazineInfo(String name, double cost) {
        this.magazine.setName(name);
        this.magazine.setWeeklyCost(cost);
    }

    public void editCustomerName(Customer customer, String name) {
        customer.setName(name);
    }

    public void editCustomerAddress(Customer customer, String address) {
        customer.setAddress(address);
    }

    public void editCustomerEmailAdd(Customer customer, String emailAdd) {
        customer.setEmailAdd(emailAdd);
    }

    public void editCustomerSupplements(Customer customer, ArrayList<Supplement> supplements) {
        customer.setSupplementList(supplements);
    }

    public void editPaymentMethod(PayingCustomer payingCustomer, String paymentMethod,
                                  String paymentDetails) {
        payingCustomer.setPaymentMethod(paymentMethod);
        payingCustomer.setPaymentDetails(paymentDetails);
    }

    public void editPayingCustomerAssociatesList(PayingCustomer payingCustomer, ArrayList<AssociateCustomer> associateCustomers) {
        payingCustomer.setAssociateCustomerList(associateCustomers);
        // Also set Paying Customer as Associate Customer's paying customer
        for (AssociateCustomer assCust : associateCustomers) {
            assCust.setPayingCustomer(payingCustomer);
        }
    }

    public void editAssociateCustomerPayingCustomer(AssociateCustomer associateCustomer, PayingCustomer payingCustomer) {
        associateCustomer.setPayingCustomer(payingCustomer);
        //Also add Associate Customer to Paying Customer's Associate Customer list
        payingCustomer.addAssociateCustomer(associateCustomer);
    }

    public void editSupplementInfo(Supplement supplement, String name) {
        supplement.setName(name);
    }

    public void editSupplementInfo(Supplement supplement, double cost) {
        supplement.setWeeklyCost(cost);
    }

    public void editSupplementInfo(Supplement supplement, String name, double cost) {
        supplement.setName(name);
        supplement.setWeeklyCost(cost);
    }


} // END OF MagazineModel class
