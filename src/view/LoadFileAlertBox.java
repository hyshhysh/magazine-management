package view;

import helper.FileHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadFileAlertBox {

    public static void display() {
        Stage window = new Stage();

        window.setTitle("Load Data File");;

        Label label = new Label("Please enter name of file to load: ");
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(150);

        Button loadButton = new Button("Load File");
        loadButton.setOnAction(e -> {
            String fileName = nameInput.getText() + ".ser";
            Homepage.MAGAZINE_MODEL = FileHandler.deserializeMagazineModel(fileName);
            Homepage.MAGAZINE_MODEL.setFileName(fileName);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "File " + fileName +
                    " loaded successfully!");
            alert.showAndWait();
            window.close();
        });

        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, nameInput, loadButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
