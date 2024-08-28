package megharaj.main;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pdf.password.ReadEncryptedPdf;
import com.util.PassConstant;
import com.zip.password.ReadEncryptedZip;

public class StartDecryptThreads_logic2 {
	public static boolean searchPasswoard = true;

	public static void main(String[] args) {
		char set1[] = "ABCDEFGHIKMNOPRUVXYabcdefghijmnopruvwy0123456789@_&$%!".toCharArray();
		int passMaxLength = 15;
		String prefix = "";
		int n = set1.length;
		// Basic possible
		File files[] = new File("currpted file 9955.pdf").listFiles();
		//for (File file : files) 
		{
			String fileName = "currpted file 9955.pdf"; //file.getAbsolutePath();
			System.out.println(fileName);
			//new ReadEncryptedPdf(PassConstant.constTestStr, fileName, "TR_00" + threadid).start();
			//new ReadEncryptedZip(PassConstant.constTestStr, fileName, "TR_00" + threadid).start();
			 for(int i=0; i<passMaxLength; i++){
				 printAllKLengthRec(fileName, set1, prefix, n, 12);
			 }
			sendListToThread(fileName);
		}
		System.out.println("Total Thread Need=" + threadid);
		executor.shutdown();
	}

	static ArrayList<String> list = new ArrayList<String>();
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
		for (int i = 0; i < n; ++i) {
			// Next character of input added
			if(notRepeatCharXTimes(prefix, set[i], 3)) {
				String newPrefix = prefix + set[i];
				// k is decreased, because
				// we have added a new character
				printAllKLengthRec(fileName, set, newPrefix, n, k - 1);
			}
		}
	}

	private static boolean notRepeatCharXTimes(String prefix, char c, int x) {
		boolean flag=true;
		int repeatCount=0;
		for(int i=0; i<prefix.length() && repeatCount<=x; i++) {
			if(prefix.charAt(i)==c) {
				repeatCount++;
			}
		}
				
		flag=(repeatCount<=x);
		return flag;
	}

	static int threadid = 1;
	static ExecutorService executor = Executors.newFixedThreadPool(25);

	public static void sendListToThread(String fileName) {
		String strSet1[] = list.toArray(new String[0]);
		System.out.println(list);

		//executor.execute(new ReadEncryptedPdf(strSet1, fileName, "TR_00" + threadid));
		list = new ArrayList<String>();
		System.out.println(threadid);
		threadid++;
	}
}
