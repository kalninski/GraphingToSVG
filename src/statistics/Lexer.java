package statistics;

import java.util.*;
import java.lang.StringBuilder;

public class Lexer {
	
	public String expression;
	public final List<String> FUNCTION_TOKENS = new LinkedList<String>();
	public final String[] FUNCTIONS = new String[] {"sin", "cos", "tan", "sec", "log", "N", "exp"};
	public LinkedList<String> tokens = new LinkedList<String>();
	public String[] tokensArr; 
	int currIndex = 0;
	
	public Lexer() {
		
System.out.println(" new Lexer() FUCNTIONS.length = " + FUNCTIONS.length);
		for(int i = 0; i < FUNCTIONS.length; i++) {
			FUNCTION_TOKENS.add(FUNCTIONS[i]);
			
		}
	
	}
	public Lexer(String expression) {
		this();
		expression = expression.replaceAll("(?i) ", "");
		System.out.println("expression after remove spoace = " + expression.replaceAll("(?i) ", ""));
		this.expression = expression;
		getTokens();
		System.out.println("lexer tokens " + Arrays.toString(tokensArr) );
		
	}
	
	
	public boolean isNumber(char c) {
		if(expression.length() > 0 && (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.') ) {
			System.out.println();
			
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getSingleChar() {
		
		if( expression.length() > 0 && (expression.charAt(currIndex) == '+' || expression.charAt(currIndex)  == '-' || expression.charAt(currIndex)  == '*' || expression.charAt(currIndex)  == '/' ||
			expression.charAt(currIndex)  == '(' || expression.charAt(currIndex)  == ')' || expression.charAt(currIndex)  == '^' || expression.charAt(currIndex) == '!')) {
			tokens.add(String.valueOf(expression.charAt(currIndex)));
			System.out.println("get single char = " + String.valueOf(expression.charAt(currIndex)) + " index = " + currIndex);
			currIndex++;
			
			expression = expression.substring(currIndex, expression.length());
			currIndex = 0;
			System.out.println("expression = " + expression);
			return true;
		}else {
			return false;
		}
	}
	

	
	public boolean getFunction() {
		for(String t : FUNCTION_TOKENS) {
	//		System.out.println("Function list  element = " + t);
			if(expression.length() > 0 && expression.contains(t) && currIndex == expression.indexOf(t)) {

				boolean tadd = tokens.add(t);				
				System.out.println("the function that was added = " + t + " length = " + t.length() + " token = " + t + " added = " + tadd + " currIndex = " + currIndex);
				currIndex += t.length();

				expression = expression.substring(currIndex, expression.length());

			currIndex = 0;
			System.out.println("expression = " + expression);
				System.out.println("after adding function the currIndex = " + currIndex);
				return true;
			}
		}
		return false;
	}
	
	public String stringNumber() {
			StringBuffer number = new StringBuffer();
		
			while(expression.length() > 0 && isNumber(expression.charAt(0))) {
				number.append(expression.charAt(currIndex));
				System.out.println("String number method = " + number);
				currIndex++;
				expression = expression.substring(currIndex, expression.length());
				currIndex = 0;
			}
				
				
			currIndex = 0;
			System.out.println("expression = " + expression);
		return number.toString();
	}
	
	public boolean getVariable() {
		if(expression.length() > 0 && (expression.charAt(0) == 'x' || expression.charAt(0) == 'y' || expression.charAt(0) == 'z'|| expression.charAt(0) == 't')) {
			tokens.add("" + expression.charAt(0));
			currIndex++;
			expression = expression.substring(currIndex, expression.length());
			currIndex = 0;
			System.out.println("expression = " + expression);
			return true;
		}else {
			return false;
		}
	}

	public String[] getTokens() {
		while(expression.length() > 0) {
		//	System.out.println("currIndex = " + currIndex + " expression.length() = " + expression.length());
			if(isNumber(expression.charAt(currIndex))) {
				String number = stringNumber();
				System.out.println("is number = " + number);
				boolean t = tokens.add(number);
				System.out.println("tokens.add(number) = " + t);
			
			}
			getSingleChar(); 
			getFunction();
			getVariable();


//			if(currIndex < expression.length() && expression.charAt(currIndex) == ' ') {
//				currIndex++;
//			}
		}
		int size = tokens.size();
		String[] tokensArr = new String[size];
		tokensArr = tokens.toArray(tokensArr);
		this.tokensArr = tokensArr;
		
		return tokensArr;
	}
	
}
