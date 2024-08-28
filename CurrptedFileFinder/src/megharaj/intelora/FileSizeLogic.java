import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileSizeLogic {

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String folders[] ={  
				 "K:\\Currpted" , "K:\\FULL FOLDER STRICTURE",  
				 "K:\\songs", "K:\\Recovered data 12-16-2016 at 17_49_39"
				};
		for(String srcFolder: folders){
			getFileNames(fileList, srcFolder);
		}
	}
	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				x++;
				processLoginOnFile(file);
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}
	static String lastFilePath = "";
	static int x=0;
	static int changedCount=0;
	static HashMap<String, Integer> fileTypesCount = new HashMap<String, Integer>();
	private static void processLoginOnFile(File file)  {
		if(x%5000==0){
			System.out.println("X="+x+"   "+changedCount);
		}
		lastFilePath = file.getAbsolutePath();
		try{
		if(file.length()<20){
			changedCount++;
			System.out.println( file.length() +" : "+lastFilePath);
			file.delete();
		}
		}catch(Exception e){
			System.out.println( "Not able to del : "+lastFilePath);
		}
	}
}
