package something;

public class Classification {

	private double[] weights;
	
	public Classification(int numInputs) {
		
		weights = new double[numInputs];
		for (int y = 0; y < weights.length; y++) {
			
			weights[y] = Math.random() * 3 - 1;
			
		}
		
	}
	
	public double guess(double[] inputs) {
		
		double guess = 0;
		for (int y = 0; y < weights.length; y++) {
				
			guess += inputs[y] * weights[y];
				
		}
		
		guess = Math.tanh(guess);
		
		return guess;
		
	}
	
	public void train(double[][] inputs, double[] outputs) {
		
		for (int y = 0; y < inputs.length; y++) {
			
			double guess = guess(inputs[y]);
			
			double[] change = new double[inputs[y].length];
			for (int x = 0; x < inputs[y].length; x++) { 
			
				change[x] = (outputs[y] - guess) * inputs[y][x] * 0.1;
			
			}
			
			for (int x = 0; x < weights.length; x++) {
				
				weights[x] += change[x];
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		
		Classification c = new Classification(2);
		System.out.println(c.guess(new double[] {100, 50}));
		
		for (int y = 0; y < 10000; y++) {
			
			c.train(new double[][] {{10, 20}, {-50, -20}, {-10, 50}, {20, -100}}, new double[] {-1, 1, 1, -1});
		
		}
		
		System.out.println(c.guess(new double[] {-1, 0}));
		
	}
	
}
