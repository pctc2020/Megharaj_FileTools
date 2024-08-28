import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

public class FindMoveZipFile {

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		String srcFolder = "D:\\DATALACK\\IN2\\zip xlsx docx pptx\\notxls";
		getFileNames(fileList, srcFolder);
	}
	static int fileNo = 0;
	public static void processFile(File fileObj) {
		fileNo++;
		try {
			String fileName = fileObj.getName();
			/*if (fileName.endsWith(".zip") || fileName.endsWith(".rar") || fileName.endsWith(".jar") || fileName.endsWith(".war")
				||	fileName.endsWith(".ZIP") || fileName.endsWith(".RAR") || fileName.endsWith(".JAR") || fileName.endsWith(".WAR") )*/ 
			{
				FileInputStream fis = new FileInputStream(fileObj);
				try {
					ZipInputStream zis = new ZipInputStream(fis);
					ZipEntry ze = zis.getNextEntry();
					// if (ze.getName().startsWith("android-6.0") || ze.getName().startsWith("org") || ze.getName().startsWith("com") || ze.getName().startsWith("META-INF")
					// 		|| ze.getName().startsWith("google") || ze.getName().startsWith("sun") || ze.getName().startsWith("liferay") 
					// 		|| ze.getName().startsWith("res") || ze.getName().startsWith("javafx") || ze.getName().startsWith("WEB-INF") 
					// 		|| ze.getName().startsWith("java") || ze.getName().startsWith("assets") || ze.getName().startsWith("scala")
					// 		|| ze.getName().startsWith("net") || ze.getName().startsWith("static") || ze.getName().startsWith("lib")
					// 		|| ze.getName().startsWith("features") || ze.getName().startsWith("oracle") || ze.getName().startsWith("eclipse")
					// 		) 
					{
						// if(zis.getNextEntry().getName().contains("org"))
						{
							// if(zis.getNextEntry().getName().contains("com"))
							{
								// if(zis.getNextEntry().getName().contains("android"))
								{
									// System.out.println(fileObj.getAbsolutePath());
									// System.out.println(zis.getNextEntry().getName());
									// System.out.println(zis.getNextEntry().getName());
									// System.out.println(zis.getNextEntry().getName());
									// fis.close();
									// fileObj.delete();
									// System.out.println("To be DELETED");
								}
							}
						}
					// }else{
//						System.out.println(ze.getName()+" : "+zis.getNextEntry().getName()+" : "+zis.getNextEntry().getName()+" : "+zis.getNextEntry().getName()+" : ");
					}
					// String zename = (x+"_"+(ze.getName().replaceAll("/",
					// "").replaceAll("\\", ""))+"_ file name auto created by
					// java program").substring(0, 25);
					String newName = "D:\\DATALACK\\IN2\\byProgram\\zipfiles\\"+fileNo+"_"+zis.getNextEntry().getName()+"_"+zis.getNextEntry().getName()+"_"+zis.getNextEntry().getName()+"_"+zis.getNextEntry().getName();
					System.out.println("To be clean archive file "+newName);
					fis.close();
					System.out.println("RENAME:" + fileObj.renameTo(new File(newName))+"\n\t"+newName);					
				} catch (NullPointerException e) {
					fis.close();
				//	System.out.println("1 Err in: "+fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\zip\\NotAbleToOpen\\"+fileObj.getName())));
					// System.out.println("RENAME:" + fileObj.renameTo(new
					// File("F:\\Currpted\\_0_0_0_" + fileName +
					// ".crrpted"))+"\n");
				} catch (ZipException e) {
				//	System.out.println("1 Err in: "+fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\zip\\NotAbleToOpen\\"+fileObj.getName())));
				} catch (IllegalArgumentException e) {
				//	System.out.println("1 Err in: "+fileObj.renameTo(new File("K:\\MEGHDATA\\AutonameByJavaProgram\\zip\\NotAbleToOpen\\"+fileObj.getName())));
				}
			}
		} catch (Exception e) {
			//System.out.println("1 Err in: "+fileObj.renameTo(new File("C:\\HDD\\AutonameByJavaProgram\\zip\\NotAbleToOpen\\"+fileObj.getName())));
		}

	}

	private static void getFileNames(List<File> files, String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			fileNo++;
			if (file.isFile()) {
				processFile(file);
			} else if (file.isDirectory()) {
				if(fileNo%100==0){
					System.out.println(fileNo+ " -" + directory.getAbsolutePath());					
				}
				getFileNames(files, file.getAbsolutePath());
			}
		}
	}
}
