package Matrix;

import Matrix.Matrix;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Matrix matrix1 = new Matrix(3, 3);
        matrix1.fillMatrix(2.0); // 3.1 крок
        System.out.println("Matrix 1:");
        printMatrix(matrix1);

        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, -9}});
//        Matrix matrix3 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("\nMatrix 2:");
        printMatrix(matrix2);

        System.out.println("\nMatrix 3:");
        ImmutableMatrix immatrix1 = new ImmutableMatrix(matrix2);
        printImmutableMatrix(immatrix1);

        System.out.println("\nMatrix:");
        Matrix matrix_inverse = Matrix.inverse(matrix2);
        printMatrix(matrix_inverse);

        System.out.println("\nImMatrix:");
        ImmutableMatrix imMatrix_inverse = ImmutableMatrix.inverse(immatrix1);
        printImmutableMatrix(imMatrix_inverse);


    }

    private static void printMatrix(Matrix matrix){
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getCols(); j++){
                System.out.print(matrix.getElement(i, j) + " ");
            }
            System.out.println();
        }
    }

    private static void printImmutableMatrix(ImmutableMatrix immutableMatrix){
        for (int i = 0; i < immutableMatrix.getRows(); i++){
            for (int j = 0; j < immutableMatrix.getCols(); j++){
                System.out.print(immutableMatrix.getElement(i, j) + " ");
            }
            System.out.println();
        }
    }
}
