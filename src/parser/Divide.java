package parser;

public class Divide extends Node {

	public Divide(Node a, Node b) {
		this.left = a;
		this.right = b;
		this.token = "( "+ a.token + " / " + b.token + " )";
	}
			@Override
	public String toString() {
		return "Divide node : " + left.token + " / " + right.token;
	}
}
