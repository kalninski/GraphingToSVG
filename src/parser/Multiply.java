package parser;

public class Multiply extends Node{

	public Multiply(Node a, Node b) {
		this.left = a;
		this.right = b;
		this.token ="( " +  a.token + " * " + b.token + " )";
		
	}
			@Override
	public String toString() {
		return "Multiply node : " + this.token;
	}
}
