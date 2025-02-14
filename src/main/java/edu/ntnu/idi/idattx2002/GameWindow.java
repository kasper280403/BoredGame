package edu.ntnu.idi.idattx2002;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.awt.*;
import java.util.Random;

public class GameWindow extends Application {



    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(10);

        GridPane board = TilesWindow.getBoard(9, 10);

        Button colorChanger = new Button("Change Color");
        colorChanger.setOnAction(e -> {changeColor();});

        root.getChildren().addAll(board, colorChanger);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Snakes and ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeColor() {
        Random ran = new Random();
        int a = ran.nextInt(90);

        TilesWindow.changeTileColor(a, Color.RED);

    }

    public static void main(String[] args) {
        launch(args);
    }
}


