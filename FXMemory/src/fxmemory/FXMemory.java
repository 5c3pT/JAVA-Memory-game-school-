package fxmemory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author paul
 */
public class FXMemory extends Application {
  @Override
    /**
     * Start methode
     */
    public void start(Stage primaryStage) {
        Pane menuPane = new Pane();
        Scene scene = new Scene(menuPane, 400, 500);
        new menu(menuPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}