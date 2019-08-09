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

public class OrderPlayersController {

  private Game masterObject;
  private ArrayList <Integer> diceResults = new ArrayList <> ();
  private ArrayList <Button> buttonSet = new ArrayList <> ();

  @FXML
  private ImageView backdrop, muteButton;

  @FXML
  private Button arrangeBoard, randomizeBoard, rollP1, rollP2, rollP3, rollP4, confirm;

  @FXML
  private Label playerSequence, player1Name, player2Name, player3Name, player4Name;

  @FXML
  private ImageView dice1_1, dice1_2, dice2_1, dice2_2, dice3_1, dice3_2, dice4_1, dice4_2;

  private MediaPlayer player;

  public void initialize () {

    muteButton.setStyle("-fx-cursor: hand");

    backdrop.setImage(new Image("/Images/Main/Screens/Order Players Screen 4.png"));

    arrangeBoard.setVisible(false);
    arrangeBoard.setStyle("-fx-cursor: hand");
    randomizeBoard.setVisible(false);
    randomizeBoard.setStyle("-fx-cursor: hand");

    playerSequence.setVisible(false);

    rollP1.setStyle("-fx-cursor: hand");
    rollP2.setStyle("-fx-cursor: hand");
    rollP3.setStyle("-fx-cursor: hand");
    rollP4.setStyle("-fx-cursor: hand");
    confirm.setStyle("-fx-cursor: hand");

    buttonSet.add(rollP1);
    buttonSet.add(rollP2);
    buttonSet.add(rollP3);
    buttonSet.add(rollP4);

    confirm.setVisible(false);

  }

  public void setGame (Game game) {

    this.masterObject = game;

    if (masterObject.getNumPlayers() == 4) {
      player1Name.setText(masterObject.getPlayerList().get(0).getName() + ", roll the dice.");
      player2Name.setText(masterObject.getPlayerList().get(1).getName() + ", roll the dice.");
      player3Name.setText(masterObject.getPlayerList().get(2).getName() + ", roll the dice.");
      player4Name.setText(masterObject.getPlayerList().get(3).getName() + ", roll the dice.");
    }

    if (masterObject.getNumPlayers() == 3) {
      rollP4.setVisible(false);
      rollP4.setDisable(true);
      player4Name.setVisible(false);
      backdrop.setImage(new Image("/Images/Main/Screens/Order Players Screen 3.png"));

      player1Name.setText(masterObject.getPlayerList().get(0).getName() + ", roll the dice.");
      player2Name.setText(masterObject.getPlayerList().get(1).getName() + ", roll the dice.");
      player3Name.setText(masterObject.getPlayerList().get(2).getName() + ", roll the dice.");
    }

    if (masterObject.getNumPlayers() == 2) {
      rollP4.setVisible(false);
      rollP4.setDisable(true);
      player4Name.setVisible(false);
      rollP3.setVisible(false);
      rollP3.setDisable(true);
      player3Name.setVisible(false);
      backdrop.setImage(new Image("/Images/Main/Screens/Order Players Screen 2.png"));

      player1Name.setText(masterObject.getPlayerList().get(0).getName() + ", roll the dice.");
      player2Name.setText(masterObject.getPlayerList().get(1).getName() + ", roll the dice.");
    }

    for (int i = 0; i < masterObject.getNumPlayers(); i++)
      diceResults.add(0);

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

  public void handleRoll1 () {

    rollP1.setDisable(true);

    int curr1 = rollDice();
    int curr2 = rollDice();

    dice1_1.setImage(new Image("/Images/Main/Dice/" + curr1 + ".png"));
    dice1_2.setImage(new Image("/Images/Main/Dice/" + curr2 + ".png"));

    diceResults.set(0, curr1 + curr2);
    findDuplicate();

  }

  public void handleRoll2 () {

    rollP2.setDisable(true);

    int curr1 = rollDice();
    int curr2 = rollDice();

    dice2_1.setImage(new Image("/Images/Main/Dice/" + curr1 + ".png"));
    dice2_2.setImage(new Image("/Images/Main/Dice/" + curr2 + ".png"));

    diceResults.set(1, curr1 + curr2);
    findDuplicate();

  }

  public void handleRoll3 () {

    rollP3.setDisable(true);

    int curr1 = rollDice();
    int curr2 = rollDice();

    dice3_1.setImage(new Image("/Images/Main/Dice/" + curr1 + ".png"));
    dice3_2.setImage(new Image("/Images/Main/Dice/" + curr2 + ".png"));

    diceResults.set(2, curr1 + curr2);
    findDuplicate();

  }

  public void handleRoll4 () {

    rollP4.setDisable(true);

    int curr1 = rollDice();
    int curr2 = rollDice();

    dice4_1.setImage(new Image("/Images/Main/Dice/" + curr1 + ".png"));
    dice4_2.setImage(new Image("/Images/Main/Dice/" + curr2 + ".png"));

    diceResults.set(3, curr1 + curr2);
    findDuplicate();

  }

  public int rollDice () {

    Random rndGen = new Random();

    return rndGen.nextInt(6) + 1;

  }

  public void findDuplicate () {

    int i, j;

    for (i = 0; i < diceResults.size() - 1; i++) {

      for (j = i + 1; j < diceResults.size(); j++)

        if (diceResults.get(i) == diceResults.get(j)) {

          buttonSet.get(i).setDisable(false);
          buttonSet.get(j).setDisable(false);

        }

    }

    orderIfNoDups();

  }

  public void orderIfNoDups () {

    for (Button b : buttonSet)
      if (!b.isDisable())
        return;

    confirm.setVisible(true);
    confirm.setDefaultButton(true);

  }

  public void handleContinue () {

    confirm.setVisible(false);
    backdrop.setImage(new Image("/Images/Main/Screens/Order Players Screen Blank.png"));

    for (Button b : buttonSet)
      b.setVisible(false);

    player1Name.setVisible(false);
    player2Name.setVisible(false);
    player3Name.setVisible(false);
    player4Name.setVisible(false);

    dice1_1.setVisible(false);
    dice1_2.setVisible(false);
    dice2_1.setVisible(false);
    dice2_2.setVisible(false);
    dice3_1.setVisible(false);
    dice3_2.setVisible(false);
    dice4_1.setVisible(false);
    dice4_2.setVisible(false);

    masterObject.orderPlayers(diceResults);
    playerSequence.setVisible(true);

    playerSequence.setText("Sequence of Players\n\n" + masterObject.playerOrderString());
    arrangeBoard.setVisible(true);
    randomizeBoard.setVisible(true);

    arrangeBoard.setDefaultButton(true);

  }

  public void handleArrange (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardStartUpDrag.fxml"));
      Parent root = loader.load();
      BoardStartUpDragController boardController = loader.getController();

      boardController.setPlayer(player);
      boardController.setGame(masterObject);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

  public void handleRandom (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardStartUpRandom.fxml"));
      Parent root = loader.load();
      BoardStartUpRandomController boardController = loader.getController();

      boardController.setPlayer(player);
      boardController.setGame(masterObject);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

}
