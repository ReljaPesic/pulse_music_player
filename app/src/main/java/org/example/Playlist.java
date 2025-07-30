package org.example;

import java.util.List;

public class Playlist {
  private String name;
  private List<Song> songsInPlaylist;

  public Playlist(String name, List<Song> songs) {
    this.name = name;
    this.songsInPlaylist = songs;
  }

  public Playlist() {
    this("Default Playlist", new java.util.ArrayList<>());
  }

  public Playlist(Playlist playlist) {
    this(playlist.name, playlist.songsInPlaylist);
  }

  public void addSong(Song song) {
    songsInPlaylist.add(song);
  }

  public void removeSong(Song song) {
    songsInPlaylist.remove(song);
  }

  public void shuffle() {
    java.util.Collections.shuffle(songsInPlaylist);
  }

  public List<Song> getSongsInPlaylist() {
    return songsInPlaylist;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Playlist : " + name + "\n");
    for (Song song : songsInPlaylist) {
      sb.append(song.toString()).append("\n");
    }
    return sb.toString();
  }
}
