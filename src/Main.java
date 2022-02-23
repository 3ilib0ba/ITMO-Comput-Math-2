import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import org.jfree.*;

public class Main {
    public static double[] eq;
    public static int N;

    private static Scanner getInfo() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to enter params from file or console? (Write a file or console)");
        String str = scanner.nextLine();
        boolean isFile = false;
        while (true) {
            if (str.equalsIgnoreCase("file")) {
                isFile = true;
                break;
            }
            if (str.equalsIgnoreCase("console")) {
                break;
            }
            System.out.println("Incorrect input. Please repeat");
            str = scanner.nextLine();
        }

        if (isFile) {
            System.out.println("Enter path to file");
            File file = new File(scanner.nextLine());
            scanner = new Scanner(file);
        } else {
            System.out.println("Enter an accuracy, A and B");
        }
        return scanner;
    }

    public static void main(String[] args) {
        boolean transcendent = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose expression:\n" +
                "1)3x^5+x^4-0,4x^3+2,8x^2+10,4x-8\n" +
                "2)x^3+2,28x^2-1,934x-3,907\n" +
                "3)x^2-x-sin(x)\n" +
                "4)x^3-x+4\n" +
                "5)x1^2-2x2=0\n  3x1+3-x2^2=0\n" +
                "Input here:");

        try {
            N = scanner.nextInt();
            switch (N) {
                case 1:
                    eq = new double[6];
                    eq[0] = -8;
                    eq[1] = 10.4;
                    eq[2] = 2.8;
                    eq[3] = -0.4;
                    eq[4] = 1;
                    eq[5] = 3;
                    break;
                case 2:
                    eq = new double[4];
                    eq[0] = -3.907;
                    eq[1] = -1.934;
                    eq[2] = 2.28;
                    eq[3] = 1;
                    break;
                case 3:
                    eq = new double[3];
                    eq[0] = -1;
                    eq[1] = -1;
                    eq[2] = 1;
                    transcendent = true;
                    break;
                case 4:
                    eq = new double[4];
                    eq[0] = 4;
                    eq[1] = -1;
                    eq[2] = 0;
                    eq[3] = 1;
                    break;
                case 5:
                    System.out.println("Enter an accuracy, A and B");
                    double epsLocal = scanner.nextDouble();
                    double aLocal = scanner.nextDouble();
                    double bLocal = scanner.nextDouble();
                    MethodNewTon.initSolve(epsLocal, aLocal, bLocal);
                    System.exit(15);
                    break;
                default:
                    System.out.println("incorrect input");
                    System.exit(12);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error with inputting. Exiting...");
            System.exit(13);
        }

        try {
            scanner = getInfo();
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found! Read from console");
            scanner = new Scanner(System.in);
        }

        double eps = scanner.nextDouble();
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();


        System.out.println("Choose method of solving:\n1)iteration(iter)\n2)half-dividing(half)\n3)secant(secant)");
        scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int i;
        while (true) {
            if (str.equalsIgnoreCase("iter")) {
                i = 1;
                break;
            }
            if (str.equalsIgnoreCase("half")) {
                i = 2;
                break;
            }
            if (str.equalsIgnoreCase("secant")) {
                i = 3;
                break;
            }
            System.out.println("Incorrect input. Please repeat");
            str = scanner.nextLine();
        }
        switch (i) {
            case 1:
                MethodSimpleIterations.initSolve(eq, a, b, eps, transcendent);
                break;
            case 2:
                MethodOfPartDiv method = new MethodOfPartDiv(eq, eps, transcendent);
                method.findRightSolution(a, b);
                break;
            case 3:
                MethodSecant.initSolve(eq, a, b, eps, transcendent);
                break;
        }
    }
}
