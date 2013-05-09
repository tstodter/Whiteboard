/*
	Travis Stodter
	WhiteboardGUI.java
	4 Dec 2012
	HW 3

	Sets up the GUI for part 2, allowing the user to draw shapes upon a 
	canvas.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class WhiteboardGUI extends JFrame implements ActionListener {
	JFrame frame;
	JPanel panel;

	JPanel shapePanel;
	JButton rectButton;
	JButton circButton;
	JButton lineButton;
	JButton eraseButton;

	Whiteboard drawingPanel;


	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					createGUI();
				}
			}
		);
	}

	//Begins GUI creation
	public static void createGUI() {
		WhiteboardGUI gui = new WhiteboardGUI();
		gui.setVisible(true);
	}

	
	public WhiteboardGUI() {
		super("Whiteboard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel( new BorderLayout() );

		shapePanel = new JPanel( new GridLayout(15,1) );
		drawingPanel = new Whiteboard();
		

		//build shapePanel
		rectButton = new JButton("Rectangle");
		circButton = new JButton("Circle");
		lineButton = new JButton("Line");
		eraseButton = new JButton("Erase");

		rectButton.addActionListener(this);
		circButton.addActionListener(this);
		lineButton.addActionListener(this);
		eraseButton.addActionListener(this);
	
		shapePanel.add(rectButton);
		shapePanel.add(circButton);
		shapePanel.add(lineButton);
		shapePanel.add(eraseButton);

		//build drawingPanel
		drawingPanel.setBackground(Color.WHITE);
		drawingPanel.setPreferredSize(new Dimension(500,500));
					
		panel.add(shapePanel,BorderLayout.EAST);
		panel.add(drawingPanel, BorderLayout.CENTER);
	
		this.getContentPane().add(panel);
		this.pack();
	}	

	//Called when one of the four buttons is pressed, and the whiteboard's mode
	//is set
	public void actionPerformed(ActionEvent e) {
		String name = ((JButton)e.getSource()).getText();
		if (name == "Rectangle")
			drawingPanel.setMode(DrawingMode.RECT);
		else if (name == "Circle")
			drawingPanel.setMode(DrawingMode.OVAL);
		else if (name == "Line") 
			drawingPanel.setMode(DrawingMode.LINE);
		else if (name == "Erase") {
			drawingPanel.setMode(DrawingMode.NONE);
			drawingPanel.clearShapes();
		}
	}	

	




}
