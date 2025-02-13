package edu.ntnu.idi.idattx2002.view;
import javafx.scene.layout.VBox;



public class TilesWindow{


    public static VBox getBoard(int xDimensions, int yDimensions){
        VBox box = new VBox();
        box.setStyle("-fx-background-color: lightblue; -fx-padding: 20px; -fx-alignment: center;");
        for (int i = 0; i < xDimensions; i++) {

            for (int j = 0; j < yDimensions; j++) {

            }
        }

        return box;
    }

}
