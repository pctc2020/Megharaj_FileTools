package megharaj.intelora.main;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import megharaj.intelora.pdf.password.ReadEncryptedPdf;
import megharaj.intelora.util.PassConstant;


public class StartDecryptThreads {
	public static boolean searchPasswoard = true;

	public static void main(String[] args) {
		// char set1[] = "ABCDEFGHIKMNOPRUVXYabcdefghijmnopruvwy0123456789@_&$%!".toCharArray();
		// int passMaxLength = 15;
		// String prefix = "";
		// int n = set1.length;
		// Basic possible
		File files[] = new File("D:\\Password PDF\\").listFiles();
		for (File file : files) {
			String fileName = file.getAbsolutePath();
			System.out.println(fileName);
			// new ReadEncryptedPdf(PassConstant.constTestStr, fileName, "TR_00" + threadid).start();
			//new ReadEncryptedZip(PassConstant.constTestStr, fileName, "TR_00" + threadid).start();
			// for(int i=0; i<passMaxLength; i++){
			// printAllKLengthRec(fileName, set1, prefix, n, i);
			// }
			sendListToThread(fileName);
		}
		System.out.println("Total Thread Need=" + threadid);
		executor.shutdown();
	}

/*	static ArrayList<String> list = new ArrayList<String>();
	static void printAllKLengthRec(String fileName, char[] set, String prefix, int n, int k) {
		if (!searchPasswoard) {
			return;
		}
		// Base case: k is 0,
		// print prefix
		if (k == 0) {
			// code to put into array and clear list
			list.add(prefix);
			if (list.size() > 2000) {
				sendListToThread(fileName);
			}
			return;
		}
		// One by one add all characters
		// from set and recursively
		// call for k equals to k-1
		for (int i = 5; i < n; ++i) {
			// Next character of input added
			String newPrefix = prefix + set[i];
			// k is decreased, because
			// we have added a new character
			printAllKLengthRec(fileName, set, newPrefix, n, k - 1);
		}
	} */

	static int threadid = 1;
	static ExecutorService executor = Executors.newFixedThreadPool(20);
	public static void sendListToThread(String fileName) {
		executor.execute(new ReadEncryptedPdf(PassConstant.constTestStr, fileName, "TR_00" + threadid));
		System.out.println(threadid);
		threadid++;
	}
}
