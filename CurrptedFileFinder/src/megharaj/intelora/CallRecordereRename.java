import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

public class CallRecordereRename {
	static String FILE_EXT1 = ".AMR";
	static String FILE_EXT2 = FILE_EXT1.toLowerCase();
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "C:\\Users\\raj\\Desktop\\New folder";
		String tgtFolder = srcFolder;
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: "+fileList.size());
		int x=100;
		for (File fileObj : fileList) {
			String fileName = fileObj.getAbsolutePath();
			x++;
			if (fileName.endsWith(".amr") || fileName.endsWith(".AMR") ) {
				fileName = fileName.replace(tgtFolder, "");
				fileName = fileName.replace("\\", "_");
				String newName = tgtFolder+"\\"+fileName;
				
//				System.out.println(fileName+"  >>  "+newName);
				System.out.println("RENAME:" + fileObj.renameTo(new File(newName)));
			}
		}
		
	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				if(file.getName().endsWith(FILE_EXT1) || file.getName().endsWith(FILE_EXT2)){
					files.add(file);
				}
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

}
