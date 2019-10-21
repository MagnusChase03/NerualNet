package NerualNet;

public class NerualNet {

    private double[][] input;
    private double[][] output;
    private double[] weights;

    public static double sigmoid(double x) {

        return 1 / (1 + Math.exp(-x));

    }

    public static double sigmoidDeriv(double x) {

        return x * (1 - x);

    }

    public static double[][] transpose(double[][] a1) {

        double[][] transposed = new double[a1[0].length][a1.length];
        for (int y = 0; y < a1.length; y++) {

            for (int x = 0; x < a1[y].length; x++) {

                transposed[x][y] = a1[y][x];

            }

        }

        return transposed;

    }

    public static double[] dotProduct(double[][] a1, double[] a2) {

        double[] product = new double[a1.length];

        for (int y = 0; y < a1.length; y++) {

            double sum = 0;
            for (int x = 0; x < a1[y].length; x++) {

                sum += a1[y][x] * a2[x];

            }

            product[y] = sum;

        }

        return product;

    }

    public NerualNet(double[][] i, double[][] o, double[] w) {

        input = i;
        output = o;
        weights = w;

    }

    public void train(int amount) {

        for (int i = 0; i < amount; i++) {

            double[] guess = dotProduct(input, weights);
            for (int l = 0; l < guess.length; l++) {

                guess[l] = sigmoid(guess[l]);

            }

            double[] error = new double[guess.length];
            for (int l = 0; l < guess.length; l++) {

                error[l] = output[l][0] - guess[l];

            }

            double[] sigmoidDerivTimesError = new double[guess.length];
            for (int l = 0; l < guess.length; l++) {

                sigmoidDerivTimesError[l] = sigmoidDeriv(guess[l]) * error[l];

            }

            double[] change = dotProduct(transpose(input), sigmoidDerivTimesError);
            for (int l = 0; l < change.length; l++) {

                weights[l] += change[l];

            }

        }

    }

    public void test(double[][] input) {

        double[] guess = dotProduct(input, weights);
        for (int l = 0; l < guess.length; l++) {

            guess[l] = sigmoid(guess[l]);

        }

        for (double g : guess) {

            System.out.println(g);

        }

    }

    public static void main(String[] args) {

        double[][] input = {{1, 0, 1},
                            {0, 0, 1},
                            {0, 1, 1},
                            {1, 1, 0}};

        double[][] output = {{1}, {0}, {0}, {1}};

        double[] wieghts = new double[input[0].length];
        for (int i = 0; i < wieghts.length; i++) {

            wieghts[i] = (Math.random() * 2) - 1;

        }

        NerualNet net = new NerualNet(input, output, wieghts);
        net.train(1000000);
        net.test(new double[][] {{1, 0, 0}});

    }

}
