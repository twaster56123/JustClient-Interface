/*
 * Eric Purvis
 */
package main;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends PostOffice{

	//Field
	private ServerSocket ss;
	private int port = 3757;
	
	//Methods
	public Server(){	
		
		setCurrentVersion();
		organizeFiles();
		detectChanges();
		
		try{
			//Creates Server
			ss = new ServerSocket(port);
			System.out.println("Server Initiated ~ "+InetAddress.getLocalHost().getHostAddress()+":"+port);
			//Create a thread to listen on and create connections
			new Thread(
					new Runnable(){
						public void run(){
							//Waits for a client to connect
							newEars();
						}
					}
			).start();
		}catch(Exception ex){
			System.err.println("Server(Con.): Server Broken! Shutting Down...");
			System.exit(0);
		}
	}

	//Recursive Client listening
	private void newEars(){
		try{
			
			final Socket accept = ss.accept();
			
			new Thread(new Runnable(){ public void run(){
				
				try{
					accept.setTcpNoDelay(true);
				}catch(Exception ded){}
				
				receiveMail(accept);
				
				System.out.println("Connection made: "+accept.getInetAddress());
				
				newEars();
				
				}
			}).start();
		}catch(Exception ex){
			System.err.println("Server(newEars): Something Went Wrong! Shutting Down...");
			System.exit(0);
		}
	}
	
}
