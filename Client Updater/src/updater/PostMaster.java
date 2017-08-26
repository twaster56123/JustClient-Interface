/*
 * Eric Purvis
 */
package updater;

import gui.Main;

import java.io.InputStream;
import java.io.OutputStream;

//Client Updater's Post Master
public class PostMaster extends Downloader{

	private InputStream is;
	private OutputStream os;
	
	public void MailHandler(Main main, InputStream is, OutputStream os){
		
		this.is=is;
		this.os=os;
		
		sendLine("Version Check");
		
		while(true){
			try{
				
				byte[] mail = getMail();
				String test = new String(mail);

				if(getDownloading()){
					
					while(!test.equals("~Download Finished~")){
													
							if(test.equals("~Download File~")){
								
								mail = getMail();
								test = new String(mail);
								
								//Get File Name
								if(test.startsWith("Name:")){
									test=test.replace("Name:","");
									makeFile(test);
									main.setFileName(test);
								}
								
								mail = getMail();
								test = new String(mail);
								
								//Get File Size
								if(test.startsWith("Size:")){
									test=test.replace("Size:","");
									setFileSize(test);
								}
								
								//Write to file when up
								if(getWriteTo()){
									writeToFile(main, is);
								}
								
							}
							
							mail = getMail();
							test = new String(mail);
						
					}
					
					finishedDownload();
					setVersion();
					sendLine("Version Check");
					
				}else{
					
					//Test Version
					if(test.startsWith("version")){
						
						main.setCurrentVersion( Integer.parseInt(getVersion().replace("version", "")) );
						main.setLatestVersion( Integer.parseInt(test.replace("version", "")) );
						
						if(test.equals(getVersion())){
							if(!getCompileUptoDate()){
								compileUpdates(main);
							}
						}else{
							sendLine(getVersion());
						}
					}
					
					//Start Download
					if(test.equals("~Init Download~")){
						startDownload();
					}
					
				}
				
				
			}catch(Exception ex){
				main.setErrorMessage(getClass().getName()+": Message Feed");
			}
		}
		
	}
	
	//Clients PostMaster
	
	private void sendLine(String send){
		try{
			byte[] test = send.getBytes();
			os.write(test);
			os.flush();
		}catch(Exception ex){
			main.setErrorMessage(getClass().getName()+": Client sendLine error");
		}
	}
	
	private byte[] getMail(){
		
		try{
			
			byte[] mail = new byte[1024];
			is.read(mail);
			
			String temp = new String(mail);
			temp = temp.trim();
			
			byte[] trimMail = temp.getBytes();
			
			return(trimMail);
			
		}catch(Exception ex){
			return(null);
		}
		
	}
	
}
