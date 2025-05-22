package edu.ntnu.idi.idattx2002.view.ladderGame;

import edu.ntnu.idi.idattx2002.module.ladderGame.Dice.Dice;
import edu.ntnu.idi.idattx2002.module.ladderGame.DiceObserver;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * View component for displaying animated dice rolls in Snakes and Ladders.
 * <p>
 * Implements {@code DiceObserver} to react to dice updates. Uses a {@code Timeline} animation
 * to visually simulate the dice rolling, displaying interim and final dice face images.
 * </p>
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */

public class DiceView extends HBox implements DiceObserver{

    private final Pane parent;

    private final Random random;
    private final Map<Dice, ImageView> diceMap;
    private final Map<Integer, Image> greenDiceImageMap;
    private final Map<Integer, Image> orangeDiceImageMap;

    public DiceView(Pane parent) {
        random = new Random();
        diceMap = new HashMap<>();
        greenDiceImageMap = new HashMap<>();
        orangeDiceImageMap = new HashMap<>();

        this.parent = parent;
        init();
    }

    private void loadDiceImages() {
        String standardLocation = "/ladderGameResources/images/dices/";
        if (diceMap.isEmpty()) {
            greenDiceImageMap.put(1, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice1.png"))));
            greenDiceImageMap.put(2, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice2.png"))));
            greenDiceImageMap.put(3, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice3.png"))));
            greenDiceImageMap.put(4, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice4.png"))));
            greenDiceImageMap.put(5, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice5.png"))));
            greenDiceImageMap.put(6, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "greenDice6.png"))));

            orangeDiceImageMap.put(1, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice1.png"))));
            orangeDiceImageMap.put(2, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice2.png"))));
            orangeDiceImageMap.put(3, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice3.png"))));
            orangeDiceImageMap.put(4, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice4.png"))));
            orangeDiceImageMap.put(5, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice5.png"))));
            orangeDiceImageMap.put(6, new Image(Objects.requireNonNull(
                DiceView.class.getResourceAsStream(standardLocation + "orangeDice6.png"))));
        }
    }

    private KeyFrame getDiceKeyFrame(Dice dice, int diceValue, Map<Integer, Image> diceImageMap,  double wait) {
      return new KeyFrame(Duration.millis(wait), e -> {
          Image diceImage = diceImageMap.get(diceValue);
          ImageView diceImageView = diceMap.get(dice);

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
        diceMap.put(dice, getDiceImage(dice, greenDiceImageMap));
        getChildren().add(diceMap.get(dice));
    }

    private void init(){
        loadDiceImages();

        setSpacing(10);
        setAlignment(Pos.CENTER);
    }

    public void update(Dice dice) {
        throwDice(dice);
    }

    public void show() {
        parent.getChildren().addFirst(this);
    }
}

