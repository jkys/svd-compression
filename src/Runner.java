import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by jonathankeys on 3/26/17
 *
 */
public class Runner {
  public static void main (String[] args) throws IOException {
    Image image = new Image("src/images/image1.jpg");
    double[][] imageMatrix = image.getImageMatrix();

    Matrix matrix = new Matrix(imageMatrix);

    imageMatrix = matrix.getTransposedMatrix();
    image.setImageMatrix(imageMatrix);

    ImageIO.write(image.getImage(), "jpg", new File("src/images/imageUpdated.jpg"));
  }
}