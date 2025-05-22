package edu.ntnu.idi.idattx2002.view.common;

import edu.ntnu.idi.idattx2002.controller.common.StartMenuController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Root view container for the application.
 *
 * <p>Provides a consistent layout including a background, utility buttons (Start Menu, Close App),
 * and a content pane for loading game-specific views.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class MainView extends StackPane {

  private StackPane contentPane;
  private final StartMenuController controller;

  public MainView(StartMenuController controller) {
    this.controller = controller;

    init();
  }

  public Pane getContentPane() {
    return contentPane;
  }

  private Button createCloseAppBtn() {
    Button closeAppBtn = new Button("Close App");
    closeAppBtn.setOnAction(
        e -> {
          Platform.exit();
          System.exit(0);
        });

    return closeAppBtn;
  }

  private Button createStartMenuBtn() {
    Button startMenuBtn = new Button("Start Menu");
    startMenuBtn.setOnAction(e -> controller.getStartMenuView().show());

    return startMenuBtn;
  }

  private VBox createUtilityPane() {
    VBox utilityPane = new VBox();
    utilityPane.setSpacing(10);
    utilityPane.setPadding(new Insets(15));
    utilityPane.setBackground(
        new Background(new BackgroundFill(Color.GREY, new CornerRadii(5), null)));
    utilityPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    StackPane.setAlignment(utilityPane, Pos.BOTTOM_RIGHT);

    utilityPane.getChildren().addAll(createStartMenuBtn(), createCloseAppBtn());
    return utilityPane;
  }

  private void setBackground() {
    setBackground(new Background(new BackgroundFill(Color.web("#4C82A8"), null, null)));

    Image backgroundImage = new Image("background.jpg");
    ImageView backgroundImageView = new ImageView(backgroundImage);

    backgroundImageView.setFitWidth(getWidth());
    backgroundImageView.setFitHeight(getHeight());
    backgroundImageView.setPreserveRatio(false);

    backgroundImageView.fitWidthProperty().bind(widthProperty());
    backgroundImageView.fitHeightProperty().bind(heightProperty());

    backgroundImageView.setOpacity(0.4);

    getChildren().add(backgroundImageView);
  }

  private void initContentPane() {
    contentPane = new StackPane();
    getChildren().add(contentPane);
  }

  private void init() {
    setBackground();
    initContentPane();
    getChildren().add(createUtilityPane());
  }
}
