import java.io.IOException;
import java.util.Stack;

/**
 * Created by jonathankeys on 3/26/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException {
    //Create matrix from image
//    Image image = new Image("src/images/image0.jpg");
//    int[][] imageMatrix = image.getImageMatrix();

    // Example Matrices
    int[][] twoByTwo = {{5, 5}, {-1, 7}};
    int[][] twoByThree = {{1, 2, 3}, {4, 5, 6}};
    int[][] threeByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] fourByFour = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int[][] sixByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}};

    // Push Matrices to stack
    Stack<int[][]> matrices = new Stack<>();
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
    int det = square.getDeterminant();

    System.out.println(det);



    // Do actions on image and set to new file "imageUpdated"
//    imageMatrix = matrix.getTransposedMatrix();
//    image.setImageMatrix(imageMatrix);
//
//    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }

  private static void multiply(int[][] a, int[][] b) {
    Matrix multiplier = new Matrix(a);
    int[][] product = multiplier.multiplyArray(b);

    if (product != null) {
      Matrix productMatrix = new Matrix(product);
      productMatrix.printMatrixSpecs();
      productMatrix.printMatrix();
    }
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