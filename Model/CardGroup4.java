package Model;

/**
 * Class handling the determination of whether the <b><i>Player</i></b> instance that drew an instance of this class
 * at application run will go to a random <b><i>Property</i></b> or to jail which is located at index 16 of the
 * active <b><i>Board</i></b> object.
 */

public class CardGroup4 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  /**
   * Constructor for a <b><i>CardGroup2</i></b> object that randomly generates the <b>SPEC_TYPE</b> attribute.
   * For instances with <b>SPEC_TYPE</b> == 1, the property <b>nIndexToGo</b> will already be defined upon the call
   * of this constructor. Otherwise, the property will be set to 16.
   *
   * @param board Board instance on which the <b><i>Property</i></b> instance will be randomly selected through
   *              <b>findRandomProperty()</b> for <b>SPEC_TYPE</b> == 1.
   * @see #findRandomProperty(Board)
   */

  public CardGroup4 (Board board) {

    this.SPEC_TYPE = randomGen(1, 2);

    if (this.SPEC_TYPE == 1)
      this.nIndexToGo = findRandomProperty(board);

    else
      this.nIndexToGo = 16;

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
