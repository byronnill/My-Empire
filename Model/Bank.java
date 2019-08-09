package Model;

/**
 * This class handles the bank values and transactions for a master Game object.
 */

public class Bank {

  double dValue;

  /**
   * Constructor for the class. The bank always starts with 2500 as its value multiplied by the number of players in
   * the Game.
   *
   * @param nPlayers An integer that holds the number of players in the game.
   */

  public Bank (int nPlayers) {
    this.dValue = 2500 * nPlayers;
  }

  /**
   * Getter function for the value attribute of this class.
   *
   * @return dValue A double-precision floating point number that holds the current amount of money in the Bank object.
   */

  public double getValue () {
    return this.dValue;
  }

  /**
   * Equivalent setter method for the value attribute of this class. Instead of changing the entire value, a certain
   * amount is either added or deducted to the bank value.
   *
   * @param toAdd A double-precision floating point number that is to be added to the value attribute of a Bank object.
   *              The caller of this setter function sets this parameter negative for value deduction.
   */

  public void addOrDeduct (double toAdd) {
    this.dValue += toAdd;
  }

  /**
   *
   *
   * @return
   */

  @Override
  public String toString() {
    return "Bank Value : $" + this.dValue;
  }

}
