/*
 * Eric Purvis
 */
package updater;

import gui.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Downloader extends Compile{

	//Field
	private boolean uptoDate=false;
	private boolean downloading=false;
	
	private File downloadingFile;
	private FileOutputStream writer;
	
	private boolean writeTo=false;
	
	private long fileSize=0;
	private long received=0;

	//Methods 
	
	public boolean getWriteTo(){
		return(writeTo);
	}
	//File Size
	protected long getFileSize(){
		return(fileSize);
	}
	protected long getReceived(){
		return(received);
	}
	
	public void setFileSize(String msg){
		fileSize = Long.parseLong(msg);
	}
	
	//Download Methods
	public void startDownload(){
		downloading=true;
	}
	
	public void finishedDownload(){
		downloading=false;
	}
	
	public boolean getDownloading(){
		return(downloading);
	}
	
//// downloading methods
	
	public void makeFile(String serverPath){
		try{

			String breakUp = serverPath.replace("C:\\zzzzServer Updater Files", "");
			String path = getPath()+breakUp;
			
			downloadingFile = new File(path);
			File Folders = new File(path.replace(downloadingFile.getName(), ""));
			
			Folders.mkdirs();
			downloadingFile.createNewFile();

			writer = new FileOutputStream(downloadingFile);
			
			writeTo=true;
			
		}catch(Exception ex){
			main.setErrorMessage(getClass().getName()+": Failed to Make File!!!");
		}

	}
	
	public void writeToFile(Main main, InputStream fis){
		try{
			
			main.setReceivedBytes(0);
			main.setTotalBytes(fileSize);
			
			byte[] data = new byte[1024];
			received=0;
			while(fileSize > received){
				int bytesRead = fis.read(data);
				writer.write(data, 0, bytesRead);
				writer.flush();
				received+=bytesRead;
				main.setReceivedBytes(received);
			}
			writer.close();
			writeTo=false;
		}catch(Exception ex){
			main.setErrorMessage(getClass().getName()+": Writting to File Error");
		}
	}
	
	//Up to date methods
	public void versionUptoDate(){
		uptoDate=true;
	}
	
	public boolean isUptoDate(){
		return(uptoDate);
	}
	
	
}
