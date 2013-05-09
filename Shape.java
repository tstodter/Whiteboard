/*
	Travis Stodter
	Shape.java
	4 Dec 2012
	HW 3

	Classes to represent their respective shapes. Shape class is abstract, and
	never drawn itself, but contains the basic information that the others
	extend.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Shape {
	
	int x1, y1, 
	    x2, y2;

	public Shape(int x1, int y1, int x2, int y2) {
		setPrimaryCoords(x1,y1);
		setSecondaryCoords(x2,y2);
	}	

	public void setPrimaryCoords(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public void setSecondaryCoords(int x, int y) {
		x2 = x;
		y2 = y;	
	}

	public abstract void draw(Graphics g);

}

class Line extends Shape {

	public Line(int x1, int y1, int x2, int y2) {
		super(x1,y1,x2,y2);
	}

	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);	
	}
}

class Rectangle extends Shape {

	public Rectangle(int x1, int y1, int x2, int y2) {
		super(x1,y1,x2,y2);
	} 

	public void draw(Graphics g) {
		int upLeftX = x1 < x2 ? x1 : x2;
		int upLeftY = y1 < y2 ? y1 : y2;
		g.drawRect( upLeftX, upLeftY, Math.abs(x2-x1), Math.abs(y2-y1));	
	}
}

class Oval extends Shape {

	public Oval(int x1, int y1, int x2, int y2) {
		super(x1,y1,x2,y2);
	}

	public void draw(Graphics g) {
		int upLeftX = x1 < x2 ? x1 : x2;
		int upLeftY = y1 < y2 ? y1 : y2;
		g.drawOval( upLeftX, upLeftY, Math.abs(x2-x1), Math.abs(y2-y1));	
	}
}
