import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteFaltuFileType {

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String folders[] ={  
				"K:\\MEGHDATA\\A_RC"
//				"K:\\Duplicate PST", "K:\\DVDContactData", "K:\\Education", "K:\\Other Business Docs", "K:\\SoftwaresALL", "K:\\Fun3", 
				};
		for(String srcFolder: folders){
			System.out.println(srcFolder);
			getFileNames(fileList, srcFolder);
		}
		System.out.println("Deleted: "+changedCount);

		System.out.println(fileTypesCount);
	}
	static int x=0;
	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		try{
			for (File file : fList) {
				if (file.isFile()) {
					renameToExt(file);
				} else if (file.isDirectory()) {
					getFileNames(files, file.getAbsolutePath());
				}
			}
		}catch(Exception e){
			System.out.println("Err: in file"+directory.getAbsolutePath());
		}
	}
	static int changedCount=0;
	static HashMap<String, Integer> fileTypesCount = new HashMap<String, Integer>();
	private static void renameToExt(File file)  {
		if(x%5000==0){
			System.out.println("X="+x+"   "+changedCount);
		}
		try{
			String currentFilename = file.getAbsolutePath();
			if(!currentFilename.contains("_._."))
			{
				FileReader	fr = new FileReader(file);
				int ch1 = fr.read(), ch2 = fr.read(), ch3 = fr.read(), ch4 = fr.read(), ch5 = fr.read(), ch6 = fr.read(), ch7 = fr.read();
				fr.close();
				if( ch1 == 0 && ch2 == 0 && ch3 == 1 && ch4 == 0 && ch5 == 3 && ch6 == 0 && ch7 == 16){
					changedCount++;
					file.delete();
				}
				else if( ch1 == 0 && ch2 == 0 && ch3 == 0 && ch4 == 0 && ch5 == 0  ){
					changedCount++;
					file.delete();
				}
				else if( ch1 == -1 && ch2 == -1 && ch3 == -1 && ch4 == -1 && ch5 == -1 ){
					changedCount++;
					file.delete();
				}
				else if( ch1 == 255 && ch2 == 255 && ch3 == 255 && ch4 == 255 && ch5 == 255 ){
					changedCount++;
					file.delete();
				}
				else if( ch1 == -1 && ch2 == -1 && ch3 == -1 && ch4 == -1 && ch5 == -1  ){
					changedCount++;
					file.delete();
				}
				else if(ch1 == 255 && ch2 == 254 && ch3 == 59 && ch4 == 0 && ch5 == 32 && ch6 == 0 && ch7 == 80){
					changedCount++;
					file.delete();
				}
				else if( ch1 == 77 && ch2 == 90 && ch3 == 65533 && ch4 == 0 && ch5 == 3 && ch6 == 0 && ch7 == 0){
					changedCount++;
					file.delete();
				}
				else if( ch1 == 202 && ch2 == 254 && ch3 == 186 && ch4 == 190 && ch5 == 0 && ch6 == 0 && ch7 == 0 ){
					changedCount++;
					file.delete();
				}
				else if( ch1 == 0 && ch2 == 1 && ch3 == 0 && ch4 == 0 && ch5 == 0 && ch6 == 15 && ch7 == 0 ){
					changedCount++;
					file.delete();
				}else if( ch1 == 0 && ch2 == 0 && ch3 == 0 && ch4 == 0 && ch5 == 0 && ch6 == 0 && ch7 == 0 ){
					changedCount++;
					file.delete();
//				}else if( ch1 == 105 && ch2 == 109 && ch3 == 112 && ch4 == 111 && ch5 == 114 && ch6 == 116 && ch7 == 32 ){
//					//DELETE JAVA FILES
//					changedCount++;
//					file.delete();
				}else{
//					addNewFileType(fileTypesCount, "_"+ch1+"_"+ch2+"_"+ch3+"_"+ch4+"_"+ch5, file);
				} 
				// to delete, ico, dll, sys, ini, exe, class, java
				
//				else if(ch1==82 && ch2 == 97  && ch3 == 114  && ch4 == 33 && ch5 == 26){
//					System.out.println("1: "+file.getAbsolutePath());
//					file.renameTo(new File(currentFilename.replace(".megh", ".rar")));
//				}
			}
		}catch(Exception e){
			System.out.println("Error to rename:"+file.getAbsolutePath());
		}
		x++;
	}
	private static void addNewFileType(HashMap<String, Integer> fileTypesCount, String key, File file) {
		if(fileTypesCount.get(key)==null){
			fileTypesCount.put(key, 1);
		}else{
			int value = fileTypesCount.get(key);
			value++;
			if(value>10){
				//System.out.println(value+" : "+key+" "+file.getAbsolutePath());
			}
			fileTypesCount.put(key, value);
		}
	}
}
