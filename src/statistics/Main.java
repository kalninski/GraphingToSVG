package statistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.*;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;




public class Main  {
	
	public static Panel p;
	public static Input input;
	
	
//	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createFrame();
			}
		});
		


	}
	
	public static void createFrame() {
		Panel p = new Panel();
	
		Input input = new Input();
	
		p.setPreferredSize(new Dimension(1200, 1000));//fill the w and h attributes of the actual panel object
		JFrame f = new JFrame();
		f.setVisible(true);


	



	
		f.add(p,BorderLayout.EAST);
		f.add(input, BorderLayout.WEST);
		f.setResizable(false);
		f.setTitle("Graph");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		


		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setBackground(new Color(255, 255, 255));
	 

		

	}




	
}
