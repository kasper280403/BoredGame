package edu.ntnu.idi.idattx2002.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class DiceWindow {

    private static final GridPane dice = new GridPane();
    private static final HashMap<String, ImageView> dices = new HashMap<>();
    static HashMap<Integer, Image> greenDiceImages = new HashMap<>();
    static HashMap<Integer, Image> orangeDiceImages = new HashMap<>();

    public static GridPane getDice(){
        dice.setHgap(10);
        dice.setVgap(10);

        if (dices.isEmpty()) {
            greenDiceImages.put(1, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice1.png"))));
            greenDiceImages.put(2, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice2.png"))));
            greenDiceImages.put(3, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice3.png"))));
            greenDiceImages.put(4, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice4.png"))));
            greenDiceImages.put(5, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice5.png"))));
            greenDiceImages.put(6, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/greenDice6.png"))));

            orangeDiceImages.put(1, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice1.png"))));
            orangeDiceImages.put(2, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice2.png"))));
            orangeDiceImages.put(3, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice3.png"))));
            orangeDiceImages.put(4, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice4.png"))));
            orangeDiceImages.put(5, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice5.png"))));
            orangeDiceImages.put(6, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dices/orangeDice6.png"))));

            ArrayList<ImageView> diceList = getGreenImages(1, 1);
            dices.put("diceA", diceList.get(0));
            dices.put("diceB", diceList.get(1));

            dice.add(diceList.get(0), 0, 0);
            dice.add(diceList.get(1), 1, 0);
        }

        return dice;
    }

    public static void throwDice(int A, int B){

        Random random  = new Random();
        Timeline timeline = new Timeline();

        for (int i = 0; i < 13; i++) {
            int randomDiceA = random.nextInt(6)+1;
            int randomDiceB = random.nextInt(6)+1;
            int wait = (int) (Math.pow(1.77, i) + 100);


            KeyFrame keyFrame = new KeyFrame(Duration.millis(wait), e -> {
                ArrayList<ImageView> diceList = getOrangeImages(randomDiceA, randomDiceB);

                dices.put("diceA", diceList.get(0));
                dices.put("diceB", diceList.get(1));

                dice.getChildren().clear();
                dice.add(dices.get("diceA"), 0, 0);
                dice.add(dices.get("diceB"), 1, 0);
            });

            timeline.getKeyFrames().add(keyFrame);
        }
        KeyFrame finalFrame = new KeyFrame(Duration.millis(2200), e -> {
            ArrayList<ImageView> diceList = getGreenImages(A, B);

            dices.put("diceA", diceList.get(0));
            dices.put("diceB", diceList.get(1));

            dice.getChildren().clear();
            dice.add(dices.get("diceA"), 0, 0);
            dice.add(dices.get("diceB"), 1, 0);
        });

        timeline.getKeyFrames().add(finalFrame);
        timeline.play();
    }

    private static ArrayList<ImageView> getGreenImages(int A, int B){
        Image diceAA = greenDiceImages.get(A);
        Image diceBB = greenDiceImages.get(B);
        ImageView diceA = new ImageView(diceAA);
        ImageView diceB = new ImageView(diceBB);

        diceA.setFitWidth(diceAA.getWidth() * 0.15);
        diceA.setFitHeight(diceAA.getHeight() * 0.15);
        diceA.setPreserveRatio(true);
        diceB.setFitWidth(diceAA.getWidth() * 0.15);
        diceB.setFitHeight(diceAA.getHeight() * 0.15);
        diceB.setPreserveRatio(true);

        ArrayList<ImageView> greenDiceList = new ArrayList<>();
        greenDiceList.add(diceA);
        greenDiceList.add(diceB);

        return greenDiceList;
    }

    private static ArrayList<ImageView> getOrangeImages(int A, int B){
        Image diceAA = orangeDiceImages.get(A);
        Image diceBB = orangeDiceImages.get(B);
        ImageView diceA = new ImageView(diceAA);
        ImageView diceB = new ImageView(diceBB);

        diceA.setFitWidth(diceAA.getWidth() * 0.15);
        diceA.setFitHeight(diceAA.getHeight() * 0.15);
        diceA.setPreserveRatio(true);
        diceB.setFitWidth(diceAA.getWidth() * 0.15);
        diceB.setFitHeight(diceAA.getHeight() * 0.15);
        diceB.setPreserveRatio(true);

        ArrayList<ImageView> orangeDiceList = new ArrayList<>();
        orangeDiceList.add(diceA);
        orangeDiceList.add(diceB);

        return orangeDiceList;
    }
}

