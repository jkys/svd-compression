import Jama.EigenvalueDecomposition;
import java.util.Stack;

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

  private void makeS (double[] eigenValues) {
    for (int i = 0, eigenLength = eigenValues.length; i < eigenLength; i++) {
      eigenValues[i] = Math.sqrt(eigenValues[i]);
    }

    Matrix matrixS = new Matrix();
    matrixS.createZeroIdentityFromVector(eigenValues);

    setS(matrixS.getMatrix());
  }

  private void makeV (Stack<Matrix> eigenMatrices) {
    Stack<EigenvalueDecomposition> eigenDecompositions = new Stack<>();
    while (!eigenMatrices.empty()) {
      eigenDecompositions.push(new Jama.Matrix(eigenMatrices.pop().getMatrix()).eig());
    }

    setV(eigenDecompositions.pop().getV().getArray());
  }

  private void makeU (Matrix matrix) {
    Matrix U = new Matrix(matrix.multiplyMatrix(new Matrix(getV())));
    U.createUnitMatrix(U);
    setU(U.multiplyMatrix(new Matrix(getS())).getMatrix());
  }

  public void createS (double[] eigenValues) {
    makeS(eigenValues);
  }

  public void createV (Stack<Matrix> eigenMatrices) {
    makeV(eigenMatrices);
  }

  public void  createU (Matrix matrix) {
    makeU(matrix);
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

  private void setS(double[][] S) {
    this.S = S;
  }

  private void setU(double[][] U) {
    this.U = U;
  }

  private void setV(double[][] V) {
    this.V = V;
  }
}
