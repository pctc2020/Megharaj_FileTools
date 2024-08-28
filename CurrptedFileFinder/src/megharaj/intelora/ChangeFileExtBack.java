import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChangeFileExtBack {
	private static final String replaceWithStr = ".jpg";
	static String tgtFileExt[] = {".JPG", "fkdfsdfja;sfj;fldk"};
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String folders[] ={  
				"C:\\Data", "C:\\SHARE_TODO"
		};
		for(String srcFolder: folders){
			System.out.println(srcFolder);
			getFileNames(fileList, srcFolder);
		}
	}
	static int x=0;
	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		try{
			for (File file : fList) {
				if (file.isFile()) {
					renameToExt(file);
				} else if (file.isDirectory()) {
					getFileNames(files, file.getAbsolutePath());
				}
			}
		}catch(Exception e){
			System.out.println("Err: in file"+directory.getAbsolutePath());
		}

	}
	static int changedCount=0;
	static HashMap<String, Integer> fileTypesCount = new HashMap<String, Integer>();
	private static void renameToExt(File file)  {
		if(x%5000==0){
			System.out.println("X="+x+"   "+changedCount);
		}
		try{
			String currentFilename = file.getAbsolutePath();
			if(currentFilename.endsWith(tgtFileExt[0]) || currentFilename.endsWith(tgtFileExt[1]))
			{
				changedCount++;
				String newName=currentFilename.replace(tgtFileExt[0], replaceWithStr).replace(tgtFileExt[1], replaceWithStr);
				boolean changed = file.renameTo(new File(newName)) ;
				if(!changed){
					System.out.println("Fail to name: "+currentFilename);
				}
			}
		}catch(Exception e){
			System.out.println("Error to rename:"+file.getAbsolutePath());
		}
		x++;
	}
}
