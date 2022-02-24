import tools.MathAndPrintTools;

import static tools.MathAndPrintTools.*;

public class MethodSimpleIterations {
    public static double[] expressionLocal;
    public static double[] derivativeOfExpression;
    public static double[] expressionPhiFromX;

    public static double intervalA;
    public static double intervalB;
    public static double Xi_0;
    public static double Xi_1;

    public static double epsilonLocal;
    public static double lambda;

    public static boolean transcendentLocal;

    public static void initLambda() {
        double derivative_a = solvePoint(derivativeOfExpression, intervalA);
        double derivative_b = solvePoint(derivativeOfExpression, intervalB);

        // solving lambda and starting X
        lambda = -1 / Math.max(Math.abs(derivative_a), Math.abs(derivative_b));
        Xi_0 = Math.abs(derivative_a) > Math.abs(derivative_b) ? intervalA : intervalB;

        // print lambda
        System.out.println("Lambda has been found and equals: " + String.format("%1$8.4f", lambda));
    }

    public static void initExpressionPhi() {
        // phi from x expression it's x = phiFromX(x) -> get right part
        expressionPhiFromX = new double[expressionLocal.length];

        for (int i = 0; i < expressionLocal.length; i++) {
            expressionPhiFromX[i] = expressionLocal[i] * lambda;
        }
        expressionPhiFromX[1] += 1;

        // print phi(x) expression
        System.out.print("X =");
        for (int i = expressionPhiFromX.length - 1; i >= 0; i--) {
            System.out.print(String.format("%1$+8.4f", expressionPhiFromX[i]) + (i != 0 ? "*x" + (i == 1 ? "" : "^" + i) : ""));
        }
        System.out.println("\n");
    }

    public static void solveExpression() {
        // print header of table
        String header = "   №|\t   X_0| \t  X_1|  phi(X_1)|    f(X_1)| |X_1-X_0||\n" + "+---+---------+----------+----------+----------+----------+\n";
        System.out.println(header);
        toFileResult.append(header);

        Xi_1 = solvePoint(expressionPhiFromX, Xi_0);
        int counter = 0;
        double[] tmp = new double[5];
        while (Math.abs(Xi_1 - Xi_0) >= epsilonLocal) {
            // print in table
            tmp[0] = Xi_0;
            tmp[1] = Xi_1;
            tmp[2] = solvePoint(expressionPhiFromX, Xi_1);
            tmp[3] = solvePoint(expressionLocal, Xi_1);
            tmp[4] = Math.abs(Xi_1 - Xi_0);
            print(tmp, counter);

            // go to the next iteration
            counter++;
            Xi_0 = Xi_1;
            Xi_1 = tmp[2];
        }
        tmp[0] = Xi_0;
        tmp[1] = Xi_1;
        tmp[2] = solvePoint(expressionPhiFromX, Xi_1);
        tmp[3] = solvePoint(expressionLocal, Xi_1);
        tmp[4] = Math.abs(Xi_1 - Xi_0);
        print(tmp, counter);

        String result = "\nFinish X = " + String.format("%1$8.6f", Xi_1);
        System.out.println(result);
        toFileResult.append(result);
    }

    public static void initSolve(
            double[] expr,
            double a,
            double b,
            double eps,
            boolean transcendent
    ) {
        expressionLocal = expr;
        intervalA = a;
        intervalB = b;
        epsilonLocal = eps;
        transcendentLocal = transcendent;
        derivativeOfExpression = MathAndPrintTools.solveDerivativePolinom(expressionLocal);
        initLambda();
        initExpressionPhi();

        for (double temp = a - 2.0; temp < b + 2.0; temp += 0.1) {
            points.put(temp, transcendent ? solvePointSinus(expressionLocal, temp) : solvePoint(expressionLocal, temp));
        }

        solveExpression();
        if (Xi_1 > intervalB || Xi_1 < intervalA) {
            System.out.println("Method doesn't find solve from inputting interval");
            System.exit(11);
        }

    }

}
