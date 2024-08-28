import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PDFFileMover {
	public static String fileFolderLocation = "D:\\DATALACK\\IN2\\zip xlsx docx pptx\\notxls";

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = fileFolderLocation;
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());
	}

	static int x = 10000;

	public static void processFile(File fileObj) {
		String fileName = fileObj.getName();
		if (fileName.endsWith(".pdf") || fileName.endsWith(".pdf")) {
			System.out.println(fileObj.getAbsolutePath());
			x++;

			String st = "";
			PDDocument document = null;

			try {
				String passwords[] = { "AJUPD7845F", "ajupd7845f", "01071979", "M78315", "megh0107", 
						"MEGH0107", "71515203", "MDADHICH", "ajupd7845f01071979", "AJUPD7845F01071979", 
						"", "4335876449270876", "MEGH2334", "MEGH5733", "38611038" };
				boolean isPasswordFound = false;
				for (String password : passwords) 
				for(long num=522200L; num<9999999999999L; num++){
					password=num+"";
					try {
						if(num%100==0){
						System.out.println("trying...["+password+"]         -        "+fileName);
						}
						document = PDDocument.load(fileObj, password);
						document.getClass();
						System.out.println("got ...");
						PDFTextStripperByArea stripper = new PDFTextStripperByArea();
						stripper.setSortByPosition(true);
						PDFTextStripper Tstripper = new PDFTextStripper();
						st = Tstripper.getText(document).trim().replaceAll("[\\n\\r]+", " ").replaceAll("  ", " ");
						document.close();
						st = (password + " PASS " + st
								+ " 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ")
										.substring(0, 120);
						st = st.replace(":", "").replace("//", "").replace("/", "").replace("'", "").replace("\"", "")
								.replace("|", "");
						String st1 = fileFolderLocation + st + "_" + x + ".pdf";
						System.out.println("got ...");
						isPasswordFound = true;
						boolean flag = fileObj.renameTo(new File(st1));
						System.out.println(password + " = " + st + "_" + x + ".pdf");
						if (flag == false) {
							System.out.println("Unable to name: " + st1);
						}
					} catch (InvalidPasswordException ipe) {

					}
				}
				if (!isPasswordFound) {
					document.close();
					System.out.println("not found file : "
							+ fileObj.renameTo(new File(fileObj.getAbsolutePath() + "_unknownPass.pdf"))
							+ fileObj.getAbsolutePath());
				}
			} catch (Exception e) {
				try {
					document.close();
				} catch (Exception e1) {
				}
				System.out.println(
						"not pdf file : " + fileObj.renameTo(new File(fileObj.getAbsolutePath() + "_unknownPass.pdf"))
								+ fileObj.getAbsolutePath());
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
