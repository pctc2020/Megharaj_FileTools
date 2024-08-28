import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

public class AllUnknownFileMover {
	static 			int x = 0;

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "G:\\Recovered data 01-10-2017 at 19_09_50";
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());

		try {
			for (File fileObj : fileList) {
				x++;
				System.out.println(fileObj.getAbsolutePath());
				/*String fileName = fileObj.getName();
				String fileExt = ".noExtRaj";
				FileInputStream fis = new FileInputStream(fileObj);
				try {
					if (fileName.lastIndexOf(".") != -1) {
						fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
					}
					int content;
					int c = 0;
					while (((content = fis.read()) != -1) && (c < 10)) {

					}
					fis.close();
					File dirObj = new File("F:\\NotUsefullFiles\\" + fileExt+"_ext");
					if (!dirObj.isDirectory()) {
						dirObj.mkdirs();
					}
					System.out.println("RENAME:"+ fileObj.renameTo(new File("F:\\NotUsefullFiles\\" + fileExt+"_ext\\" + fileName)));
				} catch (Exception e) {
					e.printStackTrace();
					fis.close();
					System.out.println("RENAME:"+ fileObj.renameTo(new File("F:\\Currpted\\" + fileName + "_megh_" + x + fileExt)));
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				String fileName = file.getName();
				if(file.getAbsolutePath().contains("pst")){
					file.renameTo(new File("G:\\Files\\"+file.getName()));
					System.out.println("Move"+file.getAbsolutePath());
				}
				x++;
				if(x%1000==0){
					System.out.println(x);
				}
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}
}
