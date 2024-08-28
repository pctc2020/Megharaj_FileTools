import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenameFileWithDate {
	public static void main(String[] args) {
		String folders[] = { "C:\\Share\\AllContactsFile\\" };
		for (String srcFolder : folders) {
			getFileNames(srcFolder);
		}
	}
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	static int processCount = 0;
	private static void getFileNames(String directoryName) {
		
		try {
			File directory = new File(directoryName);
			if (directory.isDirectory()) {
				if (directory.list().length > 0) {
					File[] fList = directory.listFiles();
					for (File file : fList) {
						if (file.isDirectory()) {
							getFileNames(file.getAbsolutePath());
						} else {
							Path filePath = Paths.get(file.getAbsolutePath());
							BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
							String createDate = df.format( new Date(attr.creationTime().toMillis()));
							String modifyDate = df.format( new Date(attr.lastModifiedTime().toMillis()));
							processCount++;
							String newName = file.getParent()+"\\"+(createDate+"#"+modifyDate+"#"+file.getName());
							System.out.println(newName);
							file.renameTo(new File(newName));
							System.out.println(processCount + " - " + file.getAbsolutePath()+" >> "+newName);
						}
					}
				} else {
					// System.out.println(processCount+" :
					// "+directory.getAbsolutePath()+" : "+directory.delete());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
