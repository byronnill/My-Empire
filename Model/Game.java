package Model;

import java.util.*;

/**
 * Master class that manages all actions to be done on application run. Contains instances of the other classes
 * and allows for user movement, manipulation, and observation on these objects.
 */

public class Game {

  /**
   * Integer returned in <b>turn()</b> that indicates if the application run should end after the current action.
   *
   * @see #turn(int)
   */

  public static final int GAME_IS_END = -1;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on an instance of
   * <b><i>OwnableSpace</i></b> upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_OWNABLE = 2;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on an instance of
   * <b><i>TaxSpace</i></b> upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_TAX = 3;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on an instance of
   * <b><i>ChanceSpace</i></b> upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_CHANCE = 4;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on index 0 of the <b>gameBoard</b>
   * attribute upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_START = 5;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on index 8 of the <b>gameBoard</b>
   * attribute upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_FREE = 6;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on index 16 of the <b>gameBoard</b>
   * attribute upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_JAIL = 7;

  /**
   * Integer returned in <b>turn()</b> that indicates if the active player landed on index 24 of the <b>gameBoard</b>
   * attribute upon movement.
   *
   * @see #turn(int)
   */

  public static final int LAND_ON_COMMUNITY = 8;

  private final int NUM_PLAYERS;
  private final Bank GAME_BANK;

  private ArrayList <Player> playerList;
  private Player activePlayer;
  private Board gameBoard;
  private Deck gameDeck;

  /**
   * Constructor for a <b><i>Game</i></b> object that constructs an instance of <b><i>Bank</i></b> as well.
   * The instantiation of <b>gameBoard</b> and <b>gameDeck</b> is done through the methods <b>setGameBoard()</b>
   * and <b>setGameDeck()</b>.
   *
   * <p>
   * The <b>activePlayer</b> attribute will be given a value upon the ordering of <b><i>Players</i></b>.
   *
   * @param nPlayers Integer that holds the number of players that will be in the <b><i>Game</i></b>.
   * @see #orderPlayers(ArrayList)
   * @see #setGameBoard(Board)
   * @see #setGameDeck(Deck)
   */

  public Game (int nPlayers) {

    this.NUM_PLAYERS = nPlayers;

    this.playerList = new ArrayList <> (this.NUM_PLAYERS);

    for (int i = 0; i < nPlayers; i++)
      this.playerList.add(new Player(i));

    this.GAME_BANK = new Bank(nPlayers);

  }

  /**
   * Method that orders the contents of the <b>playerList</b> attribute according to the <i>diceResults</i> parameter.
   * The corresponding values in <i>diceResults</i> is set as a virtual key for each corresponding <b><i>Player</i></b>
   * in <b>playerList</b>. These instances are then ordered in descending order according to the keys and their
   * <b>nPlayerNum</b> attribute set to the appropriate value as well.
   *
   * <p>
   * After ordering, <b>activePlayer</b> is set equal to the instance at index 0 of the modified <b>playerList</b>
   * attribute.
   *
   *
   * @param diceResults ArrayList of Integer objects that holds the results of random number generation done externally.
   */

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

    this.activePlayer = temp.get(0);
    this.playerList = temp;

  }

  /**
   * Method used to swap the positions of two <b><i>Player</i></b> instances in a list of such objects.
   *
   * @param tempList ArrayList of <b><i>Player</i></b> objects that contains the two instances that are to be swapped.
   * @param pos1 Integer holding the position of the first instance to be swapped with the second.
   * @param pos2 Integer holding the position of the second instance to be swapped with the first.
   */

  public void swapPlayers (ArrayList<Player> tempList,  int pos1, int pos2) {

    Player temp = tempList.get(pos1);

    tempList.set(pos1, tempList.get(pos2));
    tempList.set(pos2, temp);

  }

  /**
   * Method that returns a random integer between 1 and 6, inclusive, to simulate the rolling of a single die.
   * Calling this function is usually done in twos.
   *
   * @return Integer that is randomly generated between the range 1-6, inclusive.
   */

  public int rollDice () {

    Random rndGen = new Random();

    return rndGen.nextInt(6) + 1;

  }

  /**
   * Changes the <b>activePlayer</b> attribute to the next instance as ordered in the <b>playerList</b> attribute.
   */

  public void endTurn () {
    this.activePlayer = this.playerList.get((this.activePlayer.getPlayerNum() + 1) % this.NUM_PLAYERS);
  }

  /**
   * Method handling the movement and landing of the <b>activePlayer</b> attribute on the <b>gameBoard</b> attribute.
   *
   *
   * @param diceHolder Integer holding the number of "steps" that the <b>activePlayer</b> will take to go to the new
   *                   location.
   * @return Predefined final integer that is determined by where the <b>activePlayers</b> has landed or if the
   *         <b>GAME_BANK</b>'s <b>dValue</b> attribute is reduced to at most 0.
   * @see Player#movePlayer(Board, int, boolean, Bank)
   */

  public int turn (int diceHolder) {

    Space currSpace;
    int currSpaceIndex;

    //checking for jail status included in controller

    if (!this.activePlayer.movePlayer(this.gameBoard, diceHolder, true, this.GAME_BANK))
      return GAME_IS_END;

    currSpaceIndex = this.activePlayer.getLocationIndex();
    currSpace = this.gameBoard.getBoardSpaces().get(currSpaceIndex);

    if (currSpaceIndex == 0) {

      return LAND_ON_START;

    } else if (currSpaceIndex == 8) {

      return LAND_ON_FREE;

    } else if (currSpaceIndex == 16) {

      this.activePlayer.setInJail(true);

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

  /**
   * Method checking if the current <b><i>Game</i></b> will be terminated based on the following conditions:
   *
   * <ul>
   *   <li>A <b><i>Player</i></b> has already collected two full sets of <b><i>OwnableSpace</i></b> instances or</li>
   *   <li>The <b>dValue</b> attribute of the <b>GAME_BANK</b> is reduced to at most 0.</li>
   * </ul>
   *
   * <p>
   * The <b><i>Game</i></b> may also end if a <b><i>Player</i></b> is also rendered bankrupt, however checking of that
   * condition is done during instances of payment.
   *
   * @return Integer holding the reason for <b><i>Game</i></b> termination: returns 1 if the first condition was
   *         satisfied; 2 if the second condition was satisfied, and 0 if the <b><i>Game</i></b> shall resume.
   * @see Player#isTypeComplete(int)
   */

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

  /**
   * Method that returns the final ranking of <b><i>Players</i></b> at <b><i>Game</i></b> termination.
   *
   * <p>
   * <b><i>Players</i></b> are ranked based on their total cash on hand (<b>dCash</b>) and the total value of their
   * <b>ownedSpaces</b> including the prices of <b><i>Property</i></b> development.
   *
   * <p>
   * If the <b><i>Game</i></b> ends on <b><i>Player</i></b> bankruptcy, that <b><i>Player</i></b> will always be on
   * the bottom of the rankings no matter the worth of their <b>ownedSpaces</b>;
   *
   * @return ArrayList of <b><i>Player</i></b> objects that are ranked based on their <b>dWorth</b>
   */

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

  /**
   * Console printing method that displays the <b>playerList</b> on the environment console. Used only
   * in program testing and confirmation.
   */

  public void consolePrintPlayers () {

    for (Player p : this.playerList)
      System.out.println(p.getPlayerNum() + " " + p.getName());

  }

  //getters

  /**
   * Getter method for the <b>NUM_PLAYERS</b> attribute of this class.
   *
   * @return Integer holding the number of <b><i>Players</i></b> that participated in this <b><i>Game</i></b> instance.
   */

  public int getNumPlayers () {
    return NUM_PLAYERS;
  }

  /**
   * Getter method for the <b>playerList</b> attribute of this class.
   *
   * @return ArrayList of <b><i>Player</i></b> objects that holds those that participated in this <b><i>Game</i></b>
   *         instance.
   */

  public ArrayList<Player> getPlayerList () {
    return playerList;
  }

  /**
   * Getter method for the <b>activePlayer</b> attribute of this class.
   *
   * @return <b><i>Player</i></b> object that holds the current, active player of the <b><i>Game</i></b>.
   */


  public Player getActivePlayer () {
    return activePlayer;
  }

  /**
   * Getter method for the <b>gameBoard</b> attribute of this class.
   *
   * @return <b><i>Board</i></b> object.
   */


  public Board getGameBoard () {
    return gameBoard;
  }

  /**
   * Getter method for the <b>gameDeck</b> attribute of this class.
   *
   * @return <b><i>Deck</i></b> object.
   */

  public Deck getGameDeck () {
    return gameDeck;
  }

  /**
   * Getter method for the <b>GAME_BANK</b> attribute of this class.
   *
   * @return <b><i>Bank</i></b> object.
   */

  public Bank getGameBank () {
    return GAME_BANK;
  }

  /**
   * Setter method for the <b>gameBoard</b> attribute of this class.
   *
   * @param gameBoard <b><i>Board</i></b> object that will be the new <b><i>gameBoard</i></b> of the <b><i>Game</i></b>
   */

  public void setGameBoard (Board gameBoard) {
    this.gameBoard = gameBoard;
  }

  /**
   * Setter method for the <b>gameDeck</b> attribute of this class.
   *
   * @param gameDeck <b><i>Deck</i></b> object that will be the new <b><i>gameDeck</i></b> of the <b><i>Game</i></b>
   */

  public void setGameDeck (Deck gameDeck) {
    this.gameDeck = gameDeck;
  }

  /**
   * Equivalent <b>toString</b> method that returns only the objects in the <b>playerList</b> attribute in order.
   *
   * @return A concatenated String representation of the <b><i>Players</i></b> in the <b>playerList</b>.
   */

  public String playerOrderString () {

    String toReturn = "";

    for (Player p : this.playerList) {

        toReturn = toReturn + "Player " + (p.getPlayerNum() + 1) + ": " + p.getName() + "\n";

    }

    return toReturn;

  }

}
