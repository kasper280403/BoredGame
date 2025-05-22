package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility class for generating image views of chess pieces.
 *
 * <p>Maps each {@code Piece} to its corresponding image based on type and color, and returns it as
 * a scaled {@code ImageView}.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
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
    switch (piece) {
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
