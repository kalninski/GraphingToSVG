package statistics;

public class ParametricEq extends Function{

	public ParametricEq(Function x, Function y) {
		this.left = x;
		this.right = y;
//		changeRangeForAllNodes(this);
		compute(x, y);
	}
	
	public ParametricEq(SyntaxTree x, SyntaxTree y) {
		this.left = x.root;
		this.right = y.root;
		compute(x, y);
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
	

/*THE COMMENT PRECEEDIN THIS ONE IS IN FUNCTION CLASS, UNDER "WHY DIVIDE BY 2?"
 * For parametric equations there is no such downscalibg by 1/2 as it is for explicit functions in this program, so because everywhere else the the x and y
 * are scaled upfor various reasons the x coorfinate is the only one that does not end up being scaled down just like all other functions, so here it must be scaled down
 * directly because all is scaled by the Panel class with the static Function class methods which do not solve this. 
 */
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
	
//	public void changeBounds(Function f, double low, double high) {
//		if(f != null) {
//			if(f instanceof VariableT) {
//				
//			}
//		}
//	}
	

	
}
