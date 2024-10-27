import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();
        Pane root = gameController.getGamePane();

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Catch the Falling Objects");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameController.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}