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


public class SaveFileAlertBox {

    public static void display() {
        Stage window = new Stage();

        window.setTitle("Save As New File");;

        Label label = new Label("Save as:");
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(150);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String fileName = nameInput.getText() + ".ser";
            Homepage.MAGAZINE_MODEL.setFileName(fileName);
            FileHandler.serializeMagazineModel(Homepage.MAGAZINE_MODEL, fileName);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "File " + fileName +
                    " saved successfully!");
            alert.showAndWait();
            window.close();
        });


        Button closeButton = new Button("Cancel and close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, nameInput, saveButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
