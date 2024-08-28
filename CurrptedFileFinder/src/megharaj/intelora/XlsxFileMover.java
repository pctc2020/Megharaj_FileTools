import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxFileMover {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\DATALACK\\IN2\\zip xlsx docx pptx\\todo";
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());
	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		System.out.println("fList = "+fList.length);
		for (File file : fList) {
			if (file.isFile()) {
				processFile(file);
			} else if (file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

	static int count = 0;
	private static String fileFilter[] = {"Name Email Id Alternate Number Date of Birth Mobile No.",
				"CLASNAME PREFIX CALLER COMPANY PHONE",
				"Msisdn CC Name Cccity Ccczip"	};

	public static void processFile(File fileObj) {
		String fileName = fileObj.getName();
		System.out.println(count+" Processing ="+fileName);
//		if (fileName.endsWith(".xlsx")) 
		{
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(fileObj);
				Workbook workbook = new XSSFWorkbook(fis);
				count++;
				int numberOfSheets = workbook.getNumberOfSheets();
				// System.out.println(fileName+"::"+workbook.getSheetName(0));
				Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
				String strline = "";
				if (rowIterator.hasNext()) {
					// System.out.print(fileName+": \t");
					Row row1 = rowIterator.next();
					Iterator<Cell> cellIterator = row1.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						strline = strline +" "+ cell.getStringCellValue() ;
					}
				}
				strline = workbook.getSheetName(0) +" "+strline;
				
				boolean match = false;
				if(strline.contains(fileFilter[0]) || strline.contains(fileFilter[1]) || strline.contains(fileFilter[2])){
					// System.out.println("file="+fileName+"= "+strline);
					match = true;
				}
//				String matchRowvals[] = {
//						"Sr. No. DATE REQUIREMENT   Contact Number  status Candidate name  Expected CTC Notice Period  Current Location  ",
//						"Name Email Id Alternate Number Date of Birth Mobile No.",
//						"comments status Name  contact no Email ID exp  current ctc  expect ctc  ",
//						"Name Email-ID Contact No. Location Skills Exp in ", "Name MOB                 Comments",
//						"Srno NAME CITY STATE MOB", "CITY MOBILE NO NAME ADDRESS CATEGORY",
//						"STUDENT NAME PHONE ADDRESS AREA ORGCL ROUTENO",
//						"sno ADD accode mobno cname desigcode empcode address zip ", "NAME ADD1 ADD2 ADD3 ADD4 ",
//						"Sr. No. DATE REQUIREMENT   Contact Number   Candidate name  Expected CTC Notice Period  Current Location ",
//						"S. No: Module Name Page Name Issue Status", "S.No Name of the Candidate",
//						"name mobile no  name  total exp  current CTC  expect CTC  notice period" };
//				for (String matchRowval : matchRowvals) {
//					if (strline.contains(matchRowval)) {
//						match = true;
//						// fileName=fileName.replace(".megh",
//						// "").replace(".doc", "").replace(".xls",
//						// "").replace(".zip", "");
//						boolean flag = fileObj.renameTo(new File("D:\\DVDContactData\\File00_" + count + ".xls"));
//						if (!flag) {
//							System.out.println("Faile to rename= " + fileObj.getAbsolutePath());
//						}
//					}
//				}
				strline = strline+"000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
				strline = (strline.substring(0, 60).replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "").replace(":", "")
						.replace("\\", "").replace("/", "").replace("%", "").replace(",", "").replace(";", "").replace("\"", "").replace("'", "")
						.replace("<", " ").replace(">", " ").replace("?", "").replace("*", "").replace("|", "").replaceAll("  ", " "));

				if (match) {
					count++;
					boolean flag = fileObj.renameTo(new File( "D:\\DATALACK\\IN2\\byProgram\\contactxls\\" +strline+ count + ".xlsx"));
					System.out.print(flag+" "+strline + "\n");
				} else {
					boolean flag = fileObj.renameTo(new File( "D:\\DATALACK\\IN2\\byProgram\\excelfiles\\" +strline+ count + ".xlsx"));
					System.out.print(flag+" "+strline + "\n");
				}
			} catch (Exception e) {
				boolean flag = fileObj.renameTo(new File("D:\\DATALACK\\IN2\\zip xlsx docx pptx\\notxls\\" +fileName));
				System.out.println(flag+"Not Open: "+fileObj.getAbsolutePath());
			}
			try {
				fis.close();
			} catch (IOException e1) {
				// e1.printStackTrace();
			}
		}
	}
}