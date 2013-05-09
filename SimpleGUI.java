/*
	Travis Stodter
	SimpleGUI.java
	4 Dec 2012
	HW 3

	Fulfills part 1 of the homework, creating a simple Gui that allows the user
	to print text to a text area, and observe the events created by the mouse
	and buttons.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class SimpleGUI extends JFrame implements ActionListener, MouseListener, 
								  MouseMotionListener {
	JFrame frame;
	JPanel panel;

	JPanel sendPanel;
	JTextField textField;
	JButton sendButton;

	JPanel shapePanel;
	JButton rectButton;
	JButton circButton;
	JButton eraseButton;

	JPanel drawingPanel;
	JTextArea textArea;
	JScrollPane scrollPane;
		
	
	// Main function
	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					createGUI();
				}
			}
		);
	}

	//Simply begins creation of the GUI
	public static void createGUI() {
		SimpleGUI gui = new SimpleGUI();
		gui.setVisible(true);
	}

	//Creates the GUI
	public SimpleGUI() {
		super("Simple GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel( new BorderLayout() );

		sendPanel = new JPanel( new BorderLayout() );
		shapePanel = new JPanel( new GridLayout(15,1) );
		drawingPanel = new JPanel();

		//build sendPanel
		textField = new JTextField();
		textField.addActionListener(new SendListener());
		sendButton = new JButton("Send");
		sendButton.addActionListener(new SendListener());
		sendPanel.add(textField, BorderLayout.CENTER);
		sendPanel.add(sendButton, BorderLayout.EAST);
		

		//build shapePanel
		rectButton = new JButton("Rectangle");
		circButton = new JButton("Circle");
		eraseButton = new JButton("Erase");

		rectButton.addActionListener(this);
		circButton.addActionListener(this);
		eraseButton.addActionListener(this);
	
		
		shapePanel.add(new JPanel(new FlowLayout()).add(rectButton));
		shapePanel.add(new JPanel(new FlowLayout()).add(circButton));
		shapePanel.add(new JPanel(new FlowLayout()).add(eraseButton));


		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.addMouseListener(this);
		textArea.addMouseMotionListener(this);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(500,500));
		scrollPane.setHorizontalScrollBarPolicy(
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		drawingPanel.setBackground(Color.WHITE);
		drawingPanel.add(scrollPane, BorderLayout.CENTER);
					
		

		panel.add(sendPanel, BorderLayout.SOUTH);
		panel.add(shapePanel,BorderLayout.EAST);
		panel.add(drawingPanel, BorderLayout.CENTER);
	
		this.getContentPane().add(panel);
		this.pack();
	}	

	// Performed upon button press
	public void actionPerformed(ActionEvent e) {
		String name = ((JButton)e.getSource()).getText();
		textArea.append(name + "\n");
	}	

	//The following print to the text area an appropriate string
	public void mousePressed(MouseEvent e) {
		mousePrint("pressed", e); 
	}

	public void mouseReleased(MouseEvent e) {
		mousePrint("released", e); 		
	}

	public void mouseEntered(MouseEvent e) {
		mousePrint("entered", e); 
	}

	public void mouseExited(MouseEvent e) {
		mousePrint("exited", e); 
	}

	public void mouseClicked(MouseEvent e) {
		mousePrint("clicked", e); 		
	}

	public void mouseMoved(MouseEvent e) {
		mousePrint("moved", e); 
	}

	public void mouseDragged(MouseEvent e) {
		mousePrint("dragged", e); 
	}

	//Prints to the text area a string appropriate to each mouse event
	public void mousePrint(String eventDescription, MouseEvent e) {
		textArea.append("Mouse " + eventDescription + " at (" + 
					  e.getX() + ", " + e.getY() + ")" + "\n");
	}

	//Listener for the send button
	public class SendListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			textArea.append(text + "\n");
			textField.setText("");
		}
	}


}
