package Model;

import java.util.*;

/**
 * Abstract class that is inherited by all <b><i>CardGroup<u>x</u></i></b> classes to provide for easier organization
 * of <b><i>Card</i></b> objects.
 *
 * @see CardGroup1
 * @see CardGroup2
 * @see CardGroup3
 * @see CardGroup4
 * @see CardGroup5
 * @see CardGroup6
 */

public abstract class Card {

  /**
   * Empty constructor for a <b><i>Card</i></b> object.
   */

  public Card () {}

  /**
   * Random number generator used in the instantiation of <b><i>Card</i></b> subclasses. This is to provide for
   * random specific types of the subclass objects.
   *
   * @param nLo Integer that dictates the lower bound of the random number generator.
   * @param nHi Integer that dictates the upper bound of the random number generator.
   * @return Integer that is randomly generated within the bounds of <i>nLo</i> and <i>nHi</i>.
   */

  public int randomGen (int nLo, int nHi) {

    Random rnd = new Random();
    int toReturn = rnd.nextInt(nHi) % (nHi - nLo + 1) + nLo;

    return toReturn;

  }

}
