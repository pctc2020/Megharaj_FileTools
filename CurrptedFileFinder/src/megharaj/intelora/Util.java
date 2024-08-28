import java.io.File;

public class Util {
	public static String srcFolder = "F:\\Filtered Data";
	public static String tgtFolder = "F:\\Currpted\\";
	public static String getCorrectName(String fileFullPath) {
		String newFileFullPath=tgtFolder;
		String subDirs[] = fileFullPath.replace("\\", "##").split("##");
		for(int n=1; n<subDirs.length-1; n++){
			newFileFullPath = newFileFullPath+subDirs[n]+"\\";
			if(!new File(newFileFullPath).isDirectory()){
				new File(newFileFullPath).mkdir();
			}
		}
		System.out.println("OLD:"+fileFullPath+" NEW:"+newFileFullPath);
		return newFileFullPath;
	}

}
