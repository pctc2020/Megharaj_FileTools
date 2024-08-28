import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class JpegFileFilter {
	static int x = 0;
	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		// G:\Filtered Data\OLDDATA\zip
		String srcFolder = "K:\\MEGHDATA\\A_RC";
		getFileNames(fileList, srcFolder);
		System.out.println("totalFiels: " + fileList.size());
	}

	static String parentFolder = "K:\\MEGHDATA\\img_0";
	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File fileObj : fList) {
			if(x%30000==0){
				parentFolder = "K:\\MEGHDATA\\img_"+x;
				new File(parentFolder).mkdir();
				System.out.println("x="+x);
			}
			x++;
//			System.out.println(fileObj.getAbsolutePath());
			if (fileObj.isFile()) {
				if (fileObj.getName().endsWith(".jpg") || fileObj.getName().endsWith(".png")  || fileObj.getName().endsWith(".JPG") || fileObj.getName().endsWith(".PNG")) {
					String fileName = fileObj.getName();
						try {
							BufferedImage img = ImageIO.read(fileObj);
							String s1 = "" + (img.getHeight() + " : " + img.getWidth());
							System.out.println(x + " " + s1 + " RENAME:" + fileObj.renameTo(new File(parentFolder+"\\" + x + "_" + fileName)));
						} catch(NullPointerException e){
							System.out.println("not jpg file in: "+fileObj.renameTo(new File(fileObj.getAbsolutePath()+".megh")));
						}catch (Exception e) {
							System.out.println("1 Err in: "+fileObj.getAbsolutePath());
							// fileFullPath = Util.getCorrectName(fileFullPath);
							// System.out.println("RENAME:" +
							// fileObj.renameTo(new File(fileFullPath +
							// fileName)));
						}
				}
			} else if (fileObj.isDirectory()) {
				System.out.println(fileObj.getAbsolutePath());
					getFileNames(files, fileObj.getAbsolutePath());
			}
		}
	}
}
