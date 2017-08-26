/*
 * Eric Purvis
 */
package main;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class PostOffice extends PostMaster{
	
	//Methods
	
	//Await mail at the office
	public void receiveMail(final Socket sock){
		new Thread(
			new Runnable(){
				public void run(){
					try{
						
						InputStream is = sock.getInputStream();
						OutputStream os = sock.getOutputStream();
						MailHandler(is,os);
	
					}catch(Exception ex){
						System.err.println("Post Office(receiveMail): Line Has Crashed!");
					}
				}
			}
		).start();
	}
	
}
