public class PolynomialCreator {
  private double[] poly;

  public PolynomialCreator(double[] poly) {
    this.poly = poly;
  }

  private PolynomialCreator multi(PolynomialCreator multiplicand) {
    double[] result = new double[poly.length + multiplicand.poly.length - 1];

    for (int i = 0; i < poly.length; i++)
      for (int j = 0; j < multiplicand.poly.length; j++) {
        result[i + j] += poly[i] * multiplicand.poly[j];
      }
    return new PolynomialCreator(result);
  }

  public PolynomialCreator multiply(PolynomialCreator multiplicand) {
    return multi(multiplicand);
  }

  public double[] getPoly () {
    return this.poly;
  }

}