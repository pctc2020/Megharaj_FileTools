import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DOCXFileMover {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\DATALACK\\IN2\\zip xlsx docx pptx\\notxls";
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
		FileInputStream fis = null;
		System.out.println(count+" Processing ="+fileObj.getName());
//		if (fileName.contains("doc")) 
		{
			try {
				fis = new FileInputStream(fileObj.getAbsolutePath());
				XWPFDocument doc = new XWPFDocument(fis);
				List<XWPFParagraph> para = doc.getParagraphs();
				String fileData = "";
				if (para.size() > 0) {
					fileData = para.get(0).getText();
				}
				if (para.size() > 1) {
					fileData = fileData+para.get(1).getText();
				}
				if (para.size() > 2) {
					fileData = fileData+para.get(2).getText();
				}
				fileData = fileData+" 0000000000000000000000000000000000000000000000000000000000000000000000000";
	            fileData = (fileData.substring(0, 60).replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "").replace(":", "")
	            		.replace("\\", "").replace("/", "").replace("%", "").replace(",", "").replace(";", "").replace("\"", "").replace("'", "")
	            		.replace("<", " ").replace(">", " ").replace("?", "").replace("*", "").replace("|", "").replaceAll("  ", " "));
				fis.close();
				count++;
	            boolean flag = fileObj.renameTo(new File("D:\\DATALACK\\IN2\\byProgram\\wordfiles\\"+ fileData+".docx"));
	            if(!flag){
	            	boolean flag1 = fileObj.renameTo(new File("D:\\DATALACK\\IN2\\byProgram\\wordfiles\\" + count+fileData+".docx"));
		            if(!flag1){
		            	System.out.println(fileData);
		            }
	            }				
			} catch (Exception e) {
				System.out.println("Not Open: "+fileObj.getAbsolutePath());
				try {
					fis.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
