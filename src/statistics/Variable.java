package statistics;

import java.util.Arrays;

public class Variable extends Function{

	
	//put specific x values so that when x is the only input the it should behave like f(x) = x
	//and at the same time the way it behaves when it is an argument for functions like sine, log, etc. must remain the same
	
	public Variable() {
		
		createValues();
//		System.out.println("yActualVal from variable constructor = " + Arrays.toString(this.xActualVal));
	}
	
/*
 * Variable class creates the input values based on the width of the panel and the number of units visable in the closeup, there are 2400 values 
 * for more precision and on incriments is just delta X betweem one and the next value, they are spaced evenly.
 * After the values are created, they are used as input for the functions, the functions. HOWEVER!! the variable range and therefore the function values
 * are calculated on values that are like units on real number line, so if my Panel.Width / dX is saying that the number of units is 6 for example,
 * if I do not do the affine transformation the result ends up as if the output range is from -3 to 3 pixels and not in in terms of the screen space
 */
	@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(dX * 2);
		numUnits = ((double) Panel.WIDTH) / dX;

//		System.out.println("numUnits = " + numUnits + "start and end = " + (-numUnits/2) + "   " + (numUnits/2) );
		int i = 0;

		//in the moveRight moveLeft mehod the dX has to be dividded by two because the incriment is 2x less due to adding  more points, yet if the moveSide amount stays the same the navigations happens 2x as fast
		for(double x = -numUnits/2 + oneIncriment * moveSide; x < numUnits/2 + oneIncriment * moveSide && i < 2400; x += oneIncriment) {
//			System.out.println("x = " + x + " i = " + i);
			yActualVal[i] = x;
			xActualVal[i] = x;

			i++;
			
	
		}
		return this;
	}
	public static boolean isVariable(String token) {
		int len = token.length();
		if(len == 1 && token.equals("x")) {
			
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Variable node : " + Arrays.toString(yActualVal);
	}
	
	
	
}
