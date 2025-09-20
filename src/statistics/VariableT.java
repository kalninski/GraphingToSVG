package statistics;

import java.util.*;

public class VariableT extends Function{
	
	public static double domainStart= - Math.PI;
	public static double domainEnd = Math.PI; 
	
	public VariableT() {

		createValues();
	}
	
	public static void setDomainStart(double start) {
		domainStart = start;
	}
	
	public static void setDomainEnd(double end) {
		domainEnd = end;
	}
	
	
	@Override
	public Function createValues() {
		
		oneIncriment = Math.PI / (Panel.WIDTH);
		int i = 0;
		System.out.println("Start = " + domainStart + " End = " + domainEnd);
		for(double x = domainStart + oneIncriment * moveSide; x < domainEnd + oneIncriment * moveSide && i < 2400; x += oneIncriment) {
			
			yActualVal[i] = x;
			xActualVal[i] = x;

			i++;
		}
		
		return this;
	}
	public static boolean isVariable(String token) {
		int len = token.length();
		if(len == 1 && token.equals("t")) {
			
			return true;
		}
		return false;
	}
	
		@Override
	public String toString() {
		return "Variable T node : " + Arrays.toString(yActualVal);
	}
	
	public static boolean hasWrongParameter(String input) {
		StringBuilder expression = new StringBuilder(input);
		int size = input.length();
		if(input.contains("exp")) {
			System.out.println("input = " + input );
			int index = expression.indexOf("exp");
			expression.delete(index, index + 3);
			input = expression.toString();
		}
			if(input.contains("x") || input.contains("y") || input.contains("z")) {
				return true;
			}
		
		return false;
	}
	
}
