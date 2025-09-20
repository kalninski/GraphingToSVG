package vector;

public class Node {
	
	ControlPoint cp;
	String coordinates;
	Node left;
	Node right;
	
	public Node() {}
	
	public Node(ControlPoint cp) {
		this.cp = cp;
	}
	
	public Node(ControlPoint cp, Node left, Node right) {
		this(cp);
		this.right = right;
		this.left = left;
	}
	
	@Override
	public String toString() {
		return " NODE START = "+ this.cp.start + " END = " + this.cp.end;
	}

}



