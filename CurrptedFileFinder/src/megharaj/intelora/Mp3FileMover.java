import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mp3FileMover {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "K:\\MEGHDATA\\A_RC" ;
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());
	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				processFile(file);
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

	static int count = 0;
	public static void processFile(File fileObj) {
		String fileName = fileObj.getName();
		{
			FileReader fr = null;
			try {
				fr = new FileReader(fileObj);
				int ch1 = fr.read(), ch2 = fr.read(), ch3 = fr.read(), ch4 = fr.read(), ch5 = fr.read(), ch6 = fr.read(), ch7 = fr.read();
				fr.close();
				String file6CharsStr = "(ch1 == "+ch1 +" && ch2 == "+ch2 + " && ch3 == "+ch3 + " && ch4 == "+ch4 + " && ch5 == "+ch5 +" && ch6 == "+ch6 +" && ch7 == "+ch7+")";
				
				
				if (ch1 == 73 && ch2 == 68 && ch3 == 51 && ch4 == 3 && ch5 == 0 && ch6 == 0 && ch7 == 0) {
					fileName = fileName.replace(".megh", "").replace(".doc", "").replace(".xls", "").replace(".zip","");
					boolean flag = fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\Mp3Files\\" + fileName + "_" + count + ".mp3"));
					count++;
					if (!flag) {
						System.out.println("Faile to rename= " + fileObj.getAbsolutePath());
					}
				}
				
				
			} catch (Exception e) {
				try {
					fr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}