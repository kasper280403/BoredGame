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
import javafx.scene.text.Text;
import javafx.util.Duration;


public class SnakesAndLaddersView extends HBox{

    private final SnakesAndLaddersController controller;
    private final Pane mainPane;

    private VBox leftPane;
    private StackPane centerPane;
    private VBox rightPane;

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

    public VBox getRightPane() {
        return rightPane;
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
        moveInfo = new Text("MOVEINFO MOVEINFO MOVEINFO");
    }

    private void createLeftPane() {
        leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setSpacing(50);

        Color backgroundColor = Color.web("#5CE1E6").deriveColor(1, 1, 1, 0.3);
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
        centerPane.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
    }

    private void createRightPane() {
        rightPane = new VBox();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setSpacing(50);
        rightPane.setPadding(new Insets(20));

        Color backgroundColor = Color.web("#5CE1E6").deriveColor(1, 1, 1, 0.3);
        rightPane.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

        rightPane.setMaxHeight(750);
        rightPane.setMinHeight(750);

        String rules = """
            YOLO YOLO YOLO YOLO
            YOLO YOLO YOLO YOLO
            YOLO YOLO YOLO YOLO
            YOLO YOLO YOLO YOLO
            YOLO YOLO YOLO YOLO
            YODO ...
            """;

        Text rulesText = new Text(rules);
        rightPane.getChildren().add(rulesText);
    }

    public void init() {
        setBackground(new Background(new BackgroundFill(Color.web("#1B263B"), null, null)));

        setSpacing(50);
        setAlignment(Pos.CENTER);

        createCenterPane();
        createLeftPane();
        createRightPane();

        getChildren().addAll(leftPane, centerPane, rightPane);
    }

    public void show() {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(this);
    }
}




