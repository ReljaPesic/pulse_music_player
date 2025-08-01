package org.example;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class Loader {
  private static Loader instance;
  private String songsJson = """
      [
        {
          "title": "Song 1",
          "artist": "Sins We Carry",
          "pathToAudio": "/home/relja/Desktop/pulse/app/build/resources/main/playlist/song1.wav"
        },
        {
          "title": "Kao final novi uvod",
          "artist": "Sins We Carry",
          "pathToAudio": "/home/relja/Desktop/pulse/app/build/resources/main/playlist/SWC kao finalNOVI UVOD.wav"
        },
        {
          "title": "Kao final",
          "artist": "Sins We Carry",
          "pathToAudio": "/home/relja/Desktop/pulse/app/build/resources/main/playlist/SWC kao final.wav"
        },
        {
          "title": "Song 2",
          "artist": "Sins We Carry",
          "pathToAudio": "/home/relja/Desktop/pulse/app/build/resources/main/playlist/uu jeaaa.wav"
        },
        {
          "title": "grooveend",
          "artist": "KMotherFuckingU",
          "pathToAudio": "/home/relja/Desktop/pulse/app/build/resources/main/playlist/grooveend.wav"
        }
      ]
      """;

  private Loader() {
  }

  public static Loader getInstance() {
    if (instance == null) {
      instance = new Loader();
    }
    return instance;
  }

  public List<Song> loadSongsFromJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(songsJson, new TypeReference<List<Song>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
