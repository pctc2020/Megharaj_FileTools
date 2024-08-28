package megharaj.pdf.password;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.main.StartDecryptThreads;

public class ReadEncryptedPdf extends Thread {
	String passwords[];
	String threadid = "";
	private static int totalHit = 0;
	String fileName="";
	public ReadEncryptedPdf(String[] passSet, String flName, String tid) {
		passwords = passSet;
		threadid = tid;
		fileName = flName;
	}
	public void run() {
		for (String password : passwords) {
			if(totalHit%50==0)
			{
//				System.out.println("Starting thread="+threadid+" & totalHit="+totalHit+" pass="+password);
			}
			totalHit++;
			if (StartDecryptThreads.searchPasswoard) {
				try {
					byte[] ownerPassword = password.getBytes();
					PdfReader pdfReader = new PdfReader(fileName, ownerPassword);
					pdfReader.pass
					System.out.println("FileName" + fileName);
					System.out.println("Is the PDF Encrypted " + pdfReader.isEncrypted() + " password was: " + password+"\n\n");
					System.out.println("File is opened with full permissions : " + pdfReader.isOpenedWithFullPermissions());
					if(pdfReader.isTampered()){
						System.out.println("\n\n============================FILE TEMPERED==============================\n\n ");
					}
					PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
					TextExtractionStrategy strategy;
					for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
						 String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, i);
//			            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			            System.out.println(textFromPage);
			        }
					
					
					pdfReader.close();
					StartDecryptThreads.searchPasswoard=false;
				} catch (IOException e) {
				//	e.printStackTrace();
				}
			} else {
				break;
			}

		}
//		System.out.println("Finish thread="+threadid);
	}
}
