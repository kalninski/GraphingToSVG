package statistics;

import java.util.Arrays;

public class Secant extends Function{
	
	Function b;
	
	public Secant() {
		
	}
	
 
 public Secant(Function b) {
	 this.right = b;
	 createValues();
	 System.out.println("Secant node");
 }
	
	public Secant(double coeff1,double coeff2) {
		this.coeff1 = coeff1;
		this.coeff2 = coeff2;
		createValues();

	}
	
//	@Override
//	public Function createValues() {
//
//		for(int x = 0; x < Panel.WIDTH; x++) {
//			xC[x] = x;
//			yActualVal[x] = Math.pow(Math.cos(b.yActualVal[x]/Panel.scaler), -1);
//			xActualVal[x] = (double) x;
//			
//			Double outputY = yActualVal[x];
//			yC[x] = (int) (yActualVal[x]);
//
//		}
//	return this;
//	}
	@Override
	public String toString() {
		return "Secant node: " + Arrays.toString( this.yActualVal);
	}
@Override
	public Function createValues() {
		dX = Panel.dX;	
		oneIncriment = 1/(2*dX);
		numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = Math.pow(Math.cos(right.yActualVal[i]), -1);
			xActualVal[i] = x;
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
}
