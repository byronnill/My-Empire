package Model;

import java.util.*;

/**
 * Class handling the <b>gameBoard</b> attribute of a master <b><i>Game</i></b> object. In that instance, objects of
 * type <b><i>Player</i></b> move along <b><i>Spaces</i></b> and manipulate instances of <b><i>OwnableSpace</i></b>.
 */

public class Board {

  private ArrayList <Space> boardSpaces;

  /**
   * Constructor for a <b><i>Board</i></b> object that arranges <b><i>Space</i></b> instances on <b>boardSpaces</b>
   * in a random order.
   *
   * <p>
   * This constructor initializes objects of type <b><i>Space</i></b>, <b><i>TaxSpace</i></b>,
   * <b><i>ChanceSpace</i></b>, <b><i>Property</i></b>, <b><i>Railroad</i></b>, and <b><i>Utility</i></b>.
   * The last three are subclasses of <b><i>OwnableSpace</i></b>.
   *
   * <p>
   * Generic <b><i>Space</i></b> objects are always set at indices 0, 8, 16, and 24 of <b>boardSpaces</b>.
   * <b><i>TaxSpace</i></b> and <b><i>ChanceSpace</i></b> instances, meanwhile, are given random indices that are not
   * yet occupied. Lastly, this constructor arranges instances of <b><i>OwnableSpace</i></b> in the order that is
   * specified in the <b><i>Reference</i></b> class static variables in consecutive available spaces.
   *
   * @param nPlayers Integer that holds the number of players in the <b><i>Game</i></b>. This is used in determining
   *                 the <i>REQUIRED_FOOT_TRAFFIC</i> attribute of a <b><i>Property</i></b> object that is instantiated
   *                 in this constructor.
   * @see Reference
   */

  public Board (int nPlayers) {

    ArrayList <Integer> occupiedSpaces = new ArrayList <> (32);
    int holder = 0;

    boardSpaces = new ArrayList <> (32);

    for (int i = 0; i < 32; i++)
      boardSpaces.add(new Space(i));

    occupiedSpaces.add(0);
    occupiedSpaces.add(8);
    occupiedSpaces.add(16);
    occupiedSpaces.add(24);

    while (occupiedSpaces.contains(holder))
      holder = randomGen(0, 31);
    boardSpaces.set(holder, new TaxSpace(holder, true));
    occupiedSpaces.add(holder);

    while (occupiedSpaces.contains(holder))
      holder = randomGen(0, 31);
    boardSpaces.set(holder, new TaxSpace(holder, false));
    occupiedSpaces.add(holder);

    for (int i = 0; i < 3; i++) {

      while (occupiedSpaces.contains(holder))
        holder = randomGen(0, 31);
      boardSpaces.set(holder, new ChanceSpace(holder));
      occupiedSpaces.add(holder);

    }

    for (int i = 0; i < 3; i++) {

      while (occupiedSpaces.contains(holder))
        holder = randomGen(0, 31);
      boardSpaces.set(holder, new Railroad(holder, (i + 1) * 100));
      occupiedSpaces.add(holder);

    }

    for (int i = 0; i < 2; i++) {

      while (occupiedSpaces.contains(holder))
        holder = randomGen(0, 31);
      boardSpaces.set(holder, new Utility(holder, (i + 1) * 1000));
      occupiedSpaces.add(holder);

    }

    holder = 0;
    while (occupiedSpaces.contains(holder))
      holder++;

    int i, j;

    for (i = 0; i < 7; i++) {

      for (j = 0; j < Reference.PROPERTIES[i].length; j++) {

        boardSpaces.set(holder, new Property(holder, i, j, nPlayers));
        occupiedSpaces.add(holder);
        while (occupiedSpaces.contains(holder))
          holder++;

      }

    }

  }

  /**
   * Constructor for a <b>Board</b> object that instantiates all contents of <b>boardSpaces</b> as generic
   * <b><i>Space</i></b> objects.
   *
   * <p>
   * The method <b>setNewSpace()</b> below takes the function of placing new instances of <b><i>Space</i></b> and its
   * subclasses to a <b><i>Board</i></b> object's <b>boardSpaces</b> attribute with a specified identifier and index.
   *
   * @see #setNewSpace(int, int, int)
   */

  public Board () {

    boardSpaces = new ArrayList <> (32);

    for (int i = 0; i < 32; i++)
      boardSpaces.add(new Space(i));

  }

  /**
   * Method that generates random numbers within the range 0-31 inclusive. This is used in the random
   * <b><i>Board</i></b> constructor to randomize the indices of <b><i>TaxSpace</i></b>, <b><i>ChanceSpace</i></b>,
   * <b><i>Railroad</i></b>, and <b><i>Utility</i></b> instances within the <b>boardSpaces</b> attribute;
   *
   * @param nLo Integer defining the minimum number that can be generated (0).
   * @param nHi Integer defining the maximum number that can be generated (31).
   * @return Integer that is within the range <i>nLo</i>-<i>nHi</i>, inclusive.
   */

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();

    return rnd.nextInt(nHi) % (nHi - nLo + 1) + nLo;

  }

  /**
   * Console printing method that displays the contents of <b>boardSpaces</b> on the environment console. Used only
   * in program testing and confirmation.
   */

  public void consolePrintBoard () {

    for (int i = 0; i < 32; i++) {

      if (boardSpaces.get(i) instanceof ChanceSpace)
        System.out.print("Chance");

      else if (boardSpaces.get(i) instanceof TaxSpace)
        if (((TaxSpace) boardSpaces.get(i)).isIncome())
          System.out.print("Income Tax");

        else
          System.out.print("Luxury Tax");

      else if (boardSpaces.get(i) instanceof Utility)
        System.out.print(((Utility) boardSpaces.get(i)).getName());

      else if (boardSpaces.get(i) instanceof Railroad)
        System.out.print(((Railroad) boardSpaces.get(i)).getName());

      else if (boardSpaces.get(i) instanceof Property)
        System.out.print(((Property) boardSpaces.get(i)).getName());

      else if (i == 0)
        System.out.print("Start");

      else if (i == 8)
        System.out.print("Free Parking");

      else if (i == 16)
        System.out.print("Jail");

      else if (i == 24)
        System.out.print("Community Service");

      System.out.print(" (Location = " + boardSpaces.get(i).getLocationIndex());

      if (!(boardSpaces.get(i) instanceof OwnableSpace))
        System.out.println(")");

      else
        System.out.println(") + (Ownable ID = " + ((OwnableSpace) boardSpaces.get(i)).getID() + ")");

    }

  }

  //getters and setters

  /**
   * Getter method for the <b>boardSpaces</b> attribute of this class.
   *
   * @return <b>boardSpaces</b> ArrayList of <b><i>Space</i></b> objects that holds the contents of the
   *                            <b><i>Board</i></b> object.
   */

  public ArrayList<Space> getBoardSpaces () {
    return boardSpaces;
  }

  /**
   * Equivalent setter method for the contents of the <b>boardSpaces</b> attribute of this class. This method sets,
   * instead of adds, instances of either <b><i>Property</i></b>, <b><i>Railroad</i></b>, <b><i>Utility</i></b>,
   * <b><i>ChanceSpace</i></b>, or <b><i>TaxSpace</i></b> to the existing indices of <b>boardSpaces</b>.
   *
   * @param nIndex Integer holding the <b>boardSpaces</b> position that is to be changed.
   * @param nID Integer that is predefined to allow for easy determination of what will be instantiated at index
   *            <i>nID</i>
   * @param nPlayers Integer that holds the number of players in the <b><i>Game</i></b>. This is used in determining
   *                 the <i>REQUIRED_FOOT_TRAFFIC</i> attribute of a <b><i>Property</i></b> object that is instantiated
   *                 in this method.
   */

  public void setNewSpace (int nIndex, int nID, int nPlayers) {

    if (nID >= 0 && nID < 100)
      boardSpaces.set(nIndex, new Property(nIndex, nID / 10, nID % 10, nPlayers));

    else if (nID >= 100 && nID < 1000)
      boardSpaces.set(nIndex, new Railroad(nIndex, nID));

    else if (nID >= 1000)
      boardSpaces.set(nIndex, new Utility(nIndex, nID));

    else if (nID == -1)
      boardSpaces.set(nIndex, new ChanceSpace(nIndex));

    else if (nID == -2)
      boardSpaces.set(nIndex, new TaxSpace(nIndex, true));

    else if (nID == -3)
      boardSpaces.set(nIndex, new TaxSpace(nIndex, false));

  }

}
