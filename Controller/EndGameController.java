package Controller;

import Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

public class EndGameController {

  @FXML
  private Button mainMenu;

  @FXML
  private ImageView backdrop, muteButton;

  @FXML
  private Label centerLabel;

  private Game masterObject;
  private MediaPlayer player;

  public void initialize () {
    backdrop.setImage(new Image("/Images/Main/Screens/End Game.png"));

    mainMenu.setDefaultButton(true);
  }

  public void setGameAndRankPlayers (Game game) {
    this.masterObject = game;

    ArrayList <Player> ranked = masterObject.rankPlayers();
    centerLabel.setText("Player Ranking\n\n");

    for (Player p : ranked) {

      if (p.getWorth() > 0)
        centerLabel.setText(centerLabel.getText() + p.getName() + " with a total net worth of $" + p.getWorth() + "\n");

      else
        centerLabel.setText(centerLabel.getText() + p.getName() + " with a total net worth of $" + (p.getWorth() - p.getCash()) + " and liabilities of $" + p.getCash() + "\n");

    }

  }

  public void setPlayer (MediaPlayer player) {
    this.player = player;

    if (player.getVolume() > 0)
      muteButton.setImage(new Image("Images/Main/Misc/Sound on.png"));

    else
      muteButton.setImage(new Image("Images/Main/Misc/Sound off.png"));
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

  public void handleExit () {
    System.exit(0);
  }

  public void handleBack (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Start.fxml"));
      Parent root = loader.load();
      StartController controller = loader.getController();

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

}
