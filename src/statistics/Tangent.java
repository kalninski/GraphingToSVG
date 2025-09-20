package statistics;

import java.util.Arrays;

public class Tangent extends Function{
	

	
	
	
	public Tangent() {
		
	}

	
	public Tangent(Function b) {
		this.right = b;
		createValues();
	}
	
	public Tangent(double coeff1,double coeff2) {
		this.coeff1 = coeff1;
		this.coeff2 = coeff2;
		createValues();
	}
	

@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2 * dX);
		numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = Math.tan(right.yActualVal[i]);
			xActualVal[i] = x;
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
	
	

}
