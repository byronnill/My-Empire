package Model;

public class TaxSpace extends Space {

  private final boolean INCOME_TAX;

  private double dToPay;

  public TaxSpace(int nLocationIndex, boolean bIncome) {

    super(nLocationIndex);
    this.INCOME_TAX = bIncome;

  }

  public double payTax (Player currPlayer) {

    if (this.INCOME_TAX)
      dToPay = 200 > currPlayer.getCash() * 0.10 ? 200 : currPlayer.getCash() * 0.10;

    else
      dToPay = 75;

    if (currPlayer.isPaymentPossible(dToPay))
      return dToPay;

    else
      return -1;

  }

  public boolean isIncome() {
    return INCOME_TAX;
  }

}
