package statistics;

import java.util.Arrays;

//Although may be the actual tree data structure for this is not necessary
//perhaps it may later prove easier if I can parse by creating the respective tree, feed it the String[] token input
//and don't keep the token counter currT as a Function(Node) golbal attribute since the functions may be more than one
//and this way there is no need to reset the counter to zero every time a function is drawn
//SO -> one tree has global token array (the input), global counter and the tree creating methods
public class SyntaxTree {
	

	
	public Function root = new Function();
	public String[] tokens = new String[]{"cos","(","x", ")", "+", "sin", "(", "x", ")", "-", "5", "*", "sin", "(", "x", ")"};
	public String input = "cos(x) + sin(x) - 5 * sin(x)";
	public int size = tokens.length;
	public int currT = 0;
	
	public SyntaxTree() {}
	
	public SyntaxTree(String input) {
		Lexer lexer = new Lexer(input);
		
		System.out.println(Arrays.toString(lexer.tokensArr));
		this.tokens = lexer.tokensArr;
		size = tokens.length;
		System.out.println( "tokens   \t"+ Arrays.toString(this.tokens));
	//	Function n = new Function();
		root = parseE();
//		print(root);
		try {
		root.refresh();
		}catch(NullPointerException ex) {
			Input.errorDisplay.setText(ex.getMessage());
		}
		//		createValues();
//
//		print(root);
//		System.out.println("Root of the tree" + root.toString());
		
//		System.out.println(currT + " consrtuctor end" );
		currT = 0;
	}
	
	public void print(Function node) {
		if(node != null) {
			System.out.println(node.toString());
		}
		if(node.left != null) {
			print(node.left);
		}
		if(node.right != null) {
			print(node.right);
		}
	}
	
	public Function parseE() {
		Function a = null;
		
		if(currT < size && ((tokens[currT].equals("-") && currT == 0) ||(tokens[currT].equals("-") && tokens[currT - 1].equals("(")))) {
			
			currT++;
			
			a = parseT();
			a = new Negate(a);
		}else {
			a = parseT();
		}

		if(currT < size && tokens[currT].equals("+")) {
			currT++;
	//		System.out.println(currT + ".equals( currT parseE()");
			Function b = parseEprime();
			a = new Add(a, b);
	//		System.out.println(a.toString());
		}
		if(currT < size && tokens[currT].equals("-")) {
			currT++;
			Function b = parseEprime();
			a = new Subtract(a, b);
		}
	
		return a;
	}
	
	public Function parseT() {
		
		Function a = parseF();
		if(currT < size && tokens[currT].equals("*")) {
			currT++;
			Function b = parseTprime();
			a = new Multiply(a, b);
//			System.out.println(a.toString());
		}
		if(currT < size && tokens[currT].equals("/")) {
			currT++;
			Function b = parseTprime();
			a = new Divide(a, b);
//			System.out.println(a.toString());
		}
		return a;
	}
	
	public Function parseEprime() {
		
		Function a = parseT();
		if(currT < size && tokens[currT].equals("+")) {
			currT++;
			Function b = parseEprime();
			a = new Add(a, b);
		}
		if(currT < size && tokens[currT].equals("-")) {
			currT++;
			Function b = parseEprime();
			a = new Subtract(a, b);
//			System.out.println(a.toString());
		}
		
		
		return a;
	}
	
	public Function parseTprime() {
		
		Function a = parseF();
		
		if(currT < size && tokens[currT].equals("*")) {
			currT++;
			Function b = parseTprime();
			a = new Multiply(a, b);
//			System.out.println(a.toString());
		}
		if(currT < size && tokens[currT].equals("/")) {
			currT++;
			Function b = parseTprime();
			a = new Divide(a, b);
//			System.out.println(a.toString());
		}
		return a;
		
	}
	//******************************************************************************************************* PARSE F ()
	public Function parseF() {
		
		Function a = null;
		
		if(currT < size && Factor.isFactor(tokens[currT])) {
			a = new Factor(tokens[currT]);
//			System.out.println("IS A NUMBER =  " + tokens[currT]);
			
			currT++;
			
		}
		
		if(currT < size && Variable.isVariable(tokens[currT])) {
//			System.out.println("New Variable from parseF() !!!");
			a = new Variable();
//			System.out.println(a.toString());
			
			currT++;
			
		}
		if(currT < size && VariableT.isVariable(tokens[currT])) {
//			System.out.println("New Variable from parseF() !!!");
			a = new VariableT();
//			System.out.println(a.toString());
			
			currT++;
			
		}
		if(currT < size && tokens[currT].equals("^")) {
			currT++;
			Function b = parseF();
//			System.out.println("new b node =  " + b.toString());
	
			a = new Pow(a, b);
			
	
		}
		
		if(currT < size && tokens[currT].equals("(")) {
			currT++;
			a = parseE();
			if(tokens[currT].equals(")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;

				a = parseExpressionAfterClosedParenthesis(a);
			}
		}



		if(currT < size && tokens[currT].equals("sin")) {
			currT++;
			if(tokens[currT].equals("(")) {
				currT++;
			Function b = parseE();
			a = new Sine(b);
			if(currT < size && tokens[currT].equals(")") ) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
//				
				a =  parseExpressionAfterClosedParenthesis(a);
			}
			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR SINE PLZ!");
			}
		}
		if(currT < size && tokens[currT].equals("sec")) {
			currT++;
			if(tokens[currT].equals( "(")) {
				currT++;
				Function b = parseE();
				a = new Secant(b);
				if(currT < size && tokens[currT].equals(")")) {
					currT++;
					a = parseExpressionAfterClosedParenthesis(a);
			}
			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR SECANT PLZ!");
			}
		}
		if(currT < size && tokens[currT].equals("tan")) {
			currT++;
			if(tokens[currT].equals("(")) {
				currT++;
			Function b = parseE();
			a = new Tangent(b);
			if(tokens[currT].equals(")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
//				
				
				a = parseExpressionAfterClosedParenthesis(a);
			}


			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR TANGENT PLZ!");
			}
		}				

		if(currT < size && tokens[currT].equals("cos")) {
			currT++;
			if(tokens[currT].equals("(")) {
				currT++;
			Function b = parseEprime();
			a = new Cosine(b);
			
			if(tokens[currT].equals(")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
//				
				a = parseExpressionAfterClosedParenthesis(a);
			}

			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR COSINE  PLZ!");
			}
		}
		
		if(currT < size && tokens[currT].equals("log")) {
			currT++;
			if(tokens[currT].equals("(")) {
				currT++;
			Function b = parseEprime();
			a = new Log(b);
			if(tokens[currT].equals(")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
//				
				a = parseExpressionAfterClosedParenthesis(a);
			}


			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR LOG  PLZ!");
			}
		}
		if(currT < size && tokens[currT].equals("exp")) {
			System.out.println("exp in paseF()");
			
			currT++;
			if(tokens[currT].equals("(")) {
				currT++;
			Function b = parseE();
			a = new Exp(b);
			System.out.println("Exp node!");
			if(tokens[currT].equals(")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
//				
				a = parseExpressionAfterClosedParenthesis(a);
			}


			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR EXP  PLZ!");
			}
		}	
		if(currT < size && tokens[currT].equals("N")) {
			currT++;
			if(tokens[currT].equals( "(")) {
				currT++;
			Function b = parseE();
			a = new GaussDistribution(b);
			if(tokens[currT].equals( ")")) {
//								System.out.println("closing ) Tree.currT = " + currT);
				currT++;
				if(currT < size - 1) {
					if(tokens[currT].equals( "*")) {
	//					System.out.println("multiply after ) Tree.currT = " + currT);
						currT++;
						Function c = parseF();
						a = new Multiply(a, c);
//						System.out.println(a.toString());
					}
					if(tokens[currT].equals( "/")) {
						currT++;
						Function c = parseF();
						a = new Divide(a, c);
					}else
					if(tokens[currT].equals( "+")) {
						currT++;
						Function c = parseEprime();
						a = new Add(a, c);
					}else
					if(tokens[currT].equals( "-")) {
						currT++;
						Function c = parseEprime();
						a = new Subtract(a, c);
						
					}
					 
					
				}
			}


			}else {
				System.out.println("SYNTAX ERROR! FIX PARENTHESIS FOR COSINE  PLZ!");
			}
		}
				if(currT < size && tokens[currT].equals("!")) {
						currT++;
//						Function c = parseEprime();
						a = new Factorial(a);
					}
		
//	currT++;
	System.out.println("currT at end of parseF() " + currT);
		
		return a;	
	}
		
//	public void createValues() {
//		int size = root.yActualVal.length;
//		for(int i = 0; i < size; i++ ) {
//			root.yActualVal[i] = Panel.HEIGHT/2 - root.yActualVal[i] * 100 * Panel.scaler;
////			root.xActualVal[i] = root.xActualVal[i] * Panel.scaler;
////			System.out.println("scled y = " + Panel.scaler);
//			
//			root.yC[i] = (int) root.yActualVal[i];
//			root.xC[i] = (int) root.xActualVal[i];
//		}
//	}
//	
	public Function recalculateValues(Function f) {
		if(f != null) {
			
		if(f instanceof ParametricEq) {
			/*Regular explicit functions have their domain updated by static variable updates from the panel class paint method dX updates,but for parametric
			 * the below recursive change range method just updates each instance of VartiableT, this is because the explicit function class
			 * attributes can then remain as inherited by the function class, but if ParametricEq is instantiated, then all can remain in the same
			 * way, just the right parameter update is done. This may have been a stupid way to do this but this for now the best I could come up with
			 * For parametric and polar eq. the range does not get scaled as it is in explicit functions it is pretty much a fixed set of values, that only 
			 * needs the affine matrix transformation, like scaling a circle, it gets scaled but its range will allays be from x to x+2pi, where as 
			 * explicit functions after the affine transformation have this range literally enlarged, widened, so even though it looks like the function is
			 * getting closer by doing close up. More about it explained in Variable class
			 * If my logic is logicing then the reason I did not add a condition (if instanceof VariableT) in the this method, is so that I can specify the range
			 * for each function separately, or not specify anything at all and leave it as default which is the before mentioned x to x + 2pi, other wise, if 
			 * I update the range for the VariableT as a class which would be the only reason to add it in this method, it would cause the same range update to all
			 * function trees that have the VariableT node as a leaf node. 
			 */
			ParametricEq.changeRangeForAllNodes(f);
			f.left = recalculateValues(f.left);
			f.right = recalculateValues(f.right);
			f.compute(f.left, f.right);
		}
		
		if(f instanceof Polar) {
			Polar.changeRangeForAllNodes(f);
			f.right = recalculateValues(f.right);
			f.left = recalculateValues(f.left);
			f.compute(f.left, f.right);
		}
		if(f instanceof Add || f instanceof Subtract || f instanceof Multiply || f instanceof Divide || f instanceof Pow) {

			f.left = recalculateValues(f.left);
			f.right = recalculateValues(f.right);
			f.compute(f.left , f.right);//THE METHOD THAT UPTDATES THE CONTENT OF THE NODE BASED ON SCROLLING (TO SCALE THE GRAPH) (implemented in Function class)
		}
		if(f instanceof Sine || f instanceof Secant || f instanceof Cosine ||f instanceof Tangent ||f instanceof GaussDistribution  ||
				f instanceof Log || f instanceof Exp || f instanceof Negate ) {
//			print(root);
			f.right = recalculateValues(f.right);
//			System.out.println("Somehow the b value" + f.toString());
			f = f.createValues();//this should probably be also renamed to compute At this point this is a real fckn mess but it works
//			f.compute(r, r);
		}
		if(f instanceof Factor) {
			f = f.createValues();
	//		System.out.println("recalculateVal afctor = " + f.toString());
		}
		if(f instanceof Variable) {

			f = f.createValues();
			
//			System.out.println("variable in recalculateValues() dX = " + Panel.dX);
		}
		}
		return f;

	}
	
	public Function parseExpressionAfterClosedParenthesis(Function a) {
		if(currT < size - 1) {
					if(currT < size && tokens[currT].equals( "*")) {
	//					System.out.println("multiply after ) Tree.currT = " + currT);
						currT++;
						Function c = parseF();
						a = new Multiply(a, c);
						System.out.println(a.toString());
					}
					if(currT < size && tokens[currT].equals( "/")) {
						currT++;
						Function c = parseF();
						a = new Divide(a, c);
					}else
					if(currT < size && tokens[currT].equals( "+")) {
						currT++;
						Function c = parseEprime();
						a = new Add(a, c);
					}else
					if(currT < size && tokens[currT].equals( "-")) {
						currT++;
						Function c = parseEprime();
						a = new Subtract(a, c);	
					}
					if(currT < size && tokens[currT].equals("^")) {
						currT++;
						Function c = parseF();
						a = new Pow(a, c);
					}
					if(currT < size && tokens[currT].equals("!")) {
						currT++;
						Function c = parseEprime();
						a = new Factorial(a);
					}
					 
					
				}
		return a;
	}
}
