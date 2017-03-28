/**
 * Created by jonathankeys on 3/27/17.
 *
 */
public class Matrix {
  private int[][] matrix;

  public Matrix(int[][] imageMatrix) {
    this.matrix = imageMatrix;
  }

  public void printMatrixSpecs () {
    System.out.println(String.format("Matrix: %s x %s\n", getRowCount(), getColumnCount()));
  }

  private int getRowCount() {
    return this.matrix.length;
  }

  private int getColumnCount() {
    return this.matrix[0].length;
  }

  private int getRowCount(int[][] matrix) {
    return matrix.length;
  }

  private int getColumnCount(int[][] matrix) {
    return matrix[0].length;
  }

  public int[][] transposeMatrix () {
    int rows = getRowCount();
    int columns = getColumnCount();

    int[][] transposedMatrix = new int[columns][rows];

    if (rows > 0) {
      for (int i = 0; i < columns; i++) {
        for (int j = 0; j < rows; j++) {
          transposedMatrix[i][j] = this.matrix[j][i];
        }
      }
    }

    return transposedMatrix;
  }

  public int[][] multiply (int[][] multiplicand) {
    int multiplierRows = getRowCount();
    int multiplierColumns = getColumnCount();

    int multiplicandRows = getRowCount(multiplicand);
    int multiplicandColumns = getColumnCount(multiplicand);

    if (multiplierColumns != multiplicandRows) {
      System.out.println("The Matrices are not compatible");
      return null;
    }

    int[][] product = new int[multiplierRows][multiplicandColumns];

    for (int i = 0; i < multiplierRows; i++) {
      for (int j = 0; j < multiplicandColumns; j++) {
        for (int k = 0; k < multiplierColumns; k++) {
          product[i][j] += matrix[i][k] * multiplicand[k][j];
        }
      }
    }

    return product;
  }

  public int[][] getMatrix () {
    return this.matrix;
  }

  public void setMatrix (int[][] newMatrix) {
    this.matrix = newMatrix;
  }

  public void printMatrix () {
    int columns = getColumnCount();
    for (int[] aMatrix : this.matrix) {
      for (int j = 0; j < columns; j++) {
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
