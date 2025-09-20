package statistics;

import java.util.*;

public class Log extends Function{
	
	double base;

	public Log(double base) {
		this.base = base;
		coeff1 = 1;
		coeff2 = 1;

	}
	

	
	public Log(Function b) {
		this.right = b;
		createValues();
	}
	
	public Log(double coeff1, double coeff2, double base) {
		this(base);
		this.coeff1 = coeff1; //outer multiplier
		this.coeff2 = coeff2; //inner multiplier

	}
	
//	@Override
//	public Function createValues() {
//	
////		double xCoordinate = 0;
//		for(int x = 0 ; x < Panel.WIDTH; x++) {
//			
//			if(right.yActualVal[x] >= 0) {
//				yActualVal[x] = Math.log(right.yActualVal[x] / (10  * (Panel.scaler)));
//				yC[x] = (int) (yActualVal[x]);
//				}else if(Double.isInfinite(Math.abs(right.yActualVal[x])) || right.yActualVal[x] < 0 || Double.isNaN((right.yActualVal[x]))){
//					yActualVal[x] = Panel.HEIGHT; 
//					yC[x] = (int) (yActualVal[x]);			
//				
//				}
//			//	xCoordinate += 0.5;
//			xActualVal[x]= x;//x 
//
//				xC[x] = x;
//			
//
//		}
//		
//		return this;
//	}

			@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2*dX);
		numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			
			
				if(right.yActualVal[i] >= 0) {
				yActualVal[i] = Math.log(right.yActualVal[i]);
			
				}else if(Double.isInfinite(Math.abs(right.yActualVal[i])) || right.yActualVal[i] < 0 || Double.isNaN((right.yActualVal[i]))){
					yActualVal[i] = Panel.HEIGHT; 
				
				
				}
				xActualVal[i] = x;
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
		@Override
	public String toString() {
		return coeff1 + " * log( " + coeff2 + " * x )";
	}
	

}
