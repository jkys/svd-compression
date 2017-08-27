import java.io.File;
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
    public static void main (String[] args) throws Exception {
            // Create matrix from image
            Image image = new Image("src/images/image4.jpg");
            double[][] imageMatrix = image.getImageMatrix();

            double[][] twoByTwo = {{5, 5}, {-1, 7}};
            double[][] threeByTwo = {{5, 5, 7}, {-1, 7, 8}};
            double[][] fourByTwo = {{2, 4}, {1, 3}, {0, 0}, {0, 0}};
            svdCommented(twoByTwo);

            int rank = 100;

            imageMatrix = svd(imageMatrix, rank);

            image.setImageMatrix(imageMatrix);
            image.createImageFromMatrix();

            ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
    }

    private static double[][] rankedMatrixS (Matrix matrix, int rank) {
            double[][] rankedMatrix = new double[rank][rank];
            for (int i = 0; i < rank; i++) {
                    double[] rowMatrix = matrix.getMatrix()[i];
                    System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
            }
            return rankedMatrix;
    }

    private static double[][] rankedMatrixUV (Matrix matrix, int rank) {
            int rows = matrix.getRowCount();
            double[][] rankedMatrix = new double[rows][rank];
            for (int i = 0; i < rows; i++) {
                    double[] rowMatrix = matrix.getMatrix()[i];
                    System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
            }
            return rankedMatrix;
    }

    private static double[][] svd (double[][] imageMatrix, int rank) throws Exception {
            Jama.Matrix matrix = new Jama.Matrix(imageMatrix);

            int maxRank = matrix.rank();

            if (rank > maxRank) {
                    rank = maxRank;
            }

            Jama.Matrix S = matrix.svd().getS();
            Jama.Matrix V = matrix.svd().getV();
            Jama.Matrix U = matrix.svd().getU();

            S = new Jama.Matrix(rankedMatrixS(new Matrix(S.getArray()), rank));
            V = new Jama.Matrix(rankedMatrixUV(new Matrix(V.getArray()), rank));
            U = new Jama.Matrix(rankedMatrixUV(new Matrix(U.getArray()), rank));

            ImageIO.write(new Image().createImage(S.getArray()), "jpg", new File("src/images/S.jpg"));
            ImageIO.write(new Image().createImage(V.getArray()), "jpg", new File("src/images/V.jpg"));
            ImageIO.write(new Image().createImage(U.getArray()), "jpg", new File("src/images/U.jpg"));

            Matrix composed = new Matrix(U.times(S).times(V.transpose()).getArray());
            return composed.getMatrix();
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
            System.out.println("Matrix: S");
            S.printMatrixTrim();

            svd.createV(eigenMatrices, matrix.getColumnCount());
            Matrix V = new Matrix(svd.getV());
            System.out.println("Matrix: V");
            V.printMatrixTrim();

            svd.createU(matrix);
            Matrix U = new Matrix(svd.getU());
            System.out.println("Matrix: U");
            U.printMatrixTrim();


            Matrix SUV = new Matrix(svd.compose());
            System.out.println("Matrix: Reconstructed");
            SUV.printMatrix();
    }
}
