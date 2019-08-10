package Model;

/**
 * Class handling the determination of where the active <b><i>Player</i></b> will go to when an instance of this class
 * is drawn on application run.
 */

public class CardGroup2 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  /**
   * Constructor for a <b><i>CardGroup2</i></b> object. The attribute <b>SPEC_TYPE</b> is randomly generated between the
   * integers 1 and 3.
   *
   * @param board <b><i>Board</i></b> object where the attribute <b>nIndexToGo</b> will be based on if <b>SPEC_TYPE</b>
   *              is equal to 1.
   */

  public CardGroup2 (Board board) {

    this.SPEC_TYPE = randomGen(1, 3);

    if (this.SPEC_TYPE == 1)
      nIndexToGo = findRandomProperty(board);

    else
      nIndexToGo = -1;

  }

  /**
   * Method that returns a random space in <i>board</i> that is of type <b><i>Property</i></b>.
   *
   * @param board <b><i>Board</i></b> object where the attribute <b>nIndexToGo</b> will be based on if <b>SPEC_TYPE</b>
   *              is equal to 1.
   * @return Integer that holds the index of the <b><i>Property</i></b> instance that was generated.
   */

  public int findRandomProperty (Board board) {

    int nHolder = randomGen(0, 32);

    while (!(board.getBoardSpaces().get(nHolder) instanceof Property))
      nHolder = randomGen(0, 32);

    return nHolder;

  }

  /**
   * Method that returns the next nearest space in <i>board</i> that is of type <b><i>Railroad</i></b> with respect to
   * the <i>player</i> parameter.
   *
   * @param board <b><i>Board</i></b> object where the returnable value will be based on if <b>SPEC_TYPE</b>
   *              is equal to 2.
   * @param player <b><i>Player</i></b> object whose <b>nLocationIndex</b> is the basis for finding the next nearest
   *               <b><i>Railroad</i></b>.
   * @return Integer that holds the index of the <b><i>Property</i></b> instance that was generated.
   */

  public int findNearestRail (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder) instanceof Railroad))
      nHolder = (nHolder + 1) % 32;

    return nHolder;

  }

  /**
   * Method that returns the next nearest space in <i>board</i> that is of type <b><i>Utility</i></b> with respect to
   * the <i>player</i> parameter.
   *
   * @param board <b><i>Board</i></b> object where the returnable value will be based on if <b>SPEC_TYPE</b>
   *              is equal to 3.
   * @param player <b><i>Player</i></b> object whose <b>nLocationIndex</b> is the basis for finding the next nearest
   *               <b><i>Utility</i></b>.
   * @return Integer that holds the index of the <b><i>Property</i></b> instance that was generated.
   */

  public int findNearestUtil (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder) instanceof Utility))
      nHolder = (nHolder + 1) % 32;

    return nHolder;

  }

  /**
   * Getter method for the <b>SPEC_TYPE</b> attribute of this class.
   *
   * @return Integer holding the specific type of an instance.
   */

  public int getType () {
    return this.SPEC_TYPE;
  }

  /**
   * Getter method for the <b>nIndexToGo</b> attribute of this class.
   *
   * @return Integer holding the index to which the active <b><i>Player</i></b> will move.
   */

  public int getIndexToGo () {
    return this.nIndexToGo;
  }

}
