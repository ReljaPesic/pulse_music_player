package org.example;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    } catch (UnsupportedAudioFileException e) {
      System.err.println("Unsupported audio file: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("IO error while accessing the file: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Some other error playing file: " + e.getMessage());
    }
  }

  public void pause() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
    }
  }

  public void stop() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
      clip.close();
    }
  }

  public boolean isPlaying() {
    return clip != null && clip.isRunning();
  }

  public void resume() {
    if (clip != null && !clip.isRunning()) {
      clip.start();
    }
  }
}
