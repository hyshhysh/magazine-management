package view;

import controller.ViewController;
import customer.Customer;
import customer.PayingCustomer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import magazine.Supplement;

import java.io.FileInputStream;

public class ViewView extends Application {

    private Stage window;
    private Scene scene;
    private static final String ICON_PATH = "mag-icon.png";
    private ViewController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        controller = new ViewController(this);
        Image icon = new Image(ICON_PATH);
        window.getIcons().add(icon);
        window.setTitle("Magazine Service - View");

        // Logo
        VBox logo = createLogo();

        // Menu Buttons
        VBox buttonMenu = createButtonMenu();

        // Left menu pane
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(logo, buttonMenu);
        leftPane.setStyle("-fx-padding: 40; " +
                "-fx-background-color: #272626;");


        Label suppHeader = new Label("Supplements");
        suppHeader.setPadding(new Insets(10, 0, 0, 5));
        suppHeader.setFont(Font.font("Segoe UI", 13));

        ListView<String> suppList = new ListView<>();
        suppList.setStyle("-fx-background-color: #272626;");
        if (Homepage.MAGAZINE_MODEL != null) {
            for (Supplement supp : Homepage.MAGAZINE_MODEL.getSupplements()) {
                suppList.getItems().add(supp.getName());
            }
        } else {
            suppList.getItems().add("No supplements loaded.");
        }

        Label magTitle;
        if (Homepage.MAGAZINE_MODEL != null) {
            magTitle = new Label("Magazine: " + Homepage.MAGAZINE_MODEL.getMagazine().getName());
        } else {
            magTitle = new Label("No magazine loaded.");
        }
        magTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        VBox suppBox = new VBox(10);
        suppBox.setPadding(new Insets(50, 50, 0, 70));
        suppBox.setPrefHeight(260);
        suppBox.getChildren().addAll(magTitle, suppHeader, suppList);


        Label custHeader = new Label("Customers");
        custHeader.setPadding(new Insets(-20, 0, 0, 5));
        custHeader.setFont(Font.font("Segoe UI", 13));

        ListView<String> custList = new ListView<>();
        custList.setStyle("-fx-background-color: #272626;");

        if (Homepage.MAGAZINE_MODEL != null) {
            for (Customer cust : Homepage.MAGAZINE_MODEL.getCustomers()) {
                custList.getItems().add(cust.getName());
            }
        } else {
            custList.getItems().add("No customers loaded.");
        }

        VBox custBox = new VBox(10);
        custBox.setPadding(new Insets(-20, 50, 50, 70));
        custBox.setPrefHeight(270);
        custBox.getChildren().addAll(custHeader, custList);


        // Right pane - information panel
        Label infoHeader = new Label("Information");
        infoHeader.setPadding(new Insets(0, 0, 0, 5));
        infoHeader.setFont(Font.font("Segoe UI", 13));

        TextArea infoArea = new TextArea();
        infoArea.setPrefHeight(200);
        infoArea.setEditable(false);

        Label billHistHeader = new Label("Billing History");
        billHistHeader.setPadding(new Insets(0, 0, 0, 5));
        billHistHeader.setFont(Font.font("Segoe UI", 13));

        TextArea billHistArea = new TextArea();
        billHistArea.setPrefHeight(200);
        billHistArea.setEditable(false);

        suppList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            infoArea.setText(controller.showSupplementInformation(newValue));
        });

        custList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            infoArea.setText(controller.showCustomerInformation(newValue));
        });

        custList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            billHistArea.setText("");
            controller.showBillingHistory(billHistArea, newValue);
        });


        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(50, 80, 0, 50));
        infoBox.setPrefWidth(450);
        infoBox.getChildren().addAll(infoHeader, infoArea);

        VBox billHistBox = new VBox(10);
        billHistBox.setPadding(new Insets(-20, 50, 50, 50));
        billHistBox.setPrefWidth(320);
        billHistBox.setPrefHeight(270);
        billHistBox.getChildren().addAll(billHistHeader, billHistArea);

        BorderPane rightPane = new BorderPane();
        rightPane.setTop(infoBox);
        rightPane.setBottom(billHistBox);

        BorderPane middlePane = new BorderPane();
        middlePane.setTop(suppBox);
        middlePane.setBottom(custBox);

        // Border pane for whole layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftPane);
        borderPane.setCenter(middlePane);
        borderPane.setRight(rightPane);

        // Set scene and show
        scene = new Scene(borderPane, 1000, 600);
        window.setScene(scene);
        window.show();
    }

    public Scene getScene() {
        return scene;
    }

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
        Button viewButton = new Button("► VIEW");
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


} // END OF ViewView CLASS
