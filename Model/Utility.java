package Model;

public class Utility extends OwnableSpace{

  public Utility(int nLocationIndex, int nID) {

    super(nLocationIndex, nID, Reference.UTILITIES[nID / 1000 - 1], 150, 4);

  }

}
