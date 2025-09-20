package parser;

public class Sine extends Node{
	
	public Sine(Node b) {
//		this.left = a;
		this.right = b;
		this.token = "sin( " + b.token + " )";
		System.out.println("Sine node made! ");
		
	}
	
	@Override
	public String toString() {
		return "Sine node: " + this.token;
	}

}
