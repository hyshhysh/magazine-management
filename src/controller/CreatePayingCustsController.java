package controller;

import customer.Customer;
import customer.PayingCustomer;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import magazine.Supplement;
import view.CreatePayingCustsView;
import view.Homepage;

import java.util.ArrayList;

public class CreatePayingCustsController {

    private CreatePayingCustsView view;

    public CreatePayingCustsController(CreatePayingCustsView view) {
        this.view = view;
    }

    public void addCustomer(TextField nameTextField, TextField streetNumTextField, TextField streetNameTextField,
                            TextField suburbTextField, TextField postCodeTextField, TextField emailTextField,
                            TextField paymentMethodTextField, TextField paymentDetailsTextField,
                            ComboBox<String> suppList, ArrayList<Customer> customerArrayList, ArrayList<Supplement> supplementArrayList,
                            Button addedButton) {
        String name, address, email, paymentMethod, paymentDetails;
        name = nameTextField.getText();
        address = streetNumTextField.getText() + ", " +
                streetNameTextField.getText() + ", " +
                suburbTextField.getText() + ", " +
                postCodeTextField.getText();
        email = emailTextField.getText();
        paymentMethod = paymentMethodTextField.getText();
        paymentDetails = paymentDetailsTextField.getText();

        Customer customer = new PayingCustomer("Paying", name, address, email,
                supplementArrayList, paymentMethod, paymentDetails);
        customerArrayList.add(customer);

        addedButton.setText("Added!");
        clearInputs(nameTextField, streetNumTextField, streetNameTextField, suburbTextField,
                postCodeTextField, emailTextField, paymentMethodTextField, paymentDetailsTextField);
        suppList.setValue("Select Supplements");
    }

    public void createCustomersList(TextField nameTextField, TextField streetNumTextField, TextField streetNameTextField,
                                    TextField suburbTextField, TextField postCodeTextField, TextField emailTextField,
                                    TextField paymentMethodTextField, TextField paymentDetailsTextField,
                                    ComboBox<String> suppList, ArrayList<Customer> customerArrayList, ArrayList<Supplement> supplementArrayList,
                                    Button addedButton) {
        addCustomer(nameTextField, streetNumTextField, streetNameTextField, suburbTextField, postCodeTextField,
                emailTextField, paymentMethodTextField, paymentDetailsTextField, suppList, customerArrayList, supplementArrayList,
                addedButton);
        if (!customerArrayList.isEmpty()) {
            Homepage.MAGAZINE_MODEL.setCustomers(customerArrayList);
        }
    }

    public void clearInputs(TextField... inputs) {
        for (TextField input : inputs) {
            input.clear();
        }
    }

} // END OF CreatePayingCustsController CLASS
