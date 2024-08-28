package filearrange;

import java.io.File;
import java.io.FileInputStream;

public class DeleteUnimpCodeFiles{
    public static void main(String[] args) {
        String directoryPath = "D:\\DATALACK\\CrrptedFiles";
        File directory = new File(directoryPath);

        deleteUnimpCodeFiles(directory);
        System.out.print("\n=================\n");
    }
    public static int fileCount=1;
    public static void deleteUnimpCodeFiles(File directory) {
        // Get all the files and directories in the directory
        File[] files = directory.listFiles();

        if(files != null) {
            // Iterate through all the files and directories
            for(File file : files) {
                // If the file is a directory, call the deleteDirectory method recursively
                if(file.isDirectory()) {
                    deleteUnimpCodeFiles(file);
                }else{
                    String contentStr = "package";
                    if(fileCount>0){
                        boolean isFullyUnusedCode = checkFileContent(file, contentStr);
                        if(isFullyUnusedCode){
                            // file.delete();
                        }
                    }
                }
            }
        }
    }
    public static final int validChars[] = {9, 10, 13, 32, 33, 34, 35, 36, 37, 38, 39,
        40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 
        100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,  111, 112, 113, 114, 115, 116, 117, 118, 119, 
        120, 121, 122, 123, 124, 125, 126,
        181, 201};
    public static boolean checkFileContent(File fileObj, String contentStr){
        // for(int validChar : validChars){
        //     System.out.print((char) validChar);
        // }
        
        fileCount++;
        boolean isFullyUnusedCode = false;
        try {
            if (fileObj.length()>3) {
                boolean isCodeFile = true;
                FileInputStream fis = new FileInputStream(fileObj);
                char current;
                while (fis.available() > 0) {
                    int ch = fis.read();
                    if(!contain(validChars, ch)){
                        current = (char) ch;
                        isCodeFile=false;
                        //System.out.println("Not code file:"+fileObj.getAbsolutePath()+" for:> "+ch+" ("+current+")");
                        break;
                    }
                }
                fis.close();
                if(fileCount%500==0){
                //     try {
                //         Thread.currentThread().wait(1000);
                        System.err.println(fileCount+" Waiting for 1 sec");
                //     } catch (InterruptedException e) {
                //         // TODO Auto-generated catch block
                //         e.printStackTrace();
                //     }
                }
                if(isCodeFile){
                    String fileFullpath = fileObj.getAbsolutePath();
                    System.out.println(fileCount+" codeUnimp file:"+fileFullpath);
                    if(!fileFullpath.endsWith("codeUnimpmegh"))
                    {
                        System.out.println(fileObj.renameTo(new File(fileFullpath+".codeUnimpmegh")));
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("\n\t"+fileCount+"ERR in file: " + fileObj.getAbsolutePath()+" :"+e.getMessage());
        }
        return isFullyUnusedCode;
    }
    private static boolean contain(int[] validChars, int ch) {
        //System.out.print( ((char) ch));
        boolean isAvailable = false;
        for(int validChar: validChars){
            if(validChar==ch){
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }
}

