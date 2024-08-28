package megharaj.intelora.contact.csv2db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBToCSVPure {
	public static void main(String ar[]) throws ClassNotFoundException, SQLException, IOException{
		Connection srcConn = DBMover.getConnection("localhost:3306", "root", "admin", "test");
		String srcTable = "mergedcontactbook_3_mergedsrc";
		String outputFileName="C:\\temp\\alldata.csv";
		ArrayList<String> cols = DBMover.getAllColumnsFromTable(srcConn, srcTable);
		System.out.println("COLS="+cols);
		FileWriter fileObj=new FileWriter(new File(outputFileName));
		String data[]=new String[cols.size()];
		for(int i=0; i<cols.size(); i++){
			data[i]=cols.get(i);
		}
		wirteLineIntoFile(fileObj, data);
		Statement st = srcConn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + srcTable +" ");
		while (rs.next()) {
			data[0]=rs.getString(1);
			data[1]=rs.getString(2);
			data[2]=rs.getString(3);
			data[3]=rs.getString(4);
			data[4]=rs.getString(5);
			data[5]=rs.getString(6);
			data[6]=rs.getString(7);
			data[7]=rs.getString(8);
			data[8]=rs.getString(9);
			data[9]=rs.getString(10);
			data[10]=rs.getString(11);
			data[11]=rs.getString(12);
			data[12]=rs.getString(13);
			data[13]=rs.getString(14);
			data[14]=rs.getString(15);
			data[15]=rs.getString(16);
			data[16]=rs.getString(17);
			data[17]=rs.getString(18);
			data[18]=rs.getString(19);
			data[19]=rs.getString(20);
			data[20]=rs.getString(21);
			data[21]=rs.getString(22);
			data[22]=rs.getString(23);
			data[23]=rs.getString(24);
			data[24]=rs.getString(25);
			data[25]=rs.getString(26);
			data[26]=rs.getString(27);
			data[27]=rs.getString(28);
			data[28]=rs.getString(29);
			data[29]=rs.getString(30);
			data[30]=rs.getString(31);
			data[31]=rs.getString(32);
			data[32]=rs.getString(33);
			data[33]=rs.getString(34);
			data[34]=rs.getString(35);
			data[35]=rs.getString(36);
			data[36]=rs.getString(37);
			data[37]=rs.getString(38);
			wirteLineIntoFile(fileObj, data);
		}
		
		fileObj.close();
	}

	private static void wirteLineIntoFile(FileWriter fileObj, String[] data) throws IOException {
		String dataLine="";
		for(int i=0; i<data.length; i++){
			if(data[i]==null){
				data[i]="";
			}else{
				data[i]=(data[i]).replace("\n", " ").replace("\"", "").replace("'", "").replace(",", "");
			}
			dataLine=dataLine+data[i]+",";
			data[i]="";
		}
		fileObj.write(dataLine+"\n");
	}
}
