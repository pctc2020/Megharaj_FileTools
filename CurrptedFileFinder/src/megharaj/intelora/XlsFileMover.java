import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class XlsFileMover {
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\DATALACK\\IN2\\zip xlsx docx pptx";
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
				System.out.println(file.getAbsolutePath());
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

	static int count = 0;
	public static void processFile(File fileObj) {
		String fileName = fileObj.getName();
		System.out.println("Processing ="+fileName);
		//if (fileName.contains("xls"))
		{
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(fileObj);
				Workbook workbook = null;
				workbook = new HSSFWorkbook(fis);
				count++;
				int numberOfSheets = workbook.getNumberOfSheets();
				Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
				String strline = "";
				if(rowIterator.hasNext()){
					//System.out.print(fileName+": \t");
					Row row1 = rowIterator.next();
					Iterator<Cell> cellIterator = row1.cellIterator();
					while(cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						strline = strline + cell.getStringCellValue()+" ";
					}
				}
				boolean match = false;
//				String matchRowvals[] = {
//						"Sr. No. DATE REQUIREMENT   Contact Number  status Candidate name  Expected CTC Notice Period  Current Location  ",
//						"Name Email Id Alternate Number Date of Birth Mobile No.",
//						"comments status Name  contact no Email ID exp  current ctc  expect ctc  ",
//						"Name Email-ID Contact No. Location Skills Exp in ",
//						"Name MOB                 Comments",
//						"Srno NAME CITY STATE MOB",
//						"CITY MOBILE NO NAME ADDRESS CATEGORY",
//						"STUDENT NAME PHONE ADDRESS AREA ORGCL ROUTENO",
//						"sno ADD accode mobno cname desigcode empcode address zip ",
//						"NAME ADD1 ADD2 ADD3 ADD4 ",
//						"Sr. No. DATE REQUIREMENT   Contact Number   Candidate name  Expected CTC Notice Period  Current Location ",
//						"S. No: Module Name Page Name Issue Status",
//						"S.No Name of the Candidate",
//						"name mobile no  name  total exp  current CTC  expect CTC  notice period"
//				};
//				for(String matchRowval :  matchRowvals){
//					if(strline.contains(matchRowval)){
//						match = true;
//						boolean flag = fileObj.renameTo(new File("D:\\DVDContactData\\File00_"+count+ ".xls"));
//						if(!flag){
//							System.out.println("Faile to rename= "+fileObj.getAbsolutePath());
//						}
//					}
//				}
				if(!match){
					strline = strline+"00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
					System.out.println("strline="+strline);
					strline = strline.substring(0,80);
					count++;
					// boolean flag = fileObj.renameTo(new File("D:\\DATALACK\\IN2\\zip xlsx docx pptx\\contactxls" +strline+ count + ".xls"));
					System.out.print(strline+"\n");
				}
			} catch (Exception e) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}