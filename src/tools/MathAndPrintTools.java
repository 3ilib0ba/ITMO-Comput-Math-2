package tools;

public class MathAndPrintTools {
    public static double solvePointSinus(double[] expression, double point) {
        double solve = 0;

        solve += point*point-point-Math.sin(point);

        return solve;
    }

    public static double solvePoint(double[] expression, double point) {
        double solve = 0;

        for (int size = expression.length - 1; size >= 0; size--) {
            solve += expression[size]*Math.pow(point, size);
        }

        return solve;
    }

    public static double[] solveDerivativePolinom(double[] expression) {
        int size = expression.length;
        double[] derivative = new double[size - 1];

        for (int i = 1; i < size; i++) {
            derivative[i-1] = expression[i] * (i);
        }

        return derivative;
    }

    public static void print(double[] a, int count) {
        StringBuilder s = new StringBuilder(String.format(" %1$03d|", count));
        for (int i = 0; i < a.length; i++) {
            s.append(String.format("%1$8.3f | ", a[i]));
        }
        System.out.println(s);
    }

}
