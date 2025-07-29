package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout.fxml"));
    Parent root = loader.load();

    stage.setTitle("JavaFX Application");
    stage.setScene(new Scene(root, 800, 600));
    stage.show();
  }
}
