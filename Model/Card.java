package Model;

import java.util.*;

/**
 * Abstract class that effectively organizes the contents of a <b><i>Deck</i></b> object.
 */

public abstract class Card {

  /**
   * Empty constructor for a <b><i>Card</i></b> object that allows for subclasses to be instantiated as well.
   */

  public Card () {}

  /**
   * Method that generates random numbers within varying ranges for the different subclasses of this class.
   *
   * @param nLo Integer defining the minimum number that can be generated.
   * @param nHi Integer defining the maximum number that can be generated.
   * @return Integer that is within the range <i>nLo</i>-<i>nHi</i>, inclusive.
   */

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();

    return rnd.nextInt(nHi) % (nHi - nLo + 1) + nLo;

  }

}
