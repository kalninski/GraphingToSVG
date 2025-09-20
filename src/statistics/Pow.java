package statistics;

import java.util.Arrays;

public class Pow extends Function {
	
	Function power;

	

	public Pow(Function b, Function power) {
		this.left = b; //FIX HERE DO NOT NEED TO FIEDS FOR THE SAME THING
		this.right = power;
		
		createValues();
//		System.out.println("Base = " + b.toString() + " Power = " + left.toString());
	}
	


//	@Override
//	public Function createValues() {
//		StringBuilder str = new StringBuilder();
//
//		int y = 0;
//		int counter = 0;
//		for(int x = 0; x < Panel.WIDTH; x++) {
//			xC[x] = x;
//			yActualVal[x] = Math.pow((b.input[x]/(Panel.scaler * 100)), power.input[x]);
//			input[x] = yActualVal[x];
//			
//			
//			str.append(x + ",");
//			str.append(yC[x] + " ");
//			
//		}
//		System.out.println(str.toString());
//		return this;
//	}
	@Override
	public Function createValues() {
		double dX = Panel.scaler;
		double oneIncriment = 1/(2*dX);
		double numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			
			yActualVal[i] = Math.pow(left.yActualVal[i], right.yActualVal[i]);
//			System.out.println("right Yval =  " + right.yActualVal[i] + " power = " +power.yActualVal[i] );
			if(right.yActualVal[i] == 0) {
				yActualVal[i] = 1;
			}

			xActualVal[i] = x;

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
	
			@Override
		public void compute(Function l, Function r) {
			double dX = Panel.dX;

			double oneIncriment = 1/(dX * 2);
			double numUnits = ((double) Panel.WIDTH) / dX;
			int i = 0;
			for(double y = -numUnits/2; y < numUnits/2 && i < Panel.WIDTH * 2; y+=oneIncriment) {
			yActualVal[i] = Math.pow(left.yActualVal[i], right.yActualVal[i]);


			xActualVal[i] = y;
		
				i++;
			}
		}
			
			@Override
			public String toString() {
				return "Pow node : " + this.right.getClass() + this.left.getClass();
			}
}
