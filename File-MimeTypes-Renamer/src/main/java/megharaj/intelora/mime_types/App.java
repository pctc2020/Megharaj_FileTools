package megharaj.intelora.mime_types;

import java.io.File;

public class App
{
    public static void main(String[] arg) throws GetBytesException
    {
        String srcFolder = "D:\\DATALACK\\CrrptedFiles\\";
        getAllFileNames(srcFolder);
    }

    static int x = 0;
	public static void processFile(File fileObj) throws GetBytesException {
        String filename = fileObj.getAbsolutePath();
        if(filename.endsWith("_megh.megh")){
            x++;
            MimeTypeDetector detector = new MimeTypeDetector();
            String mimeType = detector.detectMimeType(fileObj);
            // System.out.println(filename+ ": " + mimeType);
            String newSurfix = "_mgh_"+mimeType.replace("/", ".")
                    .replace("application", "appl_");
            String newFileName = filename.replace("_megh.megh", newSurfix);
            // System.out.println(filename+"\n\t"+newFileName);
            fileObj.renameTo(new File(newFileName));
            if(x%500==0){
                System.out.println(" "+fileObj.getAbsolutePath());
            }
        }
    }

    private static void getAllFileNames(String directoryName)  {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                try{
                    processFile(file);
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else if (file.isDirectory()) {
                getAllFileNames(file.getAbsolutePath());
            }
        }    
        
	}
}
