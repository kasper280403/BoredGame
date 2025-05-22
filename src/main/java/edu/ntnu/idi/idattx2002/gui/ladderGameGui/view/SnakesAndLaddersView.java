package edu.ntnu.idi.idattx2002.gui.ladderGameGui.view;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersController;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class SnakesAndLaddersView extends HBox{

    private final SnakesAndLaddersController controller;
    private final Pane mainPane;

    private VBox leftPane;
    private StackPane centerPane;

    private Text moveInfo;


    public SnakesAndLaddersView(Pane mainPane, SnakesAndLaddersController controller) {
        this.mainPane = mainPane;
        this.controller = controller;
        init();
    }

    public Pane getLeftPane() {
        return leftPane;
    }

    public Pane getCenterPane() {
        return centerPane;
    }

    public void setMoveInfo(String message) {
        moveInfo.setText(message);
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

    private void createMoveInfo() {
        moveInfo = new Text();
        moveInfo.setFont(new Font("Helvetica", 20));
        moveInfo.setFill(Color.WHITE);
    }

    private void createLeftPane() {
        leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setSpacing(50);

        Color backgroundColor = Color.BLACK.deriveColor(1, 1, 1, 0.4);
        leftPane.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

        createMoveInfo();

        leftPane.getChildren().addAll(createPlayTurnButton(), moveInfo);

        leftPane.setMaxHeight(750);
        leftPane.setMinHeight(750);
    }

    private void createCenterPane() {
        centerPane = new StackPane();
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    private void init() {
        setSpacing(50);
        setAlignment(Pos.CENTER);

        createCenterPane();
        createLeftPane();

        getChildren().addAll(leftPane, centerPane);
    }

    public void show() {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(this);
    }
}




