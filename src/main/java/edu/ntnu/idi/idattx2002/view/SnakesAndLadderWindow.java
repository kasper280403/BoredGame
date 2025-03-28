package edu.ntnu.idi.idattx2002.view;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class SnakesAndLadderWindow extends HBox{

    private final SnakesAndLadders game;
    public static Stage primaryStage;

    public SnakesAndLadderWindow(Stage stage, List<String> players, List<Integer> pieces) {
        primaryStage = stage;
        this.game = new SnakesAndLadders(this);

        setUpGame(players, pieces);
        StartGame();
    }

    //TODO make board display at left side
    //Logic should be factored away
    public void StartGame(){
        double size = 50.0;

        game.getPieceWindow().createPieces(size);
        game.getTilesView().displayPieceAtTile(1, game.getPlayers().get(1).getPieceID());
        game.getTilesView().displayPieceAtTile(1, game.getPlayers().get(2).getPieceID());


        Button playTurn = new Button("Throw Dice");
        PauseTransition pause = new PauseTransition(Duration.millis(2400));
        pause.setOnFinished(event -> playTurn.setDisable(false));
        playTurn.setOnAction(e -> {
            playTurn();
            playTurn.setDisable(true);
            pause.play();

        });

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(playTurn);

        getChildren().addAll(leftSide);
        game.getDiceView().show();
        game.getTilesView().show();

        Scene scene = new Scene(this, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void playTurn() {
        game.playTurn();
    }

    //Should be move into snakeandLadders
    public void setUpGame(List<String> players, List<Integer> pieces) {
        for (int i = 0; i < players.size(); i++) {
            game.addPlayer(players.get(i), pieces.get(i));
        }

        //game id må velges på en eller annen måte
        String gameID = "RegularSnakesAndLadders";
        game.setActions(gameID);
    }

    public static void closeStage(){
        primaryStage.close();
    }

}




