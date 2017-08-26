/*
 * Eric Purvis
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

//Server Updater's Post Master
public class PostMaster extends Changed{

	private InputStream is;
	private OutputStream os;
	
	public void MailHandler(InputStream is, OutputStream os){
		
		this.is=is;
		this.os=os;
		
		while(true){
			try{
					
				
				byte[] mail = getMail();
				String test = new String(mail);
					
				//send current version
				if(test.equals("Version Check")){
					sendLine(getCurrentVersion());
				}
				
				//Find Version to update
				if(test.startsWith("version")){

					String end = test.replace("version","");
					int temp = Integer.parseInt(end);
					temp++;
					end = ""+temp;
					File indexFile = new File(getPath()+"/log"+end+".changed");
					
					Scanner index = new Scanner(indexFile);
					
					sendLine("~Init Download~");
					
					String indexRead = index.nextLine();
					indexRead = index.nextLine();
					while(!indexRead.equals("end")){
						
						sendLine("~Download File~");
						Thread.sleep(100);
						//File
						File file = new File(indexRead);
						//Send File Abs Path
						sendLine("Name:"+indexRead);
						Thread.sleep(100);
						//Send File Size
						sendLine("Size:"+file.length());
						Thread.sleep(100);
						//Send File Contents
						byte[] buffer = new byte[1024];
						FileInputStream fis = new FileInputStream(file);
						int length;
						while( (length = fis.read(buffer)) > 0){
							os.write(buffer, 0, length);
							os.flush();
						}
						
						Thread.sleep(100);
						
						indexRead = index.nextLine();
						
					}
					
					sendLine("~Download Finished~");
					
				}
					
				
			}catch(Exception ex){}
		}
		
	}
	
	//Servers PostMaster
	
	private void sendLine(String send){
		try{
			byte[] test = send.getBytes();
			os.write(test);
			os.flush();
		}catch(Exception ex){
			System.err.println("Server sendLine error");
		}
	}
	
	private byte[] getMail(){
		
		try{
			
			byte[] mail = new byte[1024];
			is.read(mail);
			
			int marker =0;
			for(int i=0; i<mail.length; i++){
				if(mail[i]==0){
					marker=i;
					break;
				}
			}
			
			byte[] trimMail = new byte[marker];
			for(int i=0; i<trimMail.length; i++){
				trimMail[i]=mail[i];
			}
			
			return(trimMail);
			
		}catch(Exception ex){
			return(null);
		}
		
	}
	
}
