package language.matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {

    public static <T,V> void fillMatrix(T[][] matrix, T value) {
        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, value));
    }

    public static <T,V> void fillMatrixRow(T[][] matrix, Integer rowIdx,  T value) {
        Arrays.fill(matrix[rowIdx], value);
    }

    public static <T,V> void fillMatrixCol(T[][] matrix, Integer colIdx,  T value) {
        Arrays.stream(matrix).forEach(row -> row[colIdx] = value);
    }

    public static <T> void printMatrix(T[][] matrix) {
        System.out.println();
        Arrays.stream(matrix)
                .forEach( (T[] row ) -> {
                    System.out.print("[");
                    System.out.print(Arrays.stream(row).map(e -> String.format("%1$5s", e)).collect(Collectors.joining(" ")));
                    System.out.println("]");
                });
        System.out.println();
    }
}
