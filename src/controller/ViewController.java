package controller;

import customer.Customer;
import customer.PayingCustomer;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import magazine.Supplement;
import view.Homepage;
import view.ViewView;

public class ViewController {

    private ViewView view;

    public ViewController(ViewView view) {
        this.view = view;
    }

    public String showSupplementInformation(String newValue) {
        String stringMessage = "";
        if (newValue != null) {
            if (Homepage.MAGAZINE_MODEL != null) {
                for (Supplement supp : Homepage.MAGAZINE_MODEL.getSupplements()) {
                    if (newValue.equalsIgnoreCase(supp.getName())) {
                        stringMessage = ("Name: " + supp.getName() +
                                "\nWeekly Cost: " + supp.getWeeklyCost());
                    }
                }
                stringMessage += "\n\nSubscribed customers: \n";
                for (Customer cust : Homepage.MAGAZINE_MODEL.getCustomers()) {
                    if (cust.getSupplementList() != null) {
                        for (Supplement supp : cust.getSupplementList()) {
                            if (newValue.equalsIgnoreCase(supp.getName())) {
                                stringMessage += cust.getName() + "\n";
                            }
                        }
                    }
                }
            }
        }
        return stringMessage;
    }

    public String showCustomerInformation(String newValue) {
        String stringMessage = "";
        if (newValue != null) {
            if (Homepage.MAGAZINE_MODEL != null) {
                for (Customer cust : Homepage.MAGAZINE_MODEL.getCustomers()) {
                    if (newValue.equalsIgnoreCase(cust.getName())) {
                        stringMessage = "\nCustomer Details \n\n" +
                                "Name:       " + cust.getName() +
                                "\nAddress:    " + cust.getAddress().toString() +
                                "\nEmail:        " + cust.getEmailAdd();

                        if (cust.getCustomerType().equalsIgnoreCase("Paying")) {
                            stringMessage += "\nStatus:       Paying" +
                                    "\n\nAssociate Customers: \n";
                            if (!((PayingCustomer) cust).getAssociateCustomerList().isEmpty()) {
                                for (Customer assCust : ((PayingCustomer) cust).getAssociateCustomerList()) {
                                    stringMessage += assCust.getName() + "\n";
                                }
                            } else {
                                stringMessage += "No associate customers.";
                            }
                        } else {
                            stringMessage += "\nStatus:       Associate";
                        }

                        stringMessage += "\n\nSupplements subscribed:\n";
                        if (cust.getSupplementList() != null) {
                            for (Supplement supp : cust.getSupplementList()) {
                                stringMessage += supp.getName() + "\n";
                            }
                        } else {
                            stringMessage += "No supplements subscribed.";
                        }

                    }
                }
            }
        }
        return stringMessage;
    }

    public void showBillingHistory(TextArea billHistArea, String newValue) {

        if (newValue != null) {
            if (Homepage.MAGAZINE_MODEL != null) {
                for (Customer cust : Homepage.MAGAZINE_MODEL.getCustomers()) {
                    if (newValue.equalsIgnoreCase(cust.getName())) {
                        if (cust.getCustomerType().equalsIgnoreCase("Paying")) {
                            Task<String> task = new Task<>() {
                                @Override
                                protected String call() throws Exception {
                                    Thread.sleep(800); // Simulate delay
                                    return ((PayingCustomer) cust).getBillingHistory(Homepage.MAGAZINE_MODEL.getMagazine());
                                }
                            };

                            task.setOnSucceeded(e -> {
                                billHistArea.setText(task.getValue());
                            });

                            new Thread(task).start();
                        }
                    }
                }
            }
        }

    }





} // END OF ViewController CLASS
