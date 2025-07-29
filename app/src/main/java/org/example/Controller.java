package org.example;

import javafx.fxml.FXML;

public class Controller {

  @FXML
  public void onPlayButton() {
    System.out.println("Play Button clicked!");
  }

  @FXML
  public void onPauseButton() {
    System.out.println("Pause Button clicked!");
  }

  @FXML
  public void onStopButton() {
    System.out.println("Stop Button clicked!");
  }

}
