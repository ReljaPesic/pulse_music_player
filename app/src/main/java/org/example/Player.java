package org.example;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;

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
    try (
        InputStream fis = Files.newInputStream(song.getPath());
        BufferedInputStream bis = new BufferedInputStream(fis);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);) {
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
    if (clip != null) {
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
