public class Net {

    public static void main(String[] args) {
    
        double[][] input = {{0, 0, 1},
                            {1, 1, 1},
                            {1, 0, 1},
                            {0, 1, 1}};
                            
        double[][] output = {{0, 1, 1, 0}};
        output = Math2.transpose(output);
    
        double[] weights = {(Math.random() * 2) - 1, 
                            (Math.random() * 2) - 1, 
                            (Math.random() * 2) - 1};
        
        for (int i = 0; i < 10000; i++) {
        
            double[] guess = Math2.dot(input, weights);
            for (int l = 0; l < guess.length; l++) {
            
                guess[l] = Math2.sigmoid(guess[l]);
            
            }
            
            double[] error = new double[guess.length];
            for (int l = 0; l < error.length; l++) {
            
                error[l] = output[l][0] - guess[l];
            
            }
            
            double[] change = new double[error.length];
            
            double[] dSigGuess = new double[guess.length];
            for (int k = 0; k < dSigGuess.length; k++) {
            
                dSigGuess[k] = Math2.dSigmoid(guess[k]) * error[k];
            
            }
            
            change = Math2.dot(Math2.transpose(input), dSigGuess);
            
            for (int l = 0; l < weights.length; l++) {
            
                weights[l] += change[l];
            
            }
        
        }    
        
        double[] guess = Math2.dot(new double[][] {{1, 0, 0}}, weights);
        for (int l = 0; l < guess.length; l++) {
        
            guess[l] = Math2.sigmoid(guess[l]);
        
        }
        
        System.out.println(guess[0]);
        
    }

}
