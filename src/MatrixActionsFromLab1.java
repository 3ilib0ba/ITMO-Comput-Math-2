
public class MatrixActionsFromLab1 {
    public static int SIZE = -1;
    public static boolean isPerestanovka = false;

    public static double[][] matrixA;
    public static double[][] matrixB;
    public static double[][] matrixX1;
    public static double[][] matrixX2;

    public static double epsilon;
    public static int M;

    public static void setMatrixAandB(double[][] allMatrix) {
        matrixA = new double[SIZE][SIZE];
        matrixB = new double[SIZE][1];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrixA[i][j] = allMatrix[i][j];
            }
            matrixB[i][0] = allMatrix[i][SIZE];
        }
        preReversingLines();
    }

    public static void reverseLines(int i, int j) {
        double[] tmp = matrixA[i];
        matrixA[i] = matrixA[j];
        matrixA[j] = tmp;

        double tmpB = matrixB[i][0];
        matrixB[i][0] = matrixB[j][0];
        matrixB[j][0] = tmpB;
    }

    public static int searchLineWithNorma(int numberX) {
        int currentX = numberX;
        double currentCoeff;
        double sumOfOther;

        for (int i = numberX; i < SIZE; i++) {
            currentCoeff = Math.abs(matrixA[i][currentX]);
            sumOfOther = 0;
            for (int j = 0; j < SIZE; j++) {
                sumOfOther += Math.abs(matrixA[i][j]);
            }
            sumOfOther -= currentCoeff;
            if (currentCoeff >= sumOfOther) {
                if (currentCoeff > sumOfOther) {
                    isPerestanovka = true;
                }
                reverseLines(numberX, i);
                return i;
            }
        }
        System.out.println("Не получается переставить строчки так чтобы выполнилось диагональное преобладание");
        System.exit(4);
        return -1;
    }

    public static void preReversingLines() {
        for (int i = 0; i < SIZE; i++) {
            searchLineWithNorma(i);
        }
        if (isPerestanovka) {
        } else {
            System.out.println("НЕ ВЫПОЛНЕНО УСЛОВИЕ О ТОМ ЧТОБЫ ПРИ ЗАМЕНАХ СХОДИЛИСЬ ИТЕРАЦИИ");
            System.exit(5);
        }
    }

    public static void initMatrixX1andX2() {
        // first init with free's coefficients
        matrixX2 = new double[SIZE][1];
        matrixX1 = new double[SIZE][1];
        for (int i = 0; i < SIZE; i++) {
            matrixX2[i][0] = matrixB[i][0] / matrixA[i][i];
        }
    }

    public static void iteration() {
        for (int i = 0; i < SIZE; i++) {
            matrixX1[i][0] = matrixX2[i][0];
        }

        double sumOther;
        for (int i = 0; i < SIZE; i++) {
            sumOther = 0;

            for (int j = 0; j < SIZE; j++) {
                if (j < i) {
                    sumOther += matrixA[i][j] * matrixX2[j][0] / matrixA[i][i];
                } else if (j == i) {
                } else {
                    sumOther += matrixA[i][j] * matrixX1[j][0] / matrixA[i][i];
                }
            }

            matrixX2[i][0] = matrixB[i][0] / matrixA[i][i] - sumOther;
        }
    }

    public static boolean checkAllNewX() {
        for (int i = 0; i < SIZE; i++) {
            if (Math.abs(matrixX2[i][0] - matrixX1[i][0]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    public static double[] startComputed() {
        int count = 0;

        while (true) {
            iteration();
            count++;
            if (checkAllNewX() || count >= M) {
                break;
            }
        }

        System.out.println("\nПосле очередной итерации");
//        for (int i = 0; i < SIZE; i++) {
//            System.out.println("deltaX" + (i + 1) + " = " + matrixX2[i][0]);
//        }
        // hard code to 2 size matrix to LAB_2 FIXME
        System.out.println("deltaX = " + String.format("%1$8.3f", matrixX2[0][0]));
        System.out.println("deltaY = " + String.format("%1$8.3f", matrixX2[1][0]));


        return new double[] {matrixX2[0][0], matrixX2[1][0]};
    }
}
