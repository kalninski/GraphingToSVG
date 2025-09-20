package statistics;

import java.util.*;
import java.util.random.*;

public class Sine extends Function{
	
//	public Function b;//The input Factor(contstant) and Variable both extend Function sclass so they do not need their own fields per object

	
	
	public Sine() {
		
	}
	


	public Sine(Function b) {
		
		this.right = b;
		createValues();


	}
	
	public Sine(double coeff1,double coeff2) {
		this.coeff1 = coeff1;
		this.coeff2 = coeff2;
		createValues();

	}
	
	

	
	
	@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(2*dX);
		numUnits = ((double) Panel.WIDTH) / dX;

		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH * 2; x += oneIncriment) {
			yActualVal[i] = Math.sin(right.yActualVal[i]);
			xActualVal[i] = x;
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
		@Override
	public String toString() {
		return "Sine node: " + Arrays.toString( this.yActualVal);
	}
		



}
