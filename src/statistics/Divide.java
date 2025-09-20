package statistics;

import exceptions.*;
import java.util.Arrays;

public class Divide extends Function {
Function outputFunction;//function object output
	Factor outputFactor;
	String token;

	
	public Divide() {}
	
	public Divide(Function a, Function b) {
		this.left = a;
		this.right = b;
		try {
		divideFunctions();
		}catch(DivideByZero e) {
			Input.errorDisplay.setText("Division by zero!");
		//	System.out.println(e.getMessage());
		}
		this.token = Arrays.toString(this.yActualVal);
	}
	

	
	public Function divideFunctions() throws DivideByZero{
		outputFunction = new Function();
		int size = left.yActualVal.length;
		for(int i = 0; i < size; i++) {
			if(right.yActualVal[i] != 0) {
				yActualVal[i] = ( left.yActualVal[i] / right.yActualVal[i] );
				xC[i] = i;
				yC[i] = (int) (yActualVal[i]);
			}else {
				yActualVal[i] = 0;
				throw new DivideByZero("Division by zero!");
				
			}
		}
			xActualVal = left.xActualVal;
		return this;
	}
	


		
	public String toString() {
		return "Divide node : "  + this.token;
	}	

		@Override
		public void compute(Function l, Function r){
			double dX = Panel.dX;
			int size = Panel.WIDTH;
			double oneIncriment = 1/(2 * dX);
			double numUnits = ((double) Panel.WIDTH) / dX;
			int i = 0;
			for(double y = -numUnits/2; y < numUnits/2 && i < Panel.WIDTH * 2; y+=oneIncriment) {
		try{
				yActualVal[i] = l.yActualVal[i] / r.yActualVal[i];
				xActualVal[i] = y;
		}catch(Exception ex) {
				yActualVal[i] = 0;
				xActualVal[i] = y;
				
			
		}		
				i++;
			}
		}

}
