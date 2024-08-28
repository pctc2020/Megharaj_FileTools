import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class HtmlFileMover {

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "K:\\MEGHDATA\\A_RC" ;
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: "+fileList.size());
	}
	
	static int count = 0;
	public static void processFile(File fileObj){
		try {
			{
				String fileName = fileObj.getName();
			// if (fileName.endsWith(".htm") || fileName.endsWith(".html") ) 
				{
					FileInputStream fis = new FileInputStream(fileObj);
					try {
						
						boolean isTxtFound = false;
						String line1 = ""+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())
								+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read())+((char) fis.read());
						line1 = line1.toLowerCase();
						if(line1.startsWith("<!DOCTYPE".toLowerCase()) || line1.startsWith("<html".toLowerCase()) ){
							isTxtFound = true;
						}
						fis.close();
						if(isTxtFound){

						}else{
							boolean flag = fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\Mp3Files\\" + fileName + ".html"));
							if(!flag){
								flag = fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\Mp3Files\\" + fileName + "_" + count + ".html"));
								{
									System.out.println("Faile to rename= " + fileObj.getAbsolutePath());
								}
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						fis.close();
					}
				}
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
				processFile(file);
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}
}
