package statistics;
import java.util.Arrays;

public class Subtract extends Function{
	
	Function outputFunction;//function object output
	Factor outputFactor;
	String token;

	
	public Subtract() {}
	
	public Subtract(Function a, Function b) {
		this.left = a;
		this.right = b;
		subtractFunctions();
	this.token = "( " +  Arrays.toString(this.yActualVal) + " )";
	}
	

	
	public Function subtractFunctions() {
		outputFunction = new Function();
		int size = Panel.WIDTH*2;
		for(int i = 0; i < size; i++) {
			yActualVal[i] = (left.yActualVal[i] - right.yActualVal[i]);
//			System.out.println("left val = " + left.yActualVal[i] + " right val = " + right.yActualVal[i]);
			xC[i] = i;
			yC[i] = (int) (yActualVal[i]);
		}
		xActualVal = left.xActualVal;
		return outputFunction;
	}
	


		@Override
	public String toString() {
		return "Subtract node : "  + this.token;
	}	

		
//Inherited method from the function class which is overridden so that with polymorphism the method gets applied to the right instance when the SyntaxTree recursively
//updates its values
		@Override
		public void compute(Function l, Function r) {
			double dX = Panel.dX;
			int size = Panel.WIDTH;
			double oneIncriment = 1/(2 * dX);
			double numUnits = ((double) Panel.WIDTH) / dX;
			int i = 0;
			for(double y = -numUnits/2; y < numUnits/2 && i < Panel.WIDTH  * 2; y+=oneIncriment) {
				yActualVal[i] = l.yActualVal[i] - r.yActualVal[i];
				xActualVal[i] = y;
		
				i++;
			}
		}
}
