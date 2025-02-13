package edu.ntnu.idi.idattx2002;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snakes and ladders");

        BorderPane root = new BorderPane();

        TilesWindow tilesWindow = new TilesWindow();
        root.setCenter(TilesWindow.test());


        // Scene and window
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


