package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        s.append("\n");
        System.out.print(s);
        toFileResult.append(s );
    }

    public static StringBuilder toFileResult = new StringBuilder();
    public static void printToFile(String filename) {
        try(FileWriter writer = new FileWriter("src/main/java/output-files/" + filename + ".txt", false))
        {
            // запись всей строки
            writer.write(toFileResult.toString());

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    public static Map<Double, Double> points = new HashMap<>();
    public static Map<Double, Double> pointsFirst = new HashMap<>();
    public static void fillMap(double a, double b, double[] eq, boolean trenc) {
        for (double temp = a - 2.0; temp < b + 2.0; temp += 0.1) {
            pointsFirst.put(temp, trenc ? solvePointSinus(eq, temp) : solvePoint(eq, temp));
        }
    }
}
