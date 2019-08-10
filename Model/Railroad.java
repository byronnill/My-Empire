package Model;

/**
 * Railroad type of class <b><i>OwnableSpace</i></b>.
 */

public class Railroad extends OwnableSpace {

  /**
   * Constructor for a <b><i>Railroad</i></b> object. The <b>OWNABLE_ID</b> of this class's instances are based on their
   * position in <b><i>Reference</i>.RAILROAD</b> * 100.
   *
   * @param nLocationIndex Integer holding the location of an instance on a <b><i>Board</i></b>.
   * @param nID Integer holding the identification number of an instance.
   */

  public Railroad(int nLocationIndex, int nID) {
    super(nLocationIndex, nID, Reference.RAILROADS[nID / 100 - 1], 200, 25);
  }

}
