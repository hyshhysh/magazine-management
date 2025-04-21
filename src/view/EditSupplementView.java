package view;

import controller.EditSupplementController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import magazine.Supplement;
import model.MagazineModel;

import java.io.FileInputStream;

public class EditSupplementView extends Application {

    private Stage window;
    private static final String ICON_PATH = "mag-icon.png";
    private EditSupplementController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        controller = new EditSupplementController(this);
        Image icon = new Image(ICON_PATH);
        window.getIcons().add(icon);
        window.setTitle("Magazine Service - Edit Supplement");

        // Logo
        VBox logo = createLogo();

        // Menu Buttons
        VBox buttonMenu = createButtonMenu();

        // Left menu pane
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(logo, buttonMenu);
        leftPane.setStyle("-fx-padding: 40; " +
                "-fx-background-color: #272626;");

        // Right pane top menu buttons

        Button suppButton = new Button("► SUPPLEMENTS");
        suppButton.setStyle("-fx-background-color: #DEFDE0;");
        suppButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        suppButton.setPadding(new Insets(8, 20, 8, 20));

        Button custButton = new Button("CUSTOMERS");
        custButton.setStyle("-fx-background-color: #D2D4DA;");
        custButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        custButton.setPadding(new Insets(8, 20, 8, 20));
        custButton.setOnAction(e -> {
            EditPayingCustomerView editCustScene = new EditPayingCustomerView();
            try {
                editCustScene.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox suppCustButtons = new HBox(10);
        suppCustButtons.setPadding(new Insets(50, 30, 20, 30));
        suppCustButtons.getChildren().addAll(suppButton, custButton);

        // Table view for supplements
        Label tableLabel = new Label("Double-click on a cell to edit values.");
        tableLabel.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 12));
        tableLabel.setPadding(new Insets(0,0,5,0));

        TableView<Supplement> suppTableView = new TableView<>();
        suppTableView.setEditable(true);
        suppTableView.setPrefHeight(300);
        if (Homepage.MAGAZINE_MODEL != null) {
            suppTableView.setItems(getSupplement(Homepage.MAGAZINE_MODEL));
        }


        TableColumn<Supplement, String> nameCol = new TableColumn<>("Supplement Name");
        nameCol.setMinWidth(400);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<Supplement, Double> costCol = new TableColumn<>("Weekly Cost");
        costCol.setMinWidth(200);
        costCol.setCellValueFactory(new PropertyValueFactory<>("weeklyCost"));
        costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        suppTableView.getColumns().addAll(nameCol, costCol);


        // VBox for table view
        VBox suppTableBox = new VBox();
        suppTableBox.setPadding(new Insets(20,50,10,50));
        suppTableBox.getChildren().addAll(tableLabel, suppTableView);

        // Bottom add/delete customers area
        //Inputs
        TextField nameInput, costInput;
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setPrefWidth(200);

        costInput = new TextField();
        costInput.setPromptText("Weekly Cost");
        costInput.setPrefWidth(100);

        nameCol.setOnEditCommit(event -> {
            Supplement supplement = event.getRowValue();
            supplement.setName(event.getNewValue());
        });

        costCol.setOnEditCommit(event -> {
            Supplement supplement = event.getRowValue();
            supplement.setWeeklyCost(event.getNewValue());
        });

        Button addButton = new Button("Add Supplement");
        addButton.setOnAction(e -> controller.addSupplement(nameInput, costInput, suppTableView,
                Homepage.MAGAZINE_MODEL));

        Button delButton = new Button("Delete Supplement");
        delButton.setOnAction(e -> controller.deleteSupplement(suppTableView, Homepage.MAGAZINE_MODEL));

        Button saveChangesButton = new Button("Save Changes");
        saveChangesButton.setOnAction(e -> controller.saveChanges(Homepage.MAGAZINE_MODEL));

        Button saveAsButton = new Button("Save As New File");
        saveAsButton.setOnAction(e -> controller.saveAsNewFile());

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10, 10, 10, 10));
        inputBox.setSpacing(10);
        inputBox.getChildren().addAll(nameInput, costInput);

        HBox addDelButtonBox = new HBox();
        addDelButtonBox.setAlignment(Pos.CENTER);
        addDelButtonBox.setPadding(new Insets(10, 10, 50, 10));
        addDelButtonBox.setSpacing(10);
        addDelButtonBox.getChildren().addAll(addButton, delButton, saveChangesButton, saveAsButton);

        // Bottom pane border pane
        VBox bottomPane = new VBox();
        bottomPane.getChildren().addAll(inputBox, addDelButtonBox);

        // Supplement right pane layout
        BorderPane suppRightPane = new BorderPane();
        suppRightPane.setTop(suppCustButtons);
        suppRightPane.setCenter(suppTableBox);
        suppRightPane.setBottom(bottomPane);


        // Border pane for supplement layout
        BorderPane suppPane = new BorderPane();
        suppPane.setLeft(leftPane);
        suppPane.setCenter(suppRightPane);

        // Set scene and show
        Scene scene = new Scene(suppPane, 1000, 600);
        window.setScene(scene);
        window.show();
    }

    public ObservableList<Supplement> getSupplement(MagazineModel magazineModel) {
        ObservableList<Supplement> supplements = FXCollections.observableArrayList();
        supplements.addAll(magazineModel.getSupplements());

        return supplements;
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
        Button editButton = new Button("► EDIT");
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


} // END OF EditSupplementView CLASS
