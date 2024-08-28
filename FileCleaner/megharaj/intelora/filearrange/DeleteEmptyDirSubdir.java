package filearrange;

import java.io.File;

public class DeleteEmptyDirSubdir{
    public static void main(String[] args) {
        // Directory path to be deleted
        String directoryPath = "D:\\DATALACK\\CrrptedFiles";
        File directory = new File(directoryPath);

        // Call the deleteDirectory method
        deleteDirectory(directory);
        System.out.print("\n=================\n");
    }

    public static void deleteDirectory(File directory) {
        // Get all the files and directories in the directory
        File[] files = directory.listFiles();

        if(files != null) {
            // Iterate through all the files and directories
            for(File file : files) {
                // If the file is a directory, call the deleteDirectory method recursively
                if(file.isDirectory()) {
                    deleteDirectory(file);
                } else if(file.length()<2){
                    System.out.println("small file:"+file.getAbsolutePath());
                    file.delete();
                }

            }
        }
        try {
            if (directory.list().length == 0) {
                System.out.print("\n\tAbsolute Path: " + directory.getAbsolutePath());
                System.out.print("\tCanonical Path: " + directory.getCanonicalPath().toString());
                System.out.print("\tPath: " + directory.getPath());
                directory.delete();
            }
        } catch (Exception e) {
            System.out.print("\n\tERR in: " + directory.getAbsolutePath());
        }
    }
}