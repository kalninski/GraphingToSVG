package statistics;

public class Factorial extends Function {
	
	public Factorial(Function b) {
		this.right = b;
		createValues();
	}
	
	@Override
	public Function createValues() {
		dX = Panel.dX;
		oneIncriment = 1/(dX * 2);
		numUnits = ((double) Panel.WIDTH) / dX;
		
		int i = 0;
		for(double x = -numUnits/2 + oneIncriment * moveSide; x < numUnits/2 + oneIncriment * moveSide && i < 2400; x += oneIncriment) {
			yActualVal[i] = Function.factorial(right.yActualVal[i]);
			xActualVal[i] = x;
			i++;
		}
		return this;
	}


}
