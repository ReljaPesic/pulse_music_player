package org.example;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player {
  private Clip clip;
  private static Player instance;

  private Player() {
  }

  public static Player getInstance() {
    if (instance == null) {
      instance = new Player();
    }
    return instance;
  }

  public void play(Song song) {
    stop(); // Stop any currently playing song
    try {
      File file = new File(song.getPath().toString());
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (Exception e) {
      System.err.println("Some error playing file: " + e.getMessage());
    }
  }

  public void pause() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
    }
  }

  public void resume() {
    if (clip != null && !clip.isRunning()) {
      clip.start();
    }
  }

  public void stop() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
      clip.close();
      clip = null; // Clear the clip reference
    }
  }

  public boolean isPlaying() {
    return clip != null && clip.isRunning();
  }

  public boolean isPaused() {
    return clip != null && !clip.isRunning();
  }
}
