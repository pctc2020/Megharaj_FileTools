import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetFileTypeDigits {
	static HashMap<String, String> fileCharExtMap = new HashMap<String, String>();

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String folders[] ={  
				"K:\\MEGHDATA\\A_RC"
				};
		for(String srcFolder: folders){
			System.out.println(srcFolder);
			getFileNames(fileList, srcFolder);
		}
		System.out.println(fileCharExtMap);
	}
	static int x=0;
	private static void getFileNames(List<File> files, String directoryName) {
		if(x%5000==0){
			System.out.println("X="+x+"   \n"+fileCharExtMap);
		}
		x++;
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		try{
			for (File file : fList) {
				if (file.isFile()) {
					putFilechatIntoMap(file);
				} else if (file.isDirectory()) {
					getFileNames(files, file.getAbsolutePath());
				}
			}
		}catch(Exception e){
			System.out.println("Err: in file"+directory.getAbsolutePath());
		}
	}

	public static void putFilechatIntoMap(File file){
		try{
			FileReader	fr = new FileReader(file);
			int ch1 = fr.read(), ch2 = fr.read(), ch3 = fr.read(), ch4 = fr.read(), ch5 = fr.read(), ch6 = fr.read(), ch7 = fr.read();
			fr.close();
			String file6CharsStr = "(ch1 == "+ch1 +" && ch2 == "+ch2 + " && ch3 == "+ch3 + " && ch4 == "+ch4 + " && ch5 == "+ch5 +" && ch6 == "+ch6 +" && ch7 == "+ch7+")";
			if(fileCharExtMap.get(file6CharsStr)==null){
				String fileFullName = file.getAbsolutePath();
				String fileExt = fileFullName.substring(fileFullName.lastIndexOf(".")+1);
				fileCharExtMap.put(file6CharsStr, fileExt);
			}
			System.out.println(""+((char) ch1)+((char) ch2)+((char) ch3)+((char) ch4)+((char) ch5)+((char) ch6)+((char) ch7));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
