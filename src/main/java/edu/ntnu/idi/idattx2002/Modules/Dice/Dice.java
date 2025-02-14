package edu.ntnu.idi.idattx2002.Modules.Dice;

import java.util.Random;

public class Dice {

    public Dice(){}

    public int roll(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
