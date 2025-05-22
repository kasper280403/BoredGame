package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.controller.chess.BoardController;
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

/**
 * Sidebar view for the Chess game.
 *
 * <p>Displays user feedback and provides controls such as board flipping and auto-flip toggling.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class SideBarView extends VBox {

  private final Pane parent;
  private final BoardController boardController;

  private Text userFeedback;

  private final int buttonWidth;
  private final int buttonHeight;

  public SideBarView(Pane parent, BoardController boardController) {
    this.boardController = boardController;
    this.parent = parent;

    buttonWidth = 80;
    buttonHeight = 30;

    init();
  }

  public void setUserExceptionFeedback(String message) {
    userFeedback.setText(userFeedback.getText() + "\n" + message);
  }

  public void setUserFeedback(String message) {
    userFeedback.setText(message);
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

  private void setSize(int xSize, int ySize) {
    setMinSize(xSize, ySize);
    setMaxSize(xSize, ySize);
  }

  private void setBackground() {
    double opacity = 0.4;
    Color backgroundColor = Color.BLACK.deriveColor(0, 1, 1, opacity);

    setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
  }

  private void initUserFeedback() {
    userFeedback = new Text();
    userFeedback.setFill(Color.WHITE);
    userFeedback.setFont(new Font("Helvatica", 20));
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(20);
    setSize(300, 800);
    setBackground();

    initUserFeedback();

    getChildren().addAll(userFeedback, createFlipButton(), createAutoFlipButton());
  }

  public void show() {
    parent.getChildren().add(this);
  }
}
