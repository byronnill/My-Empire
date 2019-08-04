package Model;

public class Bank {

  double dValue;

  public Bank (int nPlayers) {
    this.dValue = 2500 * nPlayers;
  }

  public double getValue () {
    return dValue;
  }

  public void addOrDeduct (double toAdd) {
    dValue += toAdd;
  }

  @Override
  public String toString() {
    return "Bank Value : $" + this.dValue;
  }

}
