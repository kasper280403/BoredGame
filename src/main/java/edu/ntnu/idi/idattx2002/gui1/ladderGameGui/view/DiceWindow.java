package edu.ntnu.idi.idattx2002.gui1.ladderGameGui.view;

import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.DiceObserver;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class DiceWindow extends HBox implements DiceObserver{

    private Pane parent;

    private Random random;
    private Map<Dice, ImageView> diceMap2;
    private Map<Integer, Image> greenDiceImageMap;
    private HashMap<Integer, Image> orangeDiceImageMap;

    public DiceWindow(Pane parent) {
        this.parent = parent;
        init();
    }

    private void loadDiceImages() {
        if (diceMap2.isEmpty()) {
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

    private KeyFrame getDiceKeyFrame(Dice dice, int diceValue, Map<Integer, Image> diceImageMap,  double wait) {
      return new KeyFrame(Duration.millis(wait), e -> {
          Image diceImage = diceImageMap.get(diceValue);
          ImageView diceImageView = diceMap2.get(dice);

          if (diceImageView != null) {
              diceImageView.setImage(diceImage);
          }
      });
    }

    private ImageView getDiceImage(Dice dice, Map<Integer, Image> diceImageMap){
        Image diceImage = diceImageMap.get(dice.getCurrentValue());
        ImageView diceImageView = new ImageView(diceImage);

        diceImageView.setFitWidth(diceImage.getWidth() * 0.15);
        diceImageView.setFitHeight(diceImage.getHeight() * 0.15);
        diceImageView.setPreserveRatio(true);;

        return diceImageView;
    }

    private void throwDice(Dice dice) {
        Timeline timeline = new Timeline();

        for (int i = 0; i < 13; i++) {
            int randomDiceValue = random.nextInt(6)+1;
            int wait = (int) (Math.pow(1.77, i) + 100);

            KeyFrame keyFrame = getDiceKeyFrame(dice, randomDiceValue, orangeDiceImageMap, wait);
            timeline.getKeyFrames().add(keyFrame);
        }

        KeyFrame finalFrame = getDiceKeyFrame(dice, dice.getCurrentValue(), greenDiceImageMap, 2130);
        timeline.getKeyFrames().add(finalFrame);

        timeline.play();
    }

    public void initDice(Dice dice) {
        diceMap2.put(dice, getDiceImage(dice, greenDiceImageMap));
        getChildren().add(diceMap2.get(dice));
    }

    public void init(){
        random = new Random();
        diceMap2 = new HashMap<>();
        greenDiceImageMap = new HashMap<>();
        orangeDiceImageMap = new HashMap<>();
        loadDiceImages();

        setSpacing(10);
    }

    public void update(Dice dice) {
        throwDice(dice);
    }

    public void show() {
        parent.getChildren().add(this);
    }
}

