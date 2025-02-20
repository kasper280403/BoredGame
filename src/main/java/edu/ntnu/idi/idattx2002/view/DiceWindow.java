package edu.ntnu.idi.idattx2002.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
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
    static HashMap<Integer, Image> diceImages = new HashMap<>();

    public static GridPane getDice(){
        dice.setHgap(10);
        dice.setVgap(10);
        dice.setStyle("-fx-background-color: black;");

        if (dices.isEmpty()) {
            diceImages.put(1, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice1.png"))));
            diceImages.put(2, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice2.png"))));
            diceImages.put(3, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice3.png"))));
            diceImages.put(4, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice4.png"))));
            diceImages.put(5, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice5.png"))));
            diceImages.put(6, new Image(Objects.requireNonNull(DiceWindow.class.getResourceAsStream("/images/dice6.png"))));

            ArrayList<ImageView> diceList = getImages(1, 1);
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
                ArrayList<ImageView> diceList = getImages(randomDiceA, randomDiceB);


                diceList.get(0).setEffect(diceEffect(0.8, 0.1));
                diceList.get(1).setEffect(diceEffect(0.8, 0.1));

                dices.put("diceA", diceList.get(0));
                dices.put("diceB", diceList.get(1));

                dice.getChildren().clear();
                dice.add(dices.get("diceA"), 0, 0);
                dice.add(dices.get("diceB"), 1, 0);
            });

            timeline.getKeyFrames().add(keyFrame);
        }
        KeyFrame finalFrame = new KeyFrame(Duration.millis(2200), e -> {
            ArrayList<ImageView> diceList = getImages(A, B);


            diceList.get(0).setEffect(diceEffect(0.7, 0.7));
            diceList.get(1).setEffect(diceEffect(0.7, 0.7));

            dices.put("diceA", diceList.get(0));
            dices.put("diceB", diceList.get(1));

            dice.getChildren().clear();
            dice.add(dices.get("diceA"), 0, 0);
            dice.add(dices.get("diceB"), 1, 0);
        });

        timeline.getKeyFrames().add(finalFrame);
        timeline.play();
    }

    private static ArrayList<ImageView> getImages(int A, int B){
        Image diceAA = diceImages.get(A);
        Image diceBB = diceImages.get(B);
        ImageView diceA = new ImageView(diceAA);
        ImageView diceB = new ImageView(diceBB);

        diceA.setFitWidth(diceAA.getWidth() * 0.1);
        diceA.setFitHeight(diceAA.getHeight() * 0.1);
        diceA.setPreserveRatio(true);
        diceB.setFitWidth(diceAA.getWidth() * 0.1);
        diceB.setFitHeight(diceAA.getHeight() * 0.1);
        diceB.setPreserveRatio(true);

        ArrayList<ImageView> diceList = new ArrayList<>();
        diceList.add(diceA);
        diceList.add(diceB);

        return diceList;
    }


    public static ColorAdjust diceEffect(Double saturation, Double hue){
        ColorAdjust diceEffect = new ColorAdjust();
        diceEffect.setSaturation(saturation);
        diceEffect.setHue(hue);
        diceEffect.setBrightness(0.4);
        return diceEffect;
    }
}

