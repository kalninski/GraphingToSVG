package lexer;

import java.util.*;
import java.lang.StringBuilder;

public class Lexer {
	
	public String expression;
	public final List<String> FUNCTION_TOKENS = new LinkedList<String>();
	public final String[] FUNCTIONS = new String[] {"sin", "cos", "tan", "sec", "log", "N", };
	public LinkedList<String> tokens = new LinkedList<String>();
	public String[] tokensArr; 
	int currIndex = 0;
	
	public Lexer() {
		for(int i = 0; i < FUNCTIONS.length; i++) {
			FUNCTION_TOKENS.add(FUNCTIONS[i]);
		}
	
	}
	public Lexer(String expression) {
		this();
		this.expression = expression;
		getTokens();
		System.out.println("lexer tokens " + Arrays.toString(tokensArr) );
		
	}
	
	
	public boolean isNumber(char c) {
		if(currIndex < expression.length() && (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.') ) {
			
			
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getSingleChar() {
		
		if(currIndex < expression.length() && (expression.charAt(currIndex) == '+' || expression.charAt(currIndex)  == '-' || expression.charAt(currIndex)  == '*' || expression.charAt(currIndex)  == '/' ||
			expression.charAt(currIndex)  == '(' || expression.charAt(currIndex)  == ')' || expression.charAt(currIndex)  == '^' )) {
			tokens.add(String.valueOf(expression.charAt(currIndex)));
			currIndex++;
			return true;
		}else {
			return false;
		}
	}
	

	
	public boolean getFunction() {
		for(String t : FUNCTION_TOKENS) {
			if(currIndex < expression.length() && expression.contains(t) && currIndex == expression.indexOf(t)) {
				tokens.add(t);
				currIndex += t.length();
				return true;
			}
		}
		return false;
	}
	
	public String stringNumber() {
			StringBuffer number = new StringBuffer();
		
			while(currIndex < expression.length() && isNumber(expression.charAt(currIndex))) {
				number.append(expression.charAt(currIndex));
				
				currIndex++;
			}
		return number.toString();
	}
	
	public boolean getVariable() {
		if(currIndex < expression.length() && (expression.charAt(currIndex) == 'x' || expression.charAt(currIndex) == 'y' || expression.charAt(currIndex) == 'z')) {
			tokens.add("" + expression.charAt(currIndex));
			currIndex++;
			return true;
		}else {
			return false;
		}
	}

	public String[] getTokens() {
		while(currIndex < expression.length()) {
			if(isNumber(expression.charAt(currIndex))) {
				String number = stringNumber();
				tokens.add(number);
			}
			getSingleChar();
			getFunction();
			getVariable();

			if(currIndex < expression.length() && expression.charAt(currIndex) == ' ') {
				currIndex++;
			}
		}
		int size = tokens.size();
		String[] tokensArr = new String[size];
		tokensArr = tokens.toArray(tokensArr);
		this.tokensArr = tokensArr;
		
		return tokensArr;
	}
	
}
