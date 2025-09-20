package statistics;

import java.util.*;
import java.util.random.*;

public class GaussDistribution extends Function {
	
	public double deviation;
	public double mean;
	Function b;
	public GaussDistribution() {
		
	}
	
	public GaussDistribution(Function b) {
		this.b = b;
		createValues();
	}
	
	public GaussDistribution(double deviation, double mean) {
		this.deviation = deviation;
		this.mean = mean;
		createValues();

	}
	
	public double gaussPDF(double x) {
		double result = 0;
		double coeff = 10 * Panel.scaler/(Math.sqrt(2 * Math.PI) * deviation);
		double exponent = (-1) * ((x - (mean * 10 * Panel.scaler)) * (x - (mean * 10 * Panel.scaler)) / (2 * deviation * deviation * 100 * Panel.scaler * Panel.scaler));
		result = coeff * Math.exp(exponent);
		return result;
	}



	
	
	@Override
	public Function createValues() {
		int counter = 0;
		for(int x = 0; x < Panel.WIDTH; x++) {
			double distributionVal = gaussPDF(b.yActualVal[x]);

			this.xActualVal[x] = (double) x;
			this.yActualVal[x] = distributionVal;
			this.xC[x] = x;
			this.yC[x] = (int) distributionVal;

		}

		
		return this;
		
	}
	
}
