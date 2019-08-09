package Model;

public class Railroad extends OwnableSpace {

  public Railroad(int nLocationIndex, int nID) {
    super(nLocationIndex, nID, Reference.RAILROADS[nID / 100 - 1], 200, 25);
  }

}
