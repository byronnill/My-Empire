package Model;

/**
 * Class handling the determination of where the active <b><i>Player</i></b> will go to when an instance of this class
 * is drawn on application run. Similar to {@link CardGroup2}.
 */

public class CardGroup4 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  /**
   * Constructor for a <b><i>CardGroup4</i></b> object. The attribute <b>SPEC_TYPE</b> is randomly generated between the
   * integers 1 and 2.
   *
   * @param board <b><i>Board</i></b> object where the attribute <b>nIndexToGo</b> will be based on if <b>SPEC_TYPE</b>
   *              is equal to 2.
   */

  public CardGroup4 (Board board) {

    this.SPEC_TYPE = randomGen(1, 2);

    if (this.SPEC_TYPE == 2)
      this.nIndexToGo = findRandomProperty(board);

    else
      this.nIndexToGo = 16;

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
