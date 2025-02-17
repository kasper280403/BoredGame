package edu.ntnu.idi.idattx2002;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox(10);

        GridPane board = TilesWindow.getBoard(9, 10);
        GridPane dice = DiceWindow.getDice();

        Button colorChanger = new Button("Change Color");
        colorChanger.setOnAction(e -> {changeColor();});

        Button throwDice = new Button("Throw Dice");
        throwDice.setOnAction(e -> {throwDice(4, 6);});

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(board, colorChanger, throwDice);

        root.getChildren().addAll(leftSide, dice);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeColor() {
        Random ran = new Random();
        int a = ran.nextInt(90);

        TilesWindow.changeTileColor(a, Color.RED);

    }

    public void throwDice(int A, int B) {
        DiceWindow.throwDice(A, B);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


