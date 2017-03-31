import java.io.IOException;
import java.util.Stack;
import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;

/**
 * Created by jonathankeys on 3/25/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException, FunctionEvaluationException, ConvergenceException {
    //Create matrix from image
//    Image image = new Image("src/images/image0.jpg");
//    double[][] imageMatrix = image.getImageMatrix();



    // Example Matrices
    double[][] twoByTwo = {{5, 5}, {-1, 7}};
    double[][] twoByThree = {{1, 2, 3}, {4, 5, 6}};
    double[][] threeByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    double[][] fourByFour = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    double[][] sixByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}};
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

    // Push Matrices to stack
    Stack<double[][]> matrices = new Stack<>();
    matrices.push(twoByThree);
    matrices.push(threeByThree);
    matrices.push(fourByFour);
    matrices.push(sixByThree);

    Matrix matrix = new Matrix(twoByTwo);
    Matrix transpose = matrix.transposeMatrix();
    Matrix square = transpose.multiplyMatrix(matrix);

    matrix.printMatrix();
    transpose.printMatrix();
    square.printMatrix();

    double det = square.getDeterminant();
    double[] diagonal = square.getDiagonal();

    System.out.print("Matrix Diagonal: ");
    Stack<double[]> test = new Stack<>();
    for (double val: diagonal) {
     test.push(new double[]{val, -1});
    }



    double[] roots = new double[1];
    double[] poly = new double[1];


    while (!test.empty()) {
      PolynomialCreator blah = new PolynomialCreator(test.pop());
      if (!test.empty()) {
        PolynomialCreator eh = blah.multiply(new PolynomialCreator(test.pop()));
        test.push(eh.getPoly());
      } else {
        poly = blah.getTruePoly(det);
        roots = new PolynomialCreator(poly).getRoots();
      }
    }


    for (double val:poly) {
      System.out.print(val + "\t");
    }

    System.out.println("\nDeterminant: " + det);

    System.out.print("Eigen Values: ");
    for (double val:roots) {
      System.out.print(val + "\t");
    }

    Stack<Matrix> idens = new Stack<>();

    for (double root: roots) {
      Matrix iden = new Matrix();
      iden.createIdentityFromVector(root, roots.length);

      idens.push(iden);
    }

    Stack<Matrix> newIdens = new Stack<>();

    while (!idens.empty()) {
      newIdens.push(new Matrix(square.subtractionMatrix(idens.pop())));
    }

    System.out.println();

    while (!newIdens.empty()) {
      newIdens.pop().printMatrix();
    }

    // Do actions on image and set to new file "imageUpdated"
//    imageMatrix = matrix.getTransposedMatrix();
//    image.setImageMatrix(imageMatrix);
//
//    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }

  private static void transpose (Stack<double[][]> matrices) {
    // While stack is not empty, pop value and print matrix and its transpose
    while (!matrices.empty()) {
      Matrix matrix = new Matrix(matrices.pop());
      matrix.printMatrixSpecs();
      matrix.printMatrix();

      matrix.setMatrix(matrix.transposeArray());
      matrix.printMatrixSpecs();
      matrix.printMatrix();
    }
  }
}