import view.Homepage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Homepage homepage = new Homepage();
        try {
            homepage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}