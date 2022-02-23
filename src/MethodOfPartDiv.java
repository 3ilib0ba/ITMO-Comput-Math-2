import static tools.MathAndPrintTools.*;

public class MethodOfPartDiv {

    private final double[] equation;
    private final int n;
    private final double accuracy;

    public boolean transcendent;

    public MethodOfPartDiv(double[] equation, double accuracy, boolean transcendent) {
        this.equation = equation;
        this.accuracy = accuracy;
        n = equation.length;
        this.transcendent = transcendent;
    }

    public double execute(double x) {
        double ans = 0;
        for (int i = 0; i < n; i++) {
            ans += transcendent ? solvePointSinus(equation, x): solvePoint(equation, x);
        }
        return ans;
    }

    public void findRightSolution(double a, double b) {
        // print header of table
        System.out.println("   №|\t\t a| \t    b|\t\t   x| \t   f(a)| \t  f(b)|\t\t f(x)| \t   |a-b||");
        System.out.println("+---+---------+----------+----------+----------+----------+----------+----------+");

        double[] tmp = new double[7];
        tmp[0] = a;
        tmp[1] = b;
        calc(tmp);
        int cnt = 0;
        while (tmp[6] > accuracy && Math.abs(tmp[5]) > accuracy) {
            print(tmp, cnt);
            cnt++;
            if (tmp[3] * tmp[5] > 0) {
                tmp[0] = tmp[2];
            } else {
                tmp[1] = tmp[2];
            }
            calc(tmp);
        }
        print(tmp, cnt);

        // print results
        System.out.println("Count iterations: " + cnt);
        System.out.println("Final x = " + tmp[2]);
    }

    private void calc(double[] tmp) {
        tmp[2] = (tmp[0] + tmp[1]) / 2;
        tmp[3] = execute(tmp[0]);
        tmp[4] = execute(tmp[1]);
        tmp[5] = execute(tmp[2]);
        tmp[6] = Math.abs(tmp[0] - tmp[1]);
    }
}
