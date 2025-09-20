package statistics;

import java.util.Arrays;

public class Exp extends Function {
	

	public Exp(Function b) {
		this.right = b;
		createValues();
		System.out.println(this.toString());
		
	}
	
	
	@Override
	public String toString() {
		return "Exp node : " + Arrays.toString(this.yActualVal);
	}
@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2*dX);
		numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = Math.exp(right.yActualVal[i]);
			xActualVal[i] = x;
			
	
			i++;
			
		 
		}
		System.out.println(Arrays.toString(yActualVal));
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
}
