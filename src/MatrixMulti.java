import java.util.Random;

public class MatrixMulti {
    private static int n;

    private static final int runs = 7;
    private static final int medianIndex = 4;
    private static double[] seqTiming = new double[runs];
    private static double[] parTiming = new double[runs];

    private int run;

    /**
     * Main.
     * @param args Program arguements. index 0 = width end height of matrix, int.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java MatrixMulti n");
            System.out.println("n: The width and height of the matrix to generate.");
            return;
        }
        n = Integer.parseInt(args[0]);

        for (int i = 0; i < runs; i++) {
            new MatrixMulti(i);
        }
    }

    /**
     * The constructor, which initiates the array, and then does the tests.
     * @param run The run count, dictating where to store the runtimes.
     */
    public MatrixMulti(int run) {
        this.run = run;

        // Generate the matrix
        Random rng = new Random();
        double a[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = rng.nextDouble();
            }
        }
        double b[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = rng.nextDouble();
            }
        }

        // Do tests
    }

    /**
     * Do the work sequentially.
     * @param a Matrix A.
     * @param b Matrix B.
     * @return The resulting matrix.
     */
    private double[][] seq(double[][] a, double[][] b) {
        return null;
    }

    /**
     * Do the work in parallel.
     * @param a Matrix A.
     * @param b Matrix B.
     * @return The resulting matrix.
     */
    private double[][] par(double[][] a, double[][] b) {
        return null;
    }

    /**
     * Calculate the correct value for a single square in the matrix.
     * @param a Input matrix A.
     * @param bTrans Input matrix B, already transposed.
     * @param c Output matrix C, the answer.
     * @param col The column for the square we should work on.
     * @param row The row for the square we should work on.
     */
    private void calculate(double[][] a, double bTrans[][], double[][] c, int col, int row) {
        //
    }
}
