package view;

import helper.TestDataLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.MagazineModel;

import java.io.FileInputStream;

public class Homepage extends Application {

    Stage window;
    public static MagazineModel MAGAZINE_MODEL;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Image icon = new Image("mag-icon.png");
        window.getIcons().add(icon);
        window.setTitle("Magazine Service - Homepage");

        // Logo
        VBox logo = createLogo();

        // Menu Buttons
        VBox buttonMenu = createButtonMenu();

        // Left menu pane
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(logo, buttonMenu);
        leftPane.setStyle("-fx-padding: 40; " +
                "-fx-background-color: #272626;");



        // Right welcome message pane
        VBox welcomeMessage = new VBox(15);
        welcomeMessage.setAlignment(Pos.CENTER);

        Label message1 = new Label("Welcome to");
        message1.setFont(Font.font("Segoe UI", 35));

        Label message2 = new Label("Magazine Service");
        message2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 50));

        Label message3 = new Label("\nSelect an option to start.");
        message3.setPadding(new Insets(0,0,20,0));
        message3.setFont(Font.font("Segoe UI", 18));

        Button loadPresetButton = new Button("Load Preset Test Data");
        loadPresetButton.setStyle("-fx-background-color: #FEC5BB;");
        loadPresetButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        loadPresetButton.setPadding(new Insets(8, 32, 8, 32));
        loadPresetButton.setOnAction(e -> {
            MAGAZINE_MODEL = TestDataLoader.loadTestMagazineModel();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Preset test data loaded successfully!");
            alert.showAndWait();
        });

        Button loadFileButton = new Button("Load Data From File");
        loadFileButton.setStyle("-fx-background-color: #FEC5BB;");
        loadFileButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        loadFileButton.setPadding(new Insets(8, 36, 8, 36));
        loadFileButton.setOnAction(e -> {
            LoadFileAlertBox.display();
        });

        welcomeMessage.getChildren().addAll(message1, message2, message3, loadPresetButton, loadFileButton);


        // Border pane for whole layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftPane);
        borderPane.setCenter(welcomeMessage);


        // Set scene and show
        Scene scene = new Scene(borderPane, 1000, 600);
        window.setScene(scene);
        window.show();

    }









    //=========================== CLASS METHODS ==========================//

    private VBox createLogo() throws Exception {
        VBox logo = new VBox();
        logo.setPadding(new Insets(30, 0, 60, 0));
        logo.setAlignment(Pos.CENTER);
        FileInputStream input = new FileInputStream("src\\mag-icon.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> switchToHomepage());

        Button homeButton = new Button("HOMEPAGE");
        homeButton.setStyle("-fx-background-color: #272626;" + "-fx-text-fill: white");
        homeButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        homeButton.setOnAction(e -> switchToHomepage());

        logo.getChildren().addAll(imageView, homeButton);
        return logo;
    }

    private void switchToHomepage() {
        Homepage homeScene = new Homepage();
        try {
            homeScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private VBox createButtonMenu() {
        VBox buttonMenu = new VBox(25);
        buttonMenu.getChildren().addAll(createViewButton(), createCreateButton(), createEditButton());
        return buttonMenu;
    }

    private Button createViewButton() {
        Button viewButton = new Button("VIEW");
        viewButton.setStyle("-fx-background-color: #DEF3FD;");
        viewButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        viewButton.setPadding(new Insets(8, 30, 8, 30));
        viewButton.setOnAction(e -> switchToView());
        return viewButton;
    }

    private void switchToView() {
        ViewView viewViewScene = new ViewView();
        try {
            viewViewScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Button createCreateButton() {
        Button createButton = new Button("CREATE");
        createButton.setStyle("-fx-background-color: #DEFDE0;");
        createButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        createButton.setPadding(new Insets(8, 24, 8, 24));
        createButton.setOnAction(e -> switchToCreate());
        return createButton;
    }

    private void switchToCreate() {
        CreateMagazineView createMagazineViewScene = new CreateMagazineView();
        try {
            createMagazineViewScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Button createEditButton() {
        Button editButton = new Button("EDIT");
        editButton.setStyle("-fx-background-color: #D2D4DA;");
        editButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        editButton.setPadding(new Insets(8, 32, 8, 32));
        editButton.setOnAction(e -> switchToEditSupplement());
        return editButton;
    }

    private void switchToEditSupplement() {
        EditSupplementView editScene = new EditSupplementView();
        try {
            editScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


} // END OF Homepage CLASS
