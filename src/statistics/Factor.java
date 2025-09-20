package statistics;

import java.util.Arrays;

public class Factor extends Function{
	
	public double value;


	public static final String[] number = new String[] {"0", "1","2","3","4","5","6","7","8","9","."}; 
	
	public Factor() {
		this.isNumber = true;
		this.value = 0;
	}
	
	public Factor(String token) {
		this.token = Arrays.toString(this.yActualVal);
		if(isFactor(token)) {	
			this.value = Double.valueOf(token);
		//	System.out.println("value = " + value);
		}
			createValues();
//			System.out.println(this.toString()  + " FACTOR CUNSTRUCTOR");
		

	}
	
	public static boolean isFactor(String token) {
		if(token.contains("0") || token.contains("1") || token.contains("2")|| token.contains("3")|| token.contains("4")|| token.contains("5")|| token.contains("6")|| token.contains("7")|| token.contains("8")|| token.contains("9")|| token.contains(".")) {
//		System.out.println(token + "token ISFACTOR() methid");
			return true;
		
		}else{
//			System.out.println("FALSE token = " + token);
			return false;
		}
	}

			@Override
	public String toString() {
		return "Factor node Y : "  + Arrays.toString(this.yActualVal);
	}

	@Override
	public Function createValues() {
		double dX = Panel.dX;
		double oneIncriment = 1/(2 * dX);
		double numUnits = ((double) Panel.WIDTH) / dX;
		int i = 0;
		for(double x = -numUnits/2; x < numUnits/2 && i < Panel.WIDTH*2; x += oneIncriment) {
			yActualVal[i] = this.value ;
			xActualVal[i] = x;
//			System.out.println("Factor x = " + x + " Y value = " + value );
			

			i++;
			
		 
		}
//		System.out.println("Sine createValues() y = " + this.toString());
		return this;
	}
}
