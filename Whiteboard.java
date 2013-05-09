/*
	Travis Stodter
	Whiteboard.java
	4 Dec 2012
	HW 3

	Represents the whiteboard used in WhiteboardGUI.  Allows for the drawing
	of multiple shapes.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

enum DrawingMode { NONE, LINE, RECT, OVAL };

public class Whiteboard extends JPanel {
	private Vector<Shape> shapes;
	private Shape currentShape = null;

	DrawingMode mode = DrawingMode.NONE;

	//Performs initialization, adds listener to whiteboard
	public Whiteboard() {
		super();
		shapes = new Vector<Shape>();

		MouseComboListener mouseListener = new MouseComboListener();
		addMouseListener( mouseListener );
		addMouseMotionListener( mouseListener );
	}
					
	//Called by JVM
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : shapes)
			s.draw(g);
	}

	//Adds Shape s to the vector shapes
	public void addShape(Shape s) {
		shapes.addElement(s);
	}

	//Clears vector and whiteboard of all shapes
	public void clearShapes() {
		shapes.clear();
		repaint();
	}

	//Sets drawing mode, called by button event listeners
	public void setMode(DrawingMode mode) {
		this.mode = mode;
	}

	//Combines MouseListener and MouseMotionListener
	class MouseComboListener implements MouseListener, MouseMotionListener {
		//On mouse press, create new shape
		public void mousePressed(MouseEvent e) {
			switch (mode) {
				case NONE: currentShape = null;
						 break;
			     case LINE: currentShape = new Line(e.getX(), e.getY(), 
											e.getX(), e.getY() );
						 break;
				case RECT: currentShape = new Rectangle(e.getX(), e.getY(),
												e.getX(), e.getY() );
						 break;
				case OVAL: currentShape = new Oval(e.getX(), e.getY(),
											e.getX(), e.getY() );
						 break; 
			}
				
			if (currentShape != null)			
				addShape(currentShape);
			repaint();
		}

		//On mouse release, forget current shape and save its final position
		public void mouseReleased(MouseEvent e) {
			if (currentShape != null)
				currentShape.setSecondaryCoords(e.getX(), e.getY());
			currentShape = null;
			repaint();
		}

		public void mouseEntered(MouseEvent e){ } ;
		public void mouseExited(MouseEvent e){ } ;
		public void mouseClicked(MouseEvent e){ };
		public void mouseMoved(MouseEvent e){ };
		
		//Constantly changes and updates shape
		public void mouseDragged(MouseEvent e){ 
			if (currentShape != null)
				currentShape.setSecondaryCoords(e.getX(), e.getY());
			repaint();
		}
	}

	
}


