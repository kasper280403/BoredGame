package edu.ntnu.idi.idattx2002.view;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.controller.SnakesAndLaddersController;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class SnakesAndLadderWindow extends HBox{

    private final SnakesAndLaddersController controller;
    public static Stage primaryStage;

    public SnakesAndLadderWindow(Stage stage, SnakesAndLaddersController controller) {
        primaryStage = stage;
        this.controller = controller;

        init();
    }

    private Button createPlayTurnButton() {
        Button playTurn = new Button("Throw Dice");
        PauseTransition pause = new PauseTransition(Duration.millis(2400));
        pause.setOnFinished(event -> playTurn.setDisable(false));
        playTurn.setOnAction(e -> {
            controller.playTurn();
            playTurn.setDisable(true);
            pause.play();

        });
        return playTurn;
    }

    public VBox createLeftSide() {
        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(createPlayTurnButton());
        return leftSide;
    }

    public void init() {
        getChildren().add(createLeftSide());

        Scene scene = new Scene(this, 900, 600);

        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void closeStage(){
        primaryStage.close();
    }

}




