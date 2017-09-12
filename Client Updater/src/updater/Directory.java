/*
 * Eric Purvis
 */
package updater;

import gui.FileExplorer;

import java.io.File;

public class Directory{

	//Fields
	private String path = "C:/Users/Eric/Desktop/Client";
	private String currentVersion;
	
	private String compileLog = "/log.compile";
	private String compileFolder = "/compiledUpdates";
	
	private boolean compileUptoDate = false;
	
	//Methods
	public void selectPath(){
		FileExplorer exp = new FileExplorer();
		path = exp.getNewPath();
	}
	
	public boolean getCompileUptoDate(){
		return(compileUptoDate);
	}
	
	public void compileUptoDate(){
		compileUptoDate=true;
	}
	
	public String getCompileFolder(){
		return(compileFolder);
	}
	
	public String getCompileLog(){
		return(compileLog);
	}
	
	public String getPath(){
		return(path);
	}
	
	public String getVersion(){
		return(currentVersion);
	}
	
	public void setVersion(){
		
		//Gathers the update files
		File folder = new File(path);
		File[] files = folder.listFiles();
		
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
	
	public int getIntVersion(String version){
		return(Integer.parseInt(version.split("version")[1]));
	}

	
}
