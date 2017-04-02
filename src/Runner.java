import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;

/**
 * Created by jonathankeys on 3/25/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException, FunctionEvaluationException, ConvergenceException {
    // Create matrix from image
    Image image = new Image("src/images/image4.jpg");
    double[][] imageMatrix = image.getImageMatrix();

    double[][] twoByTwo = {{5, 5}, {-1, 7}};
    double[][] threeByTwo = {{5, 5, 7}, {-1, 7, 8}};
    double[][] fourByTwo = {{2, 4}, {1, 3}, {0, 0}, {0, 0}};
    svdCommented(imageMatrix);

    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }

  private static void svdCommented (double[][] twoByTwo) throws FunctionEvaluationException, ConvergenceException {
    Matrix matrix = new Matrix(twoByTwo);
    Matrix transpose = matrix.transposeMatrix();
    Matrix square = transpose.multiplyMatrix(matrix);

//    System.out.println("Matrix: Original");
//    matrix.printMatrixTrim();

//    System.out.println("Matrix: Transposed");
//    transpose.printMatrixTrim();

//    System.out.println("Matrix: Square");
//    square.printMatrixTrim();

    Jama.EigenvalueDecomposition r = new Jama.EigenvalueDecomposition(new Jama.Matrix(square.getMatrix()));

    List<Matrix> eigenMatrices = new ArrayList<>();

    for (double root: r.getRealEigenvalues()) {
      Matrix eigenMatrix = new Matrix();
      eigenMatrix.createZeroIdentityFromVal(root, r.getRealEigenvalues().length);
      eigenMatrix = square.subtractionMatrix(eigenMatrix);
      eigenMatrices.add(eigenMatrix);
    }

    SVD svd = new SVD();

    svd.createS(r.getRealEigenvalues(), matrix.getRowCount(), matrix.getColumnCount());
    Matrix S = new Matrix(svd.getS());
//    System.out.println("Matrix: S");
//    S.printMatrixTrim();

    svd.createV(eigenMatrices, matrix.getColumnCount());
    Matrix V = new Matrix(svd.getV());
//    System.out.println("Matrix: V");
//    V.printMatrixTrim();

    svd.createU(matrix);
    Matrix U = new Matrix(svd.getU());
//    System.out.println("Matrix: U");
//    U.printMatrixTrim();


    Matrix SUV = new Matrix(svd.compose());
//    System.out.println("Matrix: Reconstructed");
//    SUV.printMatrix();
  }
}