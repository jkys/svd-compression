/**
 * Created by jonathankeys on 3/27/17.
 *
 */
public class Matrix {
  private double[][] matrix;
  private double[][] transposedMatrix;

  public Matrix(double[][] imageMatrix) {
    this.matrix = imageMatrix;
  }

  private void transposeMatrix () {
    int height = this.matrix.length;
    int width = this.matrix[0].length;

    this.transposedMatrix = new double[width][height];
    if (this.matrix.length > 0) {
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          this.transposedMatrix[j][i] = this.matrix[i][j];
        }
      }
    }
  }

  public double[][] getTransposedMatrix () {
    transposeMatrix();
    return this.transposedMatrix;
  }

  public double[][] getMatrix () {
    return this.matrix;
  }

  public void printMatrix () {
    int height = this.matrix.length;
    int width = this.matrix[0].length;

    for (double[] aMatrix : this.matrix) {
      for (int j = 0; j < width; j++) {
        System.out.print(aMatrix[j] + " ");
      }
      System.out.println();
    }
  }

  public void printTransposedMatrix () {
    int width = this.transposedMatrix[0].length;

    for (double[] aTransposedMatrix : this.transposedMatrix) {
      for (int j = 0; j < width; j++) {
        System.out.print(aTransposedMatrix[j] + " ");
      }
      System.out.println();
    }
  }

//  public void scaleMatrix (int scale) {
//    if (scale != 1) {
//      this.width = this.width * scale;
//      this.height = this.height * scale;
//      int[][] newMatrix = new int[this.width][this.height];
//
//      int x = 0;
//      int y = 0;
//      for (int i = 0; i < this.width; i++) {
//        if(i != 0 & i % scale == 0) {
//          x++;
//        }
//        for (int j = 0; j < this.height; j++) {
//          if(j != 0 & j % scale == 0) {
//            y++;
//          }
//          int val = this.matrix[x][y];
//          newMatrix[i][j] = val;
//        }
//        y = 0;
//      }
//      this.matrix = newMatrix;
//    }
//
//    updateImage();
//  }
//
//  public void rotateImageClockwise () {
//    int newWidth = this.height;
//    int newHeight = this.width;
//    int[][] newMatrix = new int[newWidth][newHeight];
//
//    for (int i = 0; i < this.width; i++) {
//      for (int j = 0; j < this.height; j++) {
//        newMatrix[j][i] = matrix[i][j];
//      }
//    }
//
//    this.height = newHeight;
//    this.width = newWidth;
//    this.matrix = newMatrix;
//
//    updateImage();
//  }

}
