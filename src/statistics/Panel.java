package statistics;

import java.util.List;

 import javax.swing.filechooser.*;
 import java.io.*;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.geom.*;
import java.util.*;
import javax.imageio.ImageIO;
import  javax.swing.*;
import java.awt.AWTEventMulticaster;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import vector.*;

public class Panel extends JPanel implements MouseWheelListener, MouseListener,MouseMotionListener, Runnable{
	public static String sep = File.separator;
	

	public static List<SyntaxTree> listOfFunctions = Collections.synchronizedList(new ArrayList<SyntaxTree>());

	public static boolean repaintAfterInputOfNewFunction = false;
	public static final int HEIGHT = 1000;
	public static final int WIDTH = 1200;
	public static double scaler = 1;
	public static double dX;
	public boolean isLooping = true;
	public boolean saveGraph = false;
	public ScreenShot screenShot = new ScreenShot();
	public BufferedImage img;
	public BufferedImage img1;
	public Graphics2D g2d;
	public Graphics2D g2d1;
	public Graphics2D g2d2;
	public static String folder;//Default location, but should be set to desktop or something
				public String svg1 = """
						<?xml version="1.0" encoding="UTF-8"?>
						       <svg width="1200" height="1000" xmlns="http://www.w3.org/2000/svg">
						       """;
	public static String fileName = "GraphByGrapher";
	public String extention;
	public Grid grid;
	Action save;
	Action moveLeft;
	Action moveRight;
	Action moveDown;
	Action moveUp;
	Action removeLastFunction;
	
	
	
	public Panel() {

		addMouseWheelListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
//		addKeyListener(this);
		
		grid = new Grid();

		Thread t = new Thread(this);
		t.start();
		save = new SaveAction();
		moveLeft = new LeftWard();
		moveRight = new RightWard();
		moveUp = new UpWard();
		moveDown = new DownWard();
		removeLastFunction = new RemoveLastFunction();
		
		InputMap saveAction = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//		InputMap left = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap map = this.getActionMap();
		InputMap removeLast = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap removeLastMap = this.getActionMap();
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("H"), "left");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("L"), "right");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "saveAction");//puts the respective key strokes in a group and associates with a String name of an action
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "removeLastFunction");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("K"), "up");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("J"), "down");
		map.put("saveAction", save);//The String name of the action that is associated with teh set of keys is associated with my own defined action
		removeLastMap.put("removeLastFunction", removeLastFunction);
		this.getActionMap().put("up", moveDown);
		this.getActionMap().put("down", moveUp);
		this.getActionMap().put("left", moveRight);
		this.getActionMap().put("right", moveLeft);
		System.out.println(Arrays.toString(saveAction.keys()));
	}



	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		img = new BufferedImage(1200, 1000, BufferedImage.TYPE_INT_ARGB);
//		img1 = new BufferedImage(1200, 1000, BufferedImage.TYPE_INT_ARGB);
//		g2d1 = img.createGraphics();
		g2d = (Graphics2D) g;
		
		Rectangle2D.Double back = new Rectangle2D.Double(0, 0, 1200 , 1000);
		g2d.setColor(new Color(255, 255, 255));
		g2d.fill(back);

		dX = 100;
		
		if(scaler == 1) {
			dX = 100;
		}
		dX = (int) (dX * (scaler));

//		refreshFunctionUnits();
		
		grid.drawGridOnScreen(dX, g2d);
//		grid.drawGridOnImage(dX, img, g2d1);
		grid.drawAxisOnScreen(g2d, saveGraph, 150, 150, 150);
//		grid.drawAxisOnImage(img, g2d1, saveGraph, HEIGHT, WIDTH, HEIGHT);
		recalculateAllValues();
		

//		g2d1.dispose();
		
		g2d.dispose();
		
	}
	

	//SET THE SCROLL COEFFICIENT TO SOME VARIABLE TO BE ABLE TO CHANGE IT IN THE INTERFACE
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		double scroll = e.getPreciseWheelRotation();

		scaler += 10 * scroll / 100;
		
		if(scaler <= 0) {
			scaler = 1;
		}


		repaint();
		
	}


	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double dt = 0;
		double ns = 1000000000/60;
		System.out.println("running called");
		while(true) {

			long now = System.nanoTime();
			dt += (now - lastTime) / ns;
			lastTime = now;

			if(dt >= 1) {
				//repaint();
//				System.out.println("repaint after entering function");
//				repaintAfterInputOfNewFunction = false;
				dt--;
				
			}

		}
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		repaint();
		


		
	}



	@Override
	public void mouseReleased(MouseEvent e) {

		
		

	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//PUT THE METHOD INSIDE THE PAINT METHOD AND RURN METHOD LOOOP FOR LESS RENDER DELAYS
	public void recalculateAllValues() {
		ArrayList<Thread> threads = new ArrayList<>();
		

		synchronized (listOfFunctions) {
			for(SyntaxTree f: listOfFunctions) {

			try {
			Thread vThread = Thread.ofVirtual().start(() -> {
							if(f.root.isVisable && f.root != null) {

				f.root = f.recalculateValues(f.root);//RECALCULATES VALUE OF EACH NODE RECURSIVELY
				f.root.refresh();
//				g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
//	//			System.out.println("Amount of functions = " + listOfFunctions.size());
//				g2d.setColor(new Color(50, 100, 150));
//				g2d.drawPolyline(f.root.xC, f.root.yC, f.root.xC.length);
////				g2d1.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
////				g2d1.setColor(new Color(50, 100, 150));
////				g2d1.drawPolyline(f.root.xC, f.root.yC, f.root.xC.length);
//				//g2d1.drawImage(img, null, 0, 0);
					}
				
			});
			threads.add(vThread);


			}catch(NullPointerException ex) {
					Input.errorDisplay.setText("No function has been given!");
			}
		}
		}
		for(Thread t : threads) {
				try {
					t.join();
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
		}
		System.out.println("Number of thread in the list  = " + threads.size());
		for(SyntaxTree f: listOfFunctions) {
			try {
				g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
				g2d.setColor(new Color(50, 100, 150));
				g2d.drawPolyline(f.root.xC, f.root.yC, f.root.xC.length);
			}catch(NullPointerException ex) {
					Input.errorDisplay.setText("No function has been given!");
			}
		}
	}
	

	
	//CHANGE PARAMETER FOR THE FILE OBJECT CONSTRUCTOR IN THIS METHOD TO --> new File(directoryPath + sep +  fileName + extention)
	//When user inputs the location to save the file to this method receives that as the parameter
	//Set default location if no location has been chosen
	public void saveCurrentGraph(String directoryPath, String fileName, String extention) {
		if(saveGraph) {
			try {
				File path = new File(folder+ sep + fileName + extention);
				if(path.exists()) {
					fileName = fileName + "copy_";
				}
				int i = 0;
				while(path.exists()) {
					i++;
					path =  new File(folder+ sep + fileName + i);
				}
				ImageIO.write(img, "png", path);
//				System.out.println("file "  + fileName + "  \t path " + path.getAbsolutePath());
				
				
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void saveVectorOfFunction(String fileName) {
		int counter = 0;
	
		for(SyntaxTree f : listOfFunctions) {

		f.root.makeSubarrays();
//		System.out.println("amount of subarrays" + f.root.subarraysY.size());
			int size = f.root.subarraysX.size();
			
			StringBuilder outputSVG = new StringBuilder(svg1);
			for(int i = 0; i < size; i++) {
				
			GenerateXML xml = new GenerateXML(f.root, i);
			String path = String.format( "<path d=\"%s\" stroke=\"black\" fill=\"none\" stroke-width=\"2\"/>", xml.svg);
//			System.out.println("TO BE APPENDED : " + xml.svg);
			outputSVG.append(path + "\n");
			
			
			
			}
			outputSVG.append("</svg>");
			f.root.subarraysX.clear();
			f.root.subarraysY.clear();
			try {
				
				
				File file = new File(folder + sep +  fileName +".svg");
				int i = 0;
				if(file.exists()) {
			
					fileName = fileName + "copy_";
				}
				while(file.exists()) {
					i++;
					file = new File(folder + sep + fileName + i +".svg");
				}
				
				try {
					FileWriter w = new FileWriter(file);
					w.write(outputSVG.toString());
				System.out.println("Vector saved to : " + file.getAbsolutePath());
				w.close();
				}catch(FileNotFoundException ex) {
					Input.errorDisplay.setText(ex.getMessage() + "\n Must specify write-to path!");
				}
				
				
	
		
//				System.out.println("XML : \n" + outputSVG.toString());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			counter ++;
		}
	}
	
	public class SaveAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {

				saveVectorOfFunction(fileName);

			
		}
		
	}

		public class LeftWard extends AbstractAction{

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.moveLeft(20);
				Function.moveLeft(20);
				repaint();
//				System.out.println("move side h");
				
			}
			
		}
		
		public class RemoveLastFunction extends AbstractAction{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!listOfFunctions.isEmpty()) {
					listOfFunctions.removeLast();
					repaint();
				}
				
			}
			
		}
		
		public class RightWard extends AbstractAction{

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.moveRight(20);
				Function.moveRight(20);
				repaint();
	//			System.out.println("move side l");
				
			}
			
		}
		public class UpWard extends AbstractAction{

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.moveUp(20);
				Function.moveUp(20);
				repaint();
	//			System.out.println("move up k");
				
			}
			
		}
		public class DownWard extends AbstractAction{

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.moveDown(20);
				Function.moveDown(20);
				repaint();
//				System.out.println("move down j");
				
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



	
}
