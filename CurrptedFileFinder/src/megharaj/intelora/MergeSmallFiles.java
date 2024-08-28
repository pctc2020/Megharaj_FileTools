import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MergeSmallFiles {
	static long tgt_fileSize = 2049; // in bytes

	public static void main(String[] args) throws Exception {
		String tgtFolderPath = "H:\\HDD-0";
		ArrayList<File> listFiles = new ArrayList<File>();
		getFileNames(listFiles, new File(tgtFolderPath));
		for (File fileName : listFiles) {
			Thread.currentThread().sleep(100);
			String fileData = getFileContent(fileName);
			int result = PSQLDataWriter.saveDataInToDB(fileName.getName(), fileName.getParent(), fileData);
			System.out.println(result+":"+fileName.getAbsolutePath()+" >Delted:"+fileName.delete());
		}

	}

	private static String getFileContent(File fileName) throws IOException {
		byte[] byts=Files.readAllBytes(Paths.get(fileName.getAbsolutePath()));
		int n=0;
		String data="";
		for(byte b1: byts) {
			n++;
			int asciiCode = (int) b1;
			if(asciiCode==0 || asciiCode<13  || asciiCode==17  || asciiCode==0  || asciiCode==39  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
			//		 || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0  || asciiCode==0 
					) 
			{
				
			}else {
				//System.out.print(" ("+n+") "+((int)b1)+"-"+((char)b1));
				data = data+((char) b1);
			}
		}
		//System.out.println("..."+data+"...");
		
		return data;
	}

	private static void getFileNames(ArrayList<File> listFiles, File directory) throws IOException {
		if (directory.isDirectory()) {
			File[] fList = directory.listFiles();
			for (File fileName : fList) {
				if (fileName.isDirectory()) {
					getFileNames(listFiles, fileName);
				} else {
					if (fileName.isFile() && fileName.length() < tgt_fileSize) {
						listFiles.add(fileName);
					}
				}
			}
		} else {
			if (directory.isFile() && directory.length() < tgt_fileSize) {
				listFiles.add(directory);
			}
		}
	}

}
