import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PrintAllFileNameInTxtFile {
	private static File outputfileName = new File("listFilesWithPath" + new Date().getTime() + ".txt");
	private static FileWriter writer = null;

	public static void main(String[] args) {
		String folders[] = { "\\\\192.168.1.3\\tmpShr\\listnDoNotDeleteIfCrrpt"
				+ "\\" };
		try {
			writer = new FileWriter(outputfileName);
			for (String folder : folders) {
				startWriteFileFullPath(new File(folder));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void startWriteFileFullPath(File directory) throws IOException, InterruptedException {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					startWriteFileFullPath(file);
				}
			}
			System.out.println("Scan and Writing: "+directory.getAbsolutePath());
			writer.flush();
		} else if(directory.getAbsolutePath().toUpperCase().endsWith(".MP3")){
			writer.write(directory.getAbsolutePath()+"\n");
		}
	}
}
