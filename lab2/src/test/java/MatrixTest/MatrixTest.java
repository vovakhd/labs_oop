package MatrixTest;

import static org.junit.jupiter.api.Assertions.*;
import Matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    private Matrix matrix1;

    @BeforeEach
    public void setUp() {
        matrix1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, -9}});
    }

    @Test
    public void teesMatrixConstructor() {
        assertEquals(3, matrix1.getRows());
        assertEquals(3, matrix1.getCols());
    }
    @Test
    public void teesEmptyConstructor() {
        Matrix empty_matrix = new Matrix();
        assertEquals(0, empty_matrix.getRows());
        assertEquals(0, empty_matrix.getCols());
    }

    @Test
    public void testCopyConstructor() {
        Matrix copiedMatrix = new Matrix(matrix1);
        assertEquals(matrix1.getRows(), copiedMatrix.getRows());
        assertEquals(matrix1.getCols(), copiedMatrix.getCols());
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix1.getCols(); j++) {
                assertEquals(matrix1.getElement(i, j), copiedMatrix.getElement(i, j));
            }
        }
    }
    @Test
    public void testFillMatrix() {
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, -9};
        matrix1.fillMatrix(values);
        assertEquals(1, matrix1.getElement(0, 0));
        assertEquals(2, matrix1.getElement(0, 1));
        assertEquals(3, matrix1.getElement(0, 2));
        assertEquals(4, matrix1.getElement(1, 0));
        assertEquals(5, matrix1.getElement(1, 1));
        assertEquals(6, matrix1.getElement(1, 2));
        assertEquals(7, matrix1.getElement(2, 0));
        assertEquals(8, matrix1.getElement(2, 1));
        assertEquals(-9, matrix1.getElement(2, 2));
    }
    @Test
    public void testGetElement() {
        assertEquals(1, matrix1.getElement(0, 0));
        assertEquals(5, matrix1.getElement(1, 1));
        assertEquals(-9, matrix1.getElement(2, 2));
    }

    @Test
    public void testGetElementInvalid() {
        assertThrows(IllegalArgumentException.class, () -> matrix1.getElement(3, 0));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getElement(0, 3));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getElement(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getElement(0, -1));
    }
    @Test
    public void testGetRow() {
        double[] expectedRow = {7, 8, -9};
        double[] actualRow = matrix1.getRow(2);

        assertArrayEquals(expectedRow, actualRow, 0.001f);
    }

    @Test
    public void testGetRowInvalid() {
        assertThrows(IllegalArgumentException.class, () -> matrix1.getRow(3));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getRow(-1));
    }

    @Test
    public void testGetColumn() {
        Matrix matrix = new Matrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.fillMatrix(values);

        double[] expectedColumn = {1, 4, 7};
        double[] actualColumn = matrix.getColumn(0);

        assertArrayEquals(expectedColumn, actualColumn, 0.001f);
    }

    @Test
    public void testGetColumnInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> matrix1.getColumn(3));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getColumn(-1));
    }

    @Test
    public void testGetMatrixSize() {
        Matrix matrix = new Matrix(3, 2);
        int[] size={3,2};
        int[] actualSize = matrix.getMatrixSize();
        assertArrayEquals(size, actualSize);
    }

    @Test
    public void testEqualsAndHashCode() {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(2, 3);
        float[] values1 = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f};
        float[] values2 = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f};
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values2);

        assertEquals(matrix1, matrix2);
        assertEquals(matrix1.hashCode(), matrix2.hashCode());
    }
    @Test
    public void testNotEqual() {
        Matrix matrix1 = new Matrix(2, 2);
        Matrix matrix2 = new Matrix(2, 2);
        float[] values1 = {1.0f, 2.0f, 3.0f, 4.0f};
        float[] values2 = {1.0f, 2.0f, 3.0f, 5.0f};
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values2);

        assertNotEquals(matrix1, matrix2);
    }

    @Test
    public void testaddMatrices(){
        float[] values1 = {1,2,3,4,5,6,7,8,9};

        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(3, 3);
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values1);
        Matrix result1 = matrix1.add(matrix2);
        float[] expectedValues1 = {2, 4, 6, 8, 10, 12, 14, 16, 18};
        Matrix expectedMatrix1 = new Matrix(3, 3);
        expectedMatrix1.fillMatrix(expectedValues1);
        assertEquals(expectedMatrix1, result1);
    }

    @Test
    public void testaddMatricesInvalid() {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(4, 2);
        assertThrows(IllegalArgumentException.class, () -> matrix1.add(matrix2));
    }

    @Test
    public void testMultiplyMatrixByScalar() {
        Matrix matrix = new Matrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.fillMatrix(values);
        float scalar = 2.0f;
        Matrix result = matrix.scalarMultiply(scalar);
        float[] expectedValues = {2, 4, 6, 8, 10, 12, 14, 16, 18};
        Matrix expectedMatrix = new Matrix(3, 3);
        expectedMatrix.fillMatrix(expectedValues);

        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testMultiplyMatrices() {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(3, 2);
        float[] values1 = {1, 2, 3, 4, 5, 6};
        matrix1.fillMatrix(values1);
        float[] values2 = {7, 8, 9, 10, 11, 12};
        matrix2.fillMatrix(values2);
        Matrix result = matrix1.multiply(matrix2);
        float[] expectedValues = {58, 64, 139, 154};
        Matrix expectedMatrix = new Matrix(2, 2);
        expectedMatrix.fillMatrix(expectedValues);
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testMultiplyMatricesInvalid() {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(4, 2);
        assertThrows(IllegalArgumentException.class, () -> matrix1.multiply(matrix2));
    }

    @Test
    public void testTransponseMatrix() {
        Matrix originalMatrix = new Matrix(3, 2);
        float[] originalValues = {1, 2, 3, 4, 5, 6};
        originalMatrix.fillMatrix(originalValues);
        Matrix transposedMatrix = originalMatrix.transpose();
        Matrix expectedTransposedMatrix = new Matrix(2, 3);
        float[] expectedValues = {1, 3, 5, 2, 4, 6};
        expectedTransposedMatrix.fillMatrix(expectedValues);
        assertEquals(expectedTransposedMatrix, transposedMatrix);
    }

    @Test
    public void testVectorMatrix() {
        double[] vector = {1, 2, 3, 4};
        Matrix diagonalMatrix = Matrix.diagonalMatrix(vector);
        Matrix expectedDiagonalMatrix = new Matrix(4, 4);
        float[] expectedValues = {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4};
        expectedDiagonalMatrix.fillMatrix(expectedValues);
        assertEquals(expectedDiagonalMatrix, diagonalMatrix);
    }

    @Test
    public void testOneMatrix() {
        Matrix identityMatrix = Matrix.unitMatrix(3);
        Matrix expectedIdentityMatrix = new Matrix(3,3);
        float[] expectedValues = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        expectedIdentityMatrix.fillMatrix(expectedValues);
        assertEquals(expectedIdentityMatrix, identityMatrix);
    };

    @Test
    public void testRowsMatrix() {
        Matrix result = Matrix.randomRowMatrix(4, 0, 10);
        assertEquals(1, result.getRows());
        assertEquals(4, result.getCols());
    }

    @Test
    public void testColumnsMatrix() {
        Matrix result = Matrix.randomColumnMatrix(5, 0, 10);
        assertEquals(5, result.getRows());
        assertEquals(1, result.getCols());
    }
    @Test
    public void testInverse() {
        Matrix matrix = new Matrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, -9};
        matrix.fillMatrix(values);
        Matrix inverseMatrix = Matrix.inverse(matrix);
        Matrix identityMatrix = matrix.multiply(inverseMatrix);
        Matrix expectedIdentityMatrix = Matrix.unitMatrix(3);
        assertEquals(expectedIdentityMatrix, identityMatrix);
    }

    @Test
    public void testInverseInvalid1() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(UnsupportedOperationException.class, () -> Matrix.inverse(matrix));
    }

    @Test
    public void testInverseInvalid2() {
        Matrix matrix = new Matrix(3, 3);
        matrix.fillMatrix(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertThrows(UnsupportedOperationException.class, () -> Matrix.inverse(matrix));
    }
}