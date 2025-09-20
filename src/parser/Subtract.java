package parser;

public class Subtract extends Node{

	public Subtract(Node a, Node b) {
		this.left = a;
		this.right = b;
		this.token = "( " + a.token + " - " + b.token + " )";
	}
	
		@Override
	public String toString() {
		return "Subtract node : " + this.token;
	}
	
}
