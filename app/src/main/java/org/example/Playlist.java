package org.example;

import java.util.List;

public class Playlist {
  private String name;
  private List<Song> songs;
  private int currentSongIndex;

  public Playlist(String name, List<Song> songs) {
    this.name = name;
    this.songs = songs;
    this.currentSongIndex = 0;
  }

  public Playlist() {
    this("Default Playlist", new java.util.ArrayList<>());
  }

  public Playlist(Playlist playlist) {
    this(playlist.name, playlist.songs);
  }

  public void playCurrentSong() {
    if (currentSongIndex < songs.size()) {
      Song currentSong = songs.get(currentSongIndex);
      currentSong.playsong();
    } else {
      System.out.println("No song to play.");
    }
  }

  public void addSong(Song song) {
    songs.add(song);
  }

  public void removeSong(Song song) {
    songs.remove(song);
  }

  public void displaySongs() {
    for (Song song : songs) {
      System.out.println(song);
    }
  }

  public void shuffle() {
    java.util.Collections.shuffle(songs);
  }

  public List<Song> getSongs() {
    return songs;
  }

  @Override
  public String toString() {
    return "Playlist " + name + " with " + songs;
  }
}
