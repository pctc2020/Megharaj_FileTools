package megharaj.zip.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadEncryptedZip extends Thread {
	String passwords[];
	String threadid = "";
	private static int totalHit = 0;
	String fileName = "";

	public ReadEncryptedZip(String[] passSet, String flName, String tid) {
		passwords = passSet;
		threadid = tid;
		fileName = flName;
	}

	public void run() {
		for (String password : passwords) {
			try {
				// password-protected zip file I need to read
				FileInputStream fis = new FileInputStream(fileName);
				// wrap it in the decrypt stream
				ZipDecryptInputStream zdis = new ZipDecryptInputStream(fis, password);
				// wrap the decrypt stream by the ZIP input stream
				ZipInputStream zis = new ZipInputStream(zdis);
				// read all the zip entries and save them as files
				ZipEntry ze;
				while ((ze = zis.getNextEntry()) != null) {
					String newFileName=fileName.replace(".zip", "_ZIP\\")+ze.getName().replace("/", "\\");
					mkDirIfPathNotThere(newFileName);
					FileOutputStream fos = new FileOutputStream(newFileName);
					int b;
					while ((b = zis.read()) != -1) {
						fos.write(b);
					}
					fos.close();
					System.out.println("Able to open:"+fileName+"   password="+password+" filename="+newFileName);
					zis.closeEntry();
				}
				zis.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
	
	
	private void mkDirIfPathNotThere(String newFilePath) {
		int indx = newFilePath.indexOf("\\", 0);
		while(indx<=newFilePath.lastIndexOf("\\")){
			int tmp = indx;
			indx = newFilePath.indexOf("\\", tmp+1);
			if(indx<0)
				break;
			String dir = newFilePath.substring(0,indx);
			if(!new File(dir).exists()){
				new File(dir).mkdirs();
			}
		}
	}

}
