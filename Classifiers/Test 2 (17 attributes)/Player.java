class Player {

  public static double classify(Object[] i)
    throws Exception {

    double p = Double.NaN;
    p = Player.Nf495c6060(i);
    return p;
  }
  static double Nf495c6060(Object []i) {
    double p = Double.NaN;
    if (i[6] == null) {
      p = 1;
    } else if (i[6].equals("pequena")) {
    p = Player.N6fb21de01(i);
    } else if (i[6].equals("media")) {
    p = Player.Nfe6c45bc8(i);
    } else if (i[6].equals("grande")) {
    p = Player.N31a134d546(i);
    } 
    return p;
  }
  static double N6fb21de01(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
    p = Player.N19de092a2(i);
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N19de092a2(Object []i) {
    double p = Double.NaN;
    if (i[10] == null) {
      p = 1;
    } else if (i[10].equals("sabio")) {
      p = 1;
    } else if (i[10].equals("valiente")) {
    p = Player.Nb23ce4273(i);
    } else if (i[10].equals("prudente")) {
      p = 1;
    } else if (i[10].equals("pasivo")) {
      p = 1;
    } 
    return p;
  }
  static double Nb23ce4273(Object []i) {
    double p = Double.NaN;
    if (i[11] == null) {
      p = 1;
    } else if (((Double) i[11]).doubleValue() <= 0.875) {
    p = Player.N954e38864(i);
    } else if (((Double) i[11]).doubleValue() > 0.875) {
      p = 0;
    } 
    return p;
  }
  static double N954e38864(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.5) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.5) {
    p = Player.Nc8db0d7d5(i);
    } 
    return p;
  }
  static double Nc8db0d7d5(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("baja")) {
      p = 1;
    } else if (i[4].equals("media")) {
    p = Player.Nacaa1e916(i);
    } else if (i[4].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double Nacaa1e916(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.N32f44c6b7(i);
    } 
    return p;
  }
  static double N32f44c6b7(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.5) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.5) {
      p = 1;
    } 
    return p;
  }
  static double Nfe6c45bc8(Object []i) {
    double p = Double.NaN;
    if (i[10] == null) {
      p = 1;
    } else if (i[10].equals("sabio")) {
    p = Player.N84587f1b9(i);
    } else if (i[10].equals("valiente")) {
    p = Player.N6fac864026(i);
    } else if (i[10].equals("prudente")) {
      p = 1;
    } else if (i[10].equals("pasivo")) {
    p = Player.N65d2d88736(i);
    } 
    return p;
  }
  static double N84587f1b9(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() <= 0.71875) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() > 0.71875) {
    p = Player.N5e0f2d2c10(i);
    } 
    return p;
  }
  static double N5e0f2d2c10(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.6696428571428573) {
    p = Player.Nbfb5df1e11(i);
    } else if (((Double) i[5]).doubleValue() > 0.6696428571428573) {
    p = Player.Nc9d17ca712(i);
    } 
    return p;
  }
  static double Nbfb5df1e11(Object []i) {
    double p = Double.NaN;
    if (i[11] == null) {
      p = 0;
    } else if (((Double) i[11]).doubleValue() <= 0.625) {
      p = 0;
    } else if (((Double) i[11]).doubleValue() > 0.625) {
      p = 1;
    } 
    return p;
  }
  static double Nc9d17ca712(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.5) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.5) {
    p = Player.N4190b44613(i);
    } 
    return p;
  }
  static double N4190b44613(Object []i) {
    double p = Double.NaN;
    if (i[11] == null) {
      p = 1;
    } else if (((Double) i[11]).doubleValue() <= 0.625) {
      p = 1;
    } else if (((Double) i[11]).doubleValue() > 0.625) {
    p = Player.N15f8abc014(i);
    } 
    return p;
  }
  static double N15f8abc014(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() <= 0.7944078947368421) {
    p = Player.Nb6a5821c15(i);
    } else if (((Double) i[13]).doubleValue() > 0.7944078947368421) {
    p = Player.N8f9b6a9b19(i);
    } 
    return p;
  }
  static double Nb6a5821c15(Object []i) {
    double p = Double.NaN;
    if (i[15] == null) {
      p = 1;
    } else if (((Double) i[15]).doubleValue() <= 0.75) {
    p = Player.N744792f816(i);
    } else if (((Double) i[15]).doubleValue() > 0.75) {
    p = Player.Na2533ae718(i);
    } 
    return p;
  }
  static double N744792f816(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("pequena")) {
      p = 1;
    } else if (i[0].equals("media")) {
    p = Player.N8df57d2e17(i);
    } else if (i[0].equals("grande")) {
      p = 1;
    } 
    return p;
  }
  static double N8df57d2e17(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double Na2533ae718(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
      p = 0;
    } 
    return p;
  }
  static double N8f9b6a9b19(Object []i) {
    double p = Double.NaN;
    if (i[11] == null) {
      p = 1;
    } else if (((Double) i[11]).doubleValue() <= 0.875) {
    p = Player.N72b1f03b20(i);
    } else if (((Double) i[11]).doubleValue() > 0.875) {
      p = 1;
    } 
    return p;
  }
  static double N72b1f03b20(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8958333333333334) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.8958333333333334) {
    p = Player.N5ff0187321(i);
    } 
    return p;
  }
  static double N5ff0187321(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("pequena")) {
    p = Player.N488d0f3222(i);
    } else if (i[2].equals("media")) {
    p = Player.Nf223c18624(i);
    } else if (i[2].equals("grande")) {
      p = 1;
    } 
    return p;
  }
  static double N488d0f3222(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
    p = Player.N8961125a23(i);
    } 
    return p;
  }
  static double N8961125a23(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("pequena")) {
      p = 1;
    } else if (i[0].equals("media")) {
      p = 0;
    } else if (i[0].equals("grande")) {
      p = 1;
    } 
    return p;
  }
  static double Nf223c18624(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
    p = Player.N2401812425(i);
    } else if (((Double) i[1]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N2401812425(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("baja")) {
      p = 0;
    } else if (i[4].equals("media")) {
      p = 0;
    } else if (i[4].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double N6fac864026(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.5) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.5) {
    p = Player.Nad0e05ee27(i);
    } 
    return p;
  }
  static double Nad0e05ee27(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("baja")) {
    p = Player.N943c0b0728(i);
    } else if (i[4].equals("media")) {
    p = Player.N231c1a6631(i);
    } else if (i[4].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double N943c0b0728(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 0;
    } else if (i[0].equals("pequena")) {
      p = 0;
    } else if (i[0].equals("media")) {
    p = Player.N67d219a29(i);
    } else if (i[0].equals("grande")) {
      p = 0;
    } 
    return p;
  }
  static double N67d219a29(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() <= 0.5) {
      p = 0;
    } else if (((Double) i[7]).doubleValue() > 0.5) {
    p = Player.N9d1b7acb30(i);
    } 
    return p;
  }
  static double N9d1b7acb30(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
      p = 0;
    } 
    return p;
  }
  static double N231c1a6631(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
    p = Player.Ned8585b532(i);
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double Ned8585b532(Object []i) {
    double p = Double.NaN;
    if (i[0] == null) {
      p = 1;
    } else if (i[0].equals("pequena")) {
      p = 1;
    } else if (i[0].equals("media")) {
    p = Player.N7fc2140633(i);
    } else if (i[0].equals("grande")) {
      p = 1;
    } 
    return p;
  }
  static double N7fc2140633(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
    p = Player.N36b10c8734(i);
    } else if (((Double) i[7]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N36b10c8734(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
    p = Player.N55f1e2c135(i);
    } 
    return p;
  }
  static double N55f1e2c135(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.6461466165413534) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.6461466165413534) {
      p = 1;
    } 
    return p;
  }
  static double N65d2d88736(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
    p = Player.Nfdbe091237(i);
    } 
    return p;
  }
  static double Nfdbe091237(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() <= 0.936970338983051) {
    p = Player.Nc2aacb0238(i);
    } else if (((Double) i[13]).doubleValue() > 0.936970338983051) {
    p = Player.N613c9dbf45(i);
    } 
    return p;
  }
  static double Nc2aacb0238(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() <= 0.8563596491228072) {
    p = Player.Nbf86b90939(i);
    } else if (((Double) i[13]).doubleValue() > 0.8563596491228072) {
      p = 1;
    } 
    return p;
  }
  static double Nbf86b90939(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 0;
    } else if (((Double) i[13]).doubleValue() <= 0.6947115384615383) {
      p = 0;
    } else if (((Double) i[13]).doubleValue() > 0.6947115384615383) {
    p = Player.Nfdd9c03740(i);
    } 
    return p;
  }
  static double Nfdd9c03740(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.N8488551841(i);
    } 
    return p;
  }
  static double N8488551841(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 1;
    } else if (i[4].equals("baja")) {
    p = Player.Nfa00a53d42(i);
    } else if (i[4].equals("media")) {
      p = 1;
    } else if (i[4].equals("alta")) {
      p = 1;
    } 
    return p;
  }
  static double Nfa00a53d42(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.6620762711864407) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.6620762711864407) {
    p = Player.N9e82745143(i);
    } 
    return p;
  }
  static double N9e82745143(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() <= 0.7160326086956522) {
      p = 1;
    } else if (((Double) i[13]).doubleValue() > 0.7160326086956522) {
    p = Player.N30aae55044(i);
    } 
    return p;
  }
  static double N30aae55044(Object []i) {
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
  static double N613c9dbf45(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 1;
    } 
    return p;
  }
  static double N31a134d546(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 0;
    } else if (((Double) i[13]).doubleValue() <= 0.9479166666666667) {
    p = Player.N655340d447(i);
    } else if (((Double) i[13]).doubleValue() > 0.9479166666666667) {
    p = Player.N6aef17bc61(i);
    } 
    return p;
  }
  static double N655340d447(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.5208333333333333) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.5208333333333333) {
    p = Player.Nd04a1f9748(i);
    } 
    return p;
  }
  static double Nd04a1f9748(Object []i) {
    double p = Double.NaN;
    if (i[4] == null) {
      p = 0;
    } else if (i[4].equals("baja")) {
    p = Player.Na7905d4149(i);
    } else if (i[4].equals("media")) {
      p = 0;
    } else if (i[4].equals("alta")) {
      p = 0;
    } 
    return p;
  }
  static double Na7905d4149(Object []i) {
    double p = Double.NaN;
    if (i[1] == null) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[1]).doubleValue() > 0.75) {
    p = Player.N9aebee4950(i);
    } 
    return p;
  }
  static double N9aebee4950(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 0;
    } else if (i[2].equals("pequena")) {
    p = Player.N4faf02e751(i);
    } else if (i[2].equals("media")) {
      p = 0;
    } else if (i[2].equals("grande")) {
    p = Player.N8bf5a28059(i);
    } 
    return p;
  }
  static double N4faf02e751(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 0.5) {
    p = Player.N8cdef25a52(i);
    } else if (((Double) i[3]).doubleValue() > 0.5) {
    p = Player.N3b5495153(i);
    } 
    return p;
  }
  static double N8cdef25a52(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.7417929292929293) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.7417929292929293) {
      p = 0;
    } 
    return p;
  }
  static double N3b5495153(Object []i) {
    double p = Double.NaN;
    if (i[13] == null) {
      p = 0;
    } else if (((Double) i[13]).doubleValue() <= 0.9206730769230771) {
    p = Player.Nff731c0354(i);
    } else if (((Double) i[13]).doubleValue() > 0.9206730769230771) {
      p = 1;
    } 
    return p;
  }
  static double Nff731c0354(Object []i) {
    double p = Double.NaN;
    if (i[7] == null) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() <= 0.75) {
      p = 1;
    } else if (((Double) i[7]).doubleValue() > 0.75) {
    p = Player.N140982955(i);
    } 
    return p;
  }
  static double N140982955(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.7767857142857143) {
    p = Player.Nf16129ca56(i);
    } else if (((Double) i[5]).doubleValue() > 0.7767857142857143) {
      p = 0;
    } 
    return p;
  }
  static double Nf16129ca56(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.7417929292929293) {
    p = Player.Nb010044b57(i);
    } else if (((Double) i[5]).doubleValue() > 0.7417929292929293) {
      p = 1;
    } 
    return p;
  }
  static double Nb010044b57(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
    p = Player.N9b23a44f58(i);
    } else if (((Double) i[3]).doubleValue() > 0.75) {
      p = 0;
    } 
    return p;
  }
  static double N9b23a44f58(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() <= 0.5994496855345911) {
      p = 0;
    } else if (((Double) i[5]).doubleValue() > 0.5994496855345911) {
      p = 1;
    } 
    return p;
  }
  static double N8bf5a28059(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() <= 0.75) {
      p = 0;
    } else if (((Double) i[3]).doubleValue() > 0.75) {
    p = Player.N15c11fa860(i);
    } 
    return p;
  }
  static double N15c11fa860(Object []i) {
    double p = Double.NaN;
    if (i[5] == null) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() <= 0.8823529411764706) {
      p = 1;
    } else if (((Double) i[5]).doubleValue() > 0.8823529411764706) {
      p = 0;
    } 
    return p;
  }
  static double N6aef17bc61(Object []i) {
    double p = Double.NaN;
    if (i[2] == null) {
      p = 1;
    } else if (i[2].equals("pequena")) {
      p = 1;
    } else if (i[2].equals("media")) {
      p = 0;
    } else if (i[2].equals("grande")) {
      p = 0;
    } 
    return p;
  }
}
