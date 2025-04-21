package controller;

import customer.Customer;
import customer.PayingCustomer;
import helper.FileHandler;
import helper.Validation;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MagazineModel;
import view.EditPayingCustomerView;
import view.Homepage;
import view.SaveFileAlertBox;

import java.util.Optional;

public class EditPayingCustomerController {

    private EditPayingCustomerView view;

    public EditPayingCustomerController(EditPayingCustomerView view) {
        this.view = view;
    }

    public void addCustomer(TextField nameInput, TextField emailInput, TextField addressInput,
                            TextField paymentMethodInput, TextField paymentDetailsInput,
                            TableView<Customer> customerTableView, MagazineModel magazineModel) {
        String name, email, address, paymentMethod, paymentDetails;
        name = nameInput.getText().trim();
        email = emailInput.getText().trim();
        address = addressInput.getText().trim();
        paymentMethod = paymentMethodInput.getText().trim();
        paymentDetails = paymentDetailsInput.getText().trim();

        // Validate that none of the fields are empty
        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || paymentMethod.isEmpty() || paymentDetails.isEmpty()) {
            showAlert("All fields must be filled out.");
            return;
        }

        // Validate input values
        if (!Validation.isValidName(name)) {
            showAlert("Invalid name entered. Please enter a valid name!");
            return;
        }
        if (!Validation.isValidEmail(email)) {
            showAlert("Invalid email address entered. Please enter a valid \nemail address!");
            return;
        }

        if (!Validation.isValidPaymentMethod(paymentMethod)) {
            showAlert("Invalid payment method entered. Please enter only \nCredit Card or Bank Acc Debit.");
            return;
        }

        if (!Validation.isValidPaymentDetails(paymentDetails)) {
            showAlert("Invalid payment details entered. Please re-enter (digits only).");
            return;
        }

        // Add new customer if all validation has passed
        Customer customer = new PayingCustomer("Paying", name, email, address, paymentMethod, paymentDetails);
        customerTableView.getItems().add(customer);
        magazineModel.addNewCustomer(customer);
        showAlert("New customer added successfully!");

        clearInputs(nameInput, emailInput, addressInput, paymentMethodInput, paymentDetailsInput);
    }

    public void deleteCustomer(TableView<Customer> customerTableView, MagazineModel magazineModel) {
        ObservableList<Customer> custSelected, allCustomers;
        allCustomers = customerTableView.getItems();
        custSelected = customerTableView.getSelectionModel().getSelectedItems();

        // CreateMagazineView a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete the selected customer?");
        alert.setContentText("This action cannot be undone.");

        // Wait for a response
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Proceed with deletion
            for (Customer cust : custSelected) {
                magazineModel.deleteCustomer(cust);
            }
            custSelected.forEach(allCustomers::remove);
            showAlert("Customer deleted!");

        }
    }

    public void saveChanges(MagazineModel magazineModel) {
        if (magazineModel != null) {
            String fileName = magazineModel.getFileName();
            FileHandler.serializeMagazineModel(magazineModel, fileName);
            showAlert("Changes saved!");
        } else {
            showAlert("No data to be saved!");
        }
    }

    public void saveAsNewFile() {
        if (Homepage.MAGAZINE_MODEL != null) {
            SaveFileAlertBox.display();
        } else {
            showAlert("No data to be saved!");
        }
    }

    public void clearInputs(TextField... inputs) {
        for (TextField input : inputs) {
            input.clear();
        }
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.show();
    }



} // END OF EditPayingCustomerController CLASS
