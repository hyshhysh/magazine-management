package controller;

import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import view.CreateAssociateCustomersView;
import view.Homepage;

import java.util.ArrayList;

public class CreateAssociateCustomersController {

    private CreateAssociateCustomersView view;

    public CreateAssociateCustomersController(CreateAssociateCustomersView view) {
        this.view = view;
    }

    public void addCustomer(TextField nameTextField, TextField streetNumTextField, TextField streetNameTextField,
                       TextField suburbTextField, TextField postCodeTextField, TextField emailTextField,
                       ComboBox<String> payingCustList, ArrayList<Customer> customerArrayList,
                       ComboBox<String> suppList, Button addedButton) {
        String name, address, email;
        PayingCustomer payingCustomer = null;
        name = nameTextField.getText();
        address = streetNumTextField.getText() + ", " +
                streetNameTextField.getText() + ", " +
                suburbTextField.getText() + ", " +
                postCodeTextField.getText();
        email = emailTextField.getText();
        for (Customer customer : Homepage.MAGAZINE_MODEL.getCustomers()) {
            if (customer.getName().equalsIgnoreCase(payingCustList.getValue())) {
                payingCustomer = (PayingCustomer) customer;
            }
        }
        Customer customer = new AssociateCustomer("Associate", name, address, email,
                payingCustomer);
        customerArrayList.add(customer);

        addedButton.setText("Added!");

        clearInputs(nameTextField, streetNumTextField, streetNameTextField, suburbTextField,
                postCodeTextField, emailTextField);
        suppList.setValue("Select Supplements");
        payingCustList.setValue("Select Paying Customer");
    }

    public void clearInputs(TextField... inputs) {
        for (TextField input : inputs) {
            input.clear();
        }
    }



} // END OF CreateAssociateCustomersController CLASS
