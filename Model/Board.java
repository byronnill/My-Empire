package Model;

import java.util.*;

public class Board {

  private ArrayList <Space> boardSpaces;

  //randomly arrange spaces

  /**
   * Constructor for a Board object with randomly assigned spaces.
   *
   * <p>
   * This constructor intializes objects of type Space, TaxSpace, ChanceSpace, Railroad, Utility, and Property.
   * The last three are subclasses of OwnableSpace.
   *
   * <p>
   * Generic corner spaces are always set at indices 0, 8, 16, and 24. TaxSpace and ChanceSpace instances, meanwhile,
   * are given random indices that are not yet occupied. Lastly, this constructor arranges instances of OwnableSpace
   * in the order that is specified in the Reference class static variables in consecutive available spaces.
   *
   * @param nPlayers
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

  //set corner spaces only

  /**
   * Constructor for a Board object with corner spaces only.
   *
   * <p>
   * The function setNewSpace below takes the function of placing new instances of Space to a Board object's boardSpaces
   * attribute with a specified identifier and index.
   *
   * @see #setNewSpace(int, int, int)
   */

  public Board () {

    boardSpaces = new ArrayList <> (32);

    for (int i = 0; i < 32; i++)
      boardSpaces.add(new Space(i));

  }

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();
    int toReturn = rnd.nextInt(nHi);

    while (toReturn < nLo)
      toReturn = rnd.nextInt(nHi);

    return toReturn;

  }

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

  //getter

  public ArrayList<Space> getBoardSpaces () {
    return boardSpaces;
  }

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
