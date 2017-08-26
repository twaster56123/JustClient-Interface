/*
 * Eric Purvis
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MasterDrawer {

	private int barWidth=800;
	private int barHeight=20;
	
	public void update(Graphics g){
		
	//Background
		drawBackground(g);
		
	//Foreground
		//Bars
		drawBars(g);
		
		//Text
		drawText(g);
		
	}
	
	
	private void drawBackground(Graphics g){
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 400);
		
	}
	
	private void drawBars(Graphics g){
		
		g.setColor(Color.green);
		//Version
		g.drawRect(0, 400-110-16, barWidth, barHeight);
		
		g.fillRect(0, 400-110-16, (int)Math.round(( (float)barWidth*(float)currentVersion/(float)latestVersion) ), barHeight);
		//Downloaded
		g.drawRect(0, 400-60-16, barWidth, barHeight);
		g.fillRect(0, 400-60-16, (int)Math.round( ((float)barWidth*( (float)receivedBytes/(float)totalBytes) ) ), barHeight);
		//Compiled
		g.drawRect(0, 400-35-16, barWidth, barHeight);
		g.fillRect(0, 400-35-16, (int)Math.round( ((float)barWidth*((float)compiledBytesWrote/(float)compiledFileSize)) ), barHeight);
		
	}
	
	private void drawText(Graphics g){
		
		g.setColor(Color.white);
		
		g.setFont(new Font("arial", 0, 16));
		
		g.drawString("Version "+currentVersion+"/"+latestVersion, 0, 400-110);
		
		g.drawString("File Name: "+fileName, 0, 400-85);
		g.drawString("Downloaded: "+(receivedBytes/1024)+"/"+(totalBytes/1024)+" (kB)", 0, 400-60);
		
		g.drawString("Compiled: "+(compiledBytesWrote/1024)+"/"+(compiledFileSize/1024)+" (kB) ("+compiled+")", 0, 400-35);
		
		//Display Error If Any
		if(!error.equals("")){
			g.setColor(Color.red);
			g.drawString("Error: "+error, 0, 20);
		}
		
	}
	
	//Download Variables
	private int currentVersion=0;
	private int latestVersion=1;
	
	private boolean compiled=false;
	
	private long totalBytes=1;
	private long receivedBytes=0;
	private long compiledFileSize=1;
	private long compiledBytesWrote=0;
	
	private String error="";
	private String fileName="";
	
	
	public void setCompiled(){
		this.compiled=true;
	}
	
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	
	public void setErrorMessage(String error){
		this.error = error;
	}
	
	public void setCurrentVersion(int version){
		this.currentVersion=version;
	}
	
	public void setLatestVersion(int version){
		this.latestVersion=version;
	}
	
	public void setTotalBytes(long bytes){
		this.totalBytes=bytes;
	}
	
	public void setReceivedBytes(long bytes){
		this.receivedBytes=bytes;
	}
	
	public void setCompiledFileSize(long bytes){
		this.compiledFileSize=bytes;
	}
	
	public void setCompiledBytesWrote(long bytes){
		this.compiledBytesWrote=bytes;
	}
	
}
