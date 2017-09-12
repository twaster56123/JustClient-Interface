package gui;

import java.io.File;

import javax.swing.JFileChooser;

public class FileExplorer extends JFileChooser{

	private String dir = "C:/";
	private String gameName = "Whiffle Whaffle";
	private int trigger;
	
	public FileExplorer(){
		setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		setCurrentDirectory(new File(dir));
	}
	
	public String getNewPath(){
		File tempFile = new File(dir+gameName);
		
		setSelectedFile(tempFile);
		trigger = this.showDialog(null, "Download");
			
		File file = this.getSelectedFile();
		String test=file.getAbsolutePath();
			
		//If not submitted close program and exit with error message
		if(trigger==JFileChooser.CANCEL_OPTION){
			System.err.println("FileExplorer File Location Canceled");
			System.exit(0);
		}
			
		//If an error occurs some how print this
		if(trigger==JFileChooser.ERROR_OPTION){
			System.err.println("FileExplorer Rip");
			System.exit(0);
		}
			
		//If folder does not exist make it
		if(!file.exists()){
			file.mkdirs();
		}
			
		return(test);

	}

	
}
