/*
 * Eric Purvis
 */
package gui;

import javax.swing.JFrame;

public class Window extends JFrame{

	public Window(String title, int width, int height, boolean resizable){
		
		//Custom
		setTitle(title);
		setSize(width, height);
		setResizable(resizable);
		
		//Defaulted
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
}
