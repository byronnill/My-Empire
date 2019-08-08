package Model;

/**
 * Class handling the determination of which <b><i>OwnableSpace</i></b> to go to when an instance of this class
 * is drawn on an application run.
 *
 * <p>
 * The property <b>SPEC_TYPE</b> determines whether the randomly generated
 * <b><i>OwnableSpace</i></b> instance is of type <b><i>Property</i></b>, <b><i>Railroad</i></b>, or
 * <b><i>Utility</i></b>.
 *
 * @see OwnableSpace
 */

public class CardGroup2 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  /**
   * Constructor for a <b><i>CardGroup2</i></b> object that randomly generates the <b>SPEC_TYPE</b> attribute.
   * For instances with <b>SPEC_TYPE</b> == 1, the property <b>nIndexToGo</b> will already be defined upon the call
   * of this constructor. For other cases, different methods in this same class will be called externally.
   *
   * @param board Board instance on which the <b><i>Property</i></b> instance will be randomly selected through
   *              <b>findRandomProperty()</b> for <b>SPEC_TYPE</b> == 1.
   * @see #findRandomProperty(Board)
   */

  public CardGroup2 (Board board) {

    this.SPEC_TYPE = randomGen(1, 3);

    if (this.SPEC_TYPE == 1)
      nIndexToGo = findRandomProperty(board);

  }

  /**
   * Method used to find a random property on the <i>board</i> parameter.
   *
   * @param board Board instance from which the random <b><i>Property</i></b> instance will be selected.
   * @return Integer holding the index of the randomly selected <b><i>Property</i></b>.
   */

  public int findRandomProperty (Board board) {

    int nHolder = randomGen(0, 32);

    while (!(board.getBoardSpaces().get(nHolder) instanceof Property))
      nHolder = randomGen(0, 32);

    return nHolder;

  }

  /**
   * Method used to find the nearest <b><i>Railroad</i></b> instance to the <i>player</i> parameter.
   *
   * @param board Board instance from which the next nearest <b><i>Railroad</i></b> instance will be determined.
   * @param player Player instance that dictates the basis position to find the next nearest <b><i>Railroad</i></b>.
   * @return Integer holding the index of the next nearest <b><i>Railroad</i></b>.
   */

  public int findNearestRail (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder % 32) instanceof Railroad))
      nHolder = (nHolder + 1);

    return nHolder;

  }

  /**
   * Method used to find the nearest <b><i>Utility</i></b> instance to the <i>player</i> parameter.
   *
   * @param board Board instance from which the next nearest <b><i>Utility</i></b> instance will be determined.
   * @param player Player instance that dictates the basis position to find the next nearest <b><i>Utility</i></b>.
   * @return Integer holding the index of the next nearest <b><i>Utility</i></b>.
   */

  public int findNearestUtil (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder) instanceof Utility))
      nHolder = (nHolder + 1) % 32;

    return nHolder;

  }

  //getters

  /**
   * Getter method that returns the <b>SPEC_TYPE</b> attribute of this class.
   *
   * @return SPEC_TYPE Integer holding the specific type of this class that was determined at construction.
   */

  public int getType () {
    return this.SPEC_TYPE;
  }

  /**
   * Getter method that returns the <b>nIndexToGo</b> attribute of this class.
   *
   * @return nIndexToGo Integer holding the index at a <b><i>Board</i></b> object to which the active
   * <b><i>Player</i></b> will move during application run.
   * @see Board
   */

  public int getIndexToGo () {
    return this.nIndexToGo;
  }

}
