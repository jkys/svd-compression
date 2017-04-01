import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Stack;
import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;

/**
 * Created by jonathankeys on 3/25/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException, FunctionEvaluationException, ConvergenceException {
//    // Create matrix from image
//    Image image = new Image("src/images/image0.jpg");
//    double[][] imageMatrix = image.getImageMatrix();

    /**
     * RealMatrix S=svd.get(0);
     RealMatrix U=svd.get(1);
     RealMatrix V=svd.get(2);

     RealMatrix real = U.multiply(S);
     real = real.multiply(V.transpose());

     final int m = real.getRowDimension();
     final int n = real.getColumnDimension();

     double[][] doubleMat = new double[m][n];

     for (int i = 0; i < m; i++) {
     double[] rowMatrix = real.getRow(i);

     for (int j = 0; j < rowMatrix.length; j++) {
     doubleMat[i][j] = rowMatrix[j];
     }

     }
     return doubleMat;
     */

    double[][] twoByTwo = {{5, 5}, {-1, 7}};
    svdCommented(twoByTwo);
    svdNonCommented(twoByTwo);



//    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }

  private static void transpose (Stack<double[][]> matrices) {
    // While stack is not empty, pop value and print matrix and its transpose
    while (!matrices.empty()) {
      Matrix matrix = new Matrix(matrices.pop());
      matrix.printMatrixSpecs();
      matrix.printMatrixTrim();

      matrix.setMatrix(matrix.transposeArray());
      matrix.printMatrixSpecs();
      matrix.printMatrixTrim();
    }
  }

  private static void svdCommented (double[][] twoByTwo) throws FunctionEvaluationException, ConvergenceException {
    Matrix matrix = new Matrix(twoByTwo);
    Matrix transpose = matrix.transposeMatrix();
    Matrix square = transpose.multiplyMatrix(matrix);

    System.out.println("Matrix: Original");
    matrix.printMatrixTrim();

    System.out.println("Matrix: Transposed");
    transpose.printMatrixTrim();

    System.out.println("Matrix: Square");
    square.printMatrixTrim();

    double determinant = square.getDeterminant();
    double[] squareDiagonal = square.getDiagonal();
    double[] polynomialRoots = new double[1];
    double[] polynomialEquation = new double[1];

    Stack<double[]> squarePolynomials = new Stack<>();

    for (double val: squareDiagonal) {
      squarePolynomials.push(new double[]{val, -1});
    }

    while (!squarePolynomials.empty()) {
      PolynomialCreator polynomial = new PolynomialCreator(squarePolynomials.pop());

      if (!squarePolynomials.empty()) {
        squarePolynomials.push(polynomial.multiply(new PolynomialCreator(squarePolynomials.pop())).getPoly());

      } else {
        polynomialEquation = polynomial.getTruePoly(determinant);
        polynomialRoots = new PolynomialCreator(polynomialEquation).getRoots();
      }
    }


    System.out.print("Polynomial Equation: ");
    for (int i = polynomialEquation.length - 1; i >= 0; i--) {

      double indexVal = polynomialEquation[i];

      String lambdaString = "λ^" + i;
      String end = " + ";

      if (i == 0) {
        end = "\n\n";
        lambdaString = "";
      } else if (i == 1){
        lambdaString = "λ";
      }

      if (i > 0) {
        if (polynomialEquation[i - 1] < 0) {
          end = " - ";
        }
      }
      if (indexVal < 0) {
        indexVal *= -1;
      }

      String val = new DecimalFormat("0.#").format(indexVal);

      if (val.equalsIgnoreCase("1")) {
        System.out.print(lambdaString + end);

      } else {
        System.out.print(val + lambdaString + end);
      }
    }

    System.out.println("Determinant: " + determinant);

    System.out.print("\nEigen Vector: [");
    for (int i = 0, polynomialRootsLength = polynomialRoots.length; i < polynomialRootsLength; i++) {
      String end = ", ";
      if (i == polynomialRootsLength - 1) {
        end = "]\n\n";
      }
      System.out.print(polynomialRoots[i] + end);
    }

    Stack<Matrix> eigenMatrices = new Stack<>();

    for (double root: polynomialRoots) {
      Matrix eigenMatrix = new Matrix();
      eigenMatrix.createZeroIdentityFromVal(root, polynomialRoots.length);
      eigenMatrix = square.subtractionMatrix(eigenMatrix);
      eigenMatrices.push(eigenMatrix);
    }

    SVD svd = new SVD();
    svd.createS(polynomialRoots);
    svd.createV(eigenMatrices);
    svd.createU(matrix);

    Matrix S = new Matrix(svd.getS());
    Matrix V = new Matrix(svd.getV());
    Matrix U = new Matrix(svd.getU());

    System.out.println("Matrix: S");
    S.printMatrixTrim();

    System.out.println("Matrix: V");
    V.printMatrixTrim();

    System.out.println("Matrix: U");
    U.printMatrixTrim();
  }

  private static void svdNonCommented (double[][] twoByTwo) throws FunctionEvaluationException, ConvergenceException {
    Matrix matrix = new Matrix(twoByTwo);
    Matrix transpose = matrix.transposeMatrix();
    Matrix square = transpose.multiplyMatrix(matrix);

    double determinant = square.getDeterminant();
    double[] squareDiagonal = square.getDiagonal();
    double[] polynomialRoots = new double[1];
    double[] polynomialEquation = new double[1];

    Stack<double[]> squarePolynomials = new Stack<>();

    for (double val: squareDiagonal) {
      squarePolynomials.push(new double[]{val, -1});
    }

    while (!squarePolynomials.empty()) {
      PolynomialCreator polynomial = new PolynomialCreator(squarePolynomials.pop());

      if (!squarePolynomials.empty()) {
        squarePolynomials.push(polynomial.multiply(new PolynomialCreator(squarePolynomials.pop())).getPoly());

      } else {
        polynomialRoots = new PolynomialCreator(polynomial.getTruePoly(determinant)).getRoots();
      }
    }

    Stack<Matrix> eigenMatrices = new Stack<>();

    for (double root: polynomialRoots) {
      Matrix eigenMatrix = new Matrix();
      eigenMatrix.createZeroIdentityFromVal(root, polynomialRoots.length);
      eigenMatrix = square.subtractionMatrix(eigenMatrix);
      eigenMatrices.push(eigenMatrix);
    }

    SVD svd = new SVD();
    svd.createS(polynomialRoots);
    svd.createV(eigenMatrices);
    svd.createU(matrix);

    Matrix S = new Matrix(svd.getS());
    Matrix V = new Matrix(svd.getV());
    Matrix U = new Matrix(svd.getU());
  }
}