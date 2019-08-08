package Model;

/**
 * Class handling the bank values and transactions for a master <b>Game</b> object.
 *
 * @see Game
 */

public class Bank {

  double dValue;

  /**
   * Constructor for a <b><i>Bank</i></b> object. The instances always starts with 2500 as its <b>dValue</b> multiplied
   * by the number of players in the <b><i>Game</i></b>.
   *
   * @param nPlayers Integer that holds the number of players in the <b><i>Game</i></b>.
   */

  public Bank (int nPlayers) {
    this.dValue = 2500 * nPlayers;
  }

  /**
   * Getter method that returns the <b>dValue</b> attribute of this class.
   *
   * @return dValue Double-precision floating point number that holds the current amount of money in the
   * <b><i>Bank</i></b> object.
   */

  public double getValue () {
    return this.dValue;
  }

  /**
   * Equivalent setter method for the <b>dValue</b> attribute of this class. Instead of changing the entire value,
   * a certain amount is either added or deducted to the <b><i>Bank</i> dValue</b>.
   *
   * @param toAdd Double-precision floating point number that is to be added to the <b>dValue</b> attribute of
   *              a <b><i>Bank</i></b> object. The caller of this setter function sets this parameter negative for
   *              value deduction.
   */

  public void addOrDeduct (double toAdd) {
    this.dValue += toAdd;
  }

  /**
   * Overridden method that returns the <b>dValue</b> attribute of a <b><i>Bank</i></b> object as a String object.
   *
   * @return String representation of the <b>dValue</b> attribute of a <b><i>Bank</i></b> instance.
   */

  @Override
  public String toString() {
    return "Bank Value : $" + this.dValue;
  }

}
