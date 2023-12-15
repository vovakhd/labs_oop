package Matrix;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Matrix{
    private final int rows;
    private final int cols;
    private final double[][] data;

    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public Matrix() {
        this.rows = 0;
        this.cols = 0;
        this.data = new double[rows][cols];
    }

    public Matrix(double[][] data){
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++){
            this.data[i] = Arrays.copyOf(data[i], cols);
        }
    }

    public Matrix(Matrix other){
        this(other.data);
    }

    public Matrix(ImmutableMatrix other) {
        this.rows = other.getRows();
        this.cols = other.getCols();
        this.data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = other.getElement(i, j);
            }
        }
    }

    public Matrix copy() {
        return new Matrix(data);
    }

    public void fillMatrix(float[] values) {
        if (values.length != rows * cols) {
            throw new IllegalArgumentException("The length of the array of values does not correspond to the size of the matrix");
        }
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = values[index];
                index++;
            }
        }
    }

    public void fillMatrix(double value){
        for (int i = 0; i < rows; i++){
            Arrays.fill(data[i], value);
        }
    }

    public void setElement(int row, int col, double value){
        data[row][col] = value;
    }

    public double getElement(int row, int col){
        return data[row][col];
    }
    public double[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex > rows) {
            throw new IllegalArgumentException("Invalid row index");
        }
        return Arrays.copyOf(data[rowIndex - 1], cols);
    }

    public double[] getColumn(int colIndex) {
        if (colIndex < 0 || colIndex > cols) {
            throw new IllegalArgumentException("Invalid column index");
        }
        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = data[i][colIndex - 1];
        }
        return column;
    }

    public int[] getMatrixSize() {
        return new int[]{rows,cols};
    }

    public double getEl(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > rows || colIndex < 0 || colIndex > cols) {
            throw new IllegalArgumentException("Invalid row or column index");
        }
        return data[rowIndex - 1][colIndex - 1];
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public Matrix transpose(){
        Matrix transposed = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                transposed.data[j][i] = data[i][j];
            }
        }
        return transposed;
    }

    public Matrix multiply(Matrix other){
        if (cols != other.rows){
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix");
        }

        Matrix result = new Matrix(rows, other.cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < other.cols; j++){
                for (int k = 0; k < cols; k++){
                    result.data[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }

    public Matrix scalarMultiply(double scalar){
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                result.data[i][j] = data[i][j] * scalar;
            }
        }
        return result;
    }

    public Matrix add(Matrix other){
        if (rows != other.rows || cols != other.cols){
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition");
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                result.data[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    public static Matrix diagonalMatrix(double[] diagonal){
        int n = diagonal.length;
        Matrix result = new Matrix(n, n);
        for (int i = 0; i < n; i++){
            result.setElement(i, i, diagonal[i]);
        }
        return result;
    }

    public static Matrix unitMatrix(int size){
        double[][] unitData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unitData[i][j] = (i == j) ? 1.0 : 0.0;
            }
        }
        return new Matrix(unitData);
    }

    public void matrixRandom() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomValue = random.nextInt(21) - 10;
                data[i][j] = randomValue;
            }
        }
    }

    public static Matrix randomRowMatrix(int size, int min, int max) {
        if (size <= 0 || min >= max) {
            throw new IllegalArgumentException("Invalid matrix size or range");
        }

        Matrix result = new Matrix(1, size);
        Random random = new Random();

        for (int j = 0; j < size; j++) {
            int randomValue = random.nextInt(max - min + 1) + min;
            result.setElement(0, j, randomValue);
        }

        return result;
    }

    public static Matrix randomColumnMatrix(int size, int min, int max) {
        if (size <= 0 || min >= max) {
            throw new IllegalArgumentException("Invalid matrix size or range");
        }

        Matrix result = new Matrix(size, 1);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(max - min + 1) + min;
            result.setElement(i, 0, randomValue);
        }

        return result;
    }

    public void swapRows(int row1, int row2) {
        double[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }

    public static Matrix inverse(Matrix matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getCols();
        if (rows != columns) {
            throw new UnsupportedOperationException("Матриця має бути квадратна");
        }
        Matrix tempMatrix = new Matrix(matrix);
        Matrix identityMatrix =  Matrix.unitMatrix(rows);

        for (int i = 0; i < rows; i++) {
            double divider = tempMatrix.data[i][i];
            if (divider == 0) {
                int indicator = 0;
                for (int s = 0; s < rows; s++){
                    double[] array1 = matrix.getRow(s);
                    double[] array2 = matrix.getColumn(s);
                    indicator = 0;
                    for (double element : array1) {
                        if (element == 0) {
                            indicator++;
                        }
                    }
                    if (indicator == rows) {
                        throw new UnsupportedOperationException("Неможливо знайти обернену матрицю");
                    }
                    indicator=0;
                    for (double element : array2) {
                        if (element == 0) {
                            indicator++;
                        }
                    }
                    if (indicator == columns) {
                        throw new UnsupportedOperationException("Неможливо знайти обернену матрицю");
                    }

                }
                int notZeroRow = -1;
                for (int l = i + 1; l < rows; l++) {
                    if (tempMatrix.data[l][i] != 0) {
                        notZeroRow = l;
                        break;
                    }
                }
                if(notZeroRow == -1){
                    throw new UnsupportedOperationException("Неможливо знайти обернену матрицю");
                }
                tempMatrix.swapRows(i, notZeroRow);
                identityMatrix.swapRows(i, notZeroRow);
                divider = tempMatrix.data[i][i];
            }
            for (int j = 0; j < rows; j++) {
                tempMatrix.data[i][j] /= divider;
                identityMatrix.data[i][j] /= divider;
            }
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = tempMatrix.data[k][i];
                    for (int j = 0; j < rows; j++) {
                        tempMatrix.data[k][j] -= factor * tempMatrix.data[i][j];
                        identityMatrix.data[k][j] -= factor * identityMatrix.data[i][j];
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");

        double[][] resultMatrixValues = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String formattedValue = df.format(identityMatrix.data[i][j]).replace(',', '.');
                resultMatrixValues[i][j] = Double.parseDouble(formattedValue);
            }
        }
        return new Matrix(resultMatrixValues);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix matrix = (Matrix) obj;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode(){
        return Arrays.deepHashCode(data);
    }
}