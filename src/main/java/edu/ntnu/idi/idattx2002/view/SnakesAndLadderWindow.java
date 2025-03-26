package edu.ntnu.idi.idattx2002.view;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class SnakesAndLadderWindow{

    private final SnakesAndLadders game;
    public static Stage primaryStage;

    public SnakesAndLadderWindow(Stage stage, List<String> players, List<Integer> pieces) {
        primaryStage = stage;
        this.game = new SnakesAndLadders();

        setUpGame(players, pieces);
        StartGame();
    }

    public void StartGame(){
        double size = 50.0;
        HBox root = new HBox(10);

        GridPane board = TilesWindow.getBoard(9, 10, size, game);
        GridPane dice = game.getDiceView().getDicePane();

        PieceWindow.createPieces(size);
        TilesWindow.displayPieceAtTile(1, game.getPlayers().get(1).getPieceID());
        TilesWindow.displayPieceAtTile(1, game.getPlayers().get(2).getPieceID());


        Button playTurn = new Button("Throw Dice");
        PauseTransition pause = new PauseTransition(Duration.millis(2400));
        pause.setOnFinished(event -> playTurn.setDisable(false));
        playTurn.setOnAction(e -> {
            playTurn();
            playTurn.setDisable(true);
            pause.play();

        });

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(board, playTurn);

        root.getChildren().addAll(leftSide, dice);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void playTurn() {
        game.playTurn();
    }

    public void setUpGame(List<String> players, List<Integer> pieces) {
        game.createBoard();
        for (int i = 0; i < players.size(); i++) {
            game.addPlayer(players.get(i), pieces.get(i));
        }
        game.setLandActions();
    }

    public static void closeStage(){
        primaryStage.close();
    }

}




