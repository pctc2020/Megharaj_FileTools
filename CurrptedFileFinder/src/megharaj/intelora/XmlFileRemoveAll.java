import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XmlFileRemoveAll {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		// G:\Filtered Data\OLDDATA\zip
		String srcFolder = "K:\\MEGHDATA\\A_RC";
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size()+" x="+x);
	}
	private static void getFileNames(List<File> files, String directoryName) {
		// System.out.println(directoryName);
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
	
	static int x=0;
	public static void processFile(File fileObj) {
		x++;
		try {
			String fileName = fileObj.getName();
			if (fileName.endsWith(".xml"))
			{
				FileReader	fr = new FileReader(fileObj);
				int ch1 = fr.read(), ch2 = fr.read(), ch3 = fr.read(), ch4 = fr.read(), ch5 = fr.read(), ch6 = fr.read(), ch7 = fr.read();
				fr.close();
				if(ch1==60 && ch2==63 && ch3==120 && ch4==109 && ch5==108 ){
					x++;
					System.out.println(fileObj.getAbsolutePath()+" : "+fileObj.delete());
				}else if(ch1==60 && ch2==15 && ch3==118 && ch4==103 ){
					x++;
					System.out.println(fileObj.getAbsolutePath()+" : "+fileObj.delete());
				}else{
//					System.out.println("not xml file in: "+fileObj.renameTo(new File(fileObj.getAbsolutePath()+".megh")));
				}
			}
		} catch (Exception e) {
			System.out.println("1 Err in: "+fileObj.getAbsolutePath());
		}
	}
}
