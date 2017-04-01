/**
 * Created by jonathankeys on 3/31/17.
 *
 */
public class SVD {
  private double[][] S;
  private double[][] U;
  private double[][] V;

  public SVD () {

  }

  public void createS (double[] eigen) {
    for (int i = 0, eigenLength = eigen.length; i < eigenLength; i++) {
      eigen[i] = Math.sqrt(eigen[i]);
    }

    Matrix hh = new Matrix();
    hh.createZeroIdentityFromVector(eigen);

    this.S = hh.getMatrix();
  }

  public double[][] getS () {
    return this.S;
  }

  public double[][] getU () {
    return this.U;
  }

  public double[][] getV () {
    return this.V;
  }
}
