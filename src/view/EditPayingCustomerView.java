package view;

import customer.Customer;
import customer.PayingCustomer;
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
import model.MagazineModel;
import controller.EditPayingCustomerController;

import java.io.FileInputStream;

public class EditPayingCustomerView extends Application {

    private Stage window;
    private static final String ICON_PATH = "mag-icon.png";
    private EditPayingCustomerController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        controller = new EditPayingCustomerController(this);
        Image icon = new Image(ICON_PATH);
        window.getIcons().add(icon);
        window.setTitle("Magazine Service - Edit Paying Customer");

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
        HBox suppCustButtons = createTopMenuButtons();

        // Table view for customers
        TableView<Customer> customerTableView = createCustomerTableView();

        // VBox for table view
        VBox custTableBox = new VBox();
        custTableBox.setPadding(new Insets(20, 50, 10, 50));
        custTableBox.getChildren().addAll(createTableLabel(), customerTableView);

        // Bottom add/delete customers area
        HBox inputBox = createInputBox();
        HBox addDelButtonBox = createAddDelButtonBox(customerTableView, inputBox);

        // Bottom pane border pane
        VBox bottomPane = new VBox();
        bottomPane.getChildren().addAll(inputBox, addDelButtonBox);

        // Supplement right pane layout
        BorderPane suppRightPane = new BorderPane();
        suppRightPane.setTop(suppCustButtons);
        suppRightPane.setCenter(custTableBox);
        suppRightPane.setBottom(bottomPane);

        // Border pane for customer layout
        BorderPane custPane = new BorderPane();
        custPane.setLeft(leftPane);
        custPane.setCenter(suppRightPane);

        // Set scene and show
        Scene scene = new Scene(custPane, 1000, 600);
        window.setScene(scene);
        window.show();
    }







    //========================= CLASS METHODS ===========================//

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

    private HBox createTopMenuButtons() {
        Button suppButton = new Button("SUPPLEMENTS");
        suppButton.setStyle("-fx-background-color: #D2D4DA;");
        suppButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        suppButton.setPadding(new Insets(8, 20, 8, 20));
        suppButton.setOnAction(e -> switchToEditSupplement());

        Button custButton = new Button("► CUSTOMERS");
        custButton.setStyle("-fx-background-color: #DEFDE0;");
        custButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        custButton.setPadding(new Insets(8, 20, 8, 20));

        Button payingCustButton = new Button("► PAYING");
        payingCustButton.setStyle("-fx-background-color: #DEF3FD;");
        payingCustButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        payingCustButton.setPadding(new Insets(6, 18, 6, 18));

        Button assCustButton = new Button("ASSOCIATE");
        assCustButton.setStyle("-fx-background-color: #D2D4DA;");
        assCustButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        assCustButton.setPadding(new Insets(6, 18, 6, 18));
        assCustButton.setOnAction(e -> switchToEditAssociateCust());

        HBox suppCustButtons = new HBox(10);
        suppCustButtons.setPadding(new Insets(50, 30, 20, 30));
        suppCustButtons.getChildren().addAll(suppButton, custButton, payingCustButton, assCustButton);
        return suppCustButtons;
    }

    private void switchToEditAssociateCust() {
        EditAssociateCustomerView editAssCust = new EditAssociateCustomerView();
        try {
            editAssCust.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Label createTableLabel() {
        Label tableLabel = new Label("Double-click on a cell to edit values.");
        tableLabel.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 12));
        tableLabel.setPadding(new Insets(0, 0, 5, 0));
        return tableLabel;
    }

    private TableView<Customer> createCustomerTableView() {
        TableView<Customer> customerTableView = new TableView<>();
        customerTableView.setEditable(true);
        customerTableView.setPrefHeight(300);
        if (Homepage.MAGAZINE_MODEL != null) {
            customerTableView.setItems(getCustomer(Homepage.MAGAZINE_MODEL));
        }

        // Table columns
        customerTableView.getColumns().addAll(createNameColumn(), createEmailColumn(), createAddressColumn(),
                createPaymentMethodColumn(), createPaymentDetailsColumn());

        return customerTableView;
    }

    private TableColumn<Customer, String> createNameColumn() {
        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(130);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nameCol.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setName(event.getNewValue());
        });
        return nameCol;
    }

    private TableColumn<Customer, String> createEmailColumn() {
        TableColumn<Customer, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailAdd"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        emailCol.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setEmailAdd(event.getNewValue());
        });
        return emailCol;
    }

    private TableColumn<Customer, String> createAddressColumn() {
        TableColumn<Customer, String> addressCol = new TableColumn<>("Address");
        addressCol.setMinWidth(235);
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        addressCol.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setAddress(event.getNewValue());
        });
        return addressCol;
    }

    private TableColumn<Customer, String> createPaymentMethodColumn() {
        TableColumn<Customer, String > paymentMethodCol = new TableColumn<>("Payment Method");
        paymentMethodCol.setMinWidth(118);
        paymentMethodCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        paymentMethodCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        paymentMethodCol.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            ((PayingCustomer) customer).setPaymentMethod(event.getNewValue());
        });
        return paymentMethodCol;
    }

    private TableColumn<Customer, String> createPaymentDetailsColumn() {
        TableColumn<Customer, String> paymentDetCol = new TableColumn<>("Payment Details");
        paymentDetCol.setMinWidth(130);
        paymentDetCol.setCellValueFactory(new PropertyValueFactory<>("paymentDetails"));
        paymentDetCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        paymentDetCol.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            ((PayingCustomer) customer).setPaymentDetails(event.getNewValue());
        });
        return paymentDetCol;
    }

    private HBox createInputBox() {
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setPrefWidth(110);

        TextField emailInput = new TextField();
        emailInput.setPromptText("Email Address");
        emailInput.setPrefWidth(100);

        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");
        addressInput.setPrefWidth(150);

        TextField paymentMethodInput = new TextField();
        paymentMethodInput.setPromptText("Payment Method");
        paymentMethodInput.setPrefWidth(120);

        TextField paymentDetailsInput = new TextField();
        paymentDetailsInput.setPromptText("Payment Details");
        paymentDetailsInput.setPrefWidth(120);

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10, 10, 10, 10));
        inputBox.setSpacing(10);
        inputBox.getChildren().addAll(nameInput, emailInput, addressInput, paymentMethodInput, paymentDetailsInput);
        return inputBox;
    }

    private HBox createAddDelButtonBox(TableView<Customer> customerTableView, HBox inputBox) {
        Button addButton = new Button("Add Customer");
        addButton.setOnAction(e -> controller.addCustomer((TextField) inputBox.getChildren().get(0),
                (TextField) inputBox.getChildren().get(1),
                (TextField) inputBox.getChildren().get(2),
                (TextField) inputBox.getChildren().get(3),
                (TextField) inputBox.getChildren().get(4),
                customerTableView, Homepage.MAGAZINE_MODEL));

        Button delButton = new Button("Delete Customer");
        delButton.setOnAction(e -> controller.deleteCustomer(customerTableView, Homepage.MAGAZINE_MODEL));

        Button saveChangesButton = new Button("Save Changes");
        saveChangesButton.setOnAction(e -> controller.saveChanges(Homepage.MAGAZINE_MODEL));

        Button saveAsButton = new Button("Save As New File");
        saveAsButton.setOnAction(e -> controller.saveAsNewFile());

        HBox addDelButtonBox = new HBox();
        addDelButtonBox.setAlignment(Pos.CENTER);
        addDelButtonBox.setPadding(new Insets(10, 10, 50, 10));
        addDelButtonBox.setSpacing(10);
        addDelButtonBox.getChildren().addAll(addButton, delButton, saveChangesButton, saveAsButton);
        return addDelButtonBox;
    }

    public ObservableList<Customer> getCustomer(MagazineModel magazineModel) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        for (Customer cust : magazineModel.getCustomers()) {
            if (cust.getCustomerType().equalsIgnoreCase("Paying")) {
                customers.add(cust);
            }
        }

        return customers;
    }


} // END OF EditPayingCustomerView CLASS