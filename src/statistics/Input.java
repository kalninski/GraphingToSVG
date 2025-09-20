package statistics;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;

public class Input extends JPanel implements WindowListener{
public TextField textField;
public TextField path;
public TextField fileName;
public TextField xFunction;
public TextField yFunction;
public TextField paramLowBound;
public TextField paramHighBound;
public static TextArea errorDisplay;
public TextField polarFunction;
//public static String ErrorMessage;
	
public Input() {
		this.setVisible(true);
		textField = new TextField(40);
		path = new TextField(40);
		fileName = new TextField(40);
		xFunction  = new TextField(40);
		yFunction = new TextField(40);
		errorDisplay = new TextArea(10, 40);
		paramLowBound = new TextField(10);
		paramLowBound.setPreferredSize(new Dimension(30, 30));
		paramHighBound = new TextField(10);
		polarFunction = new TextField(40);
		
		this.setPreferredSize(new Dimension(500, 1000));
		JButton drawBTTN = new JButton("Draw");
		drawBTTN.addActionListener(new InputFunction());
		drawBTTN.setPreferredSize(new Dimension(150, 35));

		JLabel drawLbl = new JLabel("EXPLICIT f : R → R where y = f(x)");
		JLabel outputDirectory = new JLabel("Path to the output directory :");
		JLabel setFileName = new JLabel("Name of the output file");
		JLabel parametricEqLBL = new JLabel("PARAMETRIC f : R → R^2 where f(t) = (x(t), y(t))");
		JLabel parametricEqLBLline2 = new JLabel("with x : R → R and y : R → R");
		JLabel xInputLBL = new JLabel("x(t) = ");
		JLabel yInputLBL = new JLabel("y(t) = ");
		JLabel explicitInputLBL = new JLabel("f(x) = ");
		JLabel paramTMoreThanLessThan = new JLabel("<= t <=");
		JLabel polarLBL = new JLabel("<html>POLAR f : R → R^2 where f(θ) = (r(θ), θ)<br>The input must be r = f(t)<br>Use t instead of θ!</html>");
		JLabel polarLBLinput = new JLabel("r(t) = ");
		JLabel saveInstr = new JLabel("Save → CRTL + S");		
		JLabel backInstr = new JLabel("Remove last graph → CRTL + Z");
		JLabel leftInstr = new JLabel("Left → H");		
		JLabel rightInstr = new JLabel("Right → L");
		
		JButton write = new JButton("Set Directory");
		JButton setName = new JButton("Set Name");
		JButton setXparam = new JButton("Set x(t)");
		JButton setYparam = new JButton("Set y(t)");
		JButton setBounds = new JButton("Set Bounds");
		JButton polarDrawBTTN = new JButton("Draw");
		JButton draw2BTTN = new JButton("Draw");
		
		write.addActionListener(new ChangePath());
		write.setPreferredSize(new Dimension(150, 35));
		setXparam.addActionListener(new SetXParameter());		
		setXparam.setPreferredSize(new Dimension(100, 35));
		setYparam.addActionListener(new SetYParameter());		
		setYparam.setPreferredSize(new Dimension(100, 35));
		draw2BTTN.addActionListener(new InputParametricFunction());
		draw2BTTN.setPreferredSize(new Dimension(150, 35));
		setName.addActionListener(new SetFileName());
		setName.setPreferredSize(new Dimension(150, 35));
		setBounds.addActionListener(new SetBounds());
		setBounds.setPreferredSize(new Dimension(150, 35));
		polarDrawBTTN.addActionListener(new InputPolarFunction());
		polarDrawBTTN.setPreferredSize(new Dimension(150, 35));

		setLayout(new FlowLayout());
		GroupLayout group = new GroupLayout(this);
		this.setLayout(group);
		group.setAutoCreateGaps(true);
		group.setAutoCreateContainerGaps(true);
		
		//Create a group of 
		GroupLayout.ParallelGroup nestedV = group.createParallelGroup(GroupLayout.Alignment.BASELINE)
				/*when parallel group is added to a horizontal of a vertical group 
				 * 
				 */
				.addComponent(paramLowBound, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addComponent(paramTMoreThanLessThan)
				.addComponent(paramHighBound, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
		
		GroupLayout.SequentialGroup nestedH = group.createSequentialGroup()
				/*when sequential left to right goup is created and added to a vertical of a horizontal group in side of a 
				 * parallel group which is one comlumn the left to right arranged nested elemens ar part of the same column
				 */
				.addComponent(paramLowBound, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addComponent(paramTMoreThanLessThan)
				.addComponent(paramHighBound, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
		
		GroupLayout.SequentialGroup instructionsL1H = group.createSequentialGroup()
				/*when sequential left to right goup is created and added to a vertical of a horizontal group in side of a 
				 * parallel group which is one comlumn the left to right arranged nested elemens ar part of the same column
				 */
				.addComponent(saveInstr)
				.addGap(35)
				.addComponent(backInstr);
		GroupLayout.SequentialGroup instructionsL2H = group.createSequentialGroup()
				/*when sequential left to right goup is created and added to a vertical of a horizontal group in side of a 
				 * parallel group which is one comlumn the left to right arranged nested elemens ar part of the same column
				 */
				.addComponent(leftInstr)
				.addGap(35)
				.addComponent(rightInstr);


	
		
		group.setHorizontalGroup(
			group.createSequentialGroup()//the parallel groups that have shit on to of one another, the verticals go sequentially from left to right
			.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(drawLbl)
				.addComponent(explicitInputLBL)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(outputDirectory)
				.addComponent(path, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(setFileName)
				.addComponent(fileName, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addComponent(parametricEqLBL)
				.addComponent(parametricEqLBLline2)

				.addComponent(xInputLBL)
				.addComponent(xFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addComponent(yInputLBL)
				.addComponent(yFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addComponent(polarLBL)
				.addComponent(polarLBLinput)
				.addComponent(polarFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				
				.addComponent(errorDisplay)
				.addGroup(instructionsL1H)
				.addGroup(instructionsL2H)
				.addGroup(nestedH))
			.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(drawBTTN)
					.addComponent(draw2BTTN)
					.addComponent(write)
					.addComponent(setName)
					.addComponent(setBounds)
					.addComponent(polarDrawBTTN))
				);
		
		group.setVerticalGroup(//count rows vertically
				group.createSequentialGroup()//squentially from top to bottom count the rows
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)//Stacked on top of another but along the respective row horizontally
						.addComponent(drawLbl))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)//new parallel group of components stacked horizontally
						.addComponent(explicitInputLBL))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(drawBTTN))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(parametricEqLBL))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(parametricEqLBLline2))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(xInputLBL))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(xFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(yInputLBL))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(yFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(draw2BTTN))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(nestedV)
						.addComponent(setBounds))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(polarLBL))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(polarLBLinput))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(polarFunction, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(polarDrawBTTN))			
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(outputDirectory))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(path, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(write))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(setFileName))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(fileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(setName))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(errorDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(saveInstr)
						.addGap(35)
						.addComponent(backInstr))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(leftInstr)
						.addGap(35)
						.addComponent(rightInstr))
		
	

				);


	
		
		add(textField);
		add(path);

		
	}	
		public class SetBounds implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					String low = paramLowBound.getText();
					String high = paramHighBound.getText();
//					if(Factor.isFactor(low) && Factor.isFactor(high)) {
					
						VariableT.setDomainStart(Double.valueOf(low));
						VariableT.setDomainEnd(Double.valueOf(high));
//					}else {
//						errorDisplay.setText("Syntax error for bound values");
//					}

					System.out.println("New bounds = " + low + " " + high);


					
				}
				
			}

		public class SetFileName implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String name = fileName.getText();
					System.out.println(fileName.getText());
					Panel.fileName = name;

					
				}
				
			}
			
			public class InputFunction implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(textField.getText());
						SyntaxTree maker = new SyntaxTree(textField.getText());
						Panel.listOfFunctions.add(maker);
//						maker.print(maker.root);
					
				}
				
			}
			
			public class ChangePath implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					Panel.folder = path.getText();
					
				}
				
			}
			public class SetXParameter implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					Panel.folder = path.getText();
					
					
				}
				
			}
			public class SetYParameter implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					Panel.folder = path.getText();
					
				}
				
			}
			
				public class InputPolarFunction implements ActionListener{
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String polar = polarFunction.getText();
		
					
					if(VariableT.hasWrongParameter(polar)) {
						Input.errorDisplay.setText("Fix the parameter to t!");
					}else {
						System.out.println("xFunction = " + polarFunction.getText());
		
						SyntaxTree right = new SyntaxTree(polar);

						SyntaxTree polarST = new SyntaxTree();
						polarST.root = new Polar(right);
						Panel.listOfFunctions.add(polarST);
					}
				}
			}		

			public class InputParametricFunction implements ActionListener{
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String xParam = xFunction.getText();
					String yParam = yFunction.getText();
		
					
					if(VariableT.hasWrongParameter(yParam) || VariableT.hasWrongParameter(xParam)) {
						Input.errorDisplay.setText("Fix the parameter to t!");
					}else {
						System.out.println("xFunction = " + xFunction.getText());
						System.out.println("yFunction = " + yFunction.getText());
						SyntaxTree left = new SyntaxTree(xParam);
						SyntaxTree right = new SyntaxTree(yParam);
						SyntaxTree parametric = new SyntaxTree();
						parametric.root = new ParametricEq(left, right);
						Panel.listOfFunctions.add(parametric);
					

					}
					
					
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}


}
