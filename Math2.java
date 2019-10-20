public class Math2 {

    public static double sigmoid(double num) {
    
        return 1 / (1 + Math.exp(-num));
    
    }
    
    public static double dSigmoid(double num) {
    
        return num * (1 - num);
    
    }
    
    public static double[] dot(double[][] first, double[] second) {
    
        if (first[0].length != second.length) {
        
            return null;
        
        }
        
        double[] results = new double[first.length];
        for (int y = 0; y < first.length; y++) {
            
            double total = 0;
            for (int x = 0; x < first[y].length; x++) {
            
                total +=  first[y][x] * second[x];   
            
            }
            
            results[y] = total;
        
        }
        
        return results;
    
    }
    
    public static double[][] transpose(double[][] array) {
    
        double[][] result = new double[array[0].length][array.length];
        for (int y = 0; y < array.length; y++) {
        
            for (int x = 0; x < array[y].length; x++) {
            
                result[x][y] = array[y][x];
            
            }
        
        }
        
        return result;
    
    }

}
