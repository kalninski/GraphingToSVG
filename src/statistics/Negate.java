package statistics;

import java.util.*;

public class Negate extends Function{

	public Negate(Function b) {
		this.right = b;
		createValues();
		System.out.println(this.toString());
		
	}
	
	
	@Override
	public String toString() {
		return "Negate node : " + Arrays.toString(this.yActualVal);
	}
@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2*dX);
		numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = (-1) * right.yActualVal[i];
			xActualVal[i] = x;
			
			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
	
}
