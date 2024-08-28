import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GetFileExtDataStatics {
	static HashMap<String, Integer> fileExtMap = new HashMap<String, Integer>();

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\SearchEngine";
		getFileNames(fileList, srcFolder);
		System.out.println(fileList.size());
		Set<String> setKey = fileExtMap.keySet();
		for(String s: setKey){
			if(fileExtMap.get(s).intValue()>0){
				System.out.println(s+"\t"+fileExtMap.get(s));
			}
		}
	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
//		System.out.println(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				files.add(file);
				addExtIntoMAp(file.getName().toLowerCase());
			} else if (file.isDirectory()) {
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

	private static void addExtIntoMAp(String name) {
		String ext = "NULL";
//		name=name.replace(".megh", "");
		if (name.lastIndexOf(".") != -1) {
			ext = name.substring(name.lastIndexOf(".")+1);
		}
		if (fileExtMap.get(ext) == null) {
			fileExtMap.put(ext, new Integer(1));
		} else {
			int n = fileExtMap.get(ext);
			n++;
			fileExtMap.put(ext, new Integer(n));
		}
	}
}
