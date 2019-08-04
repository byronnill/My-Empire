package Model;

public abstract class Reference {
  public static final String[] TYPES = { "gray", "purple", "pink", "green", "blue", "orange", "yellow", "railroad", "utility" };

  public static final String[][] PROPERTIES =
          {
                  { "Almond Drive", "Kasoy Street" },
                  { "Rodeo Drive", "Orange Street", "Ventura Street" },
                  { "Juan Luna", "Ylaya", "J. Abad Santos" },
                  { "Madison", "Annapolis", "Connecticut" },
                  { "Bougainvilla", "Dama de Noche", "Acacia" },
                  { "Solar Street", "Galaxy Street" },
                  { "9th Street", "5th Avenue" },
          };

  public static final String[] RAILROADS = { "North Line", "South Line", "Metro Line" };

  public static final String[] UTILITIES = { "Water", "Electricity" };

  public static final double[][] PRICE =
          {
                  { 60, 60 },
                  { 100, 100, 120 },
                  { 140, 140, 160 },
                  { 180, 180, 200 },
                  { 220, 220, 240 },
                  { 260, 260 },
                  { 300, 320 },
          };

  public static final double[][] HOUSEPRICE =
          {
                  { 50, 50 },
                  { 50, 50, 50 },
                  { 100, 100, 100 },
                  { 100, 100, 100 },
                  { 150, 150, 150 },
                  { 150, 150 },
                  { 200, 200 }
          };

  public static final double[][][] RENT =
          {
                  { { 2, 10, 30, 90, 160, 450 }, { 4, 20, 60, 180, 320, 250 } },
                  { { 6, 30, 90, 270, 400, 550 }, { 6, 30, 90, 270, 400, 550 }, { 6, 40, 100, 300, 450, 600 } },
                  { { 10, 50, 150, 450, 625, 750 }, { 10, 50, 150, 450, 625, 750 }, { 12, 60, 180, 500, 700, 900 } },
                  { { 14, 70, 200, 550, 750, 950 }, { 14, 70, 200, 550, 750, 950 }, { 16, 80, 220, 600, 800, 1000 } },
                  { { 18, 90, 250, 700, 875, 1050 }, { 18, 90, 250, 700, 875, 1050 }, { 20, 100, 300, 750, 925, 1100 } },
                  { { 22, 110, 330, 800, 975, 1150 }, { 22, 110, 330, 800, 975, 1150 } },
                  { { 26, 130, 390, 900, 1100, 1275 }, { 28, 150, 450, 1000, 1200, 1400 } }
          };

  public static final double[][] MULTIPLIER =
          {
                  { 2.5, 3.0 },
                  { 3.5, 4.0, 4.0 },
                  { 4.5, 4.5, 5.0 },
                  { 5.0, 5.5, 5.5 },
                  { 6.0, 6.0, 6.5 },
                  { 6.5, 7.0 },
                  { 7.0, 8.0 },
          };
}
