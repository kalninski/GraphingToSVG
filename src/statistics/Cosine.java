package statistics;

import java.util.*;
import java.util.random.*;

public class Cosine extends Function {
	

	
	
	public Cosine(Function b) {
		this.right = b;
		createValues();
	}
	
	public Cosine() {
		coeff1 = 1;
		coeff2 = 1;
			
	}
	
	public Cosine(double coeff1,double coeff2) {
		this.coeff1 = coeff1;
		this.coeff2 = coeff2;
		createValues();
//		this.makeSubarrays();


	}
	
	
	
	

//	@Override
//	public Function createValues() {
//		
//		double xCoordinate = 0;
//		for(int x = 0; x < Panel.WIDTH; x++) {
//
//			yActualVal[x] =  Math.cos(b.input[x]/(Panel.scaler * 100));
//			xActualVal[x] = (double) x;
//			input[x] = yActualVal[x];
//			
//			xCoordinate += 0.5;
//			xActualVal[x]=  x;
//
//				xC[x] =(int) x;
//				yC[x] = (int) (yActualVal[x]);
//		
//	//	
//		}
//		return this;
//	}
	
	@Override
	public String toString() {
		return "Cosine node: " + Arrays.toString( this.yActualVal);
	}
@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2 * dX);
		numUnits = ((double) Panel.WIDTH) / dX;

		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = Math.cos(right.yActualVal[i]);
			xActualVal[i] = x;
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}

}
