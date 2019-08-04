package Model;

import java.util.*;

public class Game {

  private final int NUM_PLAYERS;
  private final Bank GAME_BANK;

  private ArrayList <Player> playerList;
  private int nActivePlayer;
  private Board gameBoard;
  private Deck gameDeck;
  private boolean gameFinished;

  public Game (int nPlayers) {

    this.NUM_PLAYERS = nPlayers;

    this.playerList = new ArrayList <> (this.NUM_PLAYERS);

    for (int i = 0; i < nPlayers; i++)
      this.playerList.add(new Player(i));

    this.GAME_BANK = new Bank(nPlayers);

    this.gameFinished = false;
    this.nActivePlayer = 0;

  }

  //=start player ordering

  public void orderPlayers (ArrayList <Integer> diceResults) {

    ArrayList <Player> temp = playerList;

    for (int i = 0; i < playerList.size(); i++)
      temp.get(i).setPlayerNum(diceResults.get(i));

    for (int i = 0; i < temp.size() - 1; i++) {

      for (int j = i + 1; j < temp.size(); j++)

        if (temp.get(i).getPlayerNum() < temp.get(j).getPlayerNum())
          swapPlayers(temp, temp.indexOf(temp.get(i)), temp.indexOf(temp.get(j)));

    }

    for (int i = 0; i < temp.size(); i++)
      temp.get(i).setPlayerNum(i);

    this.playerList = temp;

  }

  public void swapPlayers (ArrayList<Player> tempList,  int pos1, int pos2) {

    Player temp = tempList.get(pos1);

    tempList.set(pos1, tempList.get(pos2));
    tempList.set(pos2, temp);

  }

  //=end

  public int rollDice () {

    Random rndGen = new Random();

    int n1 = rndGen.nextInt(6) + 1;

    return n1;

  }

  public void endTurn () {
    this.nActivePlayer = (this.nActivePlayer + 1) % this.NUM_PLAYERS;
  }

  public boolean turn () {

    Player currPlayer = this.playerList.get(this.nActivePlayer);
    Space currSpace;
    int diceHolder, currSpaceIndex;

    //checking for jail status

    if (!currPlayer.isFreedomPossible()) {

      gameFinished = true;
      return false;

    }

    diceHolder = rollDice();
    if (!currPlayer.movePlayer(this.gameBoard, diceHolder, true, this.GAME_BANK))
      return false;

    currSpaceIndex = currPlayer.getLocationIndex();
    currSpace = this.gameBoard.getBoardSpaces().get(currSpaceIndex);

    if (currSpaceIndex == 0 || currSpaceIndex == 8) {

      this.endTurn();
      return true;

    } else if (currSpaceIndex == 16) {

      currPlayer.setInJail(true);

      this.endTurn();
      return true;

    } else if (currSpaceIndex == 24) {

      boolean bPaymentPossibility = currPlayer.isPaymentPossible(50);

      if (bPaymentPossibility) {

        currPlayer.addOrDeductCash(-50);
        this.GAME_BANK.addOrDeduct(50);

      }

      return bPaymentPossibility;

    } else if (currSpace instanceof TaxSpace) {

      return ((TaxSpace) currSpace).payTax(currPlayer, this.GAME_BANK);

    } else if (currSpace instanceof ChanceSpace) {

      Card cardDrawn = this.gameDeck.drawCard();

      return this.doChance(currPlayer, cardDrawn);

    } else if (currSpace instanceof OwnableSpace) {

      return this.doAction(currPlayer, (OwnableSpace) currSpace, 1);

    }

    return doesGameResume();

  }

  public boolean doesGameResume () {

    for (Player p : this.playerList) {

      int nCounter = 0;

      for (int j = 0; j < 9; j++) {

        if (p.isTypeComplete(j))
          nCounter++;

      }

      if (nCounter >= 2)
        return false;

    }

    this.endTurn();

    return true;
  }

  public boolean doChance (Player currPlayer, Card cardDrawn) {

    if (cardDrawn instanceof CardGroup1) {

      ((CardGroup1) cardDrawn).applyCardToPlayer(currPlayer);

    } else if (cardDrawn instanceof CardGroup2) {

      if (!((CardGroup2) cardDrawn).doChance(this.gameBoard, currPlayer, this.GAME_BANK))
        return false;

      this.gameDeck.discardCard(cardDrawn);

    } else if (cardDrawn instanceof CardGroup3) {

      if (!((CardGroup3) cardDrawn).collectCash(this.gameBoard, currPlayer, this.GAME_BANK))
        return false;

      this.gameDeck.discardCard(cardDrawn);

    } else if (cardDrawn instanceof CardGroup4) {

      if (!((CardGroup4) cardDrawn).doChance(this.gameBoard, currPlayer, this.GAME_BANK))
        return false;

      this.gameDeck.discardCard(cardDrawn);

    } else if (cardDrawn instanceof CardGroup5) {

      if (!((CardGroup5) cardDrawn).isCardApplicable(currPlayer)) {

        this.gameDeck.discardCard(cardDrawn);

      } else {

        OwnableSpace space = currPlayer.getOwnedSpaces().get(0);  //change to accommodate GUI

        ((CardGroup5) cardDrawn).applyCardToSpace(space);

        if (((CardGroup5) cardDrawn).getSpecific() != 1)
          this.gameDeck.discardCard(cardDrawn);

      }

    } else if (cardDrawn instanceof CardGroup6) {

      if (!((CardGroup6) cardDrawn).payCash(currPlayer, this.GAME_BANK))
        return false;

      this.gameDeck.discardCard(cardDrawn);

    }

    return true;

  }

  public boolean doAction (Player currPlayer, OwnableSpace currSpace, int action) {

    switch (action) {

//      case 1: currPlayer.buyProperty(currSpace, this.GAME_BANK); break;
//      case 2: currPlayer.developProperty((Property) currSpace, this.GAME_BANK);
//      case 3: currPlayer.payRent(currSpace, currSpace.getOwner(), this, this.gameDeck);
//      case 4: currPlayer.tradeProperty();

    }


    return true;

  }

  public ArrayList <Player> rankPlayers () {

    ArrayList <Player> ranked = this.playerList;
    double[] nWorth = new double[NUM_PLAYERS];

    for (int i = 0; i < this.NUM_PLAYERS; i++) {

     if (this.playerList.get(i).getCash() <= 0)
        nWorth[i] = 0;

     else {

        nWorth[i] = this.playerList.get(i).getCash();

        for (OwnableSpace s : this.playerList.get(i).getOwnedSpaces())
          nWorth[i] += s.getWorth();

     }

    }

    for (int i = 0; i < nWorth.length - 1; i++) {

      for (int j = i + 1; j < nWorth.length; j++)

        if (nWorth[i] < nWorth[j]) {
          double temp = nWorth[i];

          nWorth[i] = nWorth[j];
          nWorth[j] = temp;

          swapPlayers(ranked, ranked.indexOf(ranked.get(i)), ranked.indexOf(ranked.get(j)));
        }

    }

    return ranked;

  }

  //console

  public void consolePrintPlayers () {

    for (Player p : this.playerList)
      System.out.println(p.getPlayerNum() + " " + p.getName());

  }

  //getters

  public int getNumPlayers () {
    return NUM_PLAYERS;
  }

  public ArrayList<Player> getPlayerList () {
    return playerList;
  }

  public int getActivePlayer () {
    return nActivePlayer;
  }

  public Board getGameBoard () {
    return gameBoard;
  }

  public Deck getGameDeck () {
    return gameDeck;
  }

  public Bank getGameBank () {
    return GAME_BANK;
  }

  public boolean isGameFinished () {
    return gameFinished;
  }

  //setters

  public void setGameBoard (Board gameBoard) {
    this.gameBoard = gameBoard;
  }

  public void setGameDeck (Deck gameDeck) {
    this.gameDeck = gameDeck;
  }

  public void setGameFinished (boolean gameFinished) {
    this.gameFinished = gameFinished;
  }

  public String playerOrderString () {

    String toReturn = new String();

    for (Player p : this.playerList) {

        toReturn = toReturn + "Player " + (p.getPlayerNum() + 1) + ": " + p.getName() + "\n";

    }

    return toReturn;

  }

}
