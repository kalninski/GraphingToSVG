package parser;

public class Add extends Node{
	
	public Add(Node a, Node b) {
		this.left = a;
		this.right = b;
		this.token = "( " + a.token + " + " + b.token + " )";
	}
	
	@Override
	public String toString() {
		return "Add node : "  + this.token;
	}

}
