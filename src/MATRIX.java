/**
 * Created by jonathankeys on 3/27/17.
 *
 */
public class Matrix {
  private int[][] matrix;
  private int[][] transposedMatrix;

  public Matrix(int[][] imageMatrix) {
    this.matrix = imageMatrix;
  }

  public void printMatrixSpecs () {
    System.out.println(String.format("Matrix: %s x %s\n", this.matrix.length, this.matrix[0].length));
  }

  private void transposeMatrix () {
    int height = this.matrix.length;
    int width = this.matrix[0].length;

    this.transposedMatrix = new int[width][height];

    if (this.matrix.length > 0) {
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          this.transposedMatrix[i][j] = this.matrix[j][i];
          this.transposedMatrix[i][j] = this.matrix[j][i];
        }
      }
    }
  }

  public int[][] getTransposedMatrix () {
    transposeMatrix();
    return this.transposedMatrix;
  }

  public int[][] getMatrix () {
    return this.matrix;
  }

  public void printMatrix () {
    for (int[] aMatrix : this.matrix) {
      for (int j = 0; j < this.matrix[0].length; j++) {
        String val = aMatrix[j] + " ";
        if (val.length() == 2) {
          val += " ";
        }
        System.out.print(val + "\t");
      }
      System.out.println();
    }
    System.out.println();
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
