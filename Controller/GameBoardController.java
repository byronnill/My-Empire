package Controller;

import Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.util.*;

public class GameBoardController {

  private Game masterObject;
  private Player masterCurrentPlayer;
  private boolean hoverEnabled;

  @FXML
  private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31;

  @FXML
  private ImageView backdrop, dice1, dice2, currentSpace, currentSpaceSquare;

  @FXML
  private Button dice, buy, doNothing, rent, trade;

  @FXML
  private TextField bankValue;

  private Image gray0, gray1, purple0, purple1, purple2, pink0, pink1, pink2, green0, green1, green2, blue0, blue1, blue2, orange0, orange1, yellow0, yellow1, rail0, rail1, rail2, util0, util1, chance, income, luxury, start, park, jail, service, backdropImage;
  private Image gray0_, gray1_, purple0_, purple1_, purple2_, pink0_, pink1_, pink2_, green0_, green1_, green2_, blue0_, blue1_, blue2_, orange0_, orange1_, yellow0_, yellow1_, rail0_, rail1_, rail2_, util0_, util1_;
  private Image dice1Holder, dice2Holder;

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
      else if (i > 24 && i < 32)
        returnImageView(i).setRotate(90);

    }


    backdrop.setImage(backdropImage);
    bankValue.setText(Double.toString(masterObject.getGameBank().getValue()));
    currentSpace.setVisible(false);
    currentSpaceSquare.setVisible(false);

    dice1.setVisible(false);
    dice2.setVisible(false);
    dice.setDefaultButton(true);

    buy.setStyle("-fx-cursor: hand");
    doNothing.setStyle("-fx-cursor: hand");
    rent.setStyle("-fx-cursor: hand");
    trade.setStyle("-fx-cursor: hand");
    dice.setStyle("-fx-cursor: hand");

    buy.setVisible(false);
    doNothing.setVisible(false);
    rent.setVisible(false);
    trade.setVisible(false);

  }

  public void initialize () {

    backdropImage = new Image("/Images/Main/Game Screen.png");
    dice1Holder = new Image("/Images/Main/Dice 6.png");
    dice2Holder = new Image("/Images/Main/Dice 6.png");
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

    hoverEnabled = true;

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

  public void setupChanceScreen (Card cardDrawn) {



  }

  public void setupOwnableScreen () {

    OwnableSpace currSpace = (OwnableSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex());



  }

  public void handleDice () {

    hoverEnabled = false;

    int curr1 = masterObject.rollDice();
    dice1Holder = new Image("/Images/Main/Dice " + curr1 + ".png");

    int curr2 = masterObject.rollDice();
    dice2Holder = new Image("/Images/Main/Dice " + curr2 + ".png");

    dice1.setImage(dice1Holder);
    dice2.setImage(dice2Holder);

    dice1.setVisible(true);
    dice2.setVisible(true);

    if (!masterCurrentPlayer.isFreedomPossible()) {
      masterObject.setGameFinished(true);
      endGameChangeScene();
    } else {

      //show freedom scene

    }

    int actionToDo = masterObject.turn(curr1 + curr2);

    System.out.println(masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex()));
    System.out.println(actionToDo);
    System.out.println();

    switch (actionToDo) {

      case Game.LAND_ON_START_FREE  : //display no possible options
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_JAIL        : //display player has been sent to jail
                                      masterCurrentPlayer.setInJail(true);
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_COMMUNITY   : if (masterCurrentPlayer.isPaymentPossible(50)) {

                                        //display Player has paid 50
                                        masterCurrentPlayer.payBank(50, masterObject.getGameBank());

                                      } else {

                                        //display bankruptcy
                                        masterCurrentPlayer.setBankruptcy();
                                        endGameChangeScene();

                                      }
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_CHANCE      : setupChanceScreen(masterObject.getGameDeck().drawCard());
                                      break;

      case Game.LAND_ON_TAX         : double toPay = ((TaxSpace) masterObject.getGameBoard().getBoardSpaces().get(masterCurrentPlayer.getLocationIndex())).payTax(masterCurrentPlayer);

                                      if (toPay != -1) {

                                        //display Player has paid tax
                                        masterCurrentPlayer.payBank(toPay, masterObject.getGameBank());

                                      } else {

                                        //display bankruptcy
                                        masterCurrentPlayer.setBankruptcy();
                                        endGameChangeScene();

                                      }
                                      doNothing.setVisible(true);
                                      break;

      case Game.LAND_ON_OWNABLE     : setupOwnableScreen();
                                      break;

      default                       : endGameChangeScene();

    }

    masterObject.endTurn();
    masterCurrentPlayer = masterObject.getActivePlayer();

  }

  public void handleBuy () {


  }

  public void handleRent () {

  }

  public void handleContinue () {

    dice.setVisible(true);
    doNothing.setVisible(false);

  }

  public void handleTrade () {

  }

  public void endGameChangeScene () {

  }

}
