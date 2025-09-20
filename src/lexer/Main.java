package lexer;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lexer l = new Lexer("sin(x) + 43.89182 - cos(3 * x)");
		System.out.println(Arrays.toString(l.getTokens()));
		

	}

}
