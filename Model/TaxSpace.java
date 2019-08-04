package Model;

public class TaxSpace extends Space {

  private final boolean INCOME_TAX;

  public TaxSpace(int nLocationIndex, boolean bIncome) {

    super(nLocationIndex);
    this.INCOME_TAX = bIncome;

  }

  public boolean payTax (Player currPlayer, Bank bank) {

    double dToPay;

    if (this.INCOME_TAX)
      dToPay = 200 > currPlayer.getCash() * 0.10 ? 200 : currPlayer.getCash() * 0.10;

    else
      dToPay = 75;

    if (currPlayer.isPaymentPossible(dToPay)) {

      currPlayer.payBank(dToPay, bank);
      return true;

    } else {

      return false;

    }


  }

  public boolean isIncome() {
    return INCOME_TAX;
  }

}
