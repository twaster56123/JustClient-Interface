/*
 * Eric Purvis
 */
package main;

public class StartUp {
	
	public static void main(String args[]){
		
		//Init TCP Server
		Server server = new Server();
		
		//Display Running Version
		String versionCheck = server.getCurrentVersion();
		System.out.println("StartUp: Version Check ~ "+versionCheck);
		
	}
	
}
