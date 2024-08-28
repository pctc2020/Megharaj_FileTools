import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DOCFileMover {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\mgh\\";
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());
	}
	static int count= 0;
	public static void processFile(File fileObj){
			String fileName = fileObj.getName();
			System.out.println("Processing ="+fileName);
			FileInputStream fis = null;
			//if (fileName.contains("doc"))
			{
				try {
					fis = new FileInputStream(fileObj);
					HWPFDocument document = new HWPFDocument(fis);
					WordExtractor extractor = new WordExtractor(document);
		            String fileData = extractor.getText();
		            fileData = fileData+" 000000000000000000000000000000000000000000000000000000000000000000000";
		            fileData = (fileData.substring(0, 60).replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "").replace(":", "")
		            		.replace("\\", "").replace("/", "").replace("%", "").replace(",", "").replace(";", "").replace("\"", "").replace("'", "")
		            		.replace("<", " ").replace(">", " ").replace("?", "").replace("*", "").replace("|", "").replaceAll("  ", " "));
					fis.close();
					count++;
		            boolean flag = fileObj.renameTo(new File("D:\\autorenames\\DocFiles\\" + fileData+".doc"));
		            if(!flag){
			            boolean flag1 = fileObj.renameTo(new File("D:\\autorenames\\DocFiles\\" + fileData+"_"+count+".doc"));
			            if(!flag1){
			            	System.out.println("Faile to rename= "+fileData);
			            }
					}
		            System.out.println(count);
				} catch (Exception e) {
					try {
						fis.close();
					} catch (IOException e1) {
					}
//					System.out.println("RENAME:" + fileObj.renameTo(new File("F:\\Currpted\\" + fileName )));
				//	e.printStackTrace();
				}
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
