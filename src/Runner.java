import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.imageio.ImageIO;

/**
 * Created by jonathankeys on 3/26/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException {
    Image image = new Image("src/images/image0.jpg");
    int[][] imageMatrix = image.getImageMatrix();

    // Example Matrices
    int[][] twoByThree = {{1, 2, 3}, {4, 5, 6}};
    int[][] threeByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] fourByFour = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int[][] sixByThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}};

    Stack<int[][]> examples = new Stack<>();
    examples.push(twoByThree);
    examples.push(threeByThree);
    examples.push(fourByFour);
    examples.push(sixByThree);

    while (!examples.empty()) {
      System.out.println("----------------------");
      Matrix example = new Matrix(examples.pop());
      example.printMatrixSpecs();
      example.printMatrix();

      Matrix exampleTransposed = new Matrix(example.getTransposedMatrix());
      exampleTransposed.printMatrixSpecs();
      exampleTransposed.printMatrix();
    }

//    imageMatrix = matrix.getTransposedMatrix();
//    image.setImageMatrix(imageMatrix);
//
//    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }
}