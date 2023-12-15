package Matrix;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.text.DecimalFormat;

public final class ImmutableMatrix {
    private final double[][] data;
    private final int rows;
    private final int cols;


    // Конструктор ініціалізації незмінної матриці
    public ImmutableMatrix(double[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Invalid matrix data");
        }

        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (data[i].length != cols) {
                throw new IllegalArgumentException("Invalid matrix data");
            }
            this.data[i] = Arrays.copyOf(data[i], cols);
        }
    }
    public ImmutableMatrix(int rows, int columns) {
        this.rows = rows;
        this.cols = columns;
        this.data = new double[rows][columns];
    }

    public ImmutableMatrix() {
        this.rows = 0;
        this.cols = 0;
        this.data = new double[0][0];
    }

    public ImmutableMatrix(ImmutableMatrix other) {
        this.rows = other.rows;
        this.cols = other.cols;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(other.data[i], 0, this.data[i], 0, cols);
        }
    }

    public ImmutableMatrix(Matrix other) {
        this.rows = other.getRows();
        this.cols = other.getCols();
        this.data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = other.getElement(i, j);
            }
        }
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

    public void ImmutableMatrixRandom() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomValue = random.nextInt(11);
                data[i][j] = randomValue;
            }
        }
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

    public double getElement(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= rows || colIndex < 0 || colIndex >= cols) {
            throw new IllegalArgumentException("Invalid matrix index");
        }
        return data[rowIndex][colIndex];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ImmutableMatrix copy() {
        return new ImmutableMatrix(data);
    }

    public ImmutableMatrix transpose() {
        double[][] transposedData = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedData[j][i] = data[i][j];
            }
        }
        return new ImmutableMatrix(transposedData);
    }

    public ImmutableMatrix multiplyScalar(double scalar) {
        double[][] resultData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = data[i][j] * scalar;
            }
        }
        return new ImmutableMatrix(resultData);
    }

    public ImmutableMatrix add(ImmutableMatrix other) {
        if (other == null || rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Invalid matrix addition");
        }
        double[][] resultData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return new ImmutableMatrix(resultData);
    }

    public ImmutableMatrix multiply(ImmutableMatrix other) {
        if (other == null || cols != other.rows) {
            throw new IllegalArgumentException("Invalid matrix multiplication");
        }

        double[][] resultData = new double[rows][other.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < cols; k++) {
                    resultData[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }

        return new ImmutableMatrix(resultData);
    }

    public static ImmutableMatrix diagonalMatrix(double[] vector) {
        if (vector == null || vector.length == 0) {
            throw new IllegalArgumentException("Invalid vector data");
        }

        int size = vector.length;
        double[][] diagonalData = new double[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(diagonalData[i], 0.0);
            diagonalData[i][i] = vector[i];
        }

        return new ImmutableMatrix(diagonalData);
    }

    public static ImmutableMatrix unitMatrix(int size) {
        double[] vector = new double[size];
        Arrays.fill(vector, 1.0);
        return diagonalMatrix(vector);
    }

    public static ImmutableMatrix randomRowMatrix(int length, int min, int max) {
        if (length <= 0 || min > max) {
            throw new IllegalArgumentException("Invalid matrix parameters");
        }

        Random random = new Random();
        double[][] randomRowData = new double[1][length];

        for (int j = 0; j < length; j++) {
            randomRowData[0][j] = random.nextInt(max - min + 1) + min;
        }

        return new ImmutableMatrix(randomRowData);
    }

    public static ImmutableMatrix randomColumnMatrix(int height, int min, int max) {
        if (height <= 0 || min > max) {
            throw new IllegalArgumentException("Invalid matrix parameters");
        }

        Random random = new Random();
        double[][] randomColumnData = new double[height][1];

        for (int i = 0; i < height; i++) {
            randomColumnData[i][0] = random.nextInt(max - min + 1) + min;
        }

        return new ImmutableMatrix(randomColumnData);
    }

    public static ImmutableMatrix inverse(ImmutableMatrix matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getCols();
        if (rows != columns) {
            throw new UnsupportedOperationException("Матриця має бути квадратна");
        }
        double[][] tempMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tempMatrix[i][j] = matrix.getElement(i, j);
            }
        }
        double[][] identityMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j == i) {
                    identityMatrix[i][j] = 1;
                } else
                    identityMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            double divider = tempMatrix[i][i];
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
                    indicator = 0;
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
                    if (tempMatrix[l][i] != 0) {
                        notZeroRow = l;
                        break;
                    }
                }
                if(notZeroRow == -1){
                    throw new UnsupportedOperationException("Неможливо знайти обернену матрицю");
                }
                double[] temp1 = tempMatrix[i];
                tempMatrix[i] = tempMatrix[notZeroRow];
                tempMatrix[notZeroRow] = temp1;
                double[] temp2 = identityMatrix[i];
                identityMatrix[i] = identityMatrix[notZeroRow];
                identityMatrix[notZeroRow] = temp2;
                divider = tempMatrix[i][i];
            }

            for (int j = 0; j < rows; j++) {
                tempMatrix[i][j] /= divider;
                identityMatrix[i][j] /= divider;
            }
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = tempMatrix[k][i];
                    for (int j = 0; j < rows; j++) {
                        tempMatrix[k][j] -= factor * tempMatrix[i][j];
                        identityMatrix[k][j] -= factor * identityMatrix[i][j];
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");

        double[][] resultMatrixValues = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String formattedValue = df.format(identityMatrix[i][j]).replace(',', '.');
                resultMatrixValues[i][j] = Double.parseDouble(formattedValue);
            }
        }
        return new ImmutableMatrix(resultMatrixValues);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ImmutableMatrix that = (ImmutableMatrix) obj;
        return rows == that.rows && cols == that.cols && Arrays.deepEquals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }
}