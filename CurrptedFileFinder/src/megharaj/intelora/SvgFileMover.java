import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SvgFileMover {
	static int x=0;
	static List<File> fileList = new ArrayList<File>();
	public static void main(String[] args) {		
		String srcFolder = "D:\\DATALACK\\CrrptedFiles";
		getFileNames(srcFolder);
		System.out.println("totalFiels: "+fileList.size());

		try {			
			for (File fileObj : fileList) {
				String fileName = fileObj.getName();
					FileReader fis = new FileReader(fileObj);
					try {
						BufferedReader br =new BufferedReader(fis);
						String line1 = (""+br.readLine()).toUpperCase();
						fis.close();
						if(line1.startsWith("<svg") ){
							
							System.out.println(line1);
							System.out.println("RENAME:" + fileObj.renameTo(new File("D:\\DATALACK\\AutoFinded\\svgfiles\\" + fileName + "_"+x+fileName.substring(fileName.lastIndexOf("."))+".svg")));
							System.out.println(fileName);
						}
					//	System.out.println(zename);
					} catch (Exception e) {
						e.printStackTrace();
						fis.close();
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getFileNames(String directoryName) {
		System.out.println("totalFiels: "+fileList.size());
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				x++;
				if (!file.getName().endsWith(".svg") ) {
					fileList.add(file);
				}
			} else if (file.isDirectory()) {
				getFileNames( file.getAbsolutePath());
			}
		}
	}
}
