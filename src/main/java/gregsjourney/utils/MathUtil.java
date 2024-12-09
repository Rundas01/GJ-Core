package gregsjourney.utils;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {
    public static Double[] solveZeroSystem(Double[][] A){
        Double[] y = new Double[A[0].length];
        for (int i = 0; i < A[0].length; i++) {
            y[i] = 0.0;
        }
        return solveSystem(A, y);
    }

    public static Double[] solveSystem(Double[][] A, Double[] y) {
        int rows = A.length;
        int cols = A[0].length;
        double epsilon = 1e-10;

        if (rows == cols) {
            if (isSingular(A, epsilon)) {
                Double[][] extendedA = new Double[rows + 1][cols];
                Double[] extendedY = new Double[rows + 1];

                for (int i = 0; i < rows; i++) {
                    System.arraycopy(A[i], 0, extendedA[i], 0, cols);
                    extendedY[i] = y[i];
                }

                extendedA[rows][0] = 1.0;
                for (int j = 1; j < cols; j++) {
                    extendedA[rows][j] = 0.0;
                }
                extendedY[rows] = 1.0;

                A = extendedA;
                y = extendedY;
                rows++;
            }
        } else {
            Double[][] extendedA = new Double[cols][cols];
            Double[] extendedY = new Double[cols];

            for (int i = 0; i < rows; i++) {
                System.arraycopy(A[i], 0, extendedA[i], 0, cols);
                extendedY[i] = y[i];
            }

            for (int i = rows; i < cols; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j == i - rows) {
                        extendedA[i][j] = 1.0;
                    } else {
                        extendedA[i][j] = 0.0;
                    }
                }
                extendedY[i] = 1.0;
            }

            A = extendedA;
            y = extendedY;
            rows = cols;
        }

        Double[][] augmentedMatrix = new Double[rows][cols + 1];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(A[i], 0, augmentedMatrix[i], 0, cols);
            augmentedMatrix[i][cols] = y[i];
        }

        for (int i = 0; i < cols; i++) {
            int maxRow = i;
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(augmentedMatrix[k][i]) > Math.abs(augmentedMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            Double[] temp = augmentedMatrix[maxRow];
            augmentedMatrix[maxRow] = augmentedMatrix[i];
            augmentedMatrix[i] = temp;

            if (Math.abs(augmentedMatrix[i][i]) < epsilon) {
                augmentedMatrix[i][i] = epsilon;
            }

            for (int k = i + 1; k < rows; k++) {
                double factor = augmentedMatrix[k][i] / augmentedMatrix[i][i];
                augmentedMatrix[k][i] = 0.0;
                for (int j = i + 1; j <= cols; j++) {
                    augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                }
            }
        }

        Double[] x = new Double[cols];
        for (int i = cols - 1; i >= 0; i--) {
            x[i] = augmentedMatrix[i][cols] / augmentedMatrix[i][i];
            for (int j = i - 1; j >= 0; j--) {
                augmentedMatrix[j][cols] -= augmentedMatrix[j][i] * x[i];
            }
        }

        return x;
    }

    public static boolean isSingular(Double[][] matrix, double epsilon) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            Double[] temp = matrix[maxRow];
            matrix[maxRow] = matrix[i];
            matrix[i] = temp;

            if (Math.abs(matrix[i][i]) < epsilon) {
                return true;
            }

            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                matrix[k][i] = 0.0;
                for (int j = i + 1; j < n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
        }
        return false;
    }

    public static void printMatrix(Double[][] matrix) {
        for (Double[] doubles : matrix) {
            for (Double aDouble : doubles) {
                GJLog.logger.debug(aDouble + "\t");
            }
        }
    }

    static int findFactor(List<Double> numbers) {
        int factor = 1;
        double epsilon = 1e-5;

        while (true) {
            boolean allIntegers = true;

            for (double number : numbers) {
                double scaledNumber = number * factor;

                if (Math.abs(scaledNumber - Math.round(scaledNumber)) >= epsilon) {
                    allIntegers = false;
                    break;
                }
            }

            if (allIntegers) {
                return factor;
            }

            factor++;
        }
    }

    static List<Integer> scaleAndRound(List<Double> list, int factor) {
        List<Integer> returnList = new ArrayList<>();
        double epsilon = 1e-5;

        for (double x : list) {
            double scaledValue = x * factor;

            int integerPart = (int) scaledValue;
            double decimalPart = scaledValue - integerPart;

            if (Math.abs(decimalPart) < epsilon) {
                returnList.add(integerPart);
            } else if (Math.abs(decimalPart - 1.0) < epsilon) {
                returnList.add(integerPart + 1);
            } else {
                returnList.add((int) Math.round(scaledValue));
            }
        }

        return returnList;
    }

    public static List<List<Integer>> findCombinations(long x, int y, long z) {
        List<List<Integer>> result = new ArrayList<>();
        if (y * z > x) {
            return result;
        }
        backtrack(x, y, new ArrayList<>(), result, z);
        return result;
    }

    private static void backtrack(long target, int numbersLeft, List<Integer> current, List<List<Integer>> result, long start) {
        if (numbersLeft == 0) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (long i = start; i <= target; i++) {
            current.add((int) i);
            backtrack(target - i, numbersLeft - 1, current, result, i);
            current.remove(current.size() - 1);
        }
    }
}
