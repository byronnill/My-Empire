package Controller;

import Model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.io.*;

public class GameBoardForTestingController {

  private static final int ROLL_DICE = -2;

  private Game masterObject;
  private Player masterCurrentPlayer;
  private Space masterCurrentSpace;
  private boolean hoverEnabled;
  private MediaPlayer player;
  private Card cardDrawn;

  @FXML
  private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31;

  @FXML
  private ImageView img0Avatar0, img0Avatar1, img0Avatar2, img0Avatar3, img1Avatar0, img1Avatar1, img1Avatar2, img1Avatar3, img2Avatar0, img2Avatar1, img2Avatar2, img2Avatar3, img3Avatar0, img3Avatar1, img3Avatar2, img3Avatar3, img4Avatar0, img4Avatar1, img4Avatar2, img4Avatar3, img5Avatar0, img5Avatar1, img5Avatar2, img5Avatar3, img6Avatar0, img6Avatar1, img6Avatar2, img6Avatar3, img7Avatar0, img7Avatar1, img7Avatar2, img7Avatar3;

  @FXML
  private ImageView img8Avatar0, img8Avatar1, img8Avatar2, img8Avatar3, img9Avatar0, img9Avatar1, img9Avatar2, img9Avatar3, img10Avatar0, img10Avatar1, img10Avatar2, img10Avatar3, img11Avatar0, img11Avatar1, img11Avatar2, img11Avatar3, img12Avatar0, img12Avatar1, img12Avatar2, img12Avatar3, img13Avatar0, img13Avatar1, img13Avatar2, img13Avatar3, img14Avatar0, img14Avatar1, img14Avatar2, img14Avatar3, img15Avatar0, img15Avatar1, img15Avatar2, img15Avatar3;

  @FXML
  private ImageView img16Avatar0, img16Avatar1, img16Avatar2, img16Avatar3, img17Avatar0, img17Avatar1, img17Avatar2, img17Avatar3, img18Avatar0, img18Avatar1, img18Avatar2, img18Avatar3, img19Avatar0, img19Avatar1, img19Avatar2, img19Avatar3, img20Avatar0, img20Avatar1, img20Avatar2, img20Avatar3, img21Avatar0, img21Avatar1, img21Avatar2, img21Avatar3, img22Avatar0, img22Avatar1, img22Avatar2, img22Avatar3, img23Avatar0, img23Avatar1, img23Avatar2, img23Avatar3;

  @FXML
  private ImageView img24Avatar0, img24Avatar1, img24Avatar2, img24Avatar3, img25Avatar0, img25Avatar1, img25Avatar2, img25Avatar3, img26Avatar0, img26Avatar1, img26Avatar2, img26Avatar3, img27Avatar0, img27Avatar1, img27Avatar2, img27Avatar3, img28Avatar0, img28Avatar1, img28Avatar2, img28Avatar3, img29Avatar0, img29Avatar1, img29Avatar2, img29Avatar3, img30Avatar0, img30Avatar1, img30Avatar2, img30Avatar3, img31Avatar0, img31Avatar1, img31Avatar2, img31Avatar3;

  @FXML
  private ImageView backdrop, dice1, dice2, currentSpace, currentSpaceSquare, muteButton, chanceImage, spaceImage, avatarImg;

  @FXML
  private Button dice, buy, doNothing, doNothingOnBuy, drawChance, rent, trade, endGame, confirmTrade, agreeTrade, disagreeTrade, applyChance5, applyChanceOther;

  @FXML
  private Label playerName, playerCash, inJail, bankValue, rentValue, instructionBox, spaceBox;

  @FXML
  private ComboBox <String> comboSelection;

  private Image gray0, gray1, purple0, purple1, purple2, pink0, pink1, pink2, green0, green1, green2, blue0, blue1, blue2, orange0, orange1, yellow0, yellow1, rail0, rail1, rail2, util0, util1, chance, income, luxury, start, park, jail, service, backdropImage;
  private Image gray0_, gray1_, purple0_, purple1_, purple2_, pink0_, pink1_, pink2_, green0_, green1_, green2_, blue0_, blue1_, blue2_, orange0_, orange1_, yellow0_, yellow1_, rail0_, rail1_, rail2_, util0_, util1_;
  private Image avatar0, avatar1, avatar2, avatar3;
  private Image dice1Holder, dice2Holder;
  private ObservableList<String> dropList;

  public void initialize () {

    backdropImage = new Image("/Images/Main/Screens/Game Screen.png");
    dice1Holder = new Image("/Images/Main/Dice/6.png");
    dice2Holder = new Image("/Images/Main/Dice/6.png");
    gray0 = new Image("/Images/Property Space/Gray/Almond Drive/0.png");
    gray1 = new Image("/Images/Property Space/Gray/Kasoy Street/0.png");
    purple0 = new Image("/Images/Property Space/Purple/Rodeo Drive/0.png");
    purple1 = new Image("/Images/Property Space/Purple/Orange Street/0.png");
    purple2 = new Image("/Images/Property Space/Purple/Ventura Street/0.png");
    pink0 = new Image("/Images/Property Space/Pink/Juan Luna/0.png");
    pink1 = new Image("/Images/Property Space/Pink/Ylaya/0.png");
    pink2 = new Image("/Images/Property Space/Pink/J Abad Santos/0.png");
    green0 = new Image("/Images/Property Space/Green/Madison/0.png");
    green1 = new Image("/Images/Property Space/Green/Annapolis/0.png");
    green2 = new Image("/Images/Property Space/Green/Connecticut/0.png");
    blue0 = new Image("/Images/Property Space/Blue/Bougainvilla/0.png");
    blue1 = new Image("/Images/Property Space/Blue/Dama De Noche/0.png");
    blue2 = new Image("/Images/Property Space/Blue/Acacia/0.png");
    orange0 = new Image("/Images/Property Space/Orange/Solar Street/0.png");
    orange1 = new Image("/Images/Property Space/Orange/Galaxy Street/0.png");
    yellow0 = new Image("/Images/Property Space/Yellow/9th Street/0.png");
    yellow1 = new Image("/Images/Property Space/Yellow/5th Avenue/0.png");
    rail0 = new Image("/Images/Railroad Space/North Line/1.png");
    rail1 = new Image("/Images/Railroad Space/South Line/1.png");
    rail2 = new Image("/Images/Railroad Space/Metro Line/1.png");
    util0 = new Image("/Images/Utility Space/Water/1.png");
    util1 = new Image("/Images/Utility Space/Electric/1.png");
    chance = new Image("/Images/Tax - Chance Space/Chance.png");
    income = new Image("/Images/Tax - Chance Space/Income Tax.png");
    luxury = new Image("/Images/Tax - Chance Space/Luxury Tax.png");
    start = new Image("/Images/Corner Space/Start.png");
    jail = new Image("/Images/Corner Space/Jail.png");
    park = new Image("/Images/Corner Space/Free Parking.png");
    service = new Image("/Images/Corner Space/Community Service.png");

    gray0_ = new Image("/Images/Property Space/Gray/Almond Drive/Full.png");
    gray1_ = new Image("/Images/Property Space/Gray/Kasoy Street/Full.png");
    purple0_ = new Image("/Images/Property Space/Purple/Rodeo Drive/Full.png");
    purple1_ = new Image("/Images/Property Space/Purple/Orange Street/Full.png");
    purple2_ = new Image("/Images/Property Space/Purple/Ventura Street/Full.png");
    pink0_ = new Image("/Images/Property Space/Pink/Juan Luna/Full.png");
    pink1_ = new Image("/Images/Property Space/Pink/Ylaya/Full.png");
    pink2_ = new Image("/Images/Property Space/Pink/J Abad Santos/Full.png");
    green0_ = new Image("/Images/Property Space/Green/Madison/Full.png");
    green1_ = new Image("/Images/Property Space/Green/Annapolis/Full.png");
    green2_ = new Image("/Images/Property Space/Green/Connecticut/Full.png");
    blue0_ = new Image("/Images/Property Space/Blue/Bougainvilla/Full.png");
    blue1_ = new Image("/Images/Property Space/Blue/Dama De Noche/Full.png");
    blue2_ = new Image("/Images/Property Space/Blue/Acacia/Full.png");
    orange0_ = new Image("/Images/Property Space/Orange/Solar Street/Full.png");
    orange1_ = new Image("/Images/Property Space/Orange/Galaxy Street/Full.png");
    yellow0_ = new Image("/Images/Property Space/Yellow/9th Street/Full.png");
    yellow1_ = new Image("/Images/Property Space/Yellow/5th Avenue/Full.png");
    rail0_ = new Image("/Images/Railroad Space/North Line/Full.png");
    rail1_ = new Image("/Images/Railroad Space/South Line/Full.png");
    rail2_ = new Image("/Images/Railroad Space/Metro Line/Full.png");
    util0_ = new Image("/Images/Utility Space/Water/Full.png");
    util1_ = new Image("/Images/Utility Space/Electric/Full.png");

    avatar0 = new Image("/Images/Tokens/Player 0.png");
    avatar1 = new Image("/Images/Tokens/Player 1.png");
    avatar2 = new Image("/Images/Tokens/Player 2.png");
    avatar3 = new Image("/Images/Tokens/Player 3.png");

    for (int i = 0; i < 32; i++){
      returnAvatar(i, 0).setImage(avatar0);
      returnAvatar(i, 0).setVisible(false);
      returnAvatar(i, 1).setImage(avatar1);
      returnAvatar(i, 1).setVisible(false);
      returnAvatar(i, 2).setImage(avatar2);
      returnAvatar(i, 2).setVisible(false);
      returnAvatar(i, 3).setImage(avatar3);
      returnAvatar(i, 3).setVisible(false);
    }

    hoverEnabled = true;

    setRentValue("-");

    backdrop.setImage(backdropImage);
    currentSpace.setVisible(false);
    currentSpaceSquare.setVisible(false);

    dice1.setVisible(false);
    dice2.setVisible(false);

    dice.setDisable(true);

    endGame.setStyle("-fx-cursor: hand");
    buy.setStyle("-fx-cursor: hand");
    doNothing.setStyle("-fx-cursor: hand");
    doNothingOnBuy.setStyle("-fx-cursor: hand");
    rent.setStyle("-fx-cursor: hand");
    trade.setStyle("-fx-cursor: hand");
    drawChance.setStyle("-fx-cursor: hand");

    comboSelection.setStyle("-fx-cursor: hand");
    confirmTrade.setStyle("-fx-cursor: hand");
    agreeTrade.setStyle("-fx-cursor: hand");
    disagreeTrade.setStyle("-fx-cursor: hand");
    applyChance5.setStyle("-fx-cursor: hand");
    applyChanceOther.setStyle("-fx-cursor: hand");

    endGame.setVisible(false);
    buy.setVisible(false);
    doNothing.setVisible(false);
    doNothingOnBuy.setVisible(false);
    rent.setVisible(false);
    trade.setVisible(false);
    drawChance.setVisible(false);

    comboSelection.setVisible(false);
    confirmTrade.setVisible(false);
    agreeTrade.setVisible(false);
    disagreeTrade.setVisible(false);
    applyChance5.setVisible(false);
    applyChanceOther.setVisible(false);

    setSpaceBox("");

    avatarImg.setImage(avatar0);
    avatarImg.setVisible(true);
  }

  public void setGame (Game game) {

    this.masterObject = game;
    masterObject.setActivePlayer(masterObject.getPlayerList().get(0));
    masterCurrentPlayer = masterObject.getActivePlayer();

    for (int i = 0; i < 32; i++) {

      Space currSpace;

      currSpace = game.getGameBoard().getBoardSpaces().get(i);

      if (i == 0 || i == 8 || i == 16 || i == 24) {

        switch (i) {
          case 0: img0.setImage(start);
          case 8: img8.setImage(park);
          case 16: img16.setImage(jail);
          case 24: img24.setImage(service);

        }

      } else if (currSpace instanceof ChanceSpace) {
        returnImageView(i).setImage(chance);
        returnImageView(i).setAccessibleText("-1");
      } else if (currSpace instanceof TaxSpace) {

        if (((TaxSpace) currSpace).isIncome()) {
          returnImageView(i).setImage(income);
          returnImageView(i).setAccessibleText("-2");
        } else {
          returnImageView(i).setImage(luxury);
          returnImageView(i).setAccessibleText("-3");
        }

      } else if (currSpace instanceof OwnableSpace) {

        returnImageView(i).setImage(returnImage(((OwnableSpace) currSpace).getID()));
        returnImageView(i).setAccessibleText(Integer.toString(((OwnableSpace) currSpace).getID()));

      }

      if (i > 0 && i < 8)
        returnImageView(i).setRotate(180);
      else if (i > 8 && i < 16)
        returnImageView(i).setRotate(270);
      else if (i > 24)
        returnImageView(i).setRotate(90);

    }

    setDetails();
    setInstructionBox(ROLL_DICE);

    masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(0);

    for (int i = 0; i < masterObject.getNumPlayers(); i++)
      returnAvatar(0, i).setVisible(true);

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

  public void enlarge0 () {

    if (hoverEnabled) {
      currentSpaceSquare.setImage(img0.getImage());
      currentSpaceSquare.setVisible(true);
    }

  }

  public void enlarge1 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img1.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge2 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img2.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge3 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img3.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge4 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img4.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge5 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img5.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge6 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img6.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge7 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img7.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge8 () {

    if (hoverEnabled) {
      currentSpaceSquare.setImage(img8.getImage());
      currentSpaceSquare.setVisible(true);
    }

  }

  public void enlarge9 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img9.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge10 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img10.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge11 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img11.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge12 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img12.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge13 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img13.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge14 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img14.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge15 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img15.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge16 () {

    if (hoverEnabled) {
      currentSpaceSquare.setImage(img16.getImage());
      currentSpaceSquare.setVisible(true);
    }

  }

  public void enlarge17 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img17.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge18 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img18.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge19 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img19.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge20 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img20.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge21 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img21.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge22 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img22.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge23 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img23.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge24 () {

    if (hoverEnabled) {
      currentSpaceSquare.setImage(img24.getImage());
      currentSpaceSquare.setVisible(true);
    }

  }

  public void enlarge25 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img25.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge26 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img26.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge27 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img27.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge28 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img28.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge29 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img29.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge30 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img30.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void enlarge31 () {

    if (hoverEnabled) {
      currentSpace.setImage(returnImageFull(Integer.parseInt(img31.getAccessibleText())));
      currentSpace.setVisible(true);
    }

  }

  public void revertImage () {

    if (hoverEnabled) {
      currentSpace.setVisible(false);
      currentSpaceSquare.setVisible(false);
      currentSpaceSquare.setImage(null);
      currentSpace.setImage(null);
    }

  }

  public void enlargeChance () {

    if (chanceImage.isVisible()) {

      chanceImage.setFitWidth(250);
      chanceImage.setFitHeight(375);

      chanceImage.setLayoutX(68);
      chanceImage.setLayoutY(264.5);

    }

  }

  public void revertChance () {

    if (chanceImage.isVisible()) {

      chanceImage.setFitWidth(200);
      chanceImage.setFitHeight(300);

      chanceImage.setLayoutX(93);
      chanceImage.setLayoutY(302);

    }

  }

  public void enlargeSpace () {

    if (spaceImage.isVisible()) {

      spaceImage.setFitWidth(250);
      spaceImage.setFitHeight(375);

      spaceImage.setLayoutX(355);
      spaceImage.setLayoutY(264.5);

    }

  }

  public void revertSpace () {

    if (spaceImage.isVisible()) {

      spaceImage.setFitWidth(200);
      spaceImage.setFitHeight(300);

      spaceImage.setLayoutX(380);
      spaceImage.setLayoutY(302);

    }

  }

  public Image returnImage(int nID){
    switch(nID){
      case 0: return gray0;
      case 1: return gray1;
      case 10: return purple0;
      case 11: return purple1;
      case 12: return purple2;
      case 20: return pink0;
      case 21: return pink1;
      case 22: return pink2;
      case 30: return green0;
      case 31: return green1;
      case 32: return green2;
      case 40: return blue0;
      case 41: return blue1;
      case 42: return blue2;
      case 50: return orange0;
      case 51: return orange1;
      case 60: return yellow0;
      case 61: return yellow1;
      case 100: return rail0;
      case 200: return rail1;
      case 300: return rail2;
      case 1000: return util0;
      case 2000: return util1;
      default: return null;
    }
  }

  public Image returnImageFull (int nID) {

    switch (nID) {

      case -1:    return chance;
      case -2:    return income;
      case -3:    return luxury;
      case 0:     return gray0_;
      case 1:     return gray1_;
      case 10:    return purple0_;
      case 11:    return purple1_;
      case 12:    return purple2_;
      case 20:    return pink0_;
      case 21:    return pink1_;
      case 22:    return pink2_;
      case 30:    return green0_;
      case 31:    return green1_;
      case 32:    return green2_;
      case 40:    return blue0_;
      case 41:    return blue1_;
      case 42:    return blue2_;
      case 50:    return orange0_;
      case 51:    return orange1_;
      case 60:    return yellow0_;
      case 61:    return yellow1_;
      case 100:   return rail0_;
      case 200:   return rail1_;
      case 300:   return rail2_;
      case 1000:  return util0_;
      case 2000:  return util1_;
      default:    return null;

    }

  }

  public ImageView returnImageView(int nIndex){
    switch(nIndex){
      case 1: return img1;
      case 2: return img2;
      case 3: return img3;
      case 4: return img4;
      case 5: return img5;
      case 6: return img6;
      case 7: return img7;
      case 9: return img9;
      case 10: return img10;
      case 11: return img11;
      case 12: return img12;
      case 13: return img13;
      case 14: return img14;
      case 15: return img15;
      case 17: return img17;
      case 18: return img18;
      case 19: return img19;
      case 20: return img20;
      case 21: return img21;
      case 22: return img22;
      case 23: return img23;
      case 25: return img25;
      case 26: return img26;
      case 27: return img27;
      case 28: return img28;
      case 29: return img29;
      case 30: return img30;
      case 31: return img31;
      default: return null;
    }
  }

  public void setDetails () {

    playerName.setText(masterCurrentPlayer.getName());
    playerCash.setText(Double.toString(masterCurrentPlayer.getCash()));
    bankValue.setText(Double.toString(masterObject.getGameBank().getValue()));

    if (masterCurrentPlayer.isInJail())
      inJail.setText("Yes");
    else
      inJail.setText("No");

  }

  public void setRentValue (String toSet) {

    rentValue.setText(toSet);

  }

  public void setRentValue (OwnableSpace currSpace) {

    if (currSpace instanceof Property) {

      if (currSpace.getOwner() == null)
        rentValue.setText(Double.toString(currSpace.getRent()));

      else {
        switch (currSpace.getOwner().getOwnedPerType(((Property) currSpace).getColorIndex())) {

          case 1:
            rentValue.setText(Double.toString(currSpace.getRent())); break;
          case 2:
            rentValue.setText(Double.toString(currSpace.getRent() + 10)); break;
          case 3:
            rentValue.setText(Double.toString(currSpace.getRent() + 20)); break;

        }
      }

    } else if (currSpace instanceof Railroad) {

      rentValue.setText(Double.toString(currSpace.getRent()));

    } else if (currSpace instanceof Utility) {

      if (currSpace.getOwner() == null)
        rentValue.setText("Dice x 4");

      else {
        switch (currSpace.getOwner().getOwnedPerType(8)) {

          case 1:
            rentValue.setText("Dice x 4"); break;
          case 2:
            rentValue.setText("Dice x 10"); break;

        }
      }

    }

  }

  public void setInstructionBox (int nType) {

    instructionBox.setTextAlignment(TextAlignment.LEFT);

    switch (nType) {

      case ROLL_DICE              : instructionBox.setText(masterCurrentPlayer.getName() + ", ROLL the dice.");
        break;

      case Game.LAND_ON_START     : setSpaceBox("Start");
        instructionBox.setText("You have been automatically given $200.");
        break;

      case Game.LAND_ON_FREE      : setSpaceBox("Free Parking");
        instructionBox.setText("No further actions possible.");
        break;

      case Game.LAND_ON_JAIL      : setSpaceBox("Jail");
        instructionBox.setText("You are now in jail.\nPay $50 or use a GET OUT OF JAIL FREE card\non your next turn.");
        break;

      case Game.LAND_ON_COMMUNITY : setSpaceBox("Community Service");
        instructionBox.setText("You have been automatically deducted $50.");
        break;

      case Game.LAND_ON_CHANCE    : setSpaceBox("Chance Card Space");
        instructionBox.setText("Chance!\nPlease see instructions on the card drawn\nfrom the deck.");
        break;

    }

  }

  public void setInstructionBox (String toSet) {

    instructionBox.setText(toSet);

  }

  public void setSpaceBox (String toSet) {

    spaceBox.setText(toSet);

  }

  public void gameIsEnd (int condition) {

    if (condition == 1)
      setInstructionBox("A player has completed 2 property sets.");
    else if (condition == 2)
      setInstructionBox("The bank has been bankrupted.");

    setInstructionBox(instructionBox.getText() + "\nThe game is over.\nPress END GAME to proceed to player rankings.");

    masterObject.setGameFinished(true);

    endGame.setVisible(true);

    dice.setVisible(false);
    buy.setVisible(false);
    doNothing.setVisible(false);
    doNothingOnBuy.setVisible(false);
    rent.setVisible(false);
    trade.setVisible(false);
    drawChance.setVisible(false);

    comboSelection.setVisible(false);
    confirmTrade.setVisible(false);
    agreeTrade.setVisible(false);
    disagreeTrade.setVisible(false);
    applyChance5.setVisible(false);
    applyChanceOther.setVisible(false);

  }

  public void playerTransition () {

    int doesGameResume = masterObject.doesGameResume();

    if (doesGameResume == 1 || doesGameResume == 2) { //ends the turn as well

      gameIsEnd(doesGameResume);

    } else {

      masterObject.endTurn();
      masterCurrentPlayer = masterObject.getActivePlayer();

      dice.setVisible(true);

      endGame.setVisible(false);
      buy.setVisible(false);
      doNothing.setVisible(false);
      doNothingOnBuy.setVisible(false);
      rent.setVisible(false);
      trade.setVisible(false);
      drawChance.setVisible(false);

      comboSelection.setVisible(false);
      confirmTrade.setVisible(false);
      agreeTrade.setVisible(false);
      disagreeTrade.setVisible(false);
      applyChance5.setVisible(false);
      applyChanceOther.setVisible(false);

      switch (masterCurrentPlayer.getPlayerNum()) {

        case 0 : avatarImg.setImage(avatar0); break;
        case 1 : avatarImg.setImage(avatar1); break;
        case 2 : avatarImg.setImage(avatar2); break;
        case 3 : avatarImg.setImage(avatar3); break;

      }

      setRentValue("-");
      setSpaceBox("");

      hoverEnabled = true;
      setDetails();

      dice1.setVisible(false);
      dice2.setVisible(false);

      if (masterCurrentPlayer.isInJail()) {

        if (masterCurrentPlayer.isFreedomPossible() == 0) {

          masterCurrentPlayer.addOrDeductCash(-50);
          setInstructionBox("You do not have a GET OUT OF JAIL FREE card\nand enough money to post bail.");
          gameIsEnd(0);

        } else if (masterCurrentPlayer.isFreedomPossible() == 1) {

          setInstructionBox("Your GET OUT OF JAIL FREE card has been used.\nPress ROLL DICE to start your turn.");

        } else if (masterCurrentPlayer.isFreedomPossible() == 2) {

          setInstructionBox("You have been automatically deducted $50\nand are now free from jail.\nPress ROLL DICE to start your turn.");

        }

      } else {

        setInstructionBox(ROLL_DICE);

      }

    }

  }

  public void setChanceImage (Image image) {
    chanceImage.setImage(image);
  }

  public void setupPropertyRentChangeScreen (Card cardDrawn) {

    if (((CardGroup5)cardDrawn).isCardApplicable(masterCurrentPlayer)) {

      comboSelection.getItems().clear();
      dropList = FXCollections.observableArrayList();

      if (((CardGroup5)cardDrawn).getType() < 4) {

        for (int i = 0; i < masterCurrentPlayer.getOwned(); i++) {

          if (masterCurrentPlayer.getOwnedSpaces().get(i) instanceof Property) {

            if (((Property) masterCurrentPlayer.getOwnedSpaces().get(i)).isDoubleRent() && ((CardGroup5) cardDrawn).getType() == 1)
              continue;

            dropList.add(masterCurrentPlayer.getOwnedSpaces().get(i).toStringShort());

          }

        }

      } else {

        for (int i = 0; i < masterCurrentPlayer.getOwned(); i++) {

          if (masterCurrentPlayer.getOwnedSpaces().get(i) instanceof Railroad || masterCurrentPlayer.getOwnedSpaces().get(i) instanceof Utility)
            dropList.add(masterCurrentPlayer.getOwnedSpaces().get(i).toStringShort());

        }

      }

      comboSelection.setItems(dropList);
      comboSelection.setVisible(true);
      applyChance5.setVisible(true);
      applyChance5.setDisable(true);

    } else {

      setInstructionBox("You have no spaces of this type.");
      masterObject.getGameDeck().discardCard(cardDrawn);
      doNothing.setVisible(true);

    }

  }

  public void confirmChance () {

    double dToPay = 1;

    for (int i = 0; i < masterCurrentPlayer.getOwnedSpaces().size(); i++)
      if (masterCurrentPlayer.getOwnedSpaces().get(i).toStringShort().equals(comboSelection.getValue()))
        dToPay = ((CardGroup5)cardDrawn).applyCardToSpace(masterCurrentPlayer.getOwnedSpaces().get(i));

    if (((CardGroup5) cardDrawn).getType() != 1)
      masterObject.getGameDeck().discardCard(cardDrawn);

    if (((CardGroup5) cardDrawn).getType() == 2) {

      if (masterCurrentPlayer.isPaymentPossible(dToPay))
        masterCurrentPlayer.payBank(dToPay, masterObject.getGameBank());

      else {

        setInstructionBox("You paid for renovation and are now bankrupt");
        gameIsEnd(0);

      }

    }

    doNothing.setVisible(true);
    applyChance5.setVisible(false);
    currentSpace.setLayoutY(200);
    currentSpace.setVisible(false);
    comboSelection.setVisible(false);

  }

  public void setupChanceScreen () {

    this.cardDrawn = masterObject.getGameDeck().drawCard();

    if (cardDrawn instanceof CardGroup1) {
      System.out.println("Card Drawn from Group 1");

      setInstructionBox("Get out of Jail free.");
      setChanceImage(new Image("/Images/Chance Cards/Chance Group 1.png"));
      applyChanceOther.setText("Acquire GET OUT OF JAIL FREE card");
      applyChanceOther.setVisible(true);

    } else if (cardDrawn instanceof CardGroup2) {
      System.out.println("Card Drawn from Group 2");

      masterObject.getGameDeck().discardCard(cardDrawn);
      int type = ((CardGroup2) cardDrawn).getType();
      int nIndexToGo;

      setChanceImage(new Image("/Images/Chance Cards/Chance Group 2_" + type + ".png"));

      if (type == 1) {

        nIndexToGo = ((CardGroup2) cardDrawn).getIndexToGo();
        setInstructionBox("Go to " + ((OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(nIndexToGo)).getName());

      } else if (type == 2) {

        nIndexToGo = ((CardGroup2) cardDrawn).findNearestRail(masterObject.getGameBoard(), masterCurrentPlayer);
        setInstructionBox("Go to " + ((OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(nIndexToGo)).getName());

      } else {

        nIndexToGo = ((CardGroup2) cardDrawn).findNearestUtil(masterObject.getGameBoard(), masterCurrentPlayer);
        setInstructionBox("Go to " + ((OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(nIndexToGo)).getName());

      }

      applyChanceOther.setText("Move to Space");
      applyChanceOther.setVisible(true);

    } else if (cardDrawn instanceof CardGroup3) {
      System.out.println("Card Drawn from Group 3");

      masterObject.getGameDeck().discardCard(cardDrawn);
      int type = ((CardGroup3) cardDrawn).getType();

      switch (type) {
        case 1: setInstructionBox("Collect $50."); break;
        case 2: setInstructionBox("Collect $100."); break;
        case 3: setInstructionBox("Proceed to Start."); break;
        case 4: setInstructionBox("Collect $300."); break;
        case 5: setInstructionBox("Collect $150."); break;
      }

      setChanceImage(new Image("/Images/Chance Cards/Chance Group 3_" + type + ".png"));

      applyChanceOther.setText("Collect Cash");
      if (type == 3)
        applyChanceOther.setText("Move to Start");

      applyChanceOther.setVisible(true);

    } else if (cardDrawn instanceof CardGroup4) {
      System.out.println("Card Drawn from Group 4");

      masterObject.getGameDeck().discardCard(cardDrawn);
      int type = ((CardGroup4) cardDrawn).getType();

      setChanceImage(new Image("/Images/Chance Cards/Chance Group 4_" + type + ".png"));

      int nIndexToGo = ((CardGroup4) cardDrawn).getIndexToGo();

      if (type == 2) {

        setInstructionBox("Go to " + ((OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(nIndexToGo)).getName());
        applyChanceOther.setText("Move to Space");

      } else {

        setInstructionBox("Go to Jail.");
        applyChanceOther.setText("Move to Jail");

      }

      applyChanceOther.setVisible(true);

    } else if (cardDrawn instanceof CardGroup5) {
      System.out.println("Card Drawn from Group 5");

      int type = ((CardGroup5) cardDrawn).getType();

      setChanceImage(new Image("/Images/Chance Cards/Chance Group 5_" + type + ".png"));

      setupPropertyRentChangeScreen(cardDrawn);

    } else if (cardDrawn instanceof CardGroup6) {
      System.out.println("Card Drawn from Group 6");

      masterObject.getGameDeck().discardCard(cardDrawn);
      int type = ((CardGroup6) cardDrawn).getType();

      setInstructionBox("Pay $" + ((CardGroup6) cardDrawn).getPay() + ".");
      setChanceImage(new Image("/Images/Chance Cards/Chance Group 6_" + type + ".png"));

      applyChanceOther.setText("Pay Cash");
      applyChanceOther.setVisible(true);

    }

  }

  public void setupOwnableScreen () {

    OwnableSpace currSpace = (OwnableSpace) masterCurrentSpace;//masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

    setSpaceBox(currSpace.getName());
    setRentValue(currSpace);

    spaceImage.setImage(returnImageFull(currSpace.getID()));
    spaceImage.setVisible(true);

    if (currSpace.getOwner() == null) {

      setInstructionBox("This space is unowned.");

      if (currSpace.canBeBought(masterCurrentPlayer)) {

        buy.setVisible(true);
        doNothingOnBuy.setVisible(true);

      } else {

        setInstructionBox(instructionBox.getText() + "\nHowever, you have insufficient funding to buy it.");

        buy.setVisible(true);
        buy.setDisable(true);
        doNothingOnBuy.setVisible(true);

      }


    } else if (currSpace.getOwner().equals(masterCurrentPlayer)) {

      if (currSpace instanceof Property && ((Property) currSpace).canBeDeveloped(masterCurrentPlayer)) {

        masterCurrentPlayer.developProperty((Property) currSpace, masterObject.getGameBank());
        setInstructionBox("This property is yours and has been automatically developed.");

        setRentValue(currSpace);
        returnImageView(masterCurrentPlayer.getLocationIndex()).setImage(new Image("Images/Property Space/" + Reference.TYPES[((Property)currSpace).getColorIndex()] + "/" + Reference.PROPERTIES[((Property)currSpace).getColorIndex()][((Property)currSpace).getPropertyIndex()] + "/" + ((Property) currSpace).getDevelopment() + ".png"));

        if (masterCurrentPlayer.getCash() <= 0) {

          setInstructionBox(instructionBox.getText() + "\nYou are now bankrupt.");
          gameIsEnd(0);

        }

      } else {

        setInstructionBox("This property is yours.");

      }

      doNothing.setVisible(true);

    } else {

      setInstructionBox("This property is owned by " + currSpace.getOwner().getName());

      rent.setVisible(true);
      trade.setVisible(true);

      rent.setDisable(false);
      trade.setDisable(false);

      if (masterCurrentPlayer.getCash() <= currSpace.getRent()) {
        rent.setDisable(true);
        setInstructionBox(instructionBox.getText() + "\nYou have insufficient funding to pay rent.");
      }
      if (masterCurrentPlayer.getOwned() == 0) {
        trade.setDisable(true);
        setInstructionBox(instructionBox.getText() + "\nYou don't have properties to trade.");
      }

      if (rent.isDisable() && trade.isDisable()) {
        gameIsEnd(0);
      }

    }

  }

  public void setupTradeScreen () {

    setInstructionBox("Select an owned space to offer for trade.");
    comboSelection.getItems().clear();
    dropList = FXCollections.observableArrayList();
    for (int i = 0; i < masterCurrentPlayer.getOwned(); i++){
      dropList.add(masterCurrentPlayer.getOwnedSpaces().get(i).toStringShort());
    }
    comboSelection.setItems(dropList);
    comboSelection.setVisible(true);
    confirmTrade.setVisible(true);
    confirmTrade.setDisable(true);

  }

  public void comboChanged(){

    OwnableSpace spaceInBox = null;

    for (int i = 0; i < masterCurrentPlayer.getOwned(); i++) {

      if (masterCurrentPlayer.getOwnedSpaces().get(i).toStringShort().equals(comboSelection.getValue())) {
        spaceInBox = masterCurrentPlayer.getOwnedSpaces().get(i);
        break;
      }

    }

    if (spaceInBox != null) {

      confirmTrade.setDisable(false);
      applyChance5.setDisable(false);
      if (spaceInBox instanceof Property) {
        currentSpace.setImage(new Image("Images/Property Space/" + Reference.TYPES[((Property) spaceInBox).getColorIndex()] + "/" + spaceInBox.getName() + "/Full.png"));
      } else if (spaceInBox instanceof Railroad) {
        currentSpace.setImage(new Image("Images/Railroad Space/" + spaceInBox.getName() + "/Full.png"));
      } else {
        currentSpace.setImage(new Image("Images/Utility Space/" + spaceInBox.getName() + "/Full.png"));
      }
      currentSpace.setLayoutY(250);
      currentSpace.setVisible(true);

    }

  }

  public void handleConfirm(){
    confirmTrade.setVisible(false);
    agreeTrade.setVisible(true);
    disagreeTrade.setVisible(true);
    setInstructionBox(masterCurrentPlayer.getName() + " has offered a trade.");
  }

  public void handleAgree(){
    agreeTrade.setVisible(false);
    disagreeTrade.setVisible(false);
    currentSpace.setLayoutY(200);
    currentSpace.setVisible(false);
    comboSelection.setVisible(false);
    masterCurrentPlayer.tradeProperty(masterCurrentPlayer.getOwnedSpaces().get(dropList.indexOf(comboSelection.getValue())), (OwnableSpace) masterCurrentSpace, ((OwnableSpace) masterCurrentSpace).getOwner());
    rent.setVisible(false);
    trade.setVisible(false);
    doNothing.setVisible(true);
    setInstructionBox("Trade successful!");
  }

  public void handleDisagree(){
    agreeTrade.setVisible(false);
    disagreeTrade.setVisible(false);
    currentSpace.setLayoutY(200);
    currentSpace.setVisible(false);
    comboSelection.setVisible(false);
    trade.setDisable(true);
    setInstructionBox(((OwnableSpace) masterCurrentSpace).getOwner().getName() + " has disagreed to the trade. You must not pay rent.");
  }
  public void handleDice () {

    hoverEnabled = false;
    dice.setVisible(false);

    int curr1 = masterObject.rollDice();
    dice1Holder = new Image("/Images/Main/Dice/" + curr1 + ".png");

    int curr2 = masterObject.rollDice();
    dice2Holder = new Image("/Images/Main/Dice/" + curr2 + ".png");

    dice1.setImage(dice1Holder);
    dice2.setImage(dice2Holder);

    dice1.setVisible(true);
    dice2.setVisible(true);

    moveAvatar((this.masterCurrentPlayer.getLocationIndex() + curr1 + curr2) % 32);

    int actionToDo = masterObject.turn(curr1 + curr2);
    masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

    System.out.println(masterCurrentSpace);
    System.out.println(actionToDo);
    System.out.println();

    setDetails();

    switch (actionToDo) {

      case Game.LAND_ON_START       : setInstructionBox(Game.LAND_ON_START);
        doNothing.setVisible(true);
        break;

      case Game.LAND_ON_FREE        : setInstructionBox(Game.LAND_ON_FREE);
        doNothing.setVisible(true);
        break;

      case Game.LAND_ON_JAIL        : setInstructionBox(Game.LAND_ON_JAIL);
        masterCurrentPlayer.setInJail(true);
        doNothing.setVisible(true);
        break;

      case Game.LAND_ON_COMMUNITY   : if (masterCurrentPlayer.isPaymentPossible(50)) {

        masterCurrentPlayer.payBank(50, masterObject.getGameBank());
        setInstructionBox(Game.LAND_ON_COMMUNITY);
        setDetails();
        doNothing.setVisible(true);

      } else {

        masterCurrentPlayer.payBank(-50, masterObject.getGameBank());
        setDetails();
        setInstructionBox("You have insufficient funding to pay for community service.");
        gameIsEnd(0);

      }
        break;

      case Game.LAND_ON_CHANCE      : setInstructionBox(Game.LAND_ON_CHANCE);
        drawChance.setVisible(true);
        break;

      case Game.LAND_ON_TAX         : String strType = ((TaxSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex())).isIncome() ? "Income" : "Luxury";
        setSpaceBox(strType + " Tax");

        double toPay = ((TaxSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex())).payTax(masterCurrentPlayer);

        if (toPay != -1) {

          masterCurrentPlayer.payBank(toPay, masterObject.getGameBank());
          setDetails();
          setInstructionBox("You have been automatically deducted $" + toPay);
          doNothing.setVisible(true);

        } else {

          masterCurrentPlayer.payBank(toPay * -1, masterObject.getGameBank());
          setDetails();
          setInstructionBox("You have insufficient funding to pay for taxes.");
          gameIsEnd(0);

        }
        break;

      case Game.LAND_ON_OWNABLE     : setupOwnableScreen();
        break;

      case Game.GAME_IS_END         : gameIsEnd(2);

    }

  }

  public void handleBuy () {

    OwnableSpace currSpace = (OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

    masterCurrentPlayer.buyProperty(currSpace, masterObject.getGameBank());

    setDetails();
    setInstructionBox("You have bought this space.");

    buy.setVisible(false);
    doNothingOnBuy.setVisible(false);

    doNothing.setVisible(true);

  }

  public void handleRent () {

    OwnableSpace currSpace = (OwnableSpace) masterCurrentSpace;
    double dToPay;

    if (currSpace instanceof Property) {

      dToPay = ((Property) currSpace).getPropertyRent(masterObject.getGameDeck());
      ((Property) currSpace).addToCollected(dToPay);

    } else if (currSpace instanceof Railroad) {

      dToPay = currSpace.getRent();

    } else {

      int curr1 = masterObject.rollDice();
      int curr2 = masterObject.rollDice();

      dice1.setImage(new Image("/Images/Main/Dice/" + curr1 + ".png"));
      dice2.setImage(new Image("/Images/Main/Dice/" + curr2 + ".png"));

      dice1.setVisible(true);
      dice2.setVisible(true);

      if (cardDrawn != null && cardDrawn instanceof CardGroup2 && ((CardGroup2) cardDrawn).getType() == 2)
        dToPay = 10 * (curr1 + curr2);

      else
        dToPay = currSpace.getRent() * (curr1 + curr2);

    }

    masterCurrentPlayer.payPlayer(dToPay, currSpace.getOwner());
    setDetails();

    if (masterCurrentPlayer.getCash() <= 0) {

      setInstructionBox("You are now bankrupt.");
      gameIsEnd(0);

    } else {

      setInstructionBox("You paid " + currSpace.getOwner().getName() + " $" + dToPay + ".");

      rent.setVisible(false);
      trade.setVisible(false);
      doNothing.setVisible(true);

    }

  }

  public void handleTrade () {

    setupTradeScreen();

  }

  public void handleDraw () {

    dice1.setVisible(false);
    dice2.setVisible(false);

    chanceImage.setVisible(true);
    setupChanceScreen();

    drawChance.setVisible(false);

  }

  public void handleApplyChance () {

    applyChanceOther.setVisible(false);

    if (cardDrawn instanceof CardGroup1) {

      ((CardGroup1) cardDrawn).applyCardToPlayer(masterCurrentPlayer);
      setInstructionBox("You now have "+ masterCurrentPlayer.getJailChanceCards().size() + "GET OUT OF JAIL FREE card(s).");

      doNothing.setVisible(true);

    } else if (cardDrawn instanceof CardGroup2) {

      int type = ((CardGroup2) cardDrawn).getType();
      int nIndexToGo;

      if (type == 1)
        nIndexToGo = ((CardGroup2) cardDrawn).getIndexToGo();

      else if (type == 2)
        nIndexToGo = ((CardGroup2) cardDrawn).findNearestRail(masterObject.getGameBoard(), masterCurrentPlayer);

      else
        nIndexToGo = ((CardGroup2) cardDrawn).findNearestUtil(masterObject.getGameBoard(), masterCurrentPlayer);

      int nSteps;

      nSteps = masterCurrentPlayer.getLocationIndex() <= nIndexToGo ? nIndexToGo - masterCurrentPlayer.getLocationIndex() : 32 - masterCurrentPlayer.getLocationIndex() + nIndexToGo;

      moveAvatar(nIndexToGo);
      masterCurrentPlayer.movePlayer(masterObject.getGameBoard(), nSteps, true, masterObject.getGameBank());
      masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

      setupOwnableScreen();

    } else if (cardDrawn instanceof CardGroup3) {

      int type = ((CardGroup3) cardDrawn).getType();

      if (type == 3) {

        int nSteps = 32 - masterCurrentPlayer.getLocationIndex();

        moveAvatar(0);
        masterCurrentPlayer.movePlayer(masterObject.getGameBoard(), nSteps, true, masterObject.getGameBank());
        masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

        setInstructionBox(Game.LAND_ON_START);

      } else {

        double dCollect = ((CardGroup3) cardDrawn).getCollect();

        masterCurrentPlayer.addOrDeductCash(dCollect);
        masterObject.getGameBank().addOrDeduct(dCollect * -1);

        setInstructionBox("You have been given $" + dCollect);

      }

      setDetails();
      doNothing.setVisible(true);

    } else if (cardDrawn instanceof CardGroup4) {

      int type = ((CardGroup4) cardDrawn).getType();

      int nIndexToGo = ((CardGroup4) cardDrawn).getIndexToGo();
      int nSteps = masterCurrentPlayer.getLocationIndex() <= nIndexToGo ? nIndexToGo - masterCurrentPlayer.getLocationIndex() : 32 - masterCurrentPlayer.getLocationIndex() + nIndexToGo;

      if (type == 2) {

        moveAvatar(nIndexToGo);
        masterCurrentPlayer.movePlayer(masterObject.getGameBoard(), nSteps, true, masterObject.getGameBank());
        masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

        setupOwnableScreen();

      } else {

        moveAvatar(nIndexToGo);
        masterCurrentPlayer.movePlayer(masterObject.getGameBoard(), nSteps, false, masterObject.getGameBank());
        masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

        masterCurrentPlayer.setInJail(true);
        setInstructionBox(Game.LAND_ON_JAIL);

        doNothing.setVisible(true);

      }

    } else if (cardDrawn instanceof CardGroup6) {

      double toPay = ((CardGroup6) cardDrawn).getPay();
      masterCurrentPlayer.payBank(toPay, masterObject.getGameBank());

      setDetails();
      doNothing.setVisible(true);

      if (masterCurrentPlayer.getCash() <= 0) {
        setInstructionBox("You are now bankrupt.");
        gameIsEnd(0);
      }

    }

  }

  public void handleContinue () {

    playerTransition();
    spaceImage.setVisible(false);
    chanceImage.setVisible(false);

  }

  public void handleEndGame (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EndGame.fxml"));
      Parent root = loader.load();
      EndGameController controller = loader.getController();

      controller.setPlayer(player);
      controller.setGameAndRankPlayers(masterObject);

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

  public void moveAvatar(int newIndex){
    returnAvatar(this.masterCurrentPlayer.getLocationIndex(), this.masterCurrentPlayer.getPlayerNum()).setVisible(false);
    returnAvatar(newIndex, this.masterCurrentPlayer.getPlayerNum()).setVisible(true);
  }

  public ImageView returnAvatar(int nIndex, int playerNum){
    switch(nIndex){
      case 0: switch(playerNum){
        case 0: return img0Avatar0;
        case 1: return img0Avatar1;
        case 2: return img0Avatar2;
        case 3: return img0Avatar3;
        default: return null;
      }
      case 1: switch(playerNum){
        case 0: return img1Avatar0;
        case 1: return img1Avatar1;
        case 2: return img1Avatar2;
        case 3: return img1Avatar3;
        default: return null;
      }
      case 2: switch(playerNum){
        case 0: return img2Avatar0;
        case 1: return img2Avatar1;
        case 2: return img2Avatar2;
        case 3: return img2Avatar3;
        default: return null;
      }
      case 3: switch(playerNum){
        case 0: return img3Avatar0;
        case 1: return img3Avatar1;
        case 2: return img3Avatar2;
        case 3: return img3Avatar3;
        default: return null;
      }
      case 4: switch(playerNum){
        case 0: return img4Avatar0;
        case 1: return img4Avatar1;
        case 2: return img4Avatar2;
        case 3: return img4Avatar3;
        default: return null;
      }
      case 5: switch(playerNum){
        case 0: return img5Avatar0;
        case 1: return img5Avatar1;
        case 2: return img5Avatar2;
        case 3: return img5Avatar3;
        default: return null;
      }
      case 6: switch(playerNum){
        case 0: return img6Avatar0;
        case 1: return img6Avatar1;
        case 2: return img6Avatar2;
        case 3: return img6Avatar3;
        default: return null;
      }
      case 7: switch(playerNum){
        case 0: return img7Avatar0;
        case 1: return img7Avatar1;
        case 2: return img7Avatar2;
        case 3: return img7Avatar3;
        default: return null;
      }
      case 8: switch(playerNum){
        case 0: return img8Avatar0;
        case 1: return img8Avatar1;
        case 2: return img8Avatar2;
        case 3: return img8Avatar3;
        default: return null;
      }
      case 9: switch(playerNum){
        case 0: return img9Avatar0;
        case 1: return img9Avatar1;
        case 2: return img9Avatar2;
        case 3: return img9Avatar3;
        default: return null;
      }
      case 10: switch(playerNum){
        case 0: return img10Avatar0;
        case 1: return img10Avatar1;
        case 2: return img10Avatar2;
        case 3: return img10Avatar3;
        default: return null;
      }
      case 11: switch(playerNum){
        case 0: return img11Avatar0;
        case 1: return img11Avatar1;
        case 2: return img11Avatar2;
        case 3: return img11Avatar3;
        default: return null;
      }
      case 12: switch(playerNum){
        case 0: return img12Avatar0;
        case 1: return img12Avatar1;
        case 2: return img12Avatar2;
        case 3: return img12Avatar3;
        default: return null;
      }
      case 13: switch(playerNum){
        case 0: return img13Avatar0;
        case 1: return img13Avatar1;
        case 2: return img13Avatar2;
        case 3: return img13Avatar3;
        default: return null;
      }
      case 14: switch(playerNum){
        case 0: return img14Avatar0;
        case 1: return img14Avatar1;
        case 2: return img14Avatar2;
        case 3: return img14Avatar3;
        default: return null;
      }
      case 15: switch(playerNum){
        case 0: return img15Avatar0;
        case 1: return img15Avatar1;
        case 2: return img15Avatar2;
        case 3: return img15Avatar3;
        default: return null;
      }
      case 16: switch(playerNum){
        case 0: return img16Avatar0;
        case 1: return img16Avatar1;
        case 2: return img16Avatar2;
        case 3: return img16Avatar3;
        default: return null;
      }
      case 17: switch(playerNum){
        case 0: return img17Avatar0;
        case 1: return img17Avatar1;
        case 2: return img17Avatar2;
        case 3: return img17Avatar3;
        default: return null;
      }
      case 18: switch(playerNum){
        case 0: return img18Avatar0;
        case 1: return img18Avatar1;
        case 2: return img18Avatar2;
        case 3: return img18Avatar3;
        default: return null;
      }
      case 19: switch(playerNum){
        case 0: return img19Avatar0;
        case 1: return img19Avatar1;
        case 2: return img19Avatar2;
        case 3: return img19Avatar3;
        default: return null;
      }
      case 20: switch(playerNum){
        case 0: return img20Avatar0;
        case 1: return img20Avatar1;
        case 2: return img20Avatar2;
        case 3: return img20Avatar3;
        default: return null;
      }
      case 21: switch(playerNum){
        case 0: return img21Avatar0;
        case 1: return img21Avatar1;
        case 2: return img21Avatar2;
        case 3: return img21Avatar3;
        default: return null;
      }
      case 22: switch(playerNum){
        case 0: return img22Avatar0;
        case 1: return img22Avatar1;
        case 2: return img22Avatar2;
        case 3: return img22Avatar3;
        default: return null;
      }
      case 23: switch(playerNum){
        case 0: return img23Avatar0;
        case 1: return img23Avatar1;
        case 2: return img23Avatar2;
        case 3: return img23Avatar3;
        default: return null;
      }
      case 24: switch(playerNum){
        case 0: return img24Avatar0;
        case 1: return img24Avatar1;
        case 2: return img24Avatar2;
        case 3: return img24Avatar3;
        default: return null;
      }
      case 25: switch(playerNum){
        case 0: return img25Avatar0;
        case 1: return img25Avatar1;
        case 2: return img25Avatar2;
        case 3: return img25Avatar3;
        default: return null;
      }
      case 26: switch(playerNum){
        case 0: return img26Avatar0;
        case 1: return img26Avatar1;
        case 2: return img26Avatar2;
        case 3: return img26Avatar3;
        default: return null;
      }
      case 27: switch(playerNum){
        case 0: return img27Avatar0;
        case 1: return img27Avatar1;
        case 2: return img27Avatar2;
        case 3: return img27Avatar3;
        default: return null;
      }
      case 28: switch(playerNum){
        case 0: return img28Avatar0;
        case 1: return img28Avatar1;
        case 2: return img28Avatar2;
        case 3: return img28Avatar3;
        default: return null;
      }
      case 29: switch(playerNum){
        case 0: return img29Avatar0;
        case 1: return img29Avatar1;
        case 2: return img29Avatar2;
        case 3: return img29Avatar3;
        default: return null;
      }
      case 30: switch(playerNum){
        case 0: return img30Avatar0;
        case 1: return img30Avatar1;
        case 2: return img30Avatar2;
        case 3: return img30Avatar3;
        default: return null;
      }
      case 31: switch(playerNum){
        case 0: return img31Avatar0;
        case 1: return img31Avatar1;
        case 2: return img31Avatar2;
        case 3: return img31Avatar3;
        default: return null;
      }
      default: return null;
    }
  }

  public void move1 () {
    setupAfterMove(1);
  }

  public void move2 () {
    setupAfterMove(2);
  }

  public void move3 () {
    setupAfterMove(3);
  }

  public void move4 () {
    setupAfterMove(4);
  }

  public void move5 () {
    setupAfterMove(5);
  }

  public void move6 () {
    setupAfterMove(6);
  }

  public void move7 () {
    setupAfterMove(7);
  }

  public void move8 () {
    setupAfterMove(8);
  }

  public void move9 () {
    setupAfterMove(9);
  }

  public void move10 () {
    setupAfterMove(10);
  }

  public void move11 () {
    setupAfterMove(11);
  }

  public void move12 () {
    setupAfterMove(12);
  }

  public void setupAfterMove (int numSteps) {

    hoverEnabled = false;
    dice.setVisible(false);

    moveAvatar((this.masterCurrentPlayer.getLocationIndex() + numSteps) % 32);

    int actionToDo = masterObject.turn(numSteps);
    masterCurrentSpace = masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());

    System.out.println(masterCurrentSpace);
    System.out.println(actionToDo);
    System.out.println();

    setDetails();

    switch (actionToDo) {

      case Game.LAND_ON_START       : setInstructionBox(Game.LAND_ON_START);
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_FREE        : setInstructionBox(Game.LAND_ON_FREE);
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_JAIL        : setInstructionBox(Game.LAND_ON_JAIL);
                                      masterCurrentPlayer.setInJail(true);
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_COMMUNITY   : if (masterCurrentPlayer.isPaymentPossible(50)) {

                                      masterCurrentPlayer.payBank(50, masterObject.getGameBank());
                                      setInstructionBox(Game.LAND_ON_COMMUNITY);
                                      setDetails();
                                      doNothing.setVisible(true);

                                    } else {

                                      masterCurrentPlayer.payBank(-50, masterObject.getGameBank());
                                      setDetails();
                                      setInstructionBox("You have insufficient funding to pay for community service.");
                                      gameIsEnd(0);

                                    }
                                      break;

      case Game.LAND_ON_CHANCE      : setInstructionBox(Game.LAND_ON_CHANCE);
                                      drawChance.setVisible(true);
                                      break;

      case Game.LAND_ON_TAX         : String strType = ((TaxSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex())).isIncome() ? "Income" : "Luxury";
                                      setSpaceBox(strType + " Tax");

                                      double toPay = ((TaxSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex())).payTax(masterCurrentPlayer);

                                      if (toPay != -1) {

                                        masterCurrentPlayer.payBank(toPay, masterObject.getGameBank());
                                        setDetails();
                                        setInstructionBox("You have been automatically deducted $" + toPay);
                                        doNothing.setVisible(true);

                                      } else {

                                        masterCurrentPlayer.payBank(toPay * -1, masterObject.getGameBank());
                                        setDetails();
                                        setInstructionBox("You have insufficient funding to pay for taxes.");
                                        gameIsEnd(0);

                                      }
                                      break;

      case Game.LAND_ON_OWNABLE     : setupOwnableScreen();
                                      break;

      case Game.GAME_IS_END         : gameIsEnd(2);

    }

  }

}
