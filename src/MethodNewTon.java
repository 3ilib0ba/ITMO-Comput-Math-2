public class MethodNewTon {
    // x^2-2y=0
    // 3x-y^2+3=0
//    public static double[] dF_po_dx = {2.0, 0.0, 0.0};
//    public static double[] dF_po_dy = {0.0, 0.0, -2.0};
//    public static double[] dG_po_dx = {0.0, 0.0, 3.0};
//    public static double[] dG_po_dy = {0.0, -2.0, 0.0};
    // после перемножения якобиана матрицы дельт
    //2xdx-2dy=2y-x^2
    //3dx-2ydy=-3+y^2-3x

    // double[size = 3] -> index:
    // double[0] -> before x
    // double[1] -> before y
    // double[2] -> before free coef

    public static double X_0;
    public static double X_1;
    public static double Y_0;
    public static double Y_1;
    public static double deltaX;
    public static double deltaY;
    public static double[][] matrix_for_lab_1 = new double[2][3];

    public static double precision;

    public static void search_dx_dy() { //поиск корней по первой лабе

        matrix_for_lab_1[0][0] = 2*X_0;
        matrix_for_lab_1[0][1] = -2;
        matrix_for_lab_1[0][2] = 2*Y_0 - X_0*X_0;
        matrix_for_lab_1[1][0] = 3;
        matrix_for_lab_1[1][1] = -2*Y_0;
        matrix_for_lab_1[1][2] = -3 + Y_0*Y_0 - 3*X_0;

    }

    public static void initSolve(
            double precision,
            double a, // начальные значения
            double b  // начальные значения
    ) {
        double[] tmp = new double[2];
        X_0 = a;
        Y_0 = b;
        MethodNewTon.precision = precision;

        System.out.println("Начальные данные:\nX_0 = " + X_0 + "\nY_0 = " + Y_0);

        MatrixActionsFromLab1.SIZE = 2;
        int counter = 0;
        while (true) {
            counter++;

            search_dx_dy();
            MatrixActionsFromLab1.setMatrixAandB(matrix_for_lab_1);
            MatrixActionsFromLab1.initMatrixX1andX2();

            tmp = MatrixActionsFromLab1.startComputed();
            deltaX = tmp[0];
            deltaY = tmp[1];

            X_1 = X_0 + deltaX;
            Y_1 = Y_0 + deltaY;
            if (check() || counter > 20) {
                break;
            }


            X_0 = X_1;
            Y_0 = Y_1;
        }

        System.out.println("\n\nПрошло итераций = " + counter);
        System.out.println("X = " + String.format("%1$8.3f", X_1) + "; \nY = " + String.format("%1$8.3f;", Y_1));
        System.out.println("Вектор погрешностей:");
        System.out.println("for X: " + String.format("%1$8.3f", (X_1 - X_0)));
        System.out.println("for Y: " + String.format("%1$8.3f", (Y_1 - Y_0)));
    }

    public static boolean check() {
        return (Math.abs(X_1-X_0) <= precision || Math.abs(Y_1-Y_0) <= precision);
    }

}
