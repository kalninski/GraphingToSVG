package statistics;


import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame implements WindowListener{
	
	public TextField textField;
	
	public Window(String title) {
		super(title);
		setLayout(new FlowLayout());
		setSize(400, 200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		addWindowListener(this);
		textField = new TextField(15);
//		textField.setText();
		add(textField);
		JButton button = new JButton("ok");
		button.addActionListener(new AL());
		button.setPreferredSize(new Dimension(50, 35));
		this.add(button);
	}	

	public static void main(String[] args) {


	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(textField.getText());
		System.out.println("OK button");

	}
		
	public class AL implements ActionListener{

			@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(textField.getText());
			SyntaxTree maker = new SyntaxTree(textField.getText());
			maker.print(maker.root);
					
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
