/*
 * Eric Purvis
 */
package main;

import java.io.File;

public class Directory{

	//Field
	private String path = "C:\\zzzzServer Updater Files";
	private File[] files;
	private String currentVersion;
	
	//Methods
	
	//gets path
	public String getPath(){
		return(path);
	}
	
	//Pass over
	public File[] getFiles(){
		return(files);
	}
	
	//Version Control
	public String getCurrentVersion(){
		return(currentVersion);
	}
	
	public void setCurrentVersion(){ 
		
		//Gathers the update files
		File folder = new File(path);
		files = folder.listFiles();
		
		//Process Files
		int high=0;
		for(int i=0; i<files.length; i++){
			if(files[i].isDirectory()){
				String temp[] = files[i].getName().split("version");
				if(temp[0].equals("")){
					int num = Integer.parseInt(temp[1]);
					if(num>high){
						high=num;
					}
				}
			}
		}
		currentVersion="version"+high;
		
	}
	
}
