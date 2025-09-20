package vector;

public class Vector {
	
	double x;
	double y;
	
	public Vector() {}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "x = " + x + " y = " + y;
	}
	
	public Vector normalize() {
		double newX = Math.pow(x, 2);
		double newY = Math.pow(y, 2);
		double sum = newX + newY;
		sum = Math.sqrt(sum);
		Vector v = new Vector();
		v.x = this.x/sum;
		v.y = this.y/sum;
		return v;
	}
	
	public double dot(Vector v) {
		return (this.x * v.x + this.y * v.y); 
	}
	
	public static Vector add2Vectors(Vector v0, Vector v1) {
		Vector sum = new Vector();
		sum.x = v0.x + v1.x;
		sum.y = v0.y + v1.y;
		return sum;
	}
	
	public static Vector add3Vectors(Vector v0, Vector v1, Vector v2) {
		Vector sum = new Vector();
		sum.x = v0.x + v1.x + v2.x;
		sum.y = v0.y + v1.y + v2.y;
		return sum;
	}
	
	public static Vector add4Vectors(Vector v0, Vector v1, Vector v2, Vector v3) {
		Vector sum = new Vector();
		sum.x = v0.x + v1.x + v2.x + v3.x;
		sum.y = v0.y + v1.y + v2.y + v3.y;
		return sum;
	}
	
	public static Vector subtract2Vectors(Vector v0, Vector v1) {
		Vector sum = new Vector();
		sum.x = v0.x - v1.x;
		sum.y = v0.y - v1.y;
		return sum;
	}
	
	public static Vector multiplyByScaler(double scaler, Vector v) {
		Vector newV = new Vector();
		double newX = v.x * scaler;
		double newY = v.y * scaler;
		newV.x = newX;
		newV.y = newY;
		return newV;
		
	}
	public static double vecMagnitude(Vector v) {
		double dx = v.x;
		double dy = v.y;
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		return dist;
	}
	
	public double dotNormaized(Vector v) {
		Vector v1 = this.normalize();
		Vector v2 = v.normalize();
		double dotProduct = v1.x * v2.x + v1.y * v2.y;
		return dotProduct;
	}
}
