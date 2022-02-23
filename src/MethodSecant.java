import static tools.MathAndPrintTools.*;

public class MethodSecant {
    public static double[] exprLocal;
    public static double intervalA;
    public static double intervalB;
    public static double epsilonLocal;

    public static double X_i0;
    public static double X_i1;
    public static double X_i2;

    public static boolean transcendentLocal = false;

    public static void solveAll() {
        System.out.println("   №|\t   X_0| \t  X_1|       X_2|    f(X_2)| |X_2-X_1||");
        System.out.println("+---+---------+----------+----------+----------+----------+");

        int counter = 1;
        double[] tmp = new double[5];
        while (Math.abs(X_i2 - X_i1) > epsilonLocal) {
            if (!transcendentLocal) {
                X_i2 = X_i1 - ((X_i1 - X_i0) / (solvePoint(exprLocal, X_i1) - solvePoint(exprLocal, X_i0))) * solvePoint(exprLocal, X_i1);
                tmp[3] = solvePoint(exprLocal, X_i2);
            } else {
                X_i2 = X_i1 - ((X_i1 - X_i0) / (solvePointSinus(exprLocal, X_i1) - solvePointSinus(exprLocal, X_i0))) * solvePointSinus(exprLocal, X_i1);
                tmp[3] = solvePointSinus(exprLocal, X_i2);
            }

            tmp[0] = X_i0;
            tmp[1] = X_i1;
            tmp[2] = X_i2;
            tmp[4] = Math.abs(X_i2 - X_i1);
            print(tmp, counter);
            X_i0 = X_i1;
            X_i1 = X_i2;
            counter++;
        }
        System.out.print("\n");
    }

    public static boolean checkStartInterval() {
        if (transcendentLocal) {
            if (solvePointSinus(exprLocal, intervalA) * solvePointSinus(exprLocal, intervalB) < 0) {
                return true;
            }
        } else {
            if (solvePoint(exprLocal, intervalA) * solvePoint(exprLocal, intervalB) < 0) {
                return true;
            }
        }
        return false;
    }

    public static void initSolve(
            double[] expr,
            double a,
            double b,
            double eps,
            boolean transcendent
    ) {
        exprLocal = expr;
        intervalA = a;
        intervalB = b;
        epsilonLocal = eps;
        transcendentLocal = transcendent;

        if (!checkStartInterval()) {
            System.out.println("Incorrect input");
            System.exit(10);
        }

        X_i0 = intervalA;
        X_i1 = intervalB;

        solveAll();
        System.out.println("Point = " + X_i2 + "\nValue = " + (transcendentLocal ? solvePointSinus(exprLocal, X_i2) : solvePoint(exprLocal, X_i2)));
    }

}
