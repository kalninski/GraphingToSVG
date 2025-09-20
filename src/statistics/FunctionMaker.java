package statistics;

public class FunctionMaker {
	
	public Functions type;
	
	String[] tokens = new String[3];
	String input;
	String function;
	double a;
	double b;
	Functions nameOfFunction;
	
	public FunctionMaker(String input) {
		this.input = input;
		System.out.println("constructor call to FunctionMaker");
		for(Functions f : Functions.values()) {
//			System.out.println(f.f + "    " + input);
			if(f.f.equals(input)) {
				nameOfFunction = f;
				functionFactory();
	//			System.out.println("new Sine");
			}
		}
	}
	
	public void setTokensForTrig(String input) {
		int size = input.length();
		for(int i = 0; i < size; i++) {
			char c = input.charAt(i);
			if(c == '(' || c == ')') {
				input = input.replace(c, '*');
			}
		}
		tokens = input.split("*");
		a = Double.valueOf(tokens[0]);
		b = Double.valueOf(tokens[2]);
	}
	//PUT THIS METHOD INSISDE THE CONSTRUCTOR TO EVALUEATE THE INPUT EVERY TIME THE BUTTON IS PRESSED AND IT WILL INSTANTIATE THE RIGHT FUNCTION BASED ON THE INPUT
	public void functionFactory() {
		//Switch statement with all the functions
		//Constructor loops trough the enum -> when finds match sets the field of nameOfFunction to the enum name i.e. SINE, COSINE
		//-> That is the input for this method
		
		switch(nameOfFunction) {
			case SINE:
				new Sine(1,1);
				break;
			case COSINE:
				new Cosine(0.5,1);
				break;
			case GAUSS:
				new GaussDistribution(0.5, 0);
				break;
			case TANGENT:
				new Tangent(5, 4);
				break;
			case SECANT:
				new Secant(1, 1);
				break;
//			case POW:
//				new Pow(3);
//				break;
			case LOG:
				new Log(1, 1, 10);
				break;
		}
		Panel.repaintAfterInputOfNewFunction = true;
	}

}
