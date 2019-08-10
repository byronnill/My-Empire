package Model;

/**
 * Class that comprises the contents of a <b><i>Board</i></b> object's <b>boardSpaced</b> attribute. Extends to
 * <b><i>OwnableSpace</i></b>, <b><i>TaxSpace</i></b>, and <b><i>ChanceSpace</i></b>.
 */

public class Space {

  /**
   * Integer holding the location of an instance on a <b><i>Board</i></b>.
   */

  protected int nLocationIndex;

  /**
   * Constructor for a <b><i>Board</i></b> object.
   *
   * @param nLocationIndex Integer holding the specific location index of an instance as explained in {@link #nLocationIndex}
   */

  public Space (int nLocationIndex) {
    this.nLocationIndex = nLocationIndex;
  }

  /**
   * Getter method for the <b>nLocationIndex</b> attribute of this class.
   * @return
   */

  public int getLocationIndex () {
    return nLocationIndex;
  }

  /**
   * Overriden <b>toString()</b> method from the Object class. Used only in program testing and confirmation.
   *
   * @return String representation of the attributes of this class.
   * @see Object#toString()
   */

  @Override
  public String toString () {

    String toReturn = "Space{" +
                      "nLocationIndex=" + nLocationIndex;

    switch (this.nLocationIndex) {

      case 0: toReturn += " (Start)}"; break;
      case 8: toReturn += " (Free Parking)}"; break;
      case 16: toReturn += " (Jail)}"; break;
      case 24: toReturn += " (Community Service)}"; break;
      default: toReturn += "}";

    }

    return toReturn;

  }

}
