package controller;

import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import helper.FileHandler;
import helper.Validation;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MagazineModel;
import view.EditAssociateCustomerView;
import view.Homepage;
import view.SaveFileAlertBox;

import java.util.Optional;

public class EditAssociateCustomerController {

    private EditAssociateCustomerView view;

    public EditAssociateCustomerController(EditAssociateCustomerView view) {
        this.view = view;
    }

    public void addCustomer(TextField nameInput, TextField emailInput, TextField addressInput,
                            ComboBox<String> payingCustComboBox, TableView customerTableView, MagazineModel magazineModel) {
        String name, email, address, payingCustName;
        PayingCustomer payingCust = null;

        name = nameInput.getText().trim();
        email = emailInput.getText().trim();
        address = addressInput.getText().trim();
        payingCustName = payingCustComboBox.getValue();

        // Validate that none of the fields are empty
        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || payingCustName == null) {
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

        //Find the selected Paying Customer 
        for (Customer customer : magazineModel.getCustomers()) {
            if (payingCustName.equalsIgnoreCase(customer.getName())) {
                payingCust = (PayingCustomer) customer;
                break;
            }
        }

        // Create and add the new Associate Customer
        Customer customer = new AssociateCustomer("Associate", name, email, address, payingCust);
        customerTableView.getItems().add(customer);
        magazineModel.addNewCustomer(customer);

        // Clear input fields
        clearInputs(nameInput, emailInput, addressInput);
        payingCustComboBox.setValue(null);

        showAlert("New customer added successfully!");
    }

    public void deleteCustomer(TableView<Customer> customerTableView, MagazineModel magazineModel) {
        ObservableList custSelected, allCustomers;
        allCustomers = customerTableView.getItems();
        custSelected = customerTableView.getSelectionModel().getSelectedItems();

        // CreateMagazineView a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete the selected customer(s)?");
        alert.setContentText("This action cannot be undone.");

        // Wait for a response
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Proceed with deletion
            for (Object cust : custSelected) {
                magazineModel.deleteCustomer((Customer) cust);
            }
            custSelected.forEach(allCustomers::remove);

            showAlert("Customer(s) deleted!");
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



} // END OF EditAssociateCustomerController CLASS