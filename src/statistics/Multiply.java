package statistics;

import java.util.Arrays;

public class Multiply extends Function{
	
	Function outputFunction;//function object output
	Factor outputFactor;
	String token;

	
	public Multiply() {}
	
	public Multiply(Function a, Function b) {
		this.left = a;
		this.right = b;
		multiplyFunctions();
		this.token = Arrays.toString(this.yActualVal);
	}
	

	
	public Function multiplyFunctions() {
		outputFunction = new Function();
		int size = left.yActualVal.length;
		double xCoordinate = 0;
		for(int i = 0; i < size; i++) {
			yActualVal[i] = left.yActualVal[i] * right.yActualVal[i];
			
			xCoordinate += 0.5;
			
			xC[i] = i;
			yC[i] = (int) (yActualVal[i]);
			xActualVal[i] = i;	
			
		}
		
		return this;
	}
	


		@Override
	public String toString() {
		return "Multiply node : "  + this.token;
	}	

//	public Function createValues() {
//		int size = yActualVal.length;
//		for(int i = 0; i < size; i++ ) {
//			yActualVal[i] = Panel.HEIGHT/2 - yActualVal[i] * 10 * Panel.scaler;
//	//		xActualVal[i] = xActualVal[i];
////			System.out.println("scled y = " + Panel.scaler);
//			
//			yC[i] = (int) yActualVal[i];
//			xC[i] = (int) xActualVal[i];
//		}
//		return this;
//	}

		@Override
		public void compute(Function l, Function r) {
			double dX = Panel.dX;

			double oneIncriment = 1/(2 * dX);
			double numUnits = ((double) Panel.WIDTH) / dX;
			int i = 0;
			for(double y = -numUnits/2; y < numUnits/2 && i < Panel.WIDTH * 2; y+=oneIncriment) {
				yActualVal[i] = l.yActualVal[i] * r.yActualVal[i];
				xActualVal[i] = y;
		
				i++;
			}
		}
}
