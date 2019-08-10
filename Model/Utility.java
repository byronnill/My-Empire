package Model;

/**
 * Utility type of class <b><i>OwnableSpace</i></b>.
 */

public class Utility extends OwnableSpace{

  /**
   * Constructor for a <b><i>Utility</i></b> object. The <b>OWNABLE_ID</b> of this class's instances are based on their
   * position in <b><i>Reference</i>.UTILITIES</b> * 1000.
   *
   * @param nLocationIndex Integer holding the location of an instance on a <b><i>Board</i></b>.
   * @param nID Integer holding the identification number of an instance.
   * @see Reference#UTILITIES
   */

  public Utility(int nLocationIndex, int nID) {

    super(nLocationIndex, nID, Reference.UTILITIES[nID / 1000 - 1], 150, 4);

  }

}
