package edu.ntnu.idi.idattx2002.view;

import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import java.util.Map;
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

    private Random random;
    private GridPane dicePane;
    private final Map<String, ImageView> diceMap;
    private Map<Integer, Image> greenDiceImageMap;
    private HashMap<Integer, Image> orangeDiceImageMap;

    public DiceWindow() {
        random = new Random();
        dicePane = new GridPane();

        diceMap = new HashMap<>();

       greenDiceImageMap = new HashMap<>();
       orangeDiceImageMap = new HashMap<>();
    }

    public GridPane getDicePane(){
        dicePane.setHgap(10);
        dicePane.setVgap(10);

        loadDiceImages();

        ArrayList<ImageView> diceList = getDiceImages(1, 1, greenDiceImageMap);
        diceMap.put("diceA", diceList.get(0));
        diceMap.put("diceB", diceList.get(1));

        dicePane.add(diceList.get(0), 0, 0);
        dicePane.add(diceList.get(1), 1, 0);

        return dicePane;
    }

    private void loadDiceImages() {
        if (diceMap.isEmpty()) {
            greenDiceImageMap.put(1, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice1.png"))));
            greenDiceImageMap.put(2, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice2.png"))));
            greenDiceImageMap.put(3, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice3.png"))));
            greenDiceImageMap.put(4, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice4.png"))));
            greenDiceImageMap.put(5, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice5.png"))));
            greenDiceImageMap.put(6, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/greenDice6.png"))));

            orangeDiceImageMap.put(1, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice1.png"))));
            orangeDiceImageMap.put(2, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice2.png"))));
            orangeDiceImageMap.put(3, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice3.png"))));
            orangeDiceImageMap.put(4, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice4.png"))));
            orangeDiceImageMap.put(5, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice5.png"))));
            orangeDiceImageMap.put(6, new Image(Objects.requireNonNull(
                DiceWindow.class.getResourceAsStream("/images/dices/orangeDice6.png"))));
        }
    }

    public void throwDice(int A, int B){

        Timeline timeline = new Timeline();

        for (int i = 0; i < 13; i++) {
            int diceValueA = random.nextInt(6)+1;
            int diceValueB = random.nextInt(6)+1;
            int wait = (int) (Math.pow(1.77, i) + 100);

            KeyFrame keyFrame = getDicesKeyFrame(diceValueA, diceValueB, orangeDiceImageMap, wait);
            timeline.getKeyFrames().add(keyFrame);
        }

        KeyFrame finalFrame = getDicesKeyFrame(A, B, greenDiceImageMap, 2130);
        timeline.getKeyFrames().add(finalFrame);

        timeline.play();
    }

    private KeyFrame getDicesKeyFrame(int diceValueA, int diceValueB, Map<Integer, Image> diceImageMap,  double wait) {
      return new KeyFrame(Duration.millis(wait), e -> {
          ArrayList<ImageView> diceList = getDiceImages(diceValueA, diceValueB, diceImageMap);

          diceMap.put("diceA", diceList.get(0));
          diceMap.put("diceB", diceList.get(1));

          dicePane.getChildren().clear();
          dicePane.add(diceMap.get("diceA"), 0, 0);
          dicePane.add(diceMap.get("diceB"), 1, 0);
      });
    }

    private ArrayList<ImageView> getDiceImages(int A, int B, Map<Integer, Image> diceImageMap){
        Image diceAA = diceImageMap.get(A);
        Image diceBB = diceImageMap.get(B);
        ImageView diceA = new ImageView(diceAA);
        ImageView diceB = new ImageView(diceBB);

        diceA.setFitWidth(diceAA.getWidth() * 0.15);
        diceA.setFitHeight(diceAA.getHeight() * 0.15);
        diceA.setPreserveRatio(true);
        diceB.setFitWidth(diceAA.getWidth() * 0.15);
        diceB.setFitHeight(diceAA.getHeight() * 0.15);
        diceB.setPreserveRatio(true);

        ArrayList<ImageView> diceList = new ArrayList<>();
        diceList.add(diceA);
        diceList.add(diceB);

        return diceList;
    }
}

