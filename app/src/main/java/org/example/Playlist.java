package org.example;

import java.util.List;

public class Playlist {
  private String name;
  private List<Song> songs;
  private int songCount;

  public Playlist(List<Song> songs, int songCount, String name) {
    this.songs = songs;
    this.songCount = songCount;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addSong(Song song) {
    songs.add(song);
    songCount++;
  }

  public void displaySongs() {
    System.out.println("Playlist: " + name);
    for (int i = 0; i < songCount; i++) {
      System.out.println(songs.get(i).toString());
    }
  }
}
