import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader {
	private static List<String> listQuery = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		List<File> fileList = new ArrayList<File>();
		String folders[] = { "D:\\autorenames\\plaintxt2" };
		File f1 = new File("D:\\autorenames\\OtherFileContentasSQL2.sql");
		FileWriter fw = new FileWriter(f1);
		for (String srcFolder : folders) {
			System.out.println(srcFolder);
			getFileNames(fileList, srcFolder, fw);
		}
	}

	private static void getFileNames(List<File> files, String directoryName, FileWriter fw) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				generateSQLQueryForData(file, fw);
			}
		}
	}

	private static void generateSQLQueryForData(File file, FileWriter fw) {
		String qry = "insert into " + file.getName() + "  (";
		try {
			if (file.getName().startsWith("ShowFolder[") || file.getName().startsWith("ShowLetter[")) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine().replace(" ", ",");
				qry = qry + data + ") values ";
				data = br.readLine();
				while ((data = br.readLine()) != null) {
					qry = qry + "\n ( " + data.replace("  ", ",") + " ),";
				}
				qry = qry + ");";
				qry = qry.replace(",);", ");");
				System.out.println(file.getName());
				fr.close();
			}
			fw.write(qry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
