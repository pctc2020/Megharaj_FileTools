import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindDeleteFileList {
	static String keyword = "style";
	static FileWriter dataToWrite = null;
	static final String srcFolder = "D:\\DATALACK\\CrrptedFiles\\";
	public static void main(String[] args) {
		try {
			dataToWrite = new FileWriter(srcFolder+"fileTodelete_"+keyword+".log");
			getFileNames(srcFolder);
			dataToWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int x = 0;
	public static void processFile(File fileObj) {
		x++;
		if(x%250==0)
		{
			// System.out.println(x+" finding in> "+fileObj.getAbsolutePath());
			if(x%5000==0){
				try {
					Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("relesing resources");
			}
		}
		try {
			if(doseFileContentWord(fileObj))
			{
				dataToWrite.append("\n "+fileObj.getAbsolutePath());
				dataToWrite.close();
				dataToWrite = new FileWriter(srcFolder+"fileTodelete_"+keyword+".log", true);	
			}
		} catch (Exception e) {
			System.out.println("4 Err in: "+fileObj.getAbsolutePath());
		}
	}

	public static boolean doseFileContentWord(File fileObj) throws IOException {
        boolean foundFlag = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileObj))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    foundFlag = true;
					break;
                }
				if(foundFlag){
					System.err.println(line);
					break;
				}else{
					
				}
            }
        }
        return foundFlag;
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
