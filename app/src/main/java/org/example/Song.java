package org.example;

import java.nio.file.Path;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Song {
  private String title;
  private String artist;
  private Path pathToAudio;

  @Override
  public String toString() {
    return title + " - " + artist;
  }
}
