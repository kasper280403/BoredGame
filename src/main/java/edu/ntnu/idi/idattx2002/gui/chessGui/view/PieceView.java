package edu.ntnu.idi.idattx2002.gui.chessGui.view;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;

public class PieceView {

  private final int size;
  private final String standardLocation;

  public PieceView(int pieceSize) {
    size = pieceSize;
    standardLocation = "/chessResources/pieceImages";
  }

  public ImageView getPieceView(Piece piece) {

    String colorString = piece.getColor() == ChessColor.WHITE ? "white" : "black";
    String pieceString = getPieceString(piece);

    String location = "/" + colorString + "-" + pieceString + ".png";
    return createImageView(standardLocation + location);
  }

  private String getPieceString(Piece piece) {
    String pieceString;
    switch(piece){
      case Pawn p -> pieceString = "pawn";
      case Rook p -> pieceString = "rook";
      case Horse h -> pieceString = "knight";
      case Bishop b -> pieceString = "bishop";
      case Queen q -> pieceString = "queen";
      case King k -> pieceString = "king";
      default -> pieceString = "null";
    }
    return pieceString;
  }

  private ImageView createImageView(String location) {
    ImageView piece = new ImageView(new Image(getClass().getResource(location).toExternalForm()));

    piece.setFitWidth(size);
    piece.setFitHeight(size);
    return piece;
  }

}
