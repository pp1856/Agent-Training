class Player {

  public static double classify(Object[] i)
    throws Exception {

    double p = Double.NaN;
    p = Player.Nad490ebf0(i);
    return p;
  }
  static double Nad490ebf0(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 0;
    } else if (i[2].equals("sabio")) {
    p = Player.Nb14ffc491(i);
    } else if (i[2].equals("valiente")) {
      p = 1;
    } else if (i[2].equals("prudente")) {
    p = Player.N736db4422(i);
    } else if (i[2].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Nb14ffc491(Object []i) {
    double p = Double.NaN;
    if (i[6] == null) {
      p = 0;
    } else if (i[6].equals("bueno")) {
      p = 0;
    } else if (i[6].equals("asustado")) {
      p = 0;
    } else if (i[6].equals("kamikaze")) {
      p = 1;
    } else if (i[6].equals("malo")) {
      p = 0;
    } 
    return p;
  }
  static double N736db4422(Object []i) {
    double p = Double.NaN;
    if (i[6] == null) {
      p = 1;
    } else if (i[6].equals("bueno")) {
      p = 1;
    } else if (i[6].equals("asustado")) {
      p = 0;
    } else if (i[6].equals("kamikaze")) {
      p = 1;
    } else if (i[6].equals("malo")) {
      p = 1;
    } 
    return p;
  }
}
