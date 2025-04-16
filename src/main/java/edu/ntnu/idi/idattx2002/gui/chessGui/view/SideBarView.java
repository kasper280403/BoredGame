package edu.ntnu.idi.idattx2002.gui.chessGui.view;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import edu.ntnu.idi.idattx2002.gui.chessGui.controller.BoardController;

public class SideBarView extends VBox {

  private Pane parent;
  private BoardController boardController;

  int buttonWidth;
  int buttonHeight;

  public SideBarView(Pane parent, BoardController boardController) {
    this.boardController = boardController;
    this.parent = parent;

    buttonWidth = 80;
    buttonHeight = 30;

    init();
  }

  private Button createRevertButton() {
    Button revertBtn = new Button("Revert Move");
    revertBtn.setMinSize(buttonWidth, buttonHeight);
    revertBtn.setMaxSize(buttonWidth, buttonHeight);
    revertBtn.setOnAction(e -> {
      try {
        boardController.revertMove();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    return revertBtn;
  }

  private Button createFlipButton() {
    Button switchBtn = new Button("Flip");
    switchBtn.setMinSize(buttonWidth, buttonHeight);
    switchBtn.setMaxSize(buttonWidth, buttonHeight);
    switchBtn.setOnAction(e -> boardController.flipBoard());

    return switchBtn;
  }

  private VBox createAutoFlipButton() {
    VBox autoFlipBox = new VBox();
    autoFlipBox.setAlignment(Pos.CENTER);

    Text autoFlipText = new Text("Auto Flip");
    autoFlipText.setFont(new Font("Lucida Console", 20));
    autoFlipText.setFill(Color.WHITESMOKE);

    ToggleButton toggle = new ToggleButton("Off");
    toggle.setMinSize(buttonWidth, buttonHeight);
    toggle.setMaxSize(buttonWidth, buttonHeight);
    toggle.setOnAction(e -> boardController.autoFlipButtonAction(toggle));

    autoFlipBox.getChildren().addAll(autoFlipText, toggle);
    return autoFlipBox;
  }

  private Button createSavePositionButton() {
    Button savePositionBtn = new Button("Save Position");
    savePositionBtn.setMinSize(buttonWidth, buttonHeight);
    savePositionBtn.setMaxSize(buttonWidth, buttonHeight);
    savePositionBtn.setOnAction(e -> {
      try {
        boardController.savePosition();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    return savePositionBtn;
  }

  private void setSize(int xSize, int ySize) {
    setMinSize(xSize, ySize);
    setMaxSize(xSize, ySize);
  }

  private void setBackground() {
    double opacity = 0.4;
    Color backgroundColor = Color.BLACK.deriveColor(0, 1, 1, opacity);

    setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(20);
    setSize(300, 800);
    setBackground();

    getChildren().addAll(createFlipButton(), createAutoFlipButton(), createSavePositionButton(), createRevertButton());
  }

  public void show() {
    parent.getChildren().add(this);
  }
}
