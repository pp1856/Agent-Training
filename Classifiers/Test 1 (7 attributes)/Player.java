class Player {

  public static double classify(Object[] i)
    throws Exception {

    double p = Double.NaN;
    p = Player.Nd720a0850(i);
    return p;
  }
  static double Nd720a0850(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("pequena")) {
      p = 1;
    } else if (i[2].equals("media")) {
    p = Player.N31f707ee1(i);
    } else if (i[2].equals("grande")) {
    p = Player.N402e48c20(i);
    } 
    return p;
  }
  static double N31f707ee1(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("mucho mas cerca")) {
    p = Player.N76f810682(i);
    } else if (i[4].equals("mas cerca")) {
    p = Player.N4c1292a66(i);
    } else if (i[4].equals("mas lejos")) {
    p = Player.Nf9be761b17(i);
    } else if (i[4].equals("mucho mas lejos")) {
      p = 1;
    } 
    return p;
  }
  static double N76f810682(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
    p = Player.N57f20e5d3(i);
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N57f20e5d3(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.5) {
    p = Player.Ndf3bb12e4(i);
    } else if (((Double) i[3]).doubleValue() > 0.5) {
      p = 1;
    } 
    return p;
  }
  static double Ndf3bb12e4(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("baja")) {
      p = 1;
    } else if (i[0].equals("media")) {
    p = Player.N260bc55b5(i);
    } else if (i[0].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double N260bc55b5(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.8680555555555557) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.8680555555555557) {
      p = 0;
    } 
    return p;
  }
  static double N4c1292a66(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.75) {
    p = Player.Nb70efd987(i);
    } else if (((Double) i[5]).doubleValue() > 0.75) {
    p = Player.N33dcf46e13(i);
    } 
    return p;
  }
  static double Nb70efd987(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("baja")) {
    p = Player.N355d6a1b8(i);
    } else if (i[0].equals("media")) {
    p = Player.N9b48f11810(i);
    } else if (i[0].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double N355d6a1b8(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.Nceecfd149(i);
    } 
    return p;
  }
  static double Nceecfd149(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.8413461538461541) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.8413461538461541) {
      p = 0;
    } 
    return p;
  }
  static double N9b48f11810(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.7996794871794871) {
    p = Player.N48f2457211(i);
    } else if (((Double) i[1]).doubleValue() > 0.7996794871794871) {
      p = 1;
    } 
    return p;
  }
  static double N48f2457211(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.6471774193548386) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.6471774193548386) {
    p = Player.Nf19194412(i);
    } 
    return p;
  }
  static double Nf19194412(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.7106481481481483) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() > 0.7106481481481483) {
      p = 1;
    } 
    return p;
  }
  static double N33dcf46e13(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.9495967741935484) {
    p = Player.N4207b6c014(i);
    } else if (((Double) i[1]).doubleValue() > 0.9495967741935484) {
      p = 1;
    } 
    return p;
  }
  static double N4207b6c014(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.N9ae1a6ab15(i);
    } 
    return p;
  }
  static double N9ae1a6ab15(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.8823529411764706) {
    p = Player.Ne32b896016(i);
    } else if (((Double) i[1]).doubleValue() > 0.8823529411764706) {
      p = 0;
    } 
    return p;
  }
  static double Ne32b896016(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 0;
    } else if (i[0].equals("baja")) {
      p = 0;
    } else if (i[0].equals("media")) {
      p = 1;
    } else if (i[0].equals("alta")) {
      p = 0;
    } 
    return p;
  }
  static double Nf9be761b17(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("baja")) {
    p = Player.N41030aee18(i);
    } else if (i[0].equals("media")) {
      p = 1;
    } else if (i[0].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double N41030aee18(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.8528386454183268) {
    p = Player.N3b9be03b19(i);
    } else if (((Double) i[1]).doubleValue() > 0.8528386454183268) {
      p = 1;
    } 
    return p;
  }
  static double N3b9be03b19(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.6620762711864407) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.6620762711864407) {
      p = 0;
    } 
    return p;
  }
  static double N402e48c20(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.8823529411764706) {
    p = Player.N5d34980921(i);
    } else if (((Double) i[1]).doubleValue() > 0.8823529411764706) {
    p = Player.N54ca31b426(i);
    } 
    return p;
  }
  static double N5d34980921(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.5208333333333333) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.5208333333333333) {
    p = Player.N23c7ab7522(i);
    } 
    return p;
  }
  static double N23c7ab7522(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 0;
    } else if (i[0].equals("baja")) {
    p = Player.N76892afa23(i);
    } else if (i[0].equals("media")) {
      p = 0;
    } else if (i[0].equals("alta")) {
      p = 0;
    } 
    return p;
  }
  static double N76892afa23(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("mucho mas cerca")) {
    p = Player.Nae9f89d724(i);
    } else if (i[4].equals("mas cerca")) {
      p = 0;
    } else if (i[4].equals("mas lejos")) {
      p = 0;
    } else if (i[4].equals("mucho mas lejos")) {
      p = 0;
    } 
    return p;
  }
  static double Nae9f89d724(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.56781914893617) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() > 0.56781914893617) {
    p = Player.N745854bf25(i);
    } 
    return p;
  }
  static double N745854bf25(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.5756578947368423) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.5756578947368423) {
      p = 0;
    } 
    return p;
  }
  static double N54ca31b426(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.N479dd0a127(i);
    } 
    return p;
  }
  static double N479dd0a127(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("mucho mas cerca")) {
      p = 1;
    } else if (i[4].equals("mas cerca")) {
      p = 0;
    } else if (i[4].equals("mas lejos")) {
      p = 1;
    } else if (i[4].equals("mucho mas lejos")) {
      p = 0;
    } 
    return p;
  }
}
