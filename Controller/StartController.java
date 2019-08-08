package Controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;
import javafx.util.*;

import java.io.*;

public class StartController {

  @FXML
  private RadioButton players2;
  @FXML
  private RadioButton players3;
  @FXML
  private RadioButton players4;

  @FXML
  private Button startButton, instructions, credits;

  @FXML
  private ImageView backdrop, muteButton;

  private ToggleGroup choices;
  private MediaPlayer player;

  public void initialize () {

    Image backdropImage = new Image("/Images/Main/Screens/Start Up.png");
    backdrop.setImage(backdropImage);

    choices = new ToggleGroup();

    players2.setToggleGroup(choices);
    players2.setStyle("-fx-cursor: hand");

    players3.setToggleGroup(choices);
    players3.setStyle("-fx-cursor: hand");

    players4.setToggleGroup(choices);
    players4.setStyle("-fx-cursor: hand");

    startButton.setDefaultButton(true);
    startButton.setStyle("-fx-cursor: hand");

    instructions.setStyle("-fx-cursor: hand");
    credits.setStyle("-fx-cursor: hand");

    choices.selectToggle(players2);

    muteButton.setImage(new Image("/Images/Main/Misc/Sound on.png"));
    muteButton.setStyle("-fx-cursor: hand");

    player = new MediaPlayer(new Media(getClass().getResource("../Theme.mp3").toExternalForm()));

    player.setAutoPlay(true);
    player.setVolume(9);
    player.play();

    player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));


  }

  public void setPlayer (MediaPlayer player) {
    this.player = player;
  }

  public void handleMute () {

    if (player.getVolume() > 0) {

      player.setVolume(0);
      muteButton.setImage(new Image("/Images/Main/Misc/Sound off.png"));

    } else {

      player.setVolume(9);
      muteButton.setImage(new Image("/Images/Main/Misc/Sound on.png"));

    }

  }

  public void handleStart (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NameAndGameType.fxml"));
      Parent root = loader.load();
      NameAndGameTypeController controller = loader.getController();

      controller.setNumPlayers(Integer.parseInt(((RadioButton) choices.getSelectedToggle()).getAccessibleText()));
      controller.setPlayer(player);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.centerOnScreen();
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void handleInstructions (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Instructions.fxml"));
      Parent root = loader.load();
      InstructionsController controller = loader.getController();

      controller.setPlayer(player);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.centerOnScreen();
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void handleCredits (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Credits.fxml"));
      Parent root = loader.load();
      CreditsController controller = loader.getController();

      controller.setPlayer(player);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.centerOnScreen();
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void handleExit () {
    System.exit(0);
  }

}
