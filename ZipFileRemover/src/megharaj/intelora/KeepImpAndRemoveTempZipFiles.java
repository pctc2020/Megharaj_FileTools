package megharaj.intelora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class KeepImpAndRemoveTempZipFiles {
	static final String folderPath = "D:\\DATALACK\\IN2\\zip xlsx docx pptx";
		
	public static void main(String[] args) throws IOException  {
		File f1 = new File(folderPath);
		String files[]= f1.list();
		FileWriter fout = new FileWriter(new File(folderPath+"\\zipFileDetail.txt"), true);
		for(String fileName: files) {
			String fullFilePath = folderPath+"\\"+fileName;
			String content="";
			try {
				content = getContentList(fullFilePath);
				if(content.contains(" org/eclipse")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/java")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/mysql")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/java")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/incors")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/sun")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/google")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/apache")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/android")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" META-INF/eclipse.inf")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" plugin.xml")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" plugin.properties")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.startsWith("maven/org")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/apache")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/hibern")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/hibern")) {
					moveToRecycle(fullFilePath, content); 
				}else if(content.contains(" org/dom4j")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/acegisecurity")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/pcbsys")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" otdlib/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" xdoclet/modules/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/jasypt")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.startsWith("org/maven")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.startsWith("org/w3c")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/wso2")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/sun")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/eclipse")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/w3c")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" antlr/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/w3c")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/w3c")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/w3c")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/spring")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains("/com.liferay.portal")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" META-INF/maven")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" javax/resource")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" javax/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/ibm")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/ctc")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" android/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sample/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" oracle/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sunw/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" /HL7/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" java2d/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/jaxen")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" res/drawable")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sunw/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" net/sf/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" net/sourceforge/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/osgi/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" com/unboundid/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sun/texti/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sun/plugin/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sun/security/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" PlayerX/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" jxl/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" javassist/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" de/muntjaki/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" org/jasypti/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" EDU/oswego/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sun/plugini/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" sun/util/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" edu/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains("/net/sonicon/videochati/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" java2 developer/")) {
					moveToRecycle(fullFilePath, content);
				}else if(content.contains(" ICM-LITE-")) {
					renameTo(fullFilePath, "ICM-LITE-");
				}else if(content.contains(" xl/_rels/")) {
					renameTo(fullFilePath, "excel");
				}else if(content.contains(" ppt/")) {
					renameTo(fullFilePath, "ppt");
				}else if(content.contains(" word/_rels/")) {
					renameTo(fullFilePath, "word");
				} else {
					// System.out.println("wring in file="+fullFilePath);
					fout.write(fullFilePath+"="+content+"\n\n");
				}
			} catch (Exception e) {
			//	String newPath = fullFilePath.replace("H:\\HDD-0\\Zip compression file\\", "H:\\HDD-0\\Zip compression file\\nozip\\");
			//	new File(fullFilePath).renameTo(new File(newPath));	
			}
		}
		fout.close();
	}
	
	private static void renameTo(String fullFilePath, String surfix) {
		System.out.println("renaming"+surfix);
		String newPath = fullFilePath.replace( folderPath, folderPath+"\\nozip\\"+surfix);
		new File(fullFilePath).renameTo(new File(newPath));	
	}	
	private static void moveToRecycle(String fullFilePath, String content) {
		System.out.println(fullFilePath+content);
		String newPath = fullFilePath.replace(folderPath, folderPath+"\\dellme\\" );
		new File(fullFilePath).renameTo(new File(newPath));	
	}

	private static String getContentList(String fullFilePath) throws Exception {
        final ZipFile file = new ZipFile(fullFilePath);
        String tmp="";
        //System.out.println("Iterating over zip file : " + fullFilePath);
        try {
            final Enumeration<? extends ZipEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
               // System.out.printf("File: %s Size %d  Modified on %TD %n", entry.getName(), entry.getSize(), new Date(entry.getTime()));
                tmp = tmp+";\n\t "+entry.getName();
               // file.close();
            }
           // System.out.println("Zip file %s extracted successfully in "+ fullFilePath);
        } finally {
            file.close();
        }
        return tmp;
    }
}
