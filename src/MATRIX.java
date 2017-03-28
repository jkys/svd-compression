/**
 * Created by jonathankeys on 3/25/17.
 *
 */
public class Matrix {
  private int[][] matrix;

  // Constructor
  public Matrix(int[][] imageMatrix) {
    this.matrix = imageMatrix;
  }

  // Matrix Row and Column Counter
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

  // Transposing Matrix
  public int[][] transposeArray () {
    return transpose();
  }

  public Matrix transposeMatrix () {
    return new Matrix(transpose());
  }

  private int[][] transpose () {
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

  // Multiplying Matrices
  private int[][] multArray (int[][] multiplicand) {
    int multiplierRows = getRowCount();
    int multiplierColumns = getColumnCount();

    int multiplicandRows = getRowCount(multiplicand);
    int multiplicandColumns = getColumnCount(multiplicand);

    if (multiplierColumns != multiplicandRows) {
      throw new Error(String.format("Matrices inner dimensions need to be equal, found %s x [%s] and [%s] x %s",
          multiplierRows,
          multiplierColumns, multiplicandRows, multiplicandColumns));
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

  private int[][] multMatrix (Matrix multiplicand) {
    int multiplierRows = getRowCount();
    int multiplierColumns = getColumnCount();

    int multiplicandRows = multiplicand.getRowCount();
    int multiplicandColumns = multiplicand.getColumnCount();

    if (multiplierColumns != multiplicandRows) {
      throw new Error(String.format("Matrices inner dimensions need to be equal, found %s x [%s] and [%s] x %s",
          multiplierRows,
          multiplierColumns, multiplicandRows, multiplicandColumns));
    }

    int[][] product = new int[multiplierRows][multiplicandColumns];

    for (int i = 0; i < multiplierRows; i++) {
      for (int j = 0; j < multiplicandColumns; j++) {
        for (int k = 0; k < multiplierColumns; k++) {
          product[i][j] += matrix[i][k] * multiplicand.getMatrix()[k][j];
        }
      }
    }

    return product;
  }

  public int[][] multiplyArray (int[][] multiplicand) {
    return multArray(multiplicand);
  }

  public int[][] multiplyArray (Matrix multiplicand) {
    return multMatrix(multiplicand);
  }

  public Matrix multiplyMatrix (int[][] multiplicand) {
    return new Matrix(multArray(multiplicand));
  }

  public Matrix multiplyMatrix (Matrix multiplicand) {
    return new Matrix(multMatrix(multiplicand));
  }

  // Adding Matrices
  private int[][] addArray (int[][] b) {
    int rows = getRowCount();
    int columns = getColumnCount();
    int[][] sum = new int[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        sum[i][j] = this.matrix[i][j] + b[i][j];
      }
    }
    return sum;
  }

  private int[][] addMatrix (Matrix b) {
    int rows = getRowCount();
    int columns = getColumnCount();
    int[][] sum = new int[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        sum[i][j] = this.matrix[i][j] + b.getMatrix()[i][j];
      }
    }
    return sum;
  }

  public int[][] additionArray (Matrix b) {
    return addMatrix(b);
  }

  public int[][] additionArray (int[][] b) {
    return addArray(b);
  }

  public Matrix additionMatrix (Matrix b) {
    return new Matrix(addMatrix(b));
  }

  public Matrix additionMatrix (int[][] b) {
    return new Matrix(addArray(b));
  }

  // Subtracting Matrices
  private int[][] subArray (int[][] b) {
    int rows = getRowCount();
    int columns = getColumnCount();
    int[][] sub = new int[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        sub[i][j] = this.matrix[i][j] - b[i][j];
      }
    }
    return sub;
  }

  private int[][] subMatrix (Matrix b) {
    int rows = getRowCount();
    int columns = getColumnCount();
    int[][] sub = new int[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        sub[i][j] = this.matrix[i][j] - b.getMatrix()[i][j];
      }
    }
    return sub;
  }

  public int[][] subtractionArray (Matrix b) {
    return addMatrix(b);
  }

  public int[][] subtractionArray (int[][] b) {
    return addArray(b);
  }

  public Matrix subtractionMatrix (Matrix b) {
    return new Matrix(addMatrix(b));
  }

  public Matrix subtractionMatrix (int[][] b) {
    return new Matrix(addArray(b));
  }

  // Determinants of matrix
  public int getDeterminant() {
    if (getColumnCount() != getRowCount()) {
      throw new Error(String.format("Matrix needs to be a square, found %s x %s.", getRowCount(), getColumnCount()));
    }
    return getDeterminant(this.matrix);
  }

  private int getDeterminant(int[][] matrix) {
    int rows = getRowCount(matrix);
    int columns = getColumnCount(matrix);
    int determinant = 0;

    for(int i = 0; i < rows; i++){
      int[][] innerMatrix = new int[rows - 1][columns - 1];
      for(int j = 1; j < rows; j++){
        for(int k = 0; k < columns; k++){
          if(k < i){
            innerMatrix[j - 1][k] = matrix[j][k];
          }
          else if(k > i){
            innerMatrix[j - 1][k - 1] = matrix[j][k];
          }
        }
      }
      if(i % 2 != 0){
        matrix[0][i] *= -1;
      }
      determinant += matrix[0][i] * getDeterminant(innerMatrix);
    }
    return(determinant);
  }

  // Getter and Setter
  public int[][] getMatrix () {
    return this.matrix;
  }

  public void setMatrix (int[][] newMatrix) {
    this.matrix = newMatrix;
  }

  // Boolean operators
  private boolean isMultAllowed (Matrix b) {
    return getColumnCount() == b.getRowCount();
  }

  private boolean isMultAllowed (int[][] b) {
    return getColumnCount() == getRowCount(b);
  }

  private boolean isArithmicAllowed (Matrix b) {
    return getRowCount() == b.getRowCount() & getColumnCount() == b.getColumnCount();
  }

  private boolean isArithmicAllowed (int[][] b) {
    return getRowCount() == getRowCount(b) & getColumnCount() == getColumnCount(b);
  }

  //Print matrix
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

  public void printMatrixSpecs () {
    System.out.println(String.format("Matrix: %s x %s\n", getRowCount(), getColumnCount()));
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
