/*
 * Eric Purvis
 */
package updater;

import gui.Main;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PostOffice extends PostMaster{

	//Mail Methods
	public void receiveMail(final Main main, final Socket sock){
		new Thread(
			new Runnable(){
				public void run(){
					try{
						
						InputStream is = sock.getInputStream();
						OutputStream os = sock.getOutputStream();
						MailHandler(main, is, os);
						
					}catch(Exception ex){
						main.setErrorMessage(getClass().getName()+": "+ex.getLocalizedMessage());
					}
								
				}
			}
		).start();
	}
	
}
