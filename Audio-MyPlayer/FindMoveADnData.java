import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class FindMoveADnData {
    static int x=0;
	static List<File> fileList = new ArrayList<File>();
	public static void main(String[] args) {		
		String srcFolder = "C:\\Users\\DELL\\Desktop\\ttt";
		getFileNames(srcFolder);
		System.out.println("totalFiels: "+fileList.size());

		try {			
			for (File fileObj : fileList) {
                boolean isADFile = identifyAndPlayFile(fileObj);
                
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
    }    
    private static Player player = null;
    private static boolean identifyAndPlayFile(File fileObj) {
        boolean isADFile = false;
        
        try {
            MediaLocator locator = new MediaLocator(new URL(fileObj.getAbsolutePath()));
            player = Manager.createPlayer(locator);
            player.addControllerListener(new ControllerListener() {
                public void controllerUpdate(ControllerEvent event) {
                if (event instanceof EndOfMediaEvent) {
                    player.stop();
                    player.close();
                }
                }
            });
            player.realize();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( isADFile + "  "+fileObj.getAbsolutePath());		
        return isADFile;
    }

    private static void getFileNames(String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				x++;
				if (!file.getName().endsWith(".svg") ) {
					fileList.add(file);
				}
			} else if (file.isDirectory()) {
				getFileNames( file.getAbsolutePath());
			}
		}
	}
}
