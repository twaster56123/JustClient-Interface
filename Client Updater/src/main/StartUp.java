/*
 * Eric Purvis
 */
package main;

import gui.Main;
import updater.Client;

public class StartUp {

	//Starts the Client
	public static void main(String args[]){
		
		//Open Gui
		Main main = new Main();
		
		//Starts Client
		Client client = new Client(main);
		
	}
	
}
