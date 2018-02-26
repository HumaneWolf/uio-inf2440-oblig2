import java.util.Arrays;
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
     * @param args Program arguments. index 0 = width end height of matrix, int.
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

        Arrays.sort(seqTiming);
        Arrays.sort(parTiming);

        System.out.printf("Sequential median  : %.3f\n", seqTiming[medianIndex]);
        System.out.printf(
                "Parallel median    : %.3f    Speedup from sequential: %.3f\n",
                parTiming[medianIndex], (seqTiming[medianIndex] / parTiming[medianIndex])
        );
        System.out.println("\nn = " + n);
    }

    /**
     * The constructor, which initiates the array, and then does the tests.
     * @param run The run count, dictating where to store the runtimes.
     */
    public MatrixMulti(int run) {
        this.run = run;
        long startTime;

        // Generate the matrix
        Random rng = new Random();
        double aSeq[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aSeq[i][j] = rng.nextDouble();
            }
        }
        double bSeq[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bSeq[i][j] = rng.nextDouble();
            }
        }

        // Clone matrix.
        double[][] aPar = aSeq.clone();
        double[][] bPar = bSeq.clone();

        // Do sequential tests
        System.out.println("Starting sequential");
        startTime = System.nanoTime();
        double[][] cSeq = new double[n][n];
        seq(aSeq, bSeq, cSeq);
        seqTiming[run] = (System.nanoTime() - startTime) / 1000000.0;
        System.out.println("Sequential time: " + seqTiming[run] + "ms.");

        // Do parallel tests
        System.out.println("Starting Parallel");
        startTime = System.nanoTime();
        double[][] cPar = new double[n][n];
        par(aPar, bPar, cPar);
        parTiming[run] = (System.nanoTime() - startTime) / 1000000.0;
        System.out.println("Parallel time: " + parTiming[run] + "ms.");

        // Check if it is correct.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cSeq[i][j] != cPar[i][j]) {
                    //System.out.println("MISMATCH: [" + i + "][" + j + "] is " + cSeq[i][j] + " and " + cPar[i][j]);
                }
            }
        }
    }

    /**
     * Do the work sequentially.
     * @param a Matrix A.
     * @param b Matrix B.
     * @param c The matrix to store the result in.
     */
    private void seq(double[][] a, double[][] b, double[][] c) {
        transpose(b); // Transpose B.

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                calculate(a, b, c, col, row);
            }
        }
    }

    /**
     * Do the work in parallel.
     * @param a Matrix A.
     * @param b Matrix B.
     * @param c The matrix to store the result in.
     */
    private void par(double[][] a, double[][] b, double[][] c) {
        int cores = Runtime.getRuntime().availableProcessors();
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
        double result = 0;
        for (int k = 0; k < a.length; k++) {
            result += a[row][k] * bTrans[col][k];
        }
        c[row][col] = result;
    }

    /**
     * Transpose the matrix b.
     * @param b The matrix to transpose.
     */
    public static void transpose(double[][] b) { // Version from the lecture.
        double temp;
        int aRows = b.length;
        int aColumns = b[0].length;

        for (int i = 0; i < aRows; i++) {
            for (int j = i+1; j < aColumns; j++) {
                temp = b[i][j];
                b[i][j] = b[j][i];
                b[j][i] = temp;
            }
        }
    }
}
