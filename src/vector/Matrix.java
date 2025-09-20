package vector;

public class Matrix {
	
	double c11;
	double c12;
	double c21;
	double c22;
	
	public Matrix() {}
	
	public Matrix(double c11, double c12, double c21, double c22) {
		this.c11 = c11;
		this.c12 = c12;
		this.c21 = c21;
		this.c22 = c22;
	}
	
	@Override
	public String toString() {
		return "c11 = " + c11 + " c12 = " + c12 + " c21 = " + c21 + " c22 = " + c22;
	}
	
	public double determinant() {
		double c11c22 = this.c11 * this.c22;
		double c12c21 = this.c12 * this.c21;
		return (c11c22 - c12c21);
	}
	
	
	

}
