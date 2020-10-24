class Player {

  public static double classify(Object[] i)
    throws Exception {

    double p = Double.NaN;
    p = Player.N8175e78a0(i);
    return p;
  }
  static double N8175e78a0(Object []i) {
    double p = Double.NaN;
    if (i[10] == null) {
      p = 0;
    } else if (i[10].equals("sabio")) {
    p = Player.Ndb7cc7c91(i);
    } else if (i[10].equals("valiente")) {
      p = 1;
    } else if (i[10].equals("prudente")) {
    p = Player.N2a7299482(i);
    } else if (i[10].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Ndb7cc7c91(Object []i) {
    double p = Double.NaN;
    if (i[14] == null) {
      p = 0;
    } else if (i[14].equals("bueno")) {
      p = 0;
    } else if (i[14].equals("asustado")) {
      p = 0;
    } else if (i[14].equals("kamikaze")) {
      p = 1;
    } else if (i[14].equals("malo")) {
      p = 0;
    } 
    return p;
  }
  static double N2a7299482(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("pequena")) {
      p = 1;
    } else if (i[2].equals("media")) {
      p = 1;
    } else if (i[2].equals("grande")) {
      p = 0;
    } 
    return p;
  }
}
