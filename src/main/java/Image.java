import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by jonathankeys on 3/26/17.
 */
public class Image {

    private BufferedImage image;
    private double[][] imageMatrix;
    private int height;
    private int width;

    public Image(String url) throws IOException {
        setImage(url);
    }

    public Image() throws IOException {
    }

    public void setImage(String url) throws IOException {
        this.image = ImageIO.read(new File(url));
    }

    public double[][] getImageMatrix() {
        createMatrixFromImage();
        return this.imageMatrix;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    private void createMatrixFromImage() {
        this.height = image.getHeight();
        this.width = image.getWidth();

        this.imageMatrix = new double[this.width][this.height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                this.imageMatrix[i][j] = rgb;
            }
        }
    }

    public void createImageFromMatrix() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int val = (int) Math.round(this.imageMatrix[i][j]);
                this.image.setRGB(i, j, val);
            }
        }
    }

    public void setImageMatrix(double[][] newImageMatrix) {
        this.imageMatrix = newImageMatrix;
    }

    public BufferedImage createImage(double[][] matrix) {
        BufferedImage newImage = new BufferedImage(matrix[0].length, matrix.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int val = (int) Math.round(matrix[j][i]);
                newImage.setRGB(i, j, val);
            }
        }
        return newImage;
    }
}
