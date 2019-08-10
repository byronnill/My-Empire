package Model;

/**
 * Chance type extension of the <b><i>Space</i></b> class. No extra methods are defined. Only provides for easier
 * <b><i>Space</i></b> instance differentiation. The associated identification number for instances of this class is
 * always -1.
 */

public class ChanceSpace extends Space {

  /**
   * Constructor for a <b><i>ChanceSpace</i></b> object.
   *
   * @param nLocationIndex Integer holding the index of a <b><i>Board</i></b> instance's <b>boardSpaces</b> attribute
   *                       in which an instance of this class will be placed.
   */

  public ChanceSpace(int nLocationIndex) {
    super(nLocationIndex);
  }

}
