package edu.ntnu.idi.idattx2002.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DiceWindow {



    public static GridPane getDice(){
        GridPane gridpane = new GridPane();

        gridpane.setHgap(10);
        gridpane.setVgap(10);

        Image diceAA = new Image("images/dice1.png");
        Image diceBB = new Image("images/dice2.png");
        ImageView diceA = new ImageView(diceAA);
        ImageView diceB = new ImageView(diceBB);

        gridpane.add(diceA, 0, 0);
        gridpane.add(diceB, 0, 1);

        return gridpane;
    }
}
