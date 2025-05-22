package edu.ntnu.idi.idattx2002.module.common.music;


/**
 * An interface for observers that want to receive updates about music changes.
 * <p>
 * Implementing classes should define how they react when a new song is played.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface MusicObserver {

  /**
   * Called when the observed subject updates with a new song.
   *
   * @param songName the name of the new song being played
   */
  void update(String songName);
}
