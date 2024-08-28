import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmallCaseExt {

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "K:\\MEGHDATA\\A_RC";
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
	static int count=0;
	private static void processFile(File fileObj) {
		if(count%2000==0){
			System.out.println(count);
		}
		count++;
		
		String fileName = fileObj.getAbsolutePath();
		if(fileName.endsWith(".pdf.pdf")){
			String newFileName = fileName.replace(".pdf.pdf", ".pdf");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PDF")){
			String newFileName = fileName.replace(".PDF", ".pdf");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PDF.PDF")){
			String newFileName = fileName.replace(".PDF.PDF", ".pdf");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".pdf.PDF")){
			String newFileName = fileName.replace(".pdf.PDF", ".pdf");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PDF.pdf")){
			String newFileName = fileName.replace(".PDF.pdf", ".pdf");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".jpg.jpg")){
			String newFileName = fileName.replace(".jpg.jpg", ".jpg");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".JPG")){
			String newFileName = fileName.replace(".JPG", ".jpg");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".JPG.JPG")){
			String newFileName = fileName.replace(".JPG.JPG", ".jpg");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".jpg.JPG")){
			String newFileName = fileName.replace(".jpg.JPG", ".jpg");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".JPG.jpg")){
			String newFileName = fileName.replace(".JPG.jpg", ".jpg");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".png.png")){
			String newFileName = fileName.replace(".png.png", ".png");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PNG")){
			String newFileName = fileName.replace(".PNG", ".png");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PNG.PNG")){
			String newFileName = fileName.replace(".PNG.PNG", ".png");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".png.PNG")){
			String newFileName = fileName.replace(".png.PNG", ".png");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".PNG.png")){
			String newFileName = fileName.replace(".PNG.png", ".png");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".zip.zip")){
			String newFileName = fileName.replace(".zip.zip", ".zip");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".ZIP")){
			String newFileName = fileName.replace(".ZIP", ".zip");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".ZIP.ZIP")){
			String newFileName = fileName.replace(".ZIP.ZIP", ".zip");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".zip.ZIP")){
			String newFileName = fileName.replace(".zip.ZIP", ".zip");
			fileObj.renameTo(new File(newFileName));
		}else if(fileName.endsWith(".ZIP.zip")){
			String newFileName = fileName.replace(".ZIP.zip", ".zip");
			fileObj.renameTo(new File(newFileName));
		}else{
			
		}
			
		
	}


}
