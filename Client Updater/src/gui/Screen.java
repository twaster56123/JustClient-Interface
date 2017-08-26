/*
 * Eric Purvis
 */
package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel{

	private MasterDrawer masterDrawer = new MasterDrawer();
	
	public void paint(Graphics g){
		
		masterDrawer.update(g);
		
	}
	
	public void setCompiled(){
		masterDrawer.setCompiled();
	}
	
	public void setFileName(String fileName){
		masterDrawer.setFileName(fileName);
	}
	
	public void setErrorMessage(String error){
		masterDrawer.setErrorMessage(error);
	}
	
	public void setCurrentVersion(int version){
		masterDrawer.setCurrentVersion(version);
	}
	
	public void setLatestVersion(int version){
		masterDrawer.setLatestVersion(version);
	}
	
	public void setTotalBytes(long bytes){
		masterDrawer.setTotalBytes(bytes);
	}
	
	public void setReceivedBytes(long bytes){
		masterDrawer.setReceivedBytes(bytes);
	}
	
	public void setCompiledFileSize(long bytes){
		masterDrawer.setCompiledFileSize(bytes);
	}
	
	public void setCompiledBytesWrote(long bytes){
		masterDrawer.setCompiledBytesWrote(bytes);
	}
	
}
