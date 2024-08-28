import java.io.File;

public class RenameFileEXt {

	public static void main(String[] args) {
		// G:\Filtered Data\OLDDATA\zip
		String srcFolder = "D:\\DATALACK\\CrrptedFiles\\";
		getFileNames(srcFolder);
		
	}

	static int x = 0;
	public static void processFile(File fileObj) {
		x++;
		// if(x%250==0){
		// 	System.out.println(x+" "+fileObj.getAbsolutePath());
		// }
		try {
			String fullPath = fileObj.getAbsolutePath();
			// if(fullPath.length()>280){
			// 	System.out.println("size:"+fullPath.length()+" "+fileObj.getAbsolutePath());
			// }
			String fileName = fileObj.getName();
			if(fileName.contains("_mgh_appl_.octet-stream"))
			{
				String newFileName = (""+fileObj.getAbsolutePath())
					.replace("_mgh_appl_.octet-stream", "_mgh_.txt");
				System.out.println(fileObj.getAbsolutePath());
				System.out.println("1 No Err in: "+fileObj.renameTo(new File(newFileName)) );
			}
		} catch (Exception e) {
			System.out.println("4 Err in: "+fileObj.getAbsolutePath());
		}

	}

	private static void getFileNames(String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				processFile(file);
			} else if (file.isDirectory()) {
				getFileNames(file.getAbsolutePath());
			}
		}
	}
}
