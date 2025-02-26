package edu.ntnu.idi.idattx2002.view;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class GameWindow extends Application {

    SnakesAndLadders game = new SnakesAndLadders();

    @Override
    public void start(Stage primaryStage) {

        setUpGame();

        HBox root = new HBox(10);

        GridPane board = TilesWindow.getBoard(9, 10);
        GridPane dice = DiceWindow.getDicePane();

        PieceWindow.createPieces(75);

        TilesWindow.displayPieceAtTile(1, game.getPlayers().get(1).getPieceID());
        TilesWindow.displayPieceAtTile(1, game.getPlayers().get(2).getPieceID());

        Button throwDice = new Button("Throw Dice");
        throwDice.setOnAction(e -> playTurn());

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(board, throwDice);

        root.getChildren().addAll(leftSide, dice);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void randomlyPlacePlayer1() {
        Random random = new Random();
        TilesWindow.displayPieceAtTile(random.nextInt(90), 1);
    }

    public void playTurn() {
        game.playTurn();
    }


    public void setUpGame() {
        game.createBoard();
        game.addPlayer("Sindre", 1);
        game.addPlayer("kasper", 3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


