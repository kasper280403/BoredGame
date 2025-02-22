package edu.ntnu.idi.idattx2002;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.PieceWindow;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class GameWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox(10);

        GridPane board = TilesWindow.getBoard(9, 10);
        GridPane dice = DiceWindow.getDicePane();

        PieceWindow.createPieces(50);

        TilesWindow.displayPieceAtTile(1, 1);
        TilesWindow.displayPieceAtTile(2, 2);

        Button colorChanger = new Button("Change Color");
        colorChanger.setOnAction(e -> changeColor(3));

        Button throwDice = new Button("Throw Dice");
        throwDice.setOnAction(e -> throwDice(5, 6));

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(board, colorChanger, throwDice);

        root.getChildren().addAll(leftSide, dice);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeColor(int tileNumber) {
        TilesWindow.changeTileColor(tileNumber, Color.RED);
    }

    public void throwDice(int A, int B) {
        DiceWindow.throwDice(A, B);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


