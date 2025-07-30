package org.example;

import javax.sound.sampled.Clip;

public class Song {
  private String title;
  private String artist;
  private String album;
  private int duration;
  private int durationPassed;
  private Clip clip;

  public Song(String title, String artist, int duration, Clip clip) {
    this.title = title;
    this.artist = artist;
    this.duration = duration;
    this.durationPassed = 0;
    this.clip = clip;
  }

  public void playsong() {
    if (clip != null) {
      clip.start();
    }
  }

  public void stopsong() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
    }
  }

  public boolean isPlaying() {
    return clip != null && clip.isRunning();
  }

  @Override
  public String toString() {
    return title + "-" + artist;
  }
}
