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
    BufferedImage image = ImageIO.read(new File("/Users/jonathankeys/code/school/linearalgebra/src/images/image1.jpg"));

    ImageVectors matrix = new ImageVectors(image);
    matrix.rotateImageClockwise();
    image = matrix.getImage();

    ImageIO.write(image, "jpg", new File("/Users/jonathankeys/code/school/linearalgebra/src/images/imageUpdated.jpg"));
  }
}