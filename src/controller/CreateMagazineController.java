package controller;

import javafx.scene.control.TextField;
import magazine.Magazine;
import model.MagazineModel;
import view.CreateMagazineView;
import view.Homepage;

public class CreateMagazineController {

    private CreateMagazineView view;

    public CreateMagazineController(CreateMagazineView view) {
        this.view = view;
    }

    public void createNewMagazine(TextField nameInput, TextField costInput) {
        String name = nameInput.getText();
        double cost = Double.parseDouble(costInput.getText());
        Homepage.MAGAZINE_MODEL = new MagazineModel();
        Homepage.MAGAZINE_MODEL.setMagazine(new Magazine(name, cost));
    }



}  // END OF CreateMagazineController CLASS
