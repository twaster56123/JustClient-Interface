/*
 * Eric Purvis
 */
package updater;

import gui.Main;

import java.net.InetAddress;
import java.net.Socket;

public class Client extends PostOffice{

	//Field
	private Socket sock;
	private int port = 3757;
	private String ip;
	
	//methods
	public Client(Main main){
		
		try{
			
			setVersion();
			
			boolean connected=false;
			while(!connected){
			
				//Local Address
				ip=InetAddress.getLocalHost().getHostAddress();
				
				//Hamachi
				//ip="0";
				
				//Ip Address
				//ip="";
				
				
				sock = new Socket(ip,port);
				sock.setTcpNoDelay(true);
				
				connected=true;
				
			}
			
			receiveMail(main, sock);
			
		}catch(Exception ex){
			
			main.setErrorMessage(getClass().getName()+":"+" Host Not Found!!!");

		}
		
	}
	
}
