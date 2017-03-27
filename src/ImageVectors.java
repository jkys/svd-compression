import java.awt.image.BufferedImage;

/**
 * Created by jonathankeys on 3/26/17.
 *
 */
public class ImageVectors {
  private BufferedImage image;
  private int[][] matrix;
  private int height;
  private int width;

  public ImageVectors(BufferedImage image) {
    this.image = image;
    this.height = image.getHeight();
    this.width = image.getWidth();
    createMatrix();
  }

  private void updateImage () {
    BufferedImage newImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        int val = this.matrix[i][j];
        newImage.setRGB(i, j, val);
      }
    }
    this.image = newImage;
  }

  public int[][] getMatrix() {
    return this.matrix;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public BufferedImage getImage () {
    return this.image;
  }

  public void scaleMatrix (int scale) {
    if (scale != 1) {
      this.width = this.width * scale;
      this.height = this.height * scale;
      int[][] newMatrix = new int[this.width][this.height];

      int x = 0;
      int y = 0;
      for (int i = 0; i < this.width; i++) {
        if(i != 0 & i % scale == 0) {
          x++;
        }
        for (int j = 0; j < this.height; j++) {
          if(j != 0 & j % scale == 0) {
            y++;
          }
          int val = this.matrix[x][y];
          newMatrix[i][j] = val;
        }
        y = 0;
      }
      this.matrix = newMatrix;
    }

    updateImage();
  }

  public void rotateImageClockwise () {
    int newWidth = this.height;
    int newHeight = this.width;
    int[][] newMatrix = new int[newWidth][newHeight];

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        newMatrix[j][i] = matrix[i][j];
      }
    }

    this.height = newHeight;
    this.width = newWidth;
    this.matrix = newMatrix;

    updateImage();
  }

  private void createMatrix () {
    int[][] matrix = new int[this.width][this.height];
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        int rgb = this.image.getRGB(i, j);
        matrix[i][j] = rgb;
      }
    }
    this.matrix = matrix;
  }
}