import java.io.File;

public class CreateEmptyFolderStructure {
	static String tgtLocation = "D:\\Lucene\\Index";
	static String srcLocation = "F:\\MEGHDATA\\DATA\\d ncr";
	private static void createIndexFolder() {
		File files[] = new File(srcLocation).listFiles();
		for(File nameFolder:files){
			if(nameFolder.isDirectory()){
				String newFile = nameFolder.getAbsolutePath().replace(srcLocation, tgtLocation);
				if(!new File(newFile).exists())
					new File(newFile).mkdir();
			}
		}
	}
	public static void main(String[] args) {
		createIndexFolder();
	}

}
