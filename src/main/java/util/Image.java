package util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by jonathankeys on 3/26/17.
 *
 * Image class to hold and set actions on an image such as creating a matrix from it.
 */
public class Image {

    private BufferedImage image;
    private double[][] imageMatrix;
    private int height;
    private int width;

    /**
     * Constructor method to set image.
     * @param url file path of the image to be created.
     * @throws IOException
     */
    public Image(String url) throws IOException {
        setImage(url);
    }

    public Image() throws IOException {
    }

    /**
     * Sets an image by reading in the file url and sets that to the class BufferedImage object
     * @param url file path of the image on local computer
     * @throws IOException
     */
    private void setImage(String url) throws IOException {
        this.image = ImageIO.read(new File(url));
    }

    /**
     * Accessor method for the matrix of the BufferedImage
     * @return double[][] containing all the values of the matrix in a double multi-dimensional array
     */
    public double[][] getImageMatrix() {
        createMatrixFromImage();
        return this.imageMatrix;
    }

    /**
     * Accessor method to retrieve the image as an image
     * @return BufferedImage object of the matrix itself
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Sets the image from a BufferedImage to a double[][] image
     */
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

    /**
     * Recreate an image based on a double matrix and set it to a BufferedImage
     */
    public void createImageFromMatrix() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int val = (int) Math.round(this.imageMatrix[i][j]);
                this.image.setRGB(i, j, val);
            }
        }
    }

    /**
     * Setter method to set the imageMatrix of the class object to be used in creating BufferedImages
     * @param newImageMatrix double[][] matrix to be set to class object
     */
    public void setImageMatrix(double[][] newImageMatrix) {
        this.imageMatrix = newImageMatrix;
    }

    /**
     * Create an image based on a double[][] matrix of values
     * @param matrix double[][] matrix to be used to converted into a BufferedImage
     * @return BufferedImage representation of the double[][] matrix
     */
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
