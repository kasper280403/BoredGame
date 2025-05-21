package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.gui.common.controller.MusicController;
import edu.ntnu.idi.idattx2002.logic.common.music.MusicObserver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MusicView extends VBox implements MusicObserver {

  private final MusicController controller;
  private final Pane mainPane;

  private Text currentlyPlayingText;

  public MusicView(MusicController controller, Pane mainPane) {
    this.controller = controller;
    this.mainPane = mainPane;

    init();
  }

  private Text createTopInfoText() {
    Text topInfo = new Text("Currently Playing:");
    topInfo.setFont(new Font("Helvetica", 12));
    topInfo.setFill(Color.LIGHTBLUE);

    return topInfo;
  }

  private void createCurrentlyPlayingText() {
    currentlyPlayingText = new Text("Nothing :/");
    currentlyPlayingText.setFont(new Font("Helvetica", 18));
    currentlyPlayingText.setFill(Color.LIGHTBLUE);
  }

  private VBox createMusicInfoPane() {
    VBox musicInfoPane = new VBox();
    musicInfoPane.setAlignment(Pos.CENTER);
    musicInfoPane.setSpacing(3);

    createCurrentlyPlayingText();

    musicInfoPane.getChildren().addAll(createTopInfoText(), currentlyPlayingText);
    return musicInfoPane;
  }

  private Button createPlayBtn() {
    Button playMusicBtn = new Button("Play");
    playMusicBtn.setOnAction(e -> controller.playMusic());

    return playMusicBtn;
  }

  private Button createSkipBtn() {
    Button skipMusicBtn = new Button("Skip");
    skipMusicBtn.setOnAction(e -> controller.playMusic());

    return skipMusicBtn;
  }

  private Button createStopBtn() {
    Button stopMusicBtn = new Button("Stop");
    stopMusicBtn.setOnAction(e -> {
      controller.pauseMusic();
      currentlyPlayingText.setText("Nothing :/");
    });

    return stopMusicBtn;
  }

  private Pane createBtnPane() {
    HBox btnPane = new HBox();
    btnPane.setAlignment(Pos.CENTER);
    btnPane.setSpacing(5);

    btnPane.getChildren().addAll(createPlayBtn(), createSkipBtn(), createStopBtn());
    return btnPane;
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(5);
    setPadding(new Insets(15));
    setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(5), null)));
    setMaxSize(200, 75);

    StackPane.setAlignment(this, Pos.BOTTOM_LEFT);

    getChildren().addAll(createMusicInfoPane(), createBtnPane());
  }

  public void show() {
    mainPane.getChildren().add(this);
  }

  @Override
  public void update(String songName) {
    currentlyPlayingText.setText(songName);
  }

}
