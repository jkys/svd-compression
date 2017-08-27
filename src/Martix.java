import java.text.DecimalFormat;

/**
 * Created by jonathankeys on 3/25/17.
 *
 */
public class Matrix {
    private double[][] matrix;

    // Constructor
    public Matrix(double[][] matrix) {
            this.matrix = matrix;
    }

    public Matrix(Matrix matrix) {
            this.matrix = matrix.getMatrix();
    }

    public Matrix () {

    }

    // Matrix Row and Column Counter
    public int getRowCount() {
            return this.matrix.length;
    }

    public int getColumnCount() {
            return this.matrix[0].length;
    }

    private int getRowCount(double[][] matrix) {
            return matrix.length;
    }

    private int getColumnCount(double[][] matrix) {
            return matrix[0].length;
    }

    // Transposing Matrix
    public double[][] transposeArray () {
            return transpose();
    }

    public Matrix transposeMatrix () {
            return new Matrix(transpose());
    }

    private double[][] transpose () {
            int rows = getRowCount();
            int columns = getColumnCount();

            double[][] transposedMatrix = new double[columns][rows];

            for(int i = 0; i < rows; i++) {
                    for(int j = 0; j < columns; j++) {
                            transposedMatrix[j][i] = matrix[i][j];
                    }
            }

            return transposedMatrix;
    }

    // Multiplying Matrices
    private double[][] multiplicationArray(double[][] multiplicand) {
            int multiplierRows = getRowCount();
            int multiplierColumns = getColumnCount();

            int multiplicandRows = getRowCount(multiplicand);
            int multiplicandColumns = getColumnCount(multiplicand);

            if (multiplierColumns != multiplicandRows) {
                    throw new Error(String.format("Matrices inner dimensions need to be equal, found %s x [%s] and [%s] x %s",
                                                  multiplierRows,
                                                  multiplierColumns, multiplicandRows, multiplicandColumns));
            }

            double[][] product = new double[multiplierRows][multiplicandColumns];

            for (int i = 0; i < multiplierRows; i++) {
                    for (int j = 0; j < multiplicandColumns; j++) {
                            for (int k = 0; k < multiplierColumns; k++) {
                                    product[i][j] += matrix[i][k] * multiplicand[k][j];
                            }
                    }
            }

            return product;
    }

    private double[][] multiplicationMatrix(Matrix multiplicand) {
            int multiplierRows = getRowCount();
            int multiplierColumns = getColumnCount();

            int multiplicandRows = multiplicand.getRowCount();
            int multiplicandColumns = multiplicand.getColumnCount();

            if (multiplierColumns != multiplicandRows) {
                    throw new Error(String.format("Matrices inner dimensions need to be equal, found %s x [%s] and [%s] x %s",
                                                  multiplierRows,
                                                  multiplierColumns, multiplicandRows, multiplicandColumns));
            }

            double[][] product = new double[multiplierRows][multiplicandColumns];
            double[][] r = multiplicand.getMatrix();

            for (int i = 0; i < multiplierRows; i++) {
                    for (int j = 0; j < multiplicandColumns; j++) {
                            for (int k = 0; k < multiplierColumns; k++) {
                                    product[i][j] += matrix[i][k] * r[k][j];
                            }
                    }
            }
            return product;
    }

    public double[][] multiplyArray (double[][] multiplicand) {
            return multiplicationArray(multiplicand);
    }

    public double[][] multiplyArray (Matrix multiplicand) {
            return multiplicationMatrix(multiplicand);
    }

    public Matrix multiplyMatrix (double[][] multiplicand) {
            return new Matrix(multiplicationArray(multiplicand));
    }

    public Matrix multiplyMatrix (Matrix multiplicand) {
            return new Matrix(multiplicationMatrix(multiplicand));
    }

    // Adding Matrices
    private double[][] addArray (double[][] b) {
            int rows = getRowCount();
            int columns = getColumnCount();
            double[][] sum = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                            sum[i][j] = this.matrix[i][j] + b[i][j];
                    }
            }
            return sum;
    }

    private double[][] addMatrix (Matrix b) {
            int rows = getRowCount();
            int columns = getColumnCount();
            double[][] sum = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                            sum[i][j] = this.matrix[i][j] + b.getMatrix()[i][j];
                    }
            }
            return sum;
    }

    public double[][] additionArray (Matrix b) {
            return addMatrix(b);
    }

    public double[][] additionArray (double[][] b) {
            return addArray(b);
    }

    public Matrix additionMatrix (Matrix b) {
            return new Matrix(addMatrix(b));
    }

    public Matrix additionMatrix (double[][] b) {
            return new Matrix(addArray(b));
    }

    // Subtracting Matrices
    private double[][] subArray (double[][] b) {
            int rows = getRowCount();
            int columns = getColumnCount();
            double[][] sub = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                            sub[i][j] = this.matrix[i][j] - b[i][j];
                    }
            }
            return sub;
    }

    private double[][] subMatrix (Matrix b) {
            int rows = getRowCount();
            int columns = getColumnCount();
            double[][] sub = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                            sub[i][j] = this.matrix[i][j] - b.getMatrix()[i][j];
                    }
            }
            return sub;
    }

    public double[][] subtractionArray (Matrix b) {
            return subMatrix(b);
    }

    public double[][] subtractionArray (double[][] b) {
            return subArray(b);
    }

    public Matrix subtractionMatrix (Matrix b) {
            return new Matrix(subMatrix(b));
    }

    public Matrix subtractionMatrix (double[][] b) {
            return new Matrix(subArray(b));
    }

    // Determinants of matrix
    public double getDeterminant() {
            if (getColumnCount() != getRowCount()) {
                    throw new Error(String.format("Matrix needs to be a square, found %s x %s.", getRowCount(), getColumnCount()));
            }

            double[][] newMatrix = this.matrix;
            return getDeterminant(newMatrix);
    }

    private double getDeterminant(double[][] matrixDet) {
            int rows = getRowCount(matrixDet);
            int columns = getColumnCount(matrixDet);
            double determinant = 0;

            if(rows == 1 & columns == 1) {
                    return matrixDet[0][0];
            }

            for(int i = 0; i < rows; i++) {
                    double[][] innerMatrix = new double[rows - 1][columns - 1];
                    for(int j = 1; j < rows; j++) {
                            for(int k = 0; k < columns; k++) {
                                    if(k < i) {
                                            innerMatrix[j - 1][k] = matrixDet[j][k];
                                    }
                                    else if(k > i) {
                                            innerMatrix[j - 1][k - 1] = matrixDet[j][k];
                                    }
                            }
                    }

                    boolean needsUndo = false;
                    if(i % 2 != 0) {
                            matrixDet[0][i] *= -1;
                            needsUndo = true;
                    }

                    determinant += matrixDet[0][i] * getDeterminant(innerMatrix);

                    if (needsUndo) {
                            matrixDet[0][i] *= -1;
                    }
            }

            return determinant;
    }

    // Get diagonal of matrix
    public double[] getDiagonal() {
            int height = getRowCount();
            double[] diagonal = new double[height];
            for (int i = 0; i < height; i++) {
                    diagonal[i] = this.matrix[i][i];
            }

            return diagonal;
    }

    // Getter and Setter
    public double[][] getMatrix () {
            return this.matrix;
    }

    public void setMatrix (double[][] newMatrix) {
            this.matrix = newMatrix;
    }

    // Boolean operators
    private boolean isMultiplicationAllowed(Matrix b) {
            return getColumnCount() == b.getRowCount();
    }

    private boolean isMultiplicationAllowed(double[][] b) {
            return getColumnCount() == getRowCount(b);
    }

    private boolean isArithmeticAllowed(Matrix b) {
            return getRowCount() == b.getRowCount() & getColumnCount() == b.getColumnCount();
    }

    private boolean isArithmeticAllowed(double[][] b) {
            return getRowCount() == getRowCount(b) & getColumnCount() == getColumnCount(b);
    }

    //Print matrix
    private void print (boolean format) {
            int columns = getColumnCount();
            for (double[] aMatrix : this.matrix) {
                    for (int j = 0; j < columns; j++) {
                            String val;
                            if(format) {
                                    val = new DecimalFormat("0.##").format(aMatrix[j]);
                            } else {
                                    val = aMatrix[j] + "";
                            }
                            while (val.length() < 6) {
                                    val += " ";
                            }

                            System.out.print(val + "\t");
                    }
                    System.out.println();
            }
            System.out.println();
    }

    public void printMatrix () {
            print(false);
    }

    public void printMatrixTrim () {
            print(true);
    }

    public void printMatrixSpecs () {
            System.out.println(String.format("Matrix: %s x %s\n", getRowCount(), getColumnCount()));
    }

    public void createZeroIdentityFromVal(double vector, int size) {
            eh(vector, size, 0);
    }

    private void eh (double vector, int size, double val) {
            double[][] identity = new double[size][size];

            for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                            if (i == j) {
                                    identity[i][j] = vector;
                            } else {
                                    identity[i][j] = val;
                            }
                    }
            }

            this.matrix = identity;
    }

    public void createIdentityFromVal(double vector, int size) {
            eh(vector, size, 1);
    }

    public void createZeroIdentityFromVector(double[] vector, int rows, int columns) {
            int size = vector.length;
            double[][] identity = new double[rows][columns];

            int index = size - 1;
            for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                            if (i == j) {
                                    identity[j][j] = vector[index];
                                    index--;
                            } else {
                                    identity[i][j] = 0;
                            }
                    }
            }
            this.matrix = identity;
    }

    public void createUnitMatrix (Matrix US) {
            int rows = US.getRowCount();
            int columns = US.getColumnCount();
            double[][] U = new double[rows][rows];
            double[][] temp = US.getMatrix();

            for (int i = 0; i < columns; i++) {
                    double x = 0;

                    for (int j = 0; j < rows; j++) {
                            double y = Math.abs(temp[j][i]);
                            x += y * y;
                    }

                    x = Math.sqrt(x);

                    for (int j = 0; j < rows; j++) {
                            U[j][i] = temp[j][i] / x;

                    }

                    for (int j = columns; j < rows; j++) {
                            for (int k = columns; k < rows; k++) {
                                    if (j != k & U[j][k] == 0) {
                                            U[j][k] = 1;
                                    }
                            }
                    }
            }

            this.matrix = U;
    }

    public void convertToInt () {
            for (int i = 0; i < getRowCount(); i++) {
                    for (int j = 0; j < getColumnCount(); j++) {
                            this.matrix[i][j] = (int) Math.round(matrix[i][j]);
                    }
            }
    }
}
