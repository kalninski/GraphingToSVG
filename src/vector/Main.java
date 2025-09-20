package vector;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import statistics.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector v = new Vector();
		Vector v1 = new Vector(1, 2);
		v.x = 3;
		v.y = 4;
		
	//		v.normalize();
			
		
//		System.out.println(v.toString());
//		System.out.println("v1 dot v2  = " + v.dot(v1));
		Vector sum2 = Vector.multiplyByScaler(2, v);
//		System.out.println("v * 2 = " + sum2.toString());
		
		String sep = File.separator;
		String folder = "/Users/maksla/Desktop";
		

		


		Sine s = new Sine(5,4);
//		Cosine c = new Cosine(1,1);
//		Tangent tg = new Tangent(1,1);
//		GaussDistribution gd = new GaussDistribution(0.4, 0);
//	s.createValues();
//		GenerateXML xml = new GenerateXML(s, 0);

		
//		xml.saveVector(folder, "line139");


		
//		String svg1 =  """
//	            <?xml version="1.0" encoding="UTF-8"?>
//	            <svg width="1200" height="1000" xmlns="http://www.w3.org/2000/svg">
//	              <path d="%s" stroke="black" fill="none" stroke-width="2"/>
//	            </svg>
//	            """;
//		String svg = String.format(svg1, xml.svg);
//		
//		try {
//			FileWriter w = new FileWriter(folder + sep + "line136.svg");
//			w.write(svg);
//			w.close();
////			System.out.println(w.getEncoding());
//		}catch(IOException ex) {
//			ex.printStackTrace();
//		}
//		
	}

}
