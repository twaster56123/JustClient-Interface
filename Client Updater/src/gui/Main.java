/*
 * Eric Purvis
 */
package gui;

public class Main {

	
	private Window window;
	private Screen screen;
	
	public Main(){
		
		try{

			//Components
			window = new Window("Eric Purvis's Downloader", 800, 400, false);
			screen = new Screen();
			
			//Combine
			window.add(screen);
			window.validate();
			
			//Continually Paint
			new Thread(new Runnable(){public void run(){
				while(true){
					screen.repaint();
				}
			}}).start();
			
		}catch(Exception ex){
			setErrorMessage(getClass().getSimpleName()+": "+ex.getLocalizedMessage());
		}
		
	}
	
	public void setCompiled(){
		screen.setCompiled();
	}
	
	public void setFileName(String fileName){
		screen.setFileName(fileName);
	}
	
	public void setErrorMessage(String error){
		screen.setErrorMessage(error);
	}
	
	public void setCurrentVersion(int version){
		screen.setCurrentVersion(version);
	}
	
	public void setLatestVersion(int version){
		screen.setLatestVersion(version);
	}
	
	public void setTotalBytes(long bytes){
		screen.setTotalBytes(bytes);
	}
	
	public void setReceivedBytes(long bytes){
		screen.setReceivedBytes(bytes);
	}
	
	public void setCompiledFileSize(long bytes){
		screen.setCompiledFileSize(bytes);
	}
	
	public void setCompiledBytesWrote(long bytes){
		screen.setCompiledBytesWrote(bytes);
	}
	
}
