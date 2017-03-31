import java.io.IOException;
import java.util.List;
import java.util.Stack;
import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.solvers.LaguerreSolver;
import org.apache.commons.math.complex.Complex;


/**
 * Created by jonathankeys on 3/25/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException, FunctionEvaluationException, ConvergenceException {
    //Create matrix from image
//    Image image = new Image("src/images/image0.jpg");
//    int[][] imageMatrix = image.getImageMatrix();

//    double[] y = {1600, -100, 1};
//
//    PolynomialFunction x = new PolynomialFunction(y);
//    Complex[] root = new LaguerreSolver().solveAll(y, 0);
//
////    double[] a = x.getCoefficients();
////    int c = x.degree();
////    System.out.println(c);
//
//    for (Complex t:root) {
//      System.out.println(t.getReal());
//    }



    // Example Matrices
    int[][] twoByTwo = {{5, 5}, {-1, 7}};
    int[][] twoByThree = {{1, 2, 3}, {4, 5, 6}};
    int[][] threeByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] fourByFour = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int[][] sixByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}};
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
    Stack<int[][]> matrices = new Stack<>();
    matrices.push(twoByThree);
    matrices.push(threeByThree);
    matrices.push(fourByFour);
    matrices.push(sixByThree);

    Matrix matrix = new Matrix(threeByThree);
    Matrix transpose = matrix.transposeMatrix();
    Matrix square = transpose.multiplyMatrix(matrix);

    matrix.printMatrix();
    transpose.printMatrix();
    square.printMatrix();
    int det = square.getDeterminant();
    int[] diagonal = square.getDiagonal();

    System.out.print("Matrix Diagonal: ( ");
    for (int val: diagonal) {
     System.out.print(val + " ");
    }

    System.out.println(")\nDeterminant: " + det);

//    List<Integer> eVector = createEVector(diagonal, det);

//    for (int val: eVector) {
//      System.out.println(String.format("Polynomial Factor"
//          + " is %s",val));
//    }


    // Do actions on image and set to new file "imageUpdated"
//    imageMatrix = matrix.getTransposedMatrix();
//    image.setImageMatrix(imageMatrix);
//
//    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }

  private static Stack<int[]> poly (Stack<int[]> lah) {
    int[] a = lah.pop();
    int[] b = lah.pop();

    int[] ans = new int[a.length + 1];

    for (int i = 0; i < ans.length; i++) {
      ans[i] = a[i] * b[i];
    }

    Stack<int[]> test = new Stack<>();
    test.push(ans);
    test.push(lah.pop());


    return test;
  }

  private static Stack<int[]> createEVector (int[] diagonal,  int det) {
    int length = diagonal.length;
    Stack<int[]> eVector = new Stack<>();

    int[] a = new int[]{66, -1};
    int[] b = new int[]{93, -1};
    int[] c = new int[]{126, -1};

    eVector.push(a);
    eVector.push(b);
    eVector.push(c);
    return eVector;
  }

  private static void transpose (Stack<int[][]> matrices) {
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