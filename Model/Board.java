package Model;

import java.util.*;

/**
 * Class handling the main board of a <b><i>Game</i></b> object. This is where <b><i>Player</i></b> instances
 * move and manipulate <b><i>OwnableSpace</i></b> instances.
 *
 * @see Game
 * @see Player
 * @see OwnableSpace
 */

public class Board {

  private ArrayList <Space> boardSpaces;

  /**
   * Constructor for a <b><i>Board</i></b> object with randomly assigned <b><i>Space</i></b> instances.
   *
   * <p>
   * This constructor initializes objects of type <b><i>Space</i></b>, <b><i>TaxSpace</i></b>, <b><i>ChanceSpace</i></b>,
   * <b><i>Property</i></b>, <b><i>Railroad</i></b>, and <b><i>Utility</i></b>. The last three are subclasses of
   * <b><i>OwnableSpace</i></b>.
   *
   * <p>
   * Generic corner spaces are always set at indices 0, 8, 16, and 24. <b><i>TaxSpace</i></b> and
   * <b><i>ShanceSpace</i></b> instances, meanwhile, are given random indices that are not yet occupied.
   * This is followed by the random insertion of <b><i>Railroad</i></b> and <b><i>Utility</i></b> instances. Lastly,
   * this constructor arranges instances of <b><i>Property</i></b> in the order that is specified in the
   * <b><i>Reference</i></b> class in consecutive available spaces.
   *
   * @param nPlayers Integer that holds the number of players in the game. This is used to set the
   *                 <i>REQUIRED_FOOT_TRAFFIC</i> attribute of a <b><i>Property</i></b> object upon instantiation.
   * @see Space
   * @see TaxSpace
   * @see ChanceSpace
   * @see OwnableSpace
   * @see Property
   * @see Railroad
   * @see Utility
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
   * Constructor for a <b><i>Board</i></b> object with generic (corner) <b><i>Space</i></b> instances only.
   *
   * <p>
   * This constructor still creates enough <b><i>Space</i></b> instances to fill the entire board. However,
   * the method <b>setNewSpace()</b> below takes the function of setting new instances of subclasses of
   * <b><i>Space</i></b> to a <b><i>Board</i></b> object's <b><i>boardSpaces</i></b> attribute with a
   * specified identifier and index.
   *
   * @see #setNewSpace(int, int, int)
   */

  public Board () {

    boardSpaces = new ArrayList <> (32);

    for (int i = 0; i < 32; i++)
      boardSpaces.add(new Space(i));

  }

  /**
   * Random number generator used in the random constructor of a <b><i>Board</i></b> object.
   *
   * @param nLo Integer that dictates the lower bound of the random number generator.
   * @param nHi Integer that dictates the upper bound of the random number generator.
   * @return Integer that is randomly generated within the bounds of <i>nLo</i> and <i>nHi</i>.
   * @see #Board(int)
   */

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();
    int toReturn = rnd.nextInt(nHi) % (nHi - nLo + 1) + nLo;

    return toReturn;

  }

  /**
   * Console display method that prints the <b>boardSpaces</b> on the program console in their arranged order.
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
   * Getter method that returns the <b>boardSpaces</b> attribute of this class.
   *
   * @return boardSpaces ArrayList of <b><i>Space</i></b> objects.
   */

  public ArrayList<Space> getBoardSpaces () {
    return boardSpaces;
  }

  /**
   * Equivalent setter method for individual elements of the <b>boardSpaces</b> attribute of this class.
   * This method changes, rather than adds, spaces on the <b><i>Board</i></b> object;
   *
   * @param nIndex Integer that dictates the index at which the new <b><i>Space</i></b> subclass object will be
   *               placed.
   * @param nID Integer that identifies which subclass of <b><i>Space</i></b> will be instantiated and set to the
   *            appropriate <i>nIndex</i>. For <b><i>Property</i></b> instances, <i>nID</i> also serves as the basis
   *            for an instance's <i>COLOR_INDEX</i> (<i>nID</i> / 10) and <i>PROPERTY_INDEX</i> (<i>nID</i> % 10),
   *            following the indices in <b><i>Reference</i></b>.
   * @param nPlayers Integer that holds the number of players in the game. This is used to set the
   *                 <i>REQUIRED_FOOT_TRAFFIC</i> attribute of a <b><i>Property</i></b> object upon instantiation.
   * @see Space
   * @see Property
   * @see Reference
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
