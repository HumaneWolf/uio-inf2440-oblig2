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

    public MatrixMulti(int run) {
        this.run = run;

        // Generate the matrix
        double nums[][] = new double[n][n];
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = rng.nextDouble();
            }
        }

        // Do tests
    }

    private void seq(double[][] mat) {
        //
    }

    private void par(double[][] mat) {
        //
    }
}
