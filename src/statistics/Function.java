package statistics;

import java.util.*;

import java.util.random.*;

public class Function {
	
	public int[] xC = new int[Panel.WIDTH * 2];//*2
	public int[] yC = new int[Panel.WIDTH * 2];//*2
	public double[] yActualVal = new double[Panel.WIDTH * 2];
	public double[] xActualVal = new double[Panel.WIDTH * 2];
	public double coeff1;
	public double coeff2;
	public boolean isVisable = true;
	String coordinatesForSVG;
	public ArrayList<Double[]> subarraysX = new ArrayList<Double[]>();
	public ArrayList<Double[]> subarraysY = new ArrayList<Double[]>();
	boolean isNumber;
	public Function left;
	public Function right;
	double value;

//	static int size = tokens.length - 1;
//	static int currT = 0;
	String token;
	public static int moveUpDown = 0;
	public static int moveSide = 0;
	public double dX = Panel.dX;
	public double oneIncriment = 1/(2*dX);
	public double numUnits = ((double) Panel.WIDTH) / dX;
	

	public Function b;
	
	public Function() {
	}
	
	public static void changeRange(Function f) {
	f.dX = Panel.dX;
	f.oneIncriment = 1/(2 * f.dX);
	f.numUnits = ((double) Panel.WIDTH) / f.dX;
	}
	
	//constructors so Function class acts like a Node class for SyntaxTree clas
	//******************************************************************************************************************************************************
	public Function(Function a, Function b) {
		this.left  = a;
		this.right = b;
	}

	public Function(String token) {
		this.token = token;
	}
	
	//parser methods
	//******************************************************************************************************************************************************

	
	
	public void setAppear(boolean isVisable) {
		this.isVisable = isVisable;
	}
	
	
	
	
	public void createOutputValues() {
//		System.out.println("Function Class YValues = " + Arrays.toString(yCoordinates) + " size = " + yCoordinates.length);
		
		
	}
	

	
	public static double factorial(double a) {
		double result = 1;
		if(a == 0) {
			return 1;
		}
		for(int i = 1; i <= a; i++) {
			result *= i;	
		}
		return result;
	} 
	
	public Function createValues() {

		return this;
	}
	
	public Function createValues(double dX) {
		
		return this;
	}
	

	
	public void makeSubarrays() {
		double height = Panel.HEIGHT;
		ArrayList<Double> currX = new ArrayList<Double>();
		ArrayList<Double> currY = new ArrayList<Double>();
		for(int i = 0; i < yActualVal.length; i++) {
			Double y = yActualVal[i];
			Double x = xActualVal[i];
			if(!y.isNaN()) {
				if(y <= height  && y >= 0) {
					if(currX.isEmpty() && i > 0 && y != Double.NaN) {
						if(yActualVal[i] < 0) {
							currX.add(xActualVal[i - 1]);
							currY.add(0.0);
						}else {
							currX.add(xActualVal[i - 1]);
							currY.add(Double.valueOf(Panel.HEIGHT));
						}
					}
					
					currX.add(x);
					currY.add(y);
				}
				if(y > height&& i < yActualVal.length - 1 && yActualVal[i + 1] <= height && yActualVal[i - 1] > y  && !y.isNaN())   {
					
					y = height;
					currX.add(x);
					currY.add(y);
				}
				
				if(y < 0 && i > 0 && yActualVal[i - 1] >= 0 && yActualVal[i + 1] < y  && !y.isNaN()) {
					y = 0.0;
					currX.add(x);
					currY.add(y);
					int sizeX = currX.size();
					Double[] subArrX = new Double[sizeX];
					Double[] subArrY = new Double[sizeX];
					subArrY = currY.toArray(subArrY);
					subArrX = currX.toArray(subArrX);
					subarraysX.add(subArrX);
					subarraysY.add(subArrY);
					currX = new ArrayList<Double>();
					currY = new ArrayList<Double>();
//					if(i + 1 < yActualVal.length) {
//						currX.add(xActualVal[i + 3]);
//						currY.add(y);
//	
//					}
				
				}
				
				if(y < 0 && i < yActualVal.length - 1 && yActualVal[i + 1] >= 0  && y > yActualVal[i + 1]  && !y.isNaN()) {
					if(currX.isEmpty() && i > 0) {
						currX.add(xActualVal[i - 1]);
						currY.add(0.0);
					}
					y = 0.0;
					currX.add(x);
					currY.add(y);
				}
				
				if(y > height && i > 0 && yActualVal[i - 1] <= height &&  yActualVal[i - 1] >0 && !y.isNaN()) {
		//			System.out.println("y>1000 and  = " + y);
					y = height;
					currX.add(x);
					currY.add(y);
					int sizeX = currX.size();
					Double[] subArrX = new Double[sizeX];
					Double[] subArrY = new Double[sizeX];
					subArrY = currY.toArray(subArrY);
					subArrX = currX.toArray(subArrX);
					subarraysX.add(subArrX);
					subarraysY.add(subArrY);
					currX = new ArrayList<Double>();
					currY = new ArrayList<Double>();
					
//					if(i + 1 < yActualVal.length) {
//						currX.add(xActualVal[i + 1]);
//						currY.add(y);
//	
//					}
				}
				
				
			}
		}
		
		int size = currX.size();
		if(size != 0) {
		Double[] subArrX = new Double[size];
		Double[] subArrY = new Double[size];
		subArrX = currX.toArray(subArrX);
		subArrY = currY.toArray(subArrY);
		subarraysX.add(subArrX);
		subarraysY.add(subArrY);
		}
		
		for(Double[] arr: subarraysY) {

	//		System.out.println("y SUBARRAY " + Arrays.toString(arr));

		}
	
//		for(Double[] arr1: subarraysX) {
//			if(arr1.length < 3) {
//				subarraysX.remove(arr1);
//			}
//			System.out.println("x SUBARRAY " + Arrays.toString(arr1));
//			System.out.println("x SUBARRAY " + Arrays.toString(arr));
//		}

	}
	
	//DOES NOT NEED IMPLEMENTATION BUT ONLY NEEDS TO BE HERE TO BE INHERITED TO BE USED FOR SUBCLASSES IN recalculateValues() method in SyntaxTree class
	public void compute(Function l, Function r) {
		//recalculates the values of the current node with the value at the left and value at the right
		
	}
	

	public static void moveLeft(int dX) {
		moveSide += dX;
		System.out.println("move left = " + moveSide);
	}
	
	public static void moveRight(int dX) {
			moveSide -= dX;
			System.out.println("move right = " + moveSide);
			
	}
		
	public static void moveUp(int dX) {
			moveUpDown -= dX/2;
			System.out.println("move up = " + moveUpDown);
	}
	//	WHY DIVIDE BY 2?
	/*Must divide by two because every where the amount of values is 2xPanel.WIDTH => the incriment for x is 2x smaller =>
	 * =>So when the bounds of x move in fact by x + moveSide*(1/(dX*2)), which is the scaled down proportional amount to move
	 * the bounds of x before the affine transformation below. and in this case the 1/dX * 1/2
	 * instead of of what it was originally ie. 1/dX after the affine transformation each coordinate, when the movement is 
	 * scaled back up to the, say 20 pixels the graph must be moved by the domain gets moved by 10-> So the increment is
	 * scaled down proportionally for the x input and THEN by 1/2, so because of this after the affine transformation when the x is scaled up by dX
	 * it is still 2x less than what it was before, and because the grid moves by also 1/2 of the set movement speed, when moving up or down the f(x) cooridinate
	 * is the only one that moves by the original set speed instead of the new one for everything else
	 */
	public static void moveDown(int dX) {
		moveUpDown += dX/2;
		System.out.println("move down = " + moveUpDown);
	}
//Linearly scales the root of the tree i.e. the output function of the whole experssion 	
	public void refresh() {
	
		int size = Panel.WIDTH * 2; //*2
			for(int y = 0; y < size; y++) {
				yActualVal[y] =  Panel.HEIGHT/2 - yActualVal[y]* Panel.dX + moveUpDown;
				xActualVal[y] =  Panel.WIDTH/2 + xActualVal[y] * Panel.dX;
				yC[y] = (int) (yActualVal[y]);
				xC[y] = (int) (xActualVal[y]);

			}	

	}
	//	System.out.println("refresh() xC = " + Arrays.toString(xActualVal)  + " \n yC = " + Arrays.toString(yActualVal));

	
@Override
public String toString() {
	return "Function b field = " + Arrays.toString(b.yActualVal);
}
	
}	