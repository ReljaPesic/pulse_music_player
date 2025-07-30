package org.example;

import java.nio.file.Path;

public class Song {
  private String title;
  private String artist;
  private Path path;

  public Song(String title, String artist, Path path) {
    this.title = title;
    this.artist = artist;
    this.path = path;
  }

  @Override
  public String toString() {
    return title + "-" + artist;
  }

  public Path getPath() {
    return path;
  }
}
