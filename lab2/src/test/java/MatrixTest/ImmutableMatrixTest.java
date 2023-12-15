package MatrixTest;

import static org.junit.jupiter.api.Assertions.*;
import Matrix.ImmutableMatrix;
import Matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImmutableMatrixTest {
    private ImmutableMatrix matrix1;

    @BeforeEach
    public void setUp() {
        matrix1 = new ImmutableMatrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, -9}});
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
        ImmutableMatrix copiedMatrix = new ImmutableMatrix(matrix1);
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
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, -9};
        matrix.fillMatrix(values);
        assertEquals(1, matrix.getElement(0, 0));
        assertEquals(2, matrix.getElement(0, 1));
        assertEquals(3, matrix.getElement(0, 2));
        assertEquals(4, matrix.getElement(1, 0));
        assertEquals(5, matrix.getElement(1, 1));
        assertEquals(6, matrix.getElement(1, 2));
        assertEquals(7, matrix.getElement(2, 0));
        assertEquals(8, matrix.getElement(2, 1));
        assertEquals(-9, matrix.getElement(2, 2));
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
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.fillMatrix(values);

        double[] expectedColumn = {1, 4, 7};
        double[] actualColumn = matrix.getColumn(0);

        assertArrayEquals(expectedColumn, actualColumn, 0.001f);
    }

    @Test
    public void testGetColumnInvalid() {
        assertThrows(IllegalArgumentException.class, () -> matrix1.getColumn(3));
        assertThrows(IllegalArgumentException.class, () -> matrix1.getColumn(-1));
    }

    @Test
    public void testGetMatrixSize() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 2);
        int[] size = {3,2};
        int[] actualSize = matrix.getMatrixSize();
        assertArrayEquals(size, actualSize);
    }

    @Test
    public void testEqualsAndHashCode() {
        ImmutableMatrix matrix1 = new ImmutableMatrix(2, 3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(2, 3);
        float[] values1 = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f};
        float[] values2 = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f};
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values2);

        assertEquals(matrix1, matrix2);
        assertEquals(matrix1.hashCode(), matrix2.hashCode());
    }
    @Test
    public void testNotEqual() {
        ImmutableMatrix matrix1 = new ImmutableMatrix(2, 2);
        ImmutableMatrix matrix2 = new ImmutableMatrix(2, 2);
        float[] values1 = {1.0f, 2.0f, 3.0f, 4.0f};
        float[] values2 = {1.0f, 2.0f, 3.0f, 5.0f};
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values2);

        assertNotEquals(matrix1, matrix2);
    }

    @Test
    public void testaddMatrices(){
        float[] values1 = {1,2,3,4,5,6,7,8,9};
        ImmutableMatrix matrix1 = new ImmutableMatrix(3, 3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(3, 3);
        matrix1.fillMatrix(values1);
        matrix2.fillMatrix(values1);
        ImmutableMatrix result1 = matrix1.add(matrix2);
        float[] expectedValues1 = {2, 4, 6, 8, 10, 12, 14, 16, 18};
        ImmutableMatrix expectedMatrix1 = new ImmutableMatrix(3, 3);
        expectedMatrix1.fillMatrix(expectedValues1);
        assertEquals(expectedMatrix1, result1);
    }

    @Test
    public void testaddMatricesInvalid() {
        ImmutableMatrix matrix1 = new ImmutableMatrix(2, 3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(4, 2);
        assertThrows(IllegalArgumentException.class, () -> matrix1.add(matrix2));
    }

    @Test
    public void testMultiplyMatrixByScalar() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        matrix.fillMatrix(values);
        float scalar = 2.0f;
        ImmutableMatrix result = matrix.multiplyScalar(scalar);
        float[] expectedValues = {2, 4, 6, 8, 10, 12, 14, 16, 18};
        ImmutableMatrix expectedMatrix = new ImmutableMatrix(3, 3);
        expectedMatrix.fillMatrix(expectedValues);

        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testMultiplyMatrices() {
        ImmutableMatrix matrix1 = new ImmutableMatrix(2, 3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(3, 2);
        float[] values1 = {1, 2, 3, 4, 5, 6};
        matrix1.fillMatrix(values1);
        float[] values2 = {7, 8, 9, 10, 11, 12};
        matrix2.fillMatrix(values2);
        ImmutableMatrix result = matrix1.multiply(matrix2);
        float[] expectedValues = {58, 64, 139, 154};
        ImmutableMatrix expectedMatrix = new ImmutableMatrix(2, 2);
        expectedMatrix.fillMatrix(expectedValues);
        assertEquals(expectedMatrix, result);
    }

    @Test
    public void testMultiplyMatricesInvalid() {
        ImmutableMatrix matrix1 = new ImmutableMatrix(2, 3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(4, 2);
        assertThrows(IllegalArgumentException.class, () -> matrix1.multiply(matrix2));
    }

    @Test
    public void testTransponseMatrix() {
        ImmutableMatrix originalMatrix = new ImmutableMatrix(3, 2);
        float[] originalValues = {1, 2, 3, 4, 5, 6};
        originalMatrix.fillMatrix(originalValues);
        ImmutableMatrix transposedMatrix = originalMatrix.transpose();
        ImmutableMatrix expectedTransposedMatrix = new ImmutableMatrix(2, 3);
        float[] expectedValues = {1, 3, 5, 2, 4, 6};
        expectedTransposedMatrix.fillMatrix(expectedValues);
        assertEquals(expectedTransposedMatrix, transposedMatrix);
    }

    @Test
    public void testVectorMatrix() {
        double[] vector = {1, 2, 3, 4};
        ImmutableMatrix diagonalMatrix = ImmutableMatrix.diagonalMatrix(vector);
        ImmutableMatrix expectedDiagonalMatrix = new ImmutableMatrix(4, 4);
        float[] expectedValues = {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4};
        expectedDiagonalMatrix.fillMatrix(expectedValues);
        assertEquals(expectedDiagonalMatrix, diagonalMatrix);
    }

    @Test
    public void testOneMatrix() {
        ImmutableMatrix identityMatrix = ImmutableMatrix.unitMatrix(3);
        ImmutableMatrix expectedIdentityMatrix = new ImmutableMatrix(3,3);
        float[] expectedValues = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        expectedIdentityMatrix.fillMatrix(expectedValues);
        assertEquals(expectedIdentityMatrix, identityMatrix);
    }

    @Test
    public void testRowsMatrix() {
        ImmutableMatrix result = ImmutableMatrix.randomRowMatrix(4, 0, 10);
        assertEquals(1, result.getRows());
        assertEquals(4, result.getCols());
    }

    @Test
    public void testColumnsMatrix() {
        ImmutableMatrix result = ImmutableMatrix.randomColumnMatrix(5, 0, 10);
        assertEquals(5, result.getRows());
        assertEquals(1, result.getCols());
    }
    @Test
    public void testInverse() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        float[] values = {1, 2, 3, 4, 5, 6, 7, 8, -9};
        matrix.fillMatrix(values);
        ImmutableMatrix inverseMatrix = ImmutableMatrix.inverse(matrix);
        ImmutableMatrix identityMatrix = matrix.multiply(inverseMatrix);
        ImmutableMatrix expectedIdentityMatrix = ImmutableMatrix.unitMatrix(3);
        assertEquals(expectedIdentityMatrix, identityMatrix);
    }

    @Test
    public void testInverseInvalid1() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 4);
        assertThrows(UnsupportedOperationException.class, () -> ImmutableMatrix.inverse(matrix));
    }

    @Test
    public void testInverseInvalid2() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        matrix.fillMatrix(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertThrows(UnsupportedOperationException.class, () -> ImmutableMatrix.inverse(matrix));
    }
    @Test
    public void testImmutable() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        matrix.fillMatrix(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertThrows(UnsupportedOperationException.class, () -> matrix.fillMatrix(new float[]{1, 2, 3, 4, -5, 6, 7, 8, -9}));
    }
}