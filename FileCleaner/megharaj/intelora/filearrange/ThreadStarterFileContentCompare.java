package filearrange;

import java.util.List;

public class ThreadStarterFileContentCompare {
    public static void main(String arg[]){
        String maxSize = "";
        String[] fileTypes =null;
       List<String> filePathList =  getFileList(maxSize, fileTypes);
       compareTheseFiles(filePathList);
    }

    private static void compareTheseFiles(List<String> filePathList) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTheseFiles'");
    }

    private static List<String> getFileList(String maxSize, String[] fileTypes) {
    }
    
}
