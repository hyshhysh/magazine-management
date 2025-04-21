package controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import magazine.Supplement;
import view.CreateSupplementsView;
import view.Homepage;

import java.util.ArrayList;

public class CreateSupplementsController {

    private CreateSupplementsView view;

    public CreateSupplementsController(CreateSupplementsView view) {
        this.view = view;
    }

    public void addSupplement(TextField nameTextField, TextField costTextField, ArrayList<Supplement> supplementsList, Button addedButton) {
        String name = nameTextField.getText();
        double cost = Double.parseDouble(costTextField.getText());
        Supplement supplement = new Supplement(name, cost);
        supplementsList.add(supplement);
        addedButton.setText("Added!");

        clearInputs(nameTextField, costTextField);
    }

    public void createSupplementsList(TextField nameTextField, TextField costTextField, ArrayList<Supplement> supplementsList, Button addedButton) {
        if (!nameTextField.getText().isBlank() && !costTextField.getText().isBlank()) {
            addSupplement(nameTextField, costTextField, supplementsList, addedButton);
        }
        if (!supplementsList.isEmpty()) {
            Homepage.MAGAZINE_MODEL.setSupplements(supplementsList);
        }
    }

    public void clearInputs(TextField... inputs) {
        for (TextField input : inputs) {
            input.clear();
        }
    }



} // END OF CreateSupplementsController CLASS
