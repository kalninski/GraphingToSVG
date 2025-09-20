package vector;

import java.util.Locale;
import statistics.Function;
import java.util.*;
import java.io.*;


public class GenerateXML {
	
	public Function f;
	Double[] functionArrX;
	Double[] functionArrY;
	double[] errorArr;
	int start;
	int end;
	public Node root;
	ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();

	
	public StringBuilder svg = new StringBuilder();
	public String restXML =  "\" stroke=\"black\" fill=\"none\" stroke-width=\"2\"/>";
							
			        

	
	public GenerateXML(Function f,int index) {
		this.f = f;
		this.functionArrY = f.subarraysY.get(index);
		this.functionArrX = f.subarraysX.get(index);
		root = new Node();
		root.cp = new ControlPoint(f, start, end, index);
		createXML(0, f.subarraysX.get(index).length - 1, root, index);
		iterate(root);
		continuityConstraintG1();	
	}
	
	
	
	
	public void createXML(int start, int end, Node node, int index) {
		
		node.cp = new ControlPoint(f, start, end, index);
		
		int e = node.cp.getErrorIndex(f);
		String points;
		
		if(node.cp.maxError > 1) {
			
			Node left = new Node();
			node.left  = left;
			
			createXML( start, start + e, node.left, index);
			
			Node right = new Node();
			node.right = right;
			createXML( start + e, end, node.right, index);
		}else {
		}

	}
	

	
	//ADD TO ARRAY LIST DURING THE CREATION OF SUBCURVES IN createXML1() method!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NOT here
	public void iterate(Node node) {


		if(node != null) {
			
			if(node.left == null && node.right == null) {
				
//				String points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionArrX[node.cp.start], functionArrY[node.cp.start], node.cp.v1.x, node.cp.v1.y, node.cp.v2.x, node.cp.v2.y, functionArrX[node.cp.end], functionArrY[node.cp.end]);

				controlPoints.add(node.cp);
				
			}
			
		}
		if(node.left != null) {
			iterate(node.left);
		}
		if(node.right != null) {
			iterate(node.right);
		}
	}
	
	public void continuityConstraintG1() {
		int size = controlPoints.size();
		int counter = 0;
		
		
		for(ControlPoint cp: controlPoints) {
			if(counter + 1 < size) {
				Vector t = Vector.subtract2Vectors(cp.v0, cp.v3);
				double distCurr = Vector.vecMagnitude(t);
				ControlPoint nextCP = controlPoints.get(counter + 1);
				double distNext = nextCP.getMagnitude();
				double dotThisV2NextV1 = cp.dotThisV2NextV1(nextCP);
				double dotThisV1LastV2 = cp.dotThisV1LastV2(nextCP);
			if(counter > 0) {

				ControlPoint lastCP = controlPoints.get(counter - 1);

				
				if((distCurr <= 50 && dotThisV1LastV2 < 0.99 && dotThisV1LastV2 > -0. ) || ( Double.valueOf(cp.v1.x).isNaN() || Double.valueOf(cp.v1.y).isNaN()) || cp.isExtremePoint()) {
//					System.out.println("V1 ONLY \t cp.v1.x = " + cp.v1.x + " cp.v1.y = " +cp.v1.y + " cp.v2.x = " + cp.v2.x + " cp.v2.y = " + cp.v2.y +  " cp.v0.x = " + cp.v0.x + " cp.v0.y = " + cp.v0.y );
							System.out.println("distCurr =  " + distCurr + "  dotThisV1LastV2 = " + dotThisV1LastV2 + " cp.isExtremePoint() = " + cp.isExtremePoint() + cp.toString());
					cp.setV1fromPrev(lastCP);
//					System.out.println("V1 ONLY AFTER \t cp.v1.x = " + cp.v1.x + " cp.v1.y = " +cp.v1.y + " cp.v2.x = " + cp.v2.x + " cp.v2.y = " + cp.v2.y);
				}
				if((distCurr < 50 && distNext > 50 && dotThisV2NextV1 < 0.99 && dotThisV2NextV1 > -0.99) || (( Double.valueOf(cp.v1.x).isNaN() || Double.valueOf(cp.v1.y).isNaN()) || ( Double.valueOf(cp.v2.x).isNaN() || Double.valueOf(cp.v2.y).isNaN()))) {
	//				System.out.println("v0 to v3 next point= " + (distNext > 50)  + " v0 to v3 currP = " + (distCurr <= 50));
	//				System.out.println("Double.valueOf(cp.v3.x).isNaN()= " + (Double.valueOf(cp.v3.x).isNaN())  + "Double.valueOf(cp.v3.y).isNaN() = " + Double.valueOf(cp.v3.y).isNaN());
					System.out.println("distCurr =  " + distCurr + " <  distNext = " + distNext + "  " + cp.toString());
//					System.out.println("V1 && V2 \t cp.v1.x = " + cp.v1.x + " cp.v1.y = " +cp.v1.y + " cp.v2.x = " + cp.v2.x + " cp.v2.y = " + cp.v2.y);
			//		cp.setV1fromPrev(lastCP);
					cp.setV2fromNext(nextCP);

				}
				

			}else if((distCurr < 50 && distNext > 50 && dotThisV2NextV1 < 0.99 && dotThisV2NextV1 > -0.99)  || Double.valueOf(cp.v2.x).isNaN() || Double.valueOf(cp.v2.y).isNaN()) {
					
					System.out.println("setV2FromNext DotThisV2nextV1  = "+ dotThisV2NextV1 + "  " + cp.toString());
					cp.setV2fromNext(nextCP);
					
				}

			}
			String points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionArrX[cp.start], functionArrY[cp.start], cp.v1.x, cp.v1.y, cp.v2.x, cp.v2.y, functionArrX[cp.end], functionArrY[cp.end]);
			counter ++;
			
			svg.append(points);

			
			
		}
	

	}
	public void continuityConstraintG1Version2() {
		int size = controlPoints.size();
		int counter = 0;
		
		
		for(ControlPoint cp: controlPoints) {
			
			
			if(counter + 1 < size) {
				Vector t = Vector.subtract2Vectors(cp.v0, cp.v3);

				ControlPoint nextCP = controlPoints.get(counter + 1);
				
				ControlPoint lastCP = controlPoints.get(counter - 1);
				double dotProdThisV2NextV1 = cp.dotThisV2NextV1(nextCP);
	//			double dotProdThisV1LastV2 = cp.dotThisV1LastV2(lastCP);
					
					if(Double.valueOf(cp.v0.x).isNaN() || Double.valueOf(cp.v0.y).isNaN()) {
						System.out.println("cp.v0.x = " + cp.v0.x + " cp.v0.y = " + cp.v0.y);
							cp.setV1fromPrev(lastCP);

					}
						
					if((dotProdThisV2NextV1 > -1 && dotProdThisV2NextV1 < 1) || Double.valueOf(cp.v3.x).isNaN() || Double.valueOf(cp.v3.y).isNaN()) {
					System.out.println("DotProdThisV2NextV1 < 1= " + (dotProdThisV2NextV1 < 1)  + "DotProdThisV2NextV1 > -1 = " + (dotProdThisV2NextV1 > -1));
					System.out.println("Double.valueOf(cp.v3.x).isNaN()= " + (Double.valueOf(cp.v3.x).isNaN())  + "Double.valueOf(cp.v3.y).isNaN() = " + Double.valueOf(cp.v3.y).isNaN());
					cp.setV2fromNext(nextCP);
				}
			}
				
				
			String points = String.format(Locale.US,"M %.2f,%.2f C %.2f,%.2f %.2f,%.2f %.2f,%.2f",  functionArrX[cp.start], functionArrY[cp.start], cp.v1.x, cp.v1.y, cp.v2.x, cp.v2.y, functionArrX[cp.end], functionArrY[cp.end]);
			counter ++;
			
			svg.append(points);

			
			
		}
	

	}
			public void saveVector(String folder, String fileName) {
				String sep = File.separator;
				
				String svg1 =  """
						<?xml version="1.0" encoding="UTF-8"?>
						       <svg width="1200" height="1000" xmlns="http://www.w3.org/2000/svg">
						         <path d="%s" stroke="black" fill="none" stroke-width="2"/>
						      
						       """;
		String svg = String.format(svg1, this.svg);
		
			try {
				FileWriter w = new FileWriter(folder + sep + fileName +".svg");
				w.write(svg);
				w.close();
//			System.out.println(w.getEncoding());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			}
	
}