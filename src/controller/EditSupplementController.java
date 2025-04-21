package controller;

import helper.FileHandler;
import helper.Validation;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import magazine.Supplement;
import model.MagazineModel;
import view.EditSupplementView;
import view.Homepage;
import view.SaveFileAlertBox;

import java.util.Optional;

public class EditSupplementController {

    private EditSupplementView view;

    public EditSupplementController(EditSupplementView view) {
        this.view = view;
    }

    public void addSupplement(TextField nameInput, TextField costInput,
                              TableView<Supplement> supplementTableView, MagazineModel magazineModel) {
        String name, strCost;
        double cost;
        name = nameInput.getText().trim();
        strCost = costInput.getText().trim();

        // Validate that none of the fields are empty
        if (name.isEmpty() || strCost.isEmpty()) {
            showAlert("All fields must be filled out.");
            return;
        }

        // Validate input values
        if (!Validation.isValidName(name)) {
            showAlert("Invalid name entered. Please enter a valid name!");
            return;
        }

        if (!Validation.isValidCost(name)) {
            showAlert("Invalid cost entered! Please enter a positive numerical value.");
            return;
        }

        // If cost is valid, convert to double
        cost = Double.parseDouble(strCost);


        Supplement supplement = new Supplement(name, cost);
        supplementTableView.getItems().add(supplement);
        magazineModel.addNewSupplement(supplement);
        showAlert("New supplement added successfully!");

        clearInputs(nameInput, costInput);
    }

    public void deleteSupplement(TableView<Supplement> supplementTableView, MagazineModel magazineModel) {
        ObservableList<Supplement> suppSelected, allSupplements;
        allSupplements = supplementTableView.getItems();
        suppSelected = supplementTableView.getSelectionModel().getSelectedItems();

        // CreateMagazineView a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete the selected supplement?");
        alert.setContentText("This action cannot be undone.");

        // Wait for a response
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Proceed with deletion
            for (Supplement supplement : suppSelected) {
                magazineModel.deleteSupplement(supplement);
            }
            suppSelected.forEach(allSupplements::remove);
            showAlert("Supplement deleted!");

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



} // END OF EditSupplementController CLASS
