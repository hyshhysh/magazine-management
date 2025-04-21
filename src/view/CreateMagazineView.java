package view;

import controller.CreateMagazineController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class CreateMagazineView extends Application {

    private Stage window;
    private static final String ICON_PATH = "mag-icon.png";
    private CreateMagazineController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        controller = new CreateMagazineController(this);
        Image icon = new Image(ICON_PATH);
        window.getIcons().add(icon);
        window.setTitle("Magazine Service - Create");

        // Logo
        VBox logo = createLogo();

        // Menu Buttons
        VBox buttonMenu = createButtonMenu();

        // Left menu pane
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(logo, buttonMenu);
        leftPane.setStyle("-fx-padding: 40; " +
                "-fx-background-color: #272626;");

        // CreateMagazineView magazine scene
        VBox topBox = new VBox(20);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(0,0,0,50));

        Label pageTitle = new Label("CREATE A MAGAZINE MODEL");
        pageTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        Label stepLabel = new Label("Step 1: Create a new magazine.");
        stepLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        topBox.getChildren().addAll(pageTitle, stepLabel);

        GridPane magCreatePage = new GridPane();
        magCreatePage.setAlignment(Pos.CENTER);
        magCreatePage.setHgap(20);
        magCreatePage.setVgap(20);
        magCreatePage.setPadding(new Insets(30,30,30,30));

        Label nameLabel = new Label("Magazine Name: ");
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        magCreatePage.add(nameLabel, 0, 2);

        TextField nameTextField = new TextField();
        magCreatePage.add(nameTextField, 1, 2);

        Label costLabel = new Label("Weekly Cost: ");
        costLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        magCreatePage.add(costLabel, 0, 3);

        TextField costTextField = new TextField();
        magCreatePage.add(costTextField, 1, 3);

        Button nextButton = new Button("NEXT");
        nextButton.setStyle("-fx-background-color: #FEC5BB;");
        nextButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        nextButton.setPadding(new Insets(5, 15, 5, 15));
        nextButton.setOnAction(e -> {
            controller.createNewMagazine(nameTextField, costTextField);
            switchToCreateSupplementsScene();
        });
        magCreatePage.add(nextButton, 0, 6);

        VBox magCreationRightPane = new VBox();
        magCreationRightPane.setAlignment(Pos.CENTER);
        magCreationRightPane.setPadding(new Insets(50,50,50,50));
        magCreationRightPane.getChildren().addAll(topBox, magCreatePage);

        HBox magCreationLayout = new HBox();
        magCreationLayout.getChildren().addAll(leftPane, magCreationRightPane);

        // Set scene and show
        Scene scene = new Scene(magCreationLayout, 1000, 600);
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
        Button createButton = new Button("► CREATE");
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

    private void switchToCreateSupplementsScene() {
        CreateSupplementsView createSuppScene = new CreateSupplementsView();
        try {
            createSuppScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



} // END OF CreateMagazineView CLASS
