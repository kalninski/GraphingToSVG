package parser;

public class Node {
	
	Node left;
	Node right;

	String token;
	
	public Node() {}
	
	public Node(Node a, Node b) {
		this.left  = a;
		this.right = b;
	}
	
	public Node(String token) {
		this.token = token;
	}
	//call parseE from some other method 
//	public Node parseE() {
//		
//		Node a = parseT();
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "+") {
//			Tree.currT++;
//			Node b = parseEPrime();
//			a = new Add(a, b);
//			System.out.println(a.toString());
//		}
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "-") {
//			Tree.currT++;
//			Node b = parseEPrime();
//			a = new Subtract(a, b);
//			System.out.println(a.toString());
//		}		
//		return a;
//	}
//	
//	public Node parseT() {
//		
//		Node a = parseF();
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "*") {
//			Tree.currT++;
//			Node b = parseTPrime();
//			a = new Multiply(a, b);
//			System.out.println(a.toString());
//
//		}
//	
//		return a;
//	}
//		
//	
//	public Node parseEPrime() {
//		
//		Node a  = parseT();
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "+") {
//			Tree.currT++;
//			Node b = parseEPrime();
//			a = new Add(a, b);
//				System.out.println(a.toString());
////			return a;
//		}
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "-") {
//			Tree.currT++;
//			Node b = parseEPrime();
//			a = new Subtract(a, b);
//				System.out.println(a.toString());
////				return a;
////			return a;
//		}
//		
//		
//		return a;
//	}
//	
//	public Node parseTPrime() {
//
//		Node a = parseF();
//		if(  Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "*" ) {
//			Tree.currT++;
//			Node b = parseTPrime();
//			a = new Multiply(a, b);
//				System.out.println(a.toString());
//		}
//		if( Tree.currT < Tree.size && Tree.tokens[Tree.currT] == "/") {
//			Tree.currT++;
//			Node b = parseTPrime();
//			a = new Divide(a, b);
//				System.out.println(a.toString());
//		}
//		
//		return a;
//	}
//	
//	public Node parseF() {
//		
//		Node a = null; 
//		
//		if(Tree.tokens[Tree.currT] == "(") {
//			
//			Tree.currT++;
//			a = parseE();
//			if(Tree.tokens[Tree.currT] == ")") {
//				System.out.println("closing ) Tree.currT = " + Tree.currT);
//				Tree.currT++;
//				if(Tree.currT < Tree.size - 1) {
//					if(Tree.tokens[Tree.currT] == "*") {
//						System.out.println("multiply after ) Tree.currT = " + Tree.currT);
//						Tree.currT++;
//						Node b = parseF();
//						a = new Multiply(a, b);
//						System.out.println(a.toString());
//					}
//					if(Tree.tokens[Tree.currT] == "/") {
//						Tree.currT++;
//						Node b = parseF();
//						a = new Divide(a, b);
//					}
//					
//				}
//			}
//		}
//		
//		if(Tree.currT < Tree.size && Tree.tokens[Tree.currT] == ")"  ) {
//			System.out.println(")");
//			Tree.currT++;
//			
//			
//		}
//		//put all numbers in a global array of numbers -> If a node is a number node -> new Node(number);
//		if(Tree.currT < Tree.size && (Tree.tokens[Tree.currT] == "0" || Tree.tokens[Tree.currT] == "1" ||Tree.tokens[Tree.currT] == "2" ||Tree.tokens[Tree.currT] == "3" ||Tree.tokens[Tree.currT] == "4" ||Tree.tokens[Tree.currT] == "5" ||Tree.tokens[Tree.currT] == "6" ||Tree.tokens[Tree.currT] == "7" ||Tree.tokens[Tree.currT] == "8" ||Tree.tokens[Tree.currT] == "9")) {
//			a = new Node(Tree.tokens[Tree.currT]);
//			Tree.currT++;
//			
//		}
//		
//		if(Tree.tokens[Tree.currT] == "sin") {
//			Tree.currT++;
//			if(Tree.tokens[Tree.currT] == "(") {
//				Tree.currT++;
//			}else {
//				System.out.println("SYNTAX ERROR! FIX PARENTHESIS PLZ!");
//			}
//			try {
//					
//			Node b = parseE();
//			a = new Sine(b);
//			
//			}catch(NullPointerException ex) {
//				System.out.println(ex.getMessage());
//			}
//		}
//	return a;
//	}
//		
	@Override
	public String toString() {
		return "node";
	}

}
