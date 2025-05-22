package edu.ntnu.idi.idattx2002.module.common.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Handles playback of music files from a local directory.
 *
 * <p>Supports playing a random MP3 file, pausing playback, and notifying an observer when a new
 * track is played.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class MusicPlayer {

  private MediaPlayer mediaPlayer;
  private final Random random;
  private final List<File> musicList;

  private MusicObserver observer;

  /** Constructs a new MusicPlayer and loads available music files. */
  public MusicPlayer() {
    random = new Random();
    musicList = new ArrayList<>();

    init();
  }

  /**
   * Registers an observer to be notified when a new song is played.
   *
   * @param observer the observer to notify
   */
  public void addObserver(MusicObserver observer) {
    this.observer = observer;
  }

  /**
   * Stops the current song if playing or paused, plays a new random song, and notifies the
   * observer. Automatically continues with the next song when the current one ends.
   */
  public void playNext() {
    if (mediaPlayer != null) {
      MediaPlayer.Status status = mediaPlayer.getStatus();
      if (status == MediaPlayer.Status.PLAYING || status == MediaPlayer.Status.PAUSED) {
        mediaPlayer.stop();
      }
      mediaPlayer.dispose();
    }

    if (musicList.isEmpty()) {
      loadMusic();
    }

    int randomSongId = random.nextInt(musicList.size());

    File songFile = musicList.get(randomSongId);
    Media songMedia = new Media(songFile.toURI().toString());
    mediaPlayer = new MediaPlayer(songMedia);

    mediaPlayer.play();
    mediaPlayer.setOnEndOfMedia(this::playNext);
    musicList.remove(randomSongId);

    observer.update(songFile.getName());
  }

  /** Pauses the currently playing song. */
  public void pause() {
    mediaPlayer.pause();
  }

  /** Loads all MP3 files from the predefined music directory. */
  private void loadMusic() {
    File musicFolder = new File("gameData/music");

    for (File file : musicFolder.listFiles()) {
      if (file.getName().toLowerCase().endsWith(".mp3")) {
        musicList.add(file);
      }
    }
  }

  /** Initializes the player by loading music files. */
  private void init() {
    loadMusic();
  }
}
