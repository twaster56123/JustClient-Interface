/*
 * Eric Purvis
 */
package main;

import java.io.File;
import java.io.PrintWriter;

public class Changed extends Directory{

	//Field
	private File[] updates;
	private File[] logs;
	private int version;
	
	//Methods
	
	public void detectChanges(){
		
		double time1;
		double totalTimePassed;
		
		time1=System.nanoTime();
		
		//Log changes
		for(int i=0; i<logs.length; i++){
			if(logs[i]==null){
				try{
					
					double t1=System.nanoTime();
					double tp;
					
					logs[i] = new File(getPath()+"/log"+(i+1)+".changed");
					logs[i].createNewFile();
					PrintWriter pw = new PrintWriter(logs[i]);
					pw.println("Eric Purvis");
					compareFolders(updates[i],updates[i+1],pw);
					pw.println("end");
					
					tp=(System.nanoTime()-t1)/1000000000.0;
					
					pw.println("Time spent ~ "+tp+" seconds");
					pw.flush();
					pw.close();
				}catch(Exception ex){
					System.out.println(ex.getLocalizedMessage());
					System.err.println("Changed(detectChanges): Failed to create log"+(i+1)+". Shutting Down...");
					System.exit(0);
				}
			}
		}
		
		totalTimePassed=(System.nanoTime()-time1)/1000000000.0;
		System.out.println("Changed(detectChanges): Time Passed ~ "+totalTimePassed+" seconds");
		
	}
	
	public void organizeFiles(){
		//acquire files
		File[] temp = getFiles();
		
		//Calc field sizes
		version = Integer.parseInt(getCurrentVersion().split("version")[1]);
		
		//Create fields
		updates = new File[(version+1)];
		logs = new File[(version)];
		
		//place files accordingly
		for(int i=0; i<temp.length; i++){
			if(temp[i].isDirectory()){
				int place = (Integer.parseInt(temp[i].getName().split("version")[1]));
				updates[place] = temp[i];
			}
			if(temp[i].isFile()){
				int place = (Integer.parseInt(temp[i].getName().split("log")[1].replaceAll(".changed", ""))-1);
				logs[place] = temp[i];
			}

		}
	
	}
	
	//Compare Files and Output Change
	private void compareFolders(File previous, File next, PrintWriter pw){

		if(previous.isDirectory() && next.isDirectory()){
			
			File[] folder2 = next.listFiles();
			
			//Check all Files in folder
			for(int i=0; i<folder2.length; i++){
			
///////
				
				//check for new files
				if(folder2[i].isFile()){
					//exists, check time stamps
					File tempFile = new File(previous.getAbsolutePath()+"/"+folder2[i].getName());
					if(tempFile.exists()){
						
						if(folder2[i].lastModified()!=tempFile.lastModified()){
							pw.println(folder2[i].getAbsolutePath());
						}
						
					}else{
					//Does Not Exist
						pw.println(folder2[i].getAbsolutePath());
					}
				}else{		
////////
				
					//check for new folder
					File temp = new File(previous.getAbsolutePath()+"/"+folder2[i].getName());
					if(folder2[i].isDirectory() && temp.exists() ){
						compareFolders(temp,folder2[i],pw);
					}else{
						//Output new folder
						for(int x=0; x<folder2[i].listFiles().length; x++){
							pw.println(folder2[i].listFiles()[x].getAbsolutePath()+"\n");
						}
					}
					
				}
				
////////
				
			}
			
		}
		
	}
	
}
