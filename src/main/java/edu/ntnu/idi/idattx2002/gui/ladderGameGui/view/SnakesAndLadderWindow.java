package edu.ntnu.idi.idattx2002.gui.ladderGameGui.view;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersController;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SnakesAndLadderWindow extends HBox{

    private final SnakesAndLaddersController controller;
    public Pane mainPane;

    public SnakesAndLadderWindow(Pane mainPane, SnakesAndLaddersController controller) {
        this.mainPane = mainPane;
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
    }

    public void show() {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(this);
    }

}




