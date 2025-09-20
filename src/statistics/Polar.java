package statistics;

public class Polar extends Function {

	public Polar(Function x, Function y) {
		this.left = x;
		this.right = y;
		changeRangeForAllNodes(this);
		compute(x, y);
	}
	
	public Polar(SyntaxTree x, SyntaxTree y) {
		this.left = x.root;
		this.right = y.root;
		compute(x, y);
	}
	
	public Polar(SyntaxTree r) {
		
		changeRangeForAllNodes(r.root);
		Function right = new Multiply(r.root, new Sine(new VariableT()));
		Function left = new Multiply(r.root, new Cosine(new VariableT()));
		this.right = right;
		this.left = left;
		compute(this.left, this.right);
	}
	
	public void compute(SyntaxTree l, SyntaxTree r) {
		compute(l.root, r.root);
	}
	
	@Override
	public void compute(Function l, Function r) {
		changeRangeForAllNodes(this);
		for(int i = 0; i < Panel.WIDTH * 2; i++) {
			yActualVal[i] = r.yActualVal[i];
			xActualVal[i] = l.yActualVal[i];
		}
		
	}
	
	@Override
	public Function createValues() {
		
	return this;
	}
	
	public void convertToCartesianX(Function l) {

		for(int i = 0; i < Panel.WIDTH * 2; i++) {
			xActualVal[i] = l.yActualVal[i] * Math.cos(l.xActualVal[i]);
		}
	}
	public void convertToCartesianY(Function r) {

		for(int i = 0; i < Panel.WIDTH * 2; i++) {
			yActualVal[i] = r.yActualVal[i] * Math.sin(r.xActualVal[i]);
		}
	}
	public static void changeRangeForAllNodes(Function f) {
		if(f != null) {
			double oneUnit = ((double) Panel.WIDTH * 2) / (Panel.dX * Math.PI);
			f.numUnits = Math.PI * 2;
			f.oneIncriment = 1/oneUnit;
	
			if(f.left != null) {
				changeRangeForAllNodes(f.left);
			}
			if(f.right != null) {
				changeRangeForAllNodes(f.right);
			}
		}
	}
	
	@Override
		public void refresh() {
		int size = Panel.WIDTH * 2; //*2
	
		for(int y = 0; y < size; y++) {
			yActualVal[y] =  Panel.HEIGHT/2 - yActualVal[y]* Panel.dX + moveUpDown;
			xActualVal[y] =  Panel.WIDTH/2 + (xActualVal[y] * Panel.dX - moveSide/2);
			//x here just like y must be treated the same 
			yC[y] = (int) (yActualVal[y]);
			xC[y] = (int) (xActualVal[y]);


		}
	//	System.out.println("refresh() xC = " + Arrays.toString(xActualVal)  + " \n yC = " + Arrays.toString(yActualVal));
	}
	
}
