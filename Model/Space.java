package Model;

public class Space {

  protected int nLocationIndex;

  public Space (int nLocationIndex) {
    this.nLocationIndex = nLocationIndex;
  }

  public int getLocationIndex () {
    return nLocationIndex;
  }

  public void setLocationIndex (int nLocationIndex) {
    this.nLocationIndex = nLocationIndex;
  }

  @Override
  public String toString () {

    String toReturn = "Space{" +
                      "nLocationIndex=" + nLocationIndex;

    switch (this.nLocationIndex) {

      case 0: toReturn += " (Start)}"; break;
      case 8: toReturn += " (Free Parking)}"; break;
      case 16: toReturn += " (Jail)}"; break;
      case 24: toReturn += " (Community Service)}"; break;
      default: toReturn += "}";

    }

    return toReturn;

  }

}
