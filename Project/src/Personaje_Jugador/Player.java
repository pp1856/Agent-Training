package Personaje_Jugador;

class Player {

  public static double classify(Object[] i)
    throws Exception {

    double p = Double.NaN;
    p = Player.N5b8bc1190(i);
    return p;
  }
  static double N5b8bc1190(Object []i) {
    double p = Double.NaN;
    if (i[6] == null) {
      p = 1;
    } else if (i[6].equals("bueno")) {
    p = Player.N658c05fb1(i);
    } else if (i[6].equals("asustado")) {
    p = Player.Ne1edea1133(i);
    } else if (i[6].equals("kamikaze")) {
    p = Player.N98e9e1c742(i);
    } else if (i[6].equals("malo")) {
      p = 1;
    } 
    return p;
  }
  static double N658c05fb1(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8708964646464646) {
    p = Player.N5a4e695f2(i);
    } else if (((Double) i[5]).doubleValue() > 0.8708964646464646) {
    p = Player.Ndac25de821(i);
    } 
    return p;
  }
  static double N5a4e695f2(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.765625) {
    p = Player.N8a312c743(i);
    } else if (((Double) i[5]).doubleValue() > 0.765625) {
    p = Player.N21e2141610(i);
    } 
    return p;
  }
  static double N8a312c743(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("sabio")) {
    p = Player.Nfc91b1d74(i);
    } else if (i[2].equals("valiente")) {
    p = Player.N892624938(i);
    } else if (i[2].equals("prudente")) {
      p = 1;
    } else if (i[2].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Nfc91b1d74(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("arriesgada")) {
      p = 1;
    } else if (i[4].equals("peligrosa")) {
      p = 1;
    } else if (i[4].equals("segura")) {
      p = 1;
    } else if (i[4].equals("facil")) {
    p = Player.Nf6b12f0d5(i);
    } 
    return p;
  }
  static double Nf6b12f0d5(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.625) {
    p = Player.Nb8d8675e6(i);
    } else if (((Double) i[7]).doubleValue() > 0.625) {
      p = 1;
    } 
    return p;
  }
  static double Nb8d8675e6(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.5) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() > 0.5) {
    p = Player.N46718c417(i);
    } 
    return p;
  }
  static double N46718c417(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.6947115384615383) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.6947115384615383) {
      p = 1;
    } 
    return p;
  }
  static double N892624938(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.7604166666666666) {
    p = Player.N256f30189(i);
    } else if (((Double) i[5]).doubleValue() > 0.7604166666666666) {
      p = 1;
    } 
    return p;
  }
  static double N256f30189(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.625) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.625) {
      p = 0;
    } 
    return p;
  }
  static double N21e2141610(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.7858231707317074) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.7858231707317074) {
    p = Player.Nb5da0ae511(i);
    } 
    return p;
  }
  static double Nb5da0ae511(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8437500000000002) {
    p = Player.N8f91ef7712(i);
    } else if (((Double) i[5]).doubleValue() > 0.8437500000000002) {
    p = Player.N3a3578d219(i);
    } 
    return p;
  }
  static double N8f91ef7712(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8423455056179776) {
    p = Player.N4476433213(i);
    } else if (((Double) i[5]).doubleValue() > 0.8423455056179776) {
      p = 1;
    } 
    return p;
  }
  static double N4476433213(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8197115384615383) {
    p = Player.N7c97b2de14(i);
    } else if (((Double) i[5]).doubleValue() > 0.8197115384615383) {
      p = 0;
    } 
    return p;
  }
  static double N7c97b2de14(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.8139880952380951) {
    p = Player.Nf2deccec15(i);
    } else if (((Double) i[5]).doubleValue() > 0.8139880952380951) {
    p = Player.Nfcc13e8317(i);
    } 
    return p;
  }
  static double Nf2deccec15(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.7869318181818181) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.7869318181818181) {
    p = Player.Nd82edf816(i);
    } 
    return p;
  }
  static double Nd82edf816(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 0;
    } else if (i[2].equals("sabio")) {
      p = 0;
    } else if (i[2].equals("valiente")) {
      p = 1;
    } else if (i[2].equals("prudente")) {
      p = 0;
    } else if (i[2].equals("pasivo")) {
      p = 0;
    } 
    return p;
  }
  static double Nfcc13e8317(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("arriesgada")) {
      p = 1;
    } else if (i[4].equals("peligrosa")) {
      p = 1;
    } else if (i[4].equals("segura")) {
      p = 1;
    } else if (i[4].equals("facil")) {
    p = Player.N6398ab8118(i);
    } 
    return p;
  }
  static double N6398ab8118(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N3a3578d219(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.865301724137931) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.865301724137931) {
    p = Player.N9d01ae4c20(i);
    } 
    return p;
  }
  static double N9d01ae4c20(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 0;
    } 
    return p;
  }
  static double Ndac25de821(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.875) {
    p = Player.N6df20ae822(i);
    } else if (((Double) i[5]).doubleValue() > 0.875) {
    p = Player.Na7ebe8e125(i);
    } 
    return p;
  }
  static double N6df20ae822(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("sabio")) {
      p = 1;
    } else if (i[2].equals("valiente")) {
    p = Player.Na50419a923(i);
    } else if (i[2].equals("prudente")) {
      p = 1;
    } else if (i[2].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Na50419a923(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
    p = Player.N59374bcc24(i);
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N59374bcc24(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.625) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.625) {
      p = 0;
    } 
    return p;
  }
  static double Na7ebe8e125(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.9479166666666667) {
    p = Player.N4de7a24a26(i);
    } else if (((Double) i[5]).doubleValue() > 0.9479166666666667) {
      p = 1;
    } 
    return p;
  }
  static double N4de7a24a26(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("arriesgada")) {
      p = 0;
    } else if (i[4].equals("peligrosa")) {
      p = 0;
    } else if (i[4].equals("segura")) {
    p = Player.N22dbf82027(i);
    } else if (i[4].equals("facil")) {
    p = Player.N3cb138bb29(i);
    } 
    return p;
  }
  static double N22dbf82027(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
    p = Player.N3f582fe728(i);
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 0;
    } 
    return p;
  }
  static double N3f582fe728(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N3cb138bb29(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.89375) {
    p = Player.N24665dcc30(i);
    } else if (((Double) i[5]).doubleValue() > 0.89375) {
    p = Player.N639f9dc531(i);
    } 
    return p;
  }
  static double N24665dcc30(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N639f9dc531(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.875) {
    p = Player.Nd0e083a332(i);
    } else if (((Double) i[3]).doubleValue() > 0.875) {
      p = 0;
    } 
    return p;
  }
  static double Nd0e083a332(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.9264193227091634) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.9264193227091634) {
      p = 1;
    } 
    return p;
  }
  static double Ne1edea1133(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.625) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.625) {
    p = Player.N60484dd034(i);
    } 
    return p;
  }
  static double N60484dd034(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.875) {
    p = Player.N535fb28435(i);
    } else if (((Double) i[5]).doubleValue() > 0.875) {
    p = Player.Ndb11c23041(i);
    } 
    return p;
  }
  static double N535fb28435(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
    p = Player.N639c0a0136(i);
    } else if (((Double) i[7]).doubleValue() > 0.75) {
    p = Player.Nfdfdafa139(i);
    } 
    return p;
  }
  static double N639c0a0136(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.8039772727272727) {
    p = Player.N940f6f6537(i);
    } else if (((Double) i[5]).doubleValue() > 0.8039772727272727) {
      p = 1;
    } 
    return p;
  }
  static double N940f6f6537(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("arriesgada")) {
      p = 0;
    } else if (i[4].equals("peligrosa")) {
      p = 0;
    } else if (i[4].equals("segura")) {
      p = 0;
    } else if (i[4].equals("facil")) {
    p = Player.Nd5d67b9938(i);
    } 
    return p;
  }
  static double Nd5d67b9938(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.875) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.875) {
      p = 0;
    } 
    return p;
  }
  static double Nfdfdafa139(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.875) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() > 0.875) {
    p = Player.N497f040440(i);
    } 
    return p;
  }
  static double N497f040440(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("arriesgada")) {
      p = 1;
    } else if (i[4].equals("peligrosa")) {
      p = 1;
    } else if (i[4].equals("segura")) {
      p = 0;
    } else if (i[4].equals("facil")) {
      p = 1;
    } 
    return p;
  }
  static double Ndb11c23041(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.875) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.875) {
      p = 1;
    } 
    return p;
  }
  static double N98e9e1c742(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.936970338983051) {
    p = Player.N1cd57aaa43(i);
    } else if (((Double) i[5]).doubleValue() > 0.936970338983051) {
      p = 1;
    } 
    return p;
  }
  static double N1cd57aaa43(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("sabio")) {
      p = 1;
    } else if (i[2].equals("valiente")) {
    p = Player.Nc56277c044(i);
    } else if (i[2].equals("prudente")) {
      p = 1;
    } else if (i[2].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Nc56277c044(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("arriesgada")) {
    p = Player.Ne1b56d6745(i);
    } else if (i[4].equals("peligrosa")) {
      p = 1;
    } else if (i[4].equals("segura")) {
      p = 1;
    } else if (i[4].equals("facil")) {
      p = 1;
    } 
    return p;
  }
  static double Ne1b56d6745(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.Neb5b5b9e46(i);
    } 
    return p;
  }
  static double Neb5b5b9e46(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.875) {
    p = Player.Ncac99b3647(i);
    } else if (((Double) i[3]).doubleValue() > 0.875) {
      p = 1;
    } 
    return p;
  }
  static double Ncac99b3647(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
}
