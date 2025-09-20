package statistics;

import java.util.Arrays;

public class Add extends Function{

	public Function outputFunction;//function object output
	public Factor outputFactor;
	public String token;

	
	public Add() {}
	
	public Add(Function a, Function b) {
		this.left = a;
		this.right = b;
		addFunctions();
			this.token =  Arrays.toString(this.yActualVal);
	}
	

	
	public Function addFunctions() {
		outputFunction = new Function();
		int size = left.yActualVal.length;
		for(int i = 0; i < size; i++) {
			yActualVal[i] =( left.yActualVal[i] + right.yActualVal[i]);
			xC[i] = i;
			yC[i] = (int) (yActualVal[i]);
		}
		xActualVal = left.xActualVal;
		return this;
	}
	


		@Override
	public String toString() {
		return "Add node : "  + this.token;
	}	

	//The overridden version of Function class compute(Function f) method which is called by the SyntaxTree instance	
		@Override
		public void compute(Function l, Function r) {
			double dX = Panel.dX;

			double oneIncriment = 1/(dX * 2);
			double numUnits = ((double) Panel.WIDTH) / dX;
			int i = 0;
			for(double y = -numUnits/2; y < numUnits/2 && i < Panel.WIDTH * 2; y+=oneIncriment) {
				yActualVal[i] = l.yActualVal[i] + r.yActualVal[i];
				xActualVal[i] = y;
		
				i++;
			}
		}
}
