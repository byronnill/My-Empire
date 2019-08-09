package Model;

import java.util.*;

public class Game {

  public static final int GAME_IS_END = -1;
//  public static final int NEXT_PLAYER_TURN = 1;
  public static final int LAND_ON_OWNABLE = 2;
  public static final int LAND_ON_TAX = 3;
  public static final int LAND_ON_CHANCE = 4;
  public static final int LAND_ON_START = 5;
  public static final int LAND_ON_FREE = 6;
  public static final int LAND_ON_JAIL = 7;
  public static final int LAND_ON_COMMUNITY = 8;

  private final int NUM_PLAYERS;
  private final Bank GAME_BANK;

  private ArrayList <Player> playerList;
  private Player activePlayer;
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
    this.activePlayer = this.playerList.get((this.activePlayer.getPlayerNum() + 1) % this.NUM_PLAYERS);
  }

  public int turn (int diceHolder) {

    Player currPlayer = this.activePlayer;
    Space currSpace;
    int currSpaceIndex;

    //checking for jail status included in controller

    if (!currPlayer.movePlayer(this.gameBoard, diceHolder, true, this.GAME_BANK))
      return GAME_IS_END;

    currSpaceIndex = currPlayer.getLocationIndex();
    currSpace = this.gameBoard.getBoardSpaces().get(currSpaceIndex);

    if (currSpaceIndex == 0) {

      return LAND_ON_START;

    } else if (currSpaceIndex == 8) {

      return LAND_ON_FREE;

    } else if (currSpaceIndex == 16) {

      currPlayer.setInJail(true);

      return LAND_ON_JAIL;

    } else if (currSpaceIndex == 24) {

      return LAND_ON_COMMUNITY;

    } else if (currSpace instanceof TaxSpace) {

      return LAND_ON_TAX;

    } else if (currSpace instanceof ChanceSpace) {

      return LAND_ON_CHANCE;

    } else  {

      return LAND_ON_OWNABLE;

    }

  }

  public int doesGameResume () {

    for (Player p : this.playerList) {

      int nCounter = 0;

      for (int j = 0; j < 9; j++) {

        if (p.isTypeComplete(j))
          nCounter++;

      }

      if (nCounter >= 2)
        return 1;

    }

    if (GAME_BANK.getValue() <= 0)
      return 2;

    return 0;
  }

  public ArrayList <Player> rankPlayers () {

    ArrayList <Player> ranked = this.playerList;
    double[] nWorth = new double[NUM_PLAYERS];

    for (int i = 0; i < this.NUM_PLAYERS; i++) {

    nWorth[i] = this.playerList.get(i).getCash();

    for (OwnableSpace s : this.playerList.get(i).getOwnedSpaces())
      nWorth[i] += s.getWorth();

    if (this.playerList.get(i).getCash() <= 0)
      nWorth[i] *= -1;

    this.playerList.get(i).setWorth(nWorth[i]);

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

  public Player getActivePlayer () {
    return activePlayer;
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

  public void setActivePlayer (Player activePlayer) {
    this.activePlayer = activePlayer;
  }

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

    String toReturn = "";

    for (Player p : this.playerList) {

        toReturn = toReturn + "Player " + (p.getPlayerNum() + 1) + ": " + p.getName() + "\n";

    }

    return toReturn;

  }

}
