package edu.ntnu.idi.idattx2002;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
        GridPane dice = DiceWindow.getDice();

        Slider slider = new Slider(1, 90, 30);
        slider.setShowTickLabels(true);  // Viser tallene
        slider.setShowTickMarks(true);   // Viser streker for verdiene
        slider.setMajorTickUnit(25);     // Store steg
        slider.setMinorTickCount(5);     // Små steg mellom hver store verdi
        slider.setBlockIncrement(1);
        Label valueLabel = new Label("Verdi: " + slider.getValue());
        slider.valueProperty().addListener((obs, oldVal, newVal) ->
                valueLabel.setText("Verdi: " + String.format("%.2f", newVal))
        );

        Button colorChanger = new Button("Change Color");
        colorChanger.setOnAction(e -> {changeColor((int)slider.getValue());});


        Slider sliderA = new Slider(1, 6, 3);
        Slider sliderB = new Slider(1, 6, 3);
        sliderA.setSnapToTicks(true);
        sliderB.setSnapToTicks(true);
        sliderA.setBlockIncrement(1.0);
        Button throwDice = new Button("Throw Dice");
        throwDice.setOnAction(e -> {throwDice((int)sliderA.getValue(), (int)sliderB.getValue());});

        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(board, slider,valueLabel, colorChanger, sliderA, sliderB, throwDice);

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


