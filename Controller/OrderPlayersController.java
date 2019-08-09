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
  private int currPlayer = 0;

  @FXML
  private ImageView backdrop, dice1, dice2, muteButton;

  @FXML
  private Button dice, arrangeBoard, randomizeBoard;

  @FXML
  private TextField currPlayerName;

  @FXML
  private Label playerSequence;

  @FXML
  private TextArea bg;

  private Image backdropHolder, dice1Holder, dice2Holder;
  private MediaPlayer player;

  public void initialize () {

    backdropHolder = new Image("/Images/Main/Screens/Order Players Screen.png");
    dice1Holder = new Image("/Images/Main/Dice/6.png");
    dice2Holder = new Image("/Images/Main/Dice/6.png");

    muteButton.setStyle("-fx-cursor: hand");

    backdrop.setImage(backdropHolder);

    arrangeBoard.setVisible(false);
    arrangeBoard.setStyle("-fx-cursor: hand");
    randomizeBoard.setVisible(false);
    randomizeBoard.setStyle("-fx-cursor: hand");

    dice.setDefaultButton(true);

    playerSequence.setVisible(false);
    bg.setVisible(false);

    dice.setStyle("-fx-cursor: hand");

  }

  public void setGame (Game game) {

    this.masterObject = game;
    currPlayerName.setText(masterObject.getPlayerList().get(currPlayer).getName() + ", please roll the dice.");

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

  public void handleDice () {

    int curr1 = rollDice();
    dice1Holder = new Image("/Images/Main/Dice/" + curr1 + ".png");

    int curr2 = rollDice();
    dice2Holder = new Image("/Images/Main/Dice/" + curr2 + ".png");

    dice1.setImage(dice1Holder);
    dice2.setImage(dice2Holder);

    diceResults.add(curr1 + curr2);

    try {
      currPlayer++;
      currPlayerName.setText(masterObject.getPlayerList().get(currPlayer).getName() + ", please roll the dice.");
    } catch (IndexOutOfBoundsException e) {}

    if (diceResults.size() == masterObject.getNumPlayers() && !findDuplicate(diceResults)) {
      dice.setVisible(false);
      currPlayerName.setVisible(false);

      arrangeBoard.setVisible(true);
      randomizeBoard.setVisible(true);

      masterObject.orderPlayers(diceResults);
      masterObject.consolePrintPlayers();

      bg.setVisible(true);
      playerSequence.setVisible(true);
      playerSequence.setText("Sequence of players\n\n" + masterObject.playerOrderString());

      arrangeBoard.setDefaultButton(true);
    }

    else if (diceResults.size() == masterObject.getNumPlayers() && findDuplicate(diceResults)) {
      diceResults.clear();
      currPlayer = 0;

      currPlayerName.setText("Duplicates detected. " + masterObject.getPlayerList().get(currPlayer).getName() + ", please roll again.");
    }

  }

  public int rollDice () {

    Random rndGen = new Random();

    return rndGen.nextInt(6) + 1;

  }

  public boolean findDuplicate (ArrayList <Integer> nNums) {

    int i, j;

    for (i = 0; i < nNums.size() - 1; i++) {

      for (j = i + 1; j < nNums.size(); j++)

        if (nNums.get(i) == nNums.get(j))
          return true;

    }

    return false;

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
