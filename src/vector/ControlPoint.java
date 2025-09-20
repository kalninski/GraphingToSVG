package vector;

import java.util.Arrays;
import java.util.Locale;

import statistics.Function;
import java.io.*;


public class ControlPoint {
	
	Vector v0;
	Vector v1;
	Vector v2;
	Vector v3;
	Double[] functionY;//array of all the values
	Double[] functionX;
	double[] polynomialY;
	double[] polynomialX;
	double[] parameterT;
	double[] error;
	double maxError;
	int start;
	int end;
	Function f;
	String points;
	int index;
	
	
	public ControlPoint(Function f, int start, int end, int index) {
		this.f = f;
		this.index = index;
		this.functionX = f.subarraysX.get(index);
		this.functionY = f.subarraysY.get(index);
		this.polynomialX = new double[end - start];
		this.polynomialY = new double[end - start];
		this.error = new double[end - start];
		this.start = start;
		this.end = end;
		this.v0 = new Vector(functionX[start], functionY[start]);
		this.v3 = new Vector(functionX[end], functionY[end]);
		initialParameterT();
		//System.out.println("T parameter array = " + Arrays.toString(parameterT));
//		parameterTXT("NEWTON_RAPSON");
		setV1();
		setV2();
//		setV1_SecondVersion();
//		setV2_SecondVersion();
//		getValuesOfCurve();
		correctParameter();
//		setV1_SecondVersion();
//		setV2_SecondVersion();
		setV1();
		setV2();
		getValuesOfCurve();
		this.points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionX[start], functionY[start], this.v1.x, this.v1.y, this.v2.x, this.v2.y, functionX[end], functionY[end]);
//		System.out.println("COSNTRUCTOR polynomialY " + Arrays.toString(polynomialY));
//		System.out.println("COSNTRUCTOR polynomialX " + Arrays.toString(polynomialX));
		
	}
	
	
	public void initialParameterT() {
		double n = end - start;
		parameterT = new double[end - start];
		double increment = 1/n;
		double t = 0;
		for(int i = 0; i < n; i++) {
			parameterT[i] = t;
			t += increment;
		}
//		System.out.println("intitial paramter T arr = " + Arrays.toString(parameterT));
	}
	
	public void initialParameterT1() {
		double n = end - start;
		double totalDist = 0;
		parameterT = new double[end - start];
		double[] distances = new double[(int) (n-1)];
		double cumulativeDist = 0;
		if(n <= 1) {
			parameterT[0] = 0;
			return;
		}
		for(int i = 0; i < n - 1; i++) {
			double dy = functionY[start + i + 1] - functionY[start + i];
			double dx = functionX[start + i + 1] - functionX[start + i];
			double dyPow2 = Math.pow(dy, 2);
			double dxPow2 = Math.pow(dx, 2);
			totalDist += Math.sqrt(dyPow2 + dxPow2);
			distances[i] = Math.sqrt(dyPow2 + dxPow2);
			
		}
		if(totalDist <= 1E-10) {
			for(int k = 0; k < n; k++) {
				parameterT[k] = ((double) k) / n;

			}
		}else {
		parameterT[0] = 0;
		for(int j = 1; j < n; j++) {
			cumulativeDist += distances[j - 1];
			parameterT[j] = cumulativeDist/totalDist;

			
		}
		}
//		System.out.println("intitial paramter T arr = " + Arrays.toString(parameterT));
	}
	
	public int getErrorIndex(Function f) {
		int errorIndex = 0;
		maxError = 0;
		double errorX = 0;
		double errorY = 0;
		error = new double[end - start];
		for(int i = 0; i < error.length; i++) {
			errorY = Math.abs(f.subarraysY.get(index)[start + i] - polynomialY[i]);
			errorX = Math.abs(f.subarraysX.get(index)[start + i] - polynomialX[i]);
//			System.out.println("errorY = " + errorY + " errorX = " + errorX + " polynomialY[i] = " + polynomialY[i] + " polynomialX[i]" + polynomialX[i]);
			errorY = Math.pow(errorY, 2);
			errorX = Math.pow(errorX, 2);
			error[i] = Math.sqrt((errorX + errorY));
//			System.out.println("error[i] = " + error[i]);
			if(error[i] > error[errorIndex]) {
				maxError = error[i];
				errorIndex = i;
//				System.out.println(i + " INDEX ERROR");
				
				
			}
		
		}
		
//		System.out.println("error = " + Arrays.toString(error));
//		System.out.println("actualVal Y = " + Arrays.toString(f.yActualVal));
//		System.out.println("polynomial Y = " + Arrays.toString(polynomialY));
		return errorIndex;
	}
	
	public Vector getTangent1() {
		double count = Math.min(2, end - start - 1);
		double distanceV0V3 = dist(); 
		if(count <= 1 && distanceV0V3 < 2) {

//			System.out.println("Count is less than 1 " + (start + 1 < functionY.length));
			if(start + 1 < functionY.length) {
				//if start of the subarray is to too near the end of the full arraym then the angent is basically a line from one point to the othher
			return new Vector(functionX[start + 1] - functionX[start], functionY[start + 1] - functionY[start]);	
			}else {
				return new Vector(1, 0);
			}
		}
		Vector tg = new Vector(0, 0);
		double x = 0;
		double y = 0;
		double trueCount = 0;
		for(int i = 1; i < 2; i++) {
			
			if((start + i) < functionY.length) {
				
				double dx = functionX[start + i] - functionX[start];
				double dy = functionY[start + i] - functionY[start];
					
					if(Math.abs(dx) > 0 || Math.abs(dy) > 0) {
						tg.x += dx;
						tg.y += dy;
						trueCount++;
					}
					
				
			}
			
		}
		
		if(trueCount == 0 || count == 0) {
//			System.out.println("trueCount = 0 so vector is (1, 0)");
			return new Vector(1, 0);
		}


		tg.x = tg.x/count;
		tg.y = tg.y/count;
//			System.out.println("tg =  \t " + tg.toString() + " coutn  = " + count);	
		if((tg.x == 0 && tg.y == 0) || Double.isNaN((tg.x)) || Double.isNaN((tg.y)) || Double.isInfinite(tg.x) || Double.isInfinite(tg.y)) {
			System.out.println("What do you know?! the tg.x = " +  tg.toString() + "\t" + count);
						if(start + 1 < functionY.length) {
				//if start of the subarray is to too near the end of the full arraym then the angent is basically a line from one point to the othher
			return new Vector(functionX[start + 1] - functionX[start], functionY[start + 1] - functionY[start]);	
			}else {
				return new Vector(1, 0);
			}
			
		}
	
		return tg.normalize();
	}
		

	
	public Vector getTangent2() {
		Vector tg = new Vector(0, 0);
		double count = Math.min(2, end - start - 1);
		double x = 0;
		double y = 0;
		double trueCount = 0;
		double distanceV0V3 = dist();
		if(count <= 1 && distanceV0V3 < 2) {
			//if the subarray is of size 1, yet not start, return vector is the difference between current and next vecotr
//			System.out.println("new Vector(-1, 0) \t count = " + count + " v3 = " + this.v3.toString() + " (end - 1)" + (end - 1));
			if(end - 1 > 0) {
				return new Vector(functionX[end - 1] - functionX[end], functionY[end - 1] - functionY[end]);
			}else {
				return new Vector (-1, 0);
			}
		}
		for(int i = 1; i < 2; i++) {
			
		if((end - i) > 0) {
			double dx = functionX[end - i] - functionX[end];
			double dy = functionY[end - i] - functionY[end];
			if(Math.abs(dx) > 0 || Math.abs(dy) > 0) {
				tg.x += dx;
				tg.y += dy;
				trueCount++;
			}
			
		}
		
		}
		if(trueCount == 0 || count == 0) {
			return new Vector(-1, 0) ;
		}
		tg.x = tg.x/count;
		tg.y = tg.y/count;
		if((tg.x == 0 && tg.y == 0) || Double.isNaN((tg.x)) || Double.isNaN((tg.y)) || Double.isInfinite(tg.x) || Double.isInfinite(tg.y)) {

			
			if(start + 1 < functionY.length) {
				//if start of the subarray is to too near the end of the full arraym then the angent is basically a line from one point to the othher
			return new Vector(functionX[start + 1] - functionX[start], functionY[start + 1] - functionY[start]);	
			}else {
				return new Vector(-1, 0);
			}
		}
		
		return tg.normalize();
	}
	

	
	public double getC11() {
		int sizeParamT = parameterT.length;
		double sum = 0;
		for(int i = 0; i < sizeParamT; i++) {
			
			double t = parameterT[i];
			sum += Math.pow(1-t, 4) * Math.pow(t, 2) * 9;
		}
		return sum;
	}
	
	public double getC12C21() {
		int sizeParamT = parameterT.length;
		double sum = 0;
		Vector tan1 = getTangent1();
		Vector tan2 = getTangent2();
		double tan1DotTan2 = tan1.dot(tan2);
		for(int i = 0; i< sizeParamT; i++) {
			
			double t = parameterT[i];
			sum += Math.pow((1-t), 3) * Math.pow(t, 3) * 9 * tan1DotTan2;
		}
		
		return sum;
	}
	
	public double getC22() {
		int sizeParamT = parameterT.length;
		double sum = 0;
		for(int i = 0; i < sizeParamT; i++) {
			
			double t = parameterT[i];
			sum += Math.pow(t, 4) * Math.pow((1-t), 2) * 9;
		}
		
		return sum;
	}
/*NOTE !!!! - the reason why only the even 1/n  = t parameters work here is because I start the curve at the end not the start
 * like the result of the first term at t = 0 in fact results in the product of 
 */
	//One bernstein term specifically for getX1(), getX2() method
	public Vector bernstein(double t, Vector v0, Vector v1, Vector v2, Vector v3 ) {
		double coeff0 = Math.pow(1-t, 3);
		double coeff1 = Math.pow((1-t), 2) * t * 3;
		double coeff2 = Math.pow(t, 2) * (1-t) * 3;
		double coeff3 = Math.pow(t, 3);
		Vector newV0 = Vector.multiplyByScaler(coeff0, v0);
		Vector newV1 = Vector.multiplyByScaler(coeff1, v1);
		Vector newV2 = Vector.multiplyByScaler(coeff2, v2);
		Vector newV3 = Vector.multiplyByScaler(coeff3, v3);
		Vector sum = Vector.add4Vectors(newV0, newV1, newV2, newV3);
//		System.out.println("Bernestein T =  " + t + " coeff0 = " + coeff0 + " newV0 = " + newV0.toString() + " v0 = " + v0 );
		return sum;
	}
	
	public double getX1() {
		Vector tan1 = getTangent1();
		int size = parameterT.length;
		double sum = 0;
		
		for(int i = 0; i < size; i ++) {
			
			double t = parameterT[i];
			Vector dI = new Vector(functionX[start + i], functionY[start + i]);
			Vector bernstein = bernstein(t, v0, v0, v3, v3);
			Vector subtraction = Vector.subtract2Vectors(dI, bernstein);
			double coeffA1 = Math.pow((1-t), 2) * t * 3;
			Vector a1 = Vector.multiplyByScaler(coeffA1, tan1);
			sum += subtraction.dot(a1);
		}
		
		return sum;
	}
	
	public double getX2() {
		Vector tan2 = getTangent2();
		int size = parameterT.length;
		double sum = 0;
		
		for(int i = 0; i < size; i ++) {
			
			double t = parameterT[i];
			Vector dI = new Vector(functionX[start + i], functionY[start + i]);
			Vector bernstein = bernstein(t, v0, v0, v3, v3);
			Vector subtraction = Vector.subtract2Vectors(dI, bernstein);
			double coeffA2 = Math.pow(t, 2) * (1-t) * 3;
			Vector a2 = Vector.multiplyByScaler(coeffA2, tan2);
			sum += subtraction.dot(a2);
			
		}
		
		return sum;
	}
	
	public void setV1() {
		Matrix matrix = new Matrix();
		matrix.c11 = getC11();
		matrix.c12 = getC12C21();
		matrix.c21 = matrix.c12;
		matrix.c22 = getC22();
		double x1 = getX1();
		double x2 = getX2();
		double alpha1;
//		double epsilon = 1e-15;
		Vector t1 = getTangent1();
		Matrix num = new Matrix(x1, matrix.c12, x2, matrix.c22);
		double numerator = num.determinant();
		double denominator = matrix.determinant();
		Double numer = (Double) numerator;
		Double denomin = (Double) denominator;
//		if(Math.abs(denominator) < epsilon) {
		if(numer.isNaN() || denomin.isNaN() || numer == 0 || denomin == 0) {	
//			System.out.println("setV1()  \t numer = " + numer + " denomin = " +  denomin);

			t1 = Vector.multiplyByScaler(1, t1);
			
		}else {
			
			alpha1 = numerator/denominator;
			Double alp = (Double) alpha1;
			if(alp.isNaN()) {
//				System.out.println("denominator = " + denominator + "  numerator = " + numerator);
			}
			t1 = Vector.multiplyByScaler(alpha1, t1);
		
		}
		v1 = Vector.add2Vectors(v0, t1);
	//	System.out.println("set V1 = " + v1.toString());
	
//		System.out.println("setV1()  \t numer = " + numer + " denomin = " +  denomin + " v1 = " + v1.toString() + " v0 = " + v0.toString() + " t1 = " + t1.toString());
	}
	
	
	public void setV2() {
		Matrix matrix = new Matrix();
		matrix.c11 = getC11();
		matrix.c12 = getC12C21();
		matrix.c21 = matrix.c12;
		matrix.c22 = getC22();
		double x1 = getX1();
		double x2 = getX2();
		double alpha1;
		double epsilon = 1e-15;
		Vector t2 = getTangent2();
		Matrix num = new Matrix(matrix.c11, x1, matrix.c21, x2);
		double numerator = num.determinant();
		double denominator = matrix.determinant();
		Double numer = (Double) numerator;
		Double denomin = (Double) denominator;
//		if(Math.abs(denominator) < epsilon) {
		if(numer.isNaN() || denomin.isNaN() || numer == 0 || denomin == 0) {		
		//	alpha1 = 0;
//		System.out.println("setV2()  \t numer = " + numer + " denomin = " +  denomin);
			
			t2 = Vector.multiplyByScaler(1, t2);
			
		}else {
			
			alpha1 = numerator/denominator;
		Double alp = (Double) alpha1;
			if(alp.isNaN()) {
//				System.out.println("denominator = " + denominator + "  numerator = " + numerator);
			}
			t2 = Vector.multiplyByScaler(alpha1, t2);
		}
		
		v2 = Vector.add2Vectors(v3, t2);
//		System.out.println("setV2()  \t numer = " + numer + " denomin = " +  denomin + " v2 = " + v2.toString());
	}
	

	
	
	public void getValuesOfCurve() {
		int size = parameterT.length;
		for(int i = 0; i < size; i++) {
			double t = parameterT[i];
			Vector lerpVec = bernstein(t, v0, v1, v2, v3);
			polynomialY[i] = lerpVec.y;
			polynomialX[i] = lerpVec.x;
	//	System.out.println("lerped vec with bernstein = " + lerpVec.toString() );
		}
	}
	
	public void parameterTXT(String name) {
		String sep = File.separator;
		File f = new File("/Users/maksla/Desktop" + sep  + name + ".txt");
		
		try {
			if(!f.equals(f)) {
				FileWriter w = new FileWriter(f);
				w.write("Parameter arrays 1");
			}
			BufferedWriter write = new BufferedWriter(new FileWriter(f, true));
			if(parameterT.length < 5) {
				write.write("!!!!!!!!!!!!!" + Arrays.toString(this.parameterT));
			}else {
			write.write(Arrays.toString(this.parameterT));
			}
			write.newLine();
			write.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public Vector getOneValueOfCurve(double t) {
		double coeff0 = Math.pow((1-t), 3);
		double coeff1 = Math.pow((1-t), 2) * t * 3;
		double coeff2 = Math.pow(t, 2) * (1-t) * 3;
		double coeff3 = Math.pow(t, 3);
		Vector v0New = Vector.multiplyByScaler(coeff0, v0);
		Vector v1New = Vector.multiplyByScaler( coeff1, v1);
		Vector v2New = Vector.multiplyByScaler(coeff2, v2);
		Vector v3New = Vector.multiplyByScaler(coeff3, v3);
		return Vector.add4Vectors(v0New, v1New, v2New, v3New);
	}
	
	public Vector getOneValueOfCurveFirstDerivative(double t) {
		double coeff0 = Math.pow((1-t), 2);
		double coeff1 = (1-t) * t * 2;
		double coeff2 = Math.pow(t, 2);
		Vector deltaV0 = Vector.subtract2Vectors(v1, v0);
		Vector deltaV1 = Vector.subtract2Vectors(v2, v1);
		Vector deltaV2 = Vector.subtract2Vectors(v3, v2);
		Vector v0New = Vector.multiplyByScaler(coeff0, deltaV0);
		Vector v1New = Vector.multiplyByScaler(coeff1, deltaV1);
		Vector v2New = Vector.multiplyByScaler(coeff2, deltaV2);
		Vector sum = Vector.add3Vectors(v0New, v1New, v2New);
		return Vector.multiplyByScaler(3, sum);
	}
	
	public Vector getOneValueOfCurveSecondDerivative(double t) {
		double coeff0 = 1-t;
		double coeff1 = t;
		Vector delta1V0 = Vector.subtract2Vectors(v1, v0);
		Vector delta1V1 = Vector.subtract2Vectors(v2, v1);
		Vector delta1V2 = Vector.subtract2Vectors(v3, v2);
		Vector delta2V0 = Vector.subtract2Vectors(delta1V1, delta1V0);
		Vector delta2V1 = Vector.subtract2Vectors(delta1V2, delta1V1);
		delta2V0 = Vector.multiplyByScaler(coeff0, delta2V0);
		delta2V1 = Vector.multiplyByScaler(coeff1, delta2V1);
		Vector sum = Vector.add2Vectors(delta2V1, delta2V0);
		return Vector.multiplyByScaler(6 ,sum);
	}
	
	public double updateTParameter(double t, int index) {
		
		Vector qT = getOneValueOfCurve(t);
		Vector dI = new Vector(functionX[start + index], functionY[start + index]);
		Vector subtraction = Vector.subtract2Vectors(qT, dI);
		Vector qPrimeT = getOneValueOfCurveFirstDerivative(t);
		double numerator = subtraction.dot(qPrimeT);
		double productRule1 = qPrimeT.dot(qPrimeT);
		Vector qDoublePrimeT = getOneValueOfCurveSecondDerivative(t);
		double productRule2 = subtraction.dot(qDoublePrimeT);
		double denominator = productRule1 + productRule2;
		double fraction;
		if(Math.abs(denominator) > 0) {
		fraction = numerator/denominator;
//		System.out.println(" UPDATE PARAMETER Fraction = numerator/denominator " + fraction);
		}else {
			
			fraction = 0;
		}
		t = t - fraction;
		t = Math.max(0, Math.min(1, t));
//		System.out.println("the new t = " + t + " old t = "  + (t + fraction));
		return t;
	}
	
	public void correctParameter() {
		int size = parameterT.length;
		for(int i = 0; i < size; i++) {
			double t = parameterT[i];
			double oldT;
			double epsilon = 1e-6;
			for(int j = 0; j < 10; j++) {
				oldT = t;
				
				t = updateTParameter(t, i);
				if(Math.abs(t - oldT) <= epsilon) {
					return;
				}
				
			}
			parameterT[i] = t;
		}
	}
	

	//Use tangents of another control point
	public void setInnerPointsLeft(ControlPoint cp) {
		Vector t1 = new Vector();

		t1 = Vector.subtract2Vectors(cp.v1, cp.v0);
//		t2 = Vector.subtract2Vectors(cp.v3, cp.v2);
		t1 = Vector.multiplyByScaler(-1, t1);
//		t2 = Vector.multiplyByScaler(-1, t2);
		t1 = t1.normalize();
//		t2 = t2.normalize();
		this.v1 = Vector.add2Vectors(this.v0, t1);
//		this.v2 = Vector.add2Vectors(this.v3, t2);
		this.points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionX[start], functionY[start], this.v1.x, this.v1.y, this.v2.x, this.v2.y, functionX[end], functionY[end]);
	}
	
	//Use tangents of another control point
	public void setInnerPoints(ControlPoint cp) {
		Vector t1 = new Vector();
		Vector t2 = new Vector();
		t1 = Vector.subtract2Vectors(cp.v1, cp.v0);
		t2 = Vector.subtract2Vectors(cp.v3, cp.v2);
		t1 = Vector.multiplyByScaler(-1, t1);
		t2 = Vector.multiplyByScaler(-1, t2);
		t1 = t1.normalize();
		t2 = t2.normalize();
		this.v1 = Vector.add2Vectors(this.v0, t1);
		this.v2 = Vector.add2Vectors(this.v3, t2);
		this.points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionX[start], functionY[start], this.v1.x, this.v1.y, this.v2.x, this.v2.y, functionX[end], functionY[end]);
	}
	
	public void changeControlPoint(ControlPoint cp, Vector v) {
		Vector t = Vector.subtract2Vectors(v, cp.v0);
	}


	public double getMagnitude() {
		Vector dV = Vector.subtract2Vectors(v3, v0);
		double dX = dV.x;
		double dY = dV.y;
		double distance = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		return distance;
	}
	
	//CONTINUITY CONSTRAINT FOR SUB-CURVES OS SAMLL LENGHT -> THE V1 VECTOR GETS ADJUSTED TO THE V2 TANGENT OF THE PREVIOUS SUB-CURVE
	public void setV1fromPrev(ControlPoint cp) {
		
		Vector t2 = getOneValueOfCurveFirstDerivativeLast(1.0, cp);//tangent of the previous sub-curve

		

		t2 = t2.normalize();
		Vector t1 = Vector.subtract2Vectors(this.v1, this.v0);//the vector to be adjusted to the last one
		double lenT1 = Vector.vecMagnitude(t1);
		t1 = Vector.multiplyByScaler(lenT1, t2);
//		t1 = t1.normalize();
		t1 = Vector.multiplyByScaler(0.1, t1);//from the derivative of bezier curve Q'(1) the derivative vector is pointing from v2 to v3 if added to v2, so no need to multiply by -1
		this.v1 = Vector.add2Vectors(this.v0, t1);

		
	}
	
	public boolean isExtremePoint() {
		
		if((functionY[end] > functionY[end - 1]) && (functionY[end] > functionY[end + 1]) && ((end + 1) < functionY.length || (end - 1) > 0)) {
			return true;
		}else {
		
			return false;
		}
	}
	
	public void setV2fromNext(ControlPoint cp) {
		Vector t1 = getOneValueOfCurveFirstDerivativeLast(0.0,cp);

		

		t1 = t1.normalize();
		
		Vector t2 = Vector.subtract2Vectors(this.v2, this.v3);
		double lenT2 = Vector.vecMagnitude(t2);
		t2 = Vector.multiplyByScaler(lenT2, t1);

		t2 = Vector.multiplyByScaler((-0.1), t2);

		this.v2 = Vector.add2Vectors(this.v3, t2);
		}
		
	
	//WTF
	public Vector getOneValueOfCurveFirstDerivativeLast(double t,ControlPoint cp) {
		double coeff0 = Math.pow((1-t), 2) * 3;
		double coeff1 = (1-t) * t * 6;
		double coeff2 = Math.pow(t, 2) * 3;
		Vector deltaV0 = Vector.subtract2Vectors(cp.v1, cp.v0);
		Vector deltaV1 = Vector.subtract2Vectors(cp.v2, cp.v1);
		Vector deltaV2 = Vector.subtract2Vectors(cp.v3, cp.v2);
		Vector v0New = Vector.multiplyByScaler(coeff0, deltaV0);
		Vector v1New = Vector.multiplyByScaler(coeff1, deltaV1);
		Vector v2New = Vector.multiplyByScaler(coeff2, deltaV2);
		Vector sum = Vector.add3Vectors(v0New, v1New, v2New);
		return sum;
	
	}
	//compare continuity with the last point of current curve and first point of next curve
	public boolean isContinuosThisLastNextFirst(ControlPoint cp) {
		Vector ddtCurr = this.getOneValueOfCurveFirstDerivative(1.0);
		Vector ddtNext = cp.getOneValueOfCurveFirstDerivative(0.0);
		ddtCurr.normalize();
		ddtNext.normalize();
		if(ddtCurr.x == ddtNext.x && ddtCurr.y == ddtNext.y) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public double dotThisV2NextV1(ControlPoint cp) {
		Vector t2This = Vector.subtract2Vectors(this.v2, this.v3);
		Vector t1Next = Vector.subtract2Vectors(cp.v1, cp.v0);
		double dotProduct = t2This.dotNormaized(t1Next);
		return dotProduct;
	}
	
	//This method may not be useful at all
	public double dotThisV1LastV2(ControlPoint cp) {
		Vector t1This = Vector.subtract2Vectors(this.v1, this.v0);
		Vector t2Last = Vector.subtract2Vectors(cp.v2, cp.v3);
		double dotProduct = t1This.dotNormaized(t2Last);
		return dotProduct;	
		
	}
	
	
	@Override
	public String toString() {
		return "t1 = "  + (Vector.subtract2Vectors(this.v1, this.v0)) + " t2 = " +  (Vector.subtract2Vectors(this.v2, this.v3)) + " v0 = " + this.v0.toString() + " v3 = " + this.v3.toString();
	}
 
	public double dist() {
		Vector dV0V3 = Vector.subtract2Vectors(v0, v3);
		double dXV0V3 = dV0V3.x;
		double dYV0V3 = dV0V3.y;
		double len = Math.sqrt(dYV0V3 * dYV0V3 + dXV0V3 * dXV0V3 );
		return len;
	}
}