package view;

import controller.CreatePayingCustsController;
import controller.EditPayingCustomerController;
import customer.Customer;
import customer.PayingCustomer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import magazine.Supplement;

import java.io.FileInputStream;
import java.util.ArrayList;

public class CreatePayingCustsView extends Application {

    private Stage window;
    private static final String ICON_PATH = "mag-icon.png";
    private CreatePayingCustsController controller;
    private ArrayList<Supplement> supplementArrayList = new ArrayList<>();
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        controller = new CreatePayingCustsController(this);
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

        // Create magazine scene
        VBox topBox = new VBox(20);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(0,0,0,50));

        Label pageTitle = new Label("CREATE A MAGAZINE MODEL");
        pageTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        Label stepLabel = new Label("Step 3: Create paying customers.");
        stepLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        topBox.getChildren().addAll(pageTitle, stepLabel);

        GridPane payingCustCreatePage = new GridPane();
        payingCustCreatePage.setAlignment(Pos.CENTER);
        payingCustCreatePage.setHgap(20);
        payingCustCreatePage.setVgap(20);
        payingCustCreatePage.setPadding(new Insets(0,30,30,30));

        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        payingCustCreatePage.add(nameLabel, 0, 2);

        TextField nameTextField = new TextField();
        payingCustCreatePage.add(nameTextField, 1, 2);

        Label emailLabel = new Label("Email: ");
        emailLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        payingCustCreatePage.add(emailLabel, 0, 3);

        TextField emailTextField = new TextField();
        payingCustCreatePage.add(emailTextField, 1, 3);

        Label addressLabel = new Label("Address: ");
        addressLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        payingCustCreatePage.add(addressLabel, 0, 4);

        TextField streetNumTextField = new TextField();
        streetNumTextField.setPromptText("Street Number");
        payingCustCreatePage.add(streetNumTextField, 1, 4);

        TextField streetNameTextField = new TextField();
        streetNameTextField.setPromptText("Street Name");
        payingCustCreatePage.add(streetNameTextField, 2, 4);

        TextField suburbTextField = new TextField();
        suburbTextField.setPromptText("Suburb");
        payingCustCreatePage.add(suburbTextField, 1, 5);

        TextField postCodeTextField = new TextField();
        postCodeTextField.setPromptText("Postcode");
        payingCustCreatePage.add(postCodeTextField, 2, 5);

        Label addSuppLabel = new Label("Add supplements: ");
        addSuppLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        payingCustCreatePage.add(addSuppLabel, 0, 6);

        Label paymentInfoLabel = new Label("Payment Info: ");
        paymentInfoLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        payingCustCreatePage.add(paymentInfoLabel, 0, 7);

        TextField paymentMethodTextField = new TextField();
        paymentMethodTextField.setPromptText("Credit Card or Bank Acc");
        payingCustCreatePage.add(paymentMethodTextField, 1, 7);

        TextField paymentDetailsTextField = new TextField();
        paymentDetailsTextField.setPromptText("Payment Details");
        payingCustCreatePage.add(paymentDetailsTextField, 2, 7);


        ComboBox<String> suppList = new ComboBox<>();
        if (Homepage.MAGAZINE_MODEL != null) {
            if (Homepage.MAGAZINE_MODEL.getSupplements() != null
                    && !Homepage.MAGAZINE_MODEL.getSupplements().isEmpty()) {
                for (Supplement supp : Homepage.MAGAZINE_MODEL.getSupplements()) {
                    suppList.getItems().add(supp.getName());
                }
            }
        }
        suppList.setPromptText("Select Supplements");
        payingCustCreatePage.add(suppList, 1, 6);

        Button addSuppButton = new Button("Add");
        addSuppButton.setOnAction(e -> {
            for (Supplement supp : Homepage.MAGAZINE_MODEL.getSupplements()) {
                if (suppList.getValue().equalsIgnoreCase(supp.getName())) {
                    supplementArrayList.add(supp);
                }
            }
            Button addedSuppButton = new Button("Added!");
            addedSuppButton.setStyle("-fx-background-color: transparent;");
            addedSuppButton.setFont(Font.font("Segoe UI", 12));
            payingCustCreatePage.add(addedSuppButton, 3, 6);
        });
        payingCustCreatePage.add(addSuppButton, 2, 6);


        Button addedButton = new Button("");
        addedButton.setStyle("-fx-background-color: transparent;");
        addedButton.setFont(Font.font("Segoe UI", 14));
        addedButton.setPadding(new Insets(-10,0,0,50));
        payingCustCreatePage.add(addedButton, 0, 10);

        Button anotherButton = new Button("ADD ANOTHER");
        anotherButton.setStyle("-fx-background-color: #FAEDCB;");
        anotherButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        anotherButton.setPadding(new Insets(5, 15, 5, 15));
        anotherButton.setOnAction(e -> controller.addCustomer(nameTextField, streetNumTextField,
                streetNameTextField, suburbTextField, postCodeTextField, emailTextField, paymentMethodTextField,
                paymentDetailsTextField, suppList, customerArrayList, supplementArrayList, addedButton));
        payingCustCreatePage.add(anotherButton, 0, 9);

        Button nextButton = new Button("NEXT");
        nextButton.setStyle("-fx-background-color: #FEC5BB;");
        nextButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        nextButton.setPadding(new Insets(5, 15, 5, 15));
        nextButton.setOnAction(e -> {
            controller.createCustomersList(nameTextField, streetNumTextField,
                    streetNameTextField, suburbTextField, postCodeTextField, emailTextField, paymentMethodTextField,
                    paymentDetailsTextField, suppList, customerArrayList, supplementArrayList, addedButton);
            switchToCreateAssociateCustomers();
        });
        payingCustCreatePage.add(nextButton, 1, 9);

        VBox magCreationRightPane = new VBox();
        magCreationRightPane.setAlignment(Pos.CENTER);
        magCreationRightPane.setPadding(new Insets(50,50,50,50));
        magCreationRightPane.getChildren().addAll(topBox, payingCustCreatePage);

        HBox magCreationLayout = new HBox();
        magCreationLayout.getChildren().addAll(leftPane, magCreationRightPane);

        // Set scene and show
        Scene scene = new Scene(magCreationLayout, 1000, 600);
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

    private void switchToCreateAssociateCustomers() {
        CreateAssociateCustomersView createAssCustScene = new CreateAssociateCustomersView();
        try {
            createAssCustScene.start(window);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

} // END OF CreatePayingCustsView CLASS
