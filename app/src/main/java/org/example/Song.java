package org.example;

import java.nio.file.Path;
import javafx.scene.media.Media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Song {
  private String title;
  private String artist;
  private Path pathToAudio;

  public Song(String title, String artist, Path pathToAudio) {
    this.title = title;
    this.artist = artist;
    this.pathToAudio = pathToAudio;
  }

  @Override
  public String toString() {
    return title + " - " + artist;
  }

  public Media getMedia() {
    return new Media(pathToAudio.toUri().toString());
  }
}
