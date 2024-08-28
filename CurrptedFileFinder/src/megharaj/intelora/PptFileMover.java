import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.record.Slide;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.SlideShow;

public class PptFileMover {
	public static String fileFolderLocation = "\\\\LPTP-001\\tmpShr\\";

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = fileFolderLocation;
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
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}

	static int count = 0;

	public static void processFile(File fileObj) {
		String fileName = fileObj.getName();
		System.out.println(count + " Processing =" + fileName);
		FileInputStream fis = null;
		// if (fileName.contains("ppt"))
		{
			try {
				fis = new FileInputStream(fileObj);
				HSLFSlideShow show = new HSLFSlideShow(fis);
				List<HSLFSlide> lst = show.getSlides();

				String fileData = lst.get(0).getTitle();
				if (lst.size() > 2) {
					fileData = fileData + lst.get(1).getTitle();
					fileData = fileData + lst.get(2).getTitle();
				}
				fileData = fileData + " 0000000000000000000000000000000000000000000000000000000000000000000000000";
				fileData = (fileData.substring(0, 60).replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "")
						.replace(":", "").replace("\\", "").replace("/", "").replace("%", "").replace(",", "")
						.replace(";", "").replace("\"", "").replace("'", "").replace("<", " ").replace(">", " ")
						.replace("?", "").replace("*", "").replace("|", "").replaceAll("  ", " "));
				fis.close();
				count++;
				boolean flag = fileObj.renameTo(new File(fileFolderLocation + fileData + ".pptx"));
				if (!flag) {
					boolean flag1 = fileObj.renameTo(new File(fileFolderLocation + fileData + count + ".pptx"));
					if (!flag1) {
						System.out.println(fileData);
					}
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