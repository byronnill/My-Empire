package Model;

/**
 * Class handling the bank values and transactions for a master <b><i>Game</i></b> object.
 */

public class Bank {

  private double dValue;

  /**
   * Constructor for a <b><i>Bank</i></b> object. The bank always starts with 2500 as its <b>dValue</b> attribute multiplied by the
   * number of players in the <b><i>Game</i></b>.
   *
   * @param nPlayers Integer that holds the number of players in the <b><i>Game</i></b>.
   */

  public Bank (int nPlayers) {
    this.dValue = 2500 * nPlayers;
  }

  /**
   * Getter method for the <b>dValue</b> attribute of this class.
   *
   * @return <b>dValue</b> Double-precision floating point number that holds the current amount of money in the
   *                       <b><i>Bank</i></b> object.
   */

  public double getValue () {
    return this.dValue;
  }

  /**
   * Equivalent setter method for the <b>dValue</b> attribute of this class. Instead of changing the entire value,
   * a certain amount is either added or deducted to the bank value.
   *
   * @param toAdd Double-precision floating point number that is to be added to the <b>dValue</b> attribute of a
   *              <b><i>Bank</i></b> object. The caller of this function sets this parameter negative for value
   *              deduction.
   */

  public void addOrDeduct (double toAdd) {
    this.dValue += toAdd;
  }

  /**
   * Overriden <b>toString()</b> method from the Object class. Used only in console testing for
   * updates on the <b>dValue</b> attribute of a <b><i>Bank</i></b> object.
   *
   * @return String representation of the <b>dValue</b> attribute of this class.
   * @see Object#toString()
   */

  @Override
  public String toString() {
    return "Bank Value : $" + this.dValue;
  }

}
