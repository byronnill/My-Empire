package Model;

import java.util.*;

public abstract class Card {

  public Card () {}

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();
    int toReturn = rnd.nextInt(nHi);

    while (toReturn < nLo)
      toReturn = rnd.nextInt(nHi);

    return toReturn;

  }

}
