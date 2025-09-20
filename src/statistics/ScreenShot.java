package statistics;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class ScreenShot {
	
	public BufferedImage image;
	private int startMouseCoordinateX;
	private int startMouseCoordinateY;
	private int endMouseCoordinateX;
	private int endMouseCoordinateY;
	public String sep = File.pathSeparator;
	public String output = "C:" + sep + "Users" + sep + "Toms" + sep + "Desktop" + sep + "ImageEXPERIMENTS";
	
	
	
	public ScreenShot() {
		this.startMouseCoordinateX = 0;
		this.startMouseCoordinateY = 0;
		this.endMouseCoordinateX = 1200;
		this.endMouseCoordinateY = 1000;
		image = new BufferedImage(endMouseCoordinateX, endMouseCoordinateY, BufferedImage.TYPE_INT_ARGB);		
	}
	
	public ScreenShot(int x, int y, int endMouseCoordinateX, int endMouseCoordinateY) {
		image = image.getSubimage(x, y, endMouseCoordinateX, endMouseCoordinateY);
	}
	
	public void setNewStartCorner(int x, int y){
		startMouseCoordinateX = x;
		startMouseCoordinateY = y;
	}
	
	public void setNewEndCorner(int endMouseCoordinateX, int endMouseCoordinateY){
		this.endMouseCoordinateX = endMouseCoordinateX;
		this.endMouseCoordinateY = endMouseCoordinateY;
		image = image.getSubimage(startMouseCoordinateX, startMouseCoordinateY, endMouseCoordinateX, endMouseCoordinateY);
	}
	
	
	
}
