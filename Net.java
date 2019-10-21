public class Net {

    private double[][] input;
    private double[][] output;
    private double[] weights;
    
    public Net(double[][] i, double[][] o, double[] w) {
    
        input = i;
        output = o;
        weights = w;
    
    }
    
    public void train(int times) {
    
        for (int i = 0; i < times; i++) {
        
            double[] guess = getGuess();
            double[] error = getError(guess);
            double[] dSigGuess = getDSigmoidError(guess, error);
            double[] change = Math2.dot(Math2.transpose(input), dSigGuess);
            
            for (int l = 0; l < weights.length; l++) {
            
                weights[l] += change[l];
            
            }
        
        }
    
    }
    
    public double[] getGuess() {
    
        double[] guess = Math2.dot(input, weights);
        for (int l = 0; l < guess.length; l++) {
        
            guess[l] = Math2.sigmoid(guess[l]);
        
        }
        
        return guess;
    
    }
    
    public double[] getError(double[] guess) {
    
        double[] error = new double[guess.length];
        for (int l = 0; l < error.length; l++) {
        
            error[l] = output[l][0] - guess[l];
        
        }
        
        return error;
    
    }
    
    public double[] getDSigmoidError(double[] guess, double[] error) {
    
        double[] dSigGuess = new double[guess.length];
        for (int k = 0; k < dSigGuess.length; k++) {
        
            dSigGuess[k] = Math2.dSigmoid(guess[k]) * error[k];
        
        }
        
        return dSigGuess;
    
    }
    
    public double[] testInputs(double[][] input) {
    
        double[] guess = Math2.dot(input, weights);
        for (int i = 0; i < guess.length; i++) {
        
            guess[i] = Math2.sigmoid(guess[i]);
        
        }
        
        return guess;
    
    }

    public double[] getWeights() {
    
        return weights;
    
    }

    public static void main(String[] args) {
    
        double[][] input = {{0, 0, 1},
                            {1, 1, 1},
                            {1, 0, 1},
                            {0, 1, 1},
                            {0, 0, 0},
                            {1, 0, 0}};
                            
        double[][] output = {{0, 1, 0, 1, 0, 0}};
        output = Math2.transpose(output);
    
        double[] weights = {(Math.random() * 2) - 1, 
                            (Math.random() * 2) - 1, 
                            (Math.random() * 2) - 1};
        
        Net net = new Net(input, output, weights);
        net.train(60000);  
        
        double[] test = net.testInputs(new double[][] {{1, 1, 0}});
        System.out.println(test[0]);  
        
        for (double w : net.getWeights()) {
        
            System.out.println(w);
        
        }
        
    }

}
