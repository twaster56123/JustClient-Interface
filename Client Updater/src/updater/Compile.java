/*
 * Eric Purvis
 */
package updater;

import gui.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compile extends Directory{

	Main main;
	
	public void compileUpdates(Main main){

		this.main = main;
		
		File compileFile = new File(getPath()+getCompileLog());
		File compileFolder = new File(getPath()+getCompileFolder());
		
		//Create File if it does not exist
		if(!compileFile.exists()){
			try{
				compileFile.createNewFile();
				PrintWriter pw = new PrintWriter(compileFile);
				pw.println("0");
				pw.flush();
				pw.close();
			}catch(Exception ex){
				main.setErrorMessage(getClass().getName()+": Failed to create log file!!!");
			}
		}
		
		//Create Folder if it does not exist
		if(!compileFolder.exists()){
			try{
				compileFolder.mkdir();
			}catch(Exception ex){
				main.setErrorMessage(getClass().getName()+": Failed to create folders!!!");
			}
		}

		//Index what to compile
		try{
			
			Scanner index = new Scanner(compileFile);
			int RecentCompileVersion = Integer.parseInt( getVersion().replace("version", "") );
			int LastCompileVersion = index.nextInt();
			
			while(LastCompileVersion<=RecentCompileVersion){
				
				compileTheFiles(LastCompileVersion);
				
				try{
					compileFile.createNewFile();
					PrintWriter pw = new PrintWriter(compileFile);
					pw.println(LastCompileVersion);
					pw.flush();
					pw.close();
				}catch(Exception ex){}
				
				LastCompileVersion++;
				
			}
			
			main.setCompiled();
			compileUptoDate();
			
		}catch(Exception ex){
			main.setErrorMessage(getClass().getName()+": Compile Failed");
		}
		
	}
	
	private void compileTheFiles(int version){
		
		File folder = new File(getPath()+"/version"+version);
		findFiles(folder);
		
	}
	
	private void findFiles(File file){
		
		if(file.isDirectory()){
			
			File[] temp = file.listFiles();
			
			for(int i=0; i<temp.length; i++){
				
				//Look through it
				if(temp[i].isDirectory()){
					findFiles(temp[i]);
				}
				
				//Move it
				if(temp[i].isFile()){
					moveFile(temp[i]);
				}
				
			}
			
		}
		
		//Move it
		if(file.isFile()){
			moveFile(file);
		}
		
	}
	
	private void moveFile(File file){
		
		try{
			
			String compileFolder = getPath()+getCompileFolder();
			String subPath[] = file.getAbsolutePath().split("version");
			String subDir = subPath[1].substring(1, subPath[1].length());
			
			File newFolders = new File(compileFolder+subDir.replace(file.getName(), ""));
			File newFile = new File(compileFolder+subDir);
			newFolders.mkdirs();
			newFile.createNewFile();
			
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(newFile);
			
			byte[] data = new byte[1024];
			long received=0;
			long fileSize=file.length();
			
			main.setCompiledBytesWrote(0);
			main.setCompiledFileSize(fileSize);
			
			while(fileSize > received){
				int bytesRead = fis.read(data);
				fos.write(data);
				fos.flush();
				received+=bytesRead;
				main.setCompiledBytesWrote(received);
			}
			
			fis.close();
			fos.close();
			
		}catch(Exception ex){
			main.setErrorMessage(getClass().getName()+": Failed to move File!!!");
		}
		
		
	}
	
}
