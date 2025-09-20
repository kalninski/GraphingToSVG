package statistics;

public class Statistics {
	
	public static double factorial(double a) {
		double result = 1;
		if(a == 0) {

			return 1;
		}
		for(int i = 1; i <= a; i++) {
			result *= i;
			
		}
//		System.out.println(result);
		return result;
	} 
	
	public static double erf(double variable) {
		double result = 0;
		double normalize = 2 / (Math.sqrt(Math.PI));
		for(int n = 0; n <= 30; n++) {
			double sign = (n % 2 == 0) ? 1 : (-1);
			double fact =(double) factorial(n);
			double denominator1 = 2 * n + 1; 
			double numerator = Math.pow(variable, denominator1);
			double denominator = fact * denominator1;
			double term = sign * numerator / denominator;
			result += term;
			
		}
		result = result  * normalize;
//		System.out.println(result);
		return result;
	}
	/**
	 * Calculate CDF from negative infinity to x
	 * @param value The upper bound for the integral
	 * @return Returns the area under the curve from negative infinity to x
	 */
	public static double fromInfToX(double value) {
		double result = (1 + erf(value/Math.sqrt(2)))/2;
//		System.out.println(result);0.5750625163166376
		return result;
		
	}
	
	
	/**
	 * By using the second part of the Fundamental Theorem of Calculus, the area under the curve form
	 * negative infinity to val1 is subtracted from the area under the curve from negative infinity to val2,
	 * the function outputs the are under the curve from val1 to val2. The property : 
	 * integral from a to b for the function f(x) dx = F(b) - F(a).
	 * @param val1 lower bound
	 * @param val2 upper bound
	 * @return
	 */
	public static double fromVal1ToVal2(double val1, double val2) {
		double result = 0;
		double toVal2 = fromInfToX(val2);
		double toVal1 = fromInfToX(val1);
		result = toVal2 - toVal1;
		System.out.println(result);
		return result;
	}
	
	/**
	 * Calculate the area under a curve from point a to point b for any mean or any deviation
	 * Given some deviation s , mean mu and bounds a to b, the variable X = a and X = b, can be described 
	 * in terms of normal distribution with the formula Z = (a - mu)/s and Z = (b - mu)/s , so upper and lower bound
	 * of what would be used for the integral can be entered as given
	 * @param a lower bound as given
	 * @param b upper bound as given
	 * @param mean mu
	 * @param deviation sigma
	 * @return
	 */
	public static double probabilityCDF(double a, double b, double mean, double deviation) {
		double result = 0;
		double newA = (a - mean) / deviation;
		double newB = (b - mean) / deviation;
		result  = fromVal1ToVal2(newA, newB);
		System.out.println(result);
		return result;
	}
	
	public static double gaussPDF(double x, double deviation, double mean) {
		double result = 0;
		double coeff = 1/(Math.sqrt(2 * Math.PI) * deviation);
		double exponent = (-1) * (x - mean) * (x - mean) / (2 * deviation * deviation);
		result = coeff * Math.exp(exponent);
		return result;
	}
	
	public static double pow(double base, int power) {
		double result  = 1;
		for(int i = 0; i < power;  i++) {
			result *= base;
		}
		
		return result;
	}
	

	
	public static double cos(double x, int terms) {
		double result = 0;

		for(int i = 0; i < terms; i++) {
			double sign = (i % 2 == 0) ? 1 : -1;
			result += ((sign * pow(x, i * 2)) / ( factorial(i*2)));
//			System.out.println(result + " = " + sign + " * " + pow(x, i*2) + " / " + factorial(i * 2));
			
		}
		
		return result;
	}
	
	public static double sin(double x, int terms) {
		double result  = 0;
		
		for(int i = 0; i < terms; i++) {
			double sign  = (i % 2 == 0) ? 1 : -1;
			double numerator = pow(x, i * 2 + 1);
			double denominator = factorial(i * 2 + 1);
			double term = (sign *  numerator) / denominator;
			result += term;
		}
		return result;
	}
	
	
	
}
