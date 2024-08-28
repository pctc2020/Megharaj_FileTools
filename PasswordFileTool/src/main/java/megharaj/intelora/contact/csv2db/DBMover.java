package megharaj.intelora.contact.csv2db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DBMover {
	private static String mergeColumnNames1="m_weblink1".toLowerCase();
	private static String mergeColumnNames2="m_groups".toLowerCase();
	
	public static void main(String[] args) {
		try { 
	//IMP TASK = verify column SUBJECT, LANGUAGE, NAME, phone_2___value, e_mail_2___value, address_1___formatted, address_1___street, key_skills

			// data will move from target to src
			Connection srcConn = getConnection("localhost:3306", "root", "admin", "test");
			String srcTable = "mergedcontactbook_3_mergedsrc";
			System.out.println("Get Column names from src");
			Connection tgtConn = getConnection("localhost:3306", "root", "admin", "test");
			String tgtTable = "mergedcontactbook_2_uniqueonly";
			System.out.println("Get Column names from tgt");
			removeSpaceFromColumnNames(tgtConn, tgtTable);
			moveDataFrom1to2RemoveDuplicateRowAddUnionColumn(srcConn, srcTable, tgtConn, tgtTable);
			printColumnLength(srcConn, srcTable);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//18604195555

	private static void printColumnLength(Connection srcConn, String srcTable) throws SQLException {
		ArrayList<String> columnNames = getAllColumnsFromTable(srcConn, srcTable);
		Statement st = srcConn.createStatement();
		for(String columnName: columnNames) {
		ResultSet rs = st.executeQuery("SELECT MAX(LENGTH("+columnName+")) as maxlength FROM " + srcTable);
			while (rs.next()) {
				String maxlength = ""+rs.getString("maxlength");
				ResultSet rs2=srcConn.createStatement().executeQuery("select count("+columnName+") from "+srcTable+" where "+columnName+" is not null;");
				rs2.next();
				int nonNullRows = rs2.getInt(1);
				System.out.println(maxlength+"<<<ColumnName:>>>"+columnName+" rows in column="+columnName+":::"+nonNullRows);
			}
		}
	}

	private static void removeSpaceFromColumnNames(Connection tgtConn, String tgtTable) throws SQLException {
		ArrayList<String> srcColumnNames = getAllColumnsFromTable(tgtConn, tgtTable);
		for (String colName : srcColumnNames) {
			if (colName.contains(" ") || colName.contains(".") || colName.contains("-")) {
				System.out.println("renaming="+colName);
				String newName = colName.replace(" ", "_").replace(".", "_").replace("-", "_");
				tgtConn.createStatement().executeUpdate("ALTER TABLE " + tgtTable + " CHANGE COLUMN `" + colName + "`  "
						+ newName + " TEXT NULL DEFAULT NULL ;");
			}
		}
	}

	private static void moveDataFrom1to2RemoveDuplicateRowAddUnionColumn(Connection srcConn, String srcTable,
			Connection tgtConn, String tgtTable) throws SQLException {
		System.out.println("Start moving rows...");
		ArrayList<String> newColumns = findUnionColumnAndGetConfirmation(srcConn, srcTable, tgtConn, tgtTable);
		System.out.println("newColumns= " + newColumns);
		if (newColumns != null && newColumns.size() > 0) {
			if (doyouWantTo("Create New Columns")) {
				for (String newColumnName : newColumns) {
					srcConn.createStatement()
							.executeUpdate("ALTER TABLE " + srcTable + " ADD " + newColumnName + " TEXT NULL ;");
				}
			}
		}
//		cleanDataFromTable1toTable2(srcConn, srcTable, tgtConn, tgtTable);
		transferDataFromTable1toTable2(srcConn, srcTable, tgtConn, tgtTable);
	}

	private static void cleanDataFromTable1toTable2(Connection srcConn, String srcTable, Connection tgtConn,
			String tgtTable) throws SQLException {
		Statement st = srcConn.createStatement();
		ResultSet rs = st.executeQuery("SELECT m_notes FROM " + srcTable +" where m_notes IS NOT NULL ");
		while (rs.next()) {
			String delQry = "delete from " + tgtTable + " where m_notes='"+rs.getString(1)+"' ;";
			System.out.println("100001DEL: " + delQry);
			Statement st1 = tgtConn.createStatement();
			int rowdel = st1.executeUpdate(delQry);
			System.out.println(rowdel+" rows delted");
		}
	}
	private static void transferDataFromTable1toTable2(Connection srcConn, String srcTable, Connection tgtConn,
			String tgtTable) throws SQLException {
		System.out.println("Start transfering.......");
		Object tgtColumnNames[] = (Object[]) getAllColumnsFromTable(tgtConn, tgtTable).toArray();
		Statement st = tgtConn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + tgtTable );
		while (rs.next()) {
			String datas[] = new String[tgtColumnNames.length];
			String colNamesStr = " id", colValuesStr = " NULL, ";
			String allDataMatchCondition = "1=1";
			for (int i = 1; i < tgtColumnNames.length; i++) {
			  if(tgtColumnNames[i].equals(mergeColumnNames1)){
			  }else if(tgtColumnNames[i].equals(mergeColumnNames2)){
			  }else{
				datas[i] = "" + rs.getString("" + tgtColumnNames[i]);
				datas[i] = datas[i].replace("\n", " ").replace(",", "-").replace("\"", "-").replace("\'", "-");
				colNamesStr = colNamesStr + ", " + tgtColumnNames[i];
				colValuesStr = colValuesStr + ", '" + datas[i] + "'";
				allDataMatchCondition = allDataMatchCondition + " and " + tgtColumnNames[i] + "='" + datas[i] + "'";
			  }
			}
			colValuesStr=colValuesStr.replace("'null'", "NULL").replace(", ,", ", ");
			allDataMatchCondition = allDataMatchCondition.replace("='null'", " is NULL ");
			
			String mergeValues1="",mergeValues2="";
			String qryToGetMergeColumnData1=" select "+mergeColumnNames1+", "+mergeColumnNames2+" from "+tgtTable+" where "+allDataMatchCondition+";";
			System.out.println("qryToGetMergeColumnData1=== "+qryToGetMergeColumnData1);
			ResultSet rs3 = srcConn.createStatement().executeQuery(qryToGetMergeColumnData1);
			while(rs3.next()) {
				if(!mergeValues1.contains(""+rs3.getString(mergeColumnNames1))) {
					mergeValues1=mergeValues1+" - "+rs3.getString(mergeColumnNames1);
				}
				if(!mergeValues2.contains(""+rs3.getString(mergeColumnNames1))) {
					mergeValues2=mergeValues2+" - "+rs3.getString(mergeColumnNames2);
				}
			}
			String insertQry = " insert into "+srcTable+" ("+mergeColumnNames1+", "+mergeColumnNames2+", "+colNamesStr+") values ('"+mergeValues1+"','"+mergeValues2+"',"+colValuesStr+");";
			System.out.println(insertQry);

			
			Statement st2 = srcConn.createStatement();
			int row = st2.executeUpdate(insertQry);
			if (row == 1) {
				String delQry = "delete from " + tgtTable + " where " + allDataMatchCondition + ";";
				System.out.println("100001DEL: " + delQry);
				Statement st1 = tgtConn.createStatement();
				int rowdel = st1.executeUpdate(delQry);
				System.out.println(rowdel+" rows delted");
			}
		}
	}

	private static boolean doyouWantTo(String questionStr) {
		boolean toCreate = true;
		System.out.println("Do you want to " + questionStr + "? Y/N ");
		Scanner reader = new Scanner(System.in);
		String str = "" + reader.nextLine();
		if (str.equalsIgnoreCase("Y")) {

			toCreate = true;
		} else {
			toCreate = false;
		}
		reader.close();
		return toCreate;
	}

	private static ArrayList<String> findUnionColumnAndGetConfirmation(Connection srcConn, String srcTable,
			Connection tgtConn, String tgtTable) throws SQLException {
		ArrayList<String> srcColumnNames = getAllColumnsFromTable(srcConn, srcTable);
		ArrayList<String> tgtColumnNames = getAllColumnsFromTable(tgtConn, tgtTable);
		ArrayList<String> newColumns = new ArrayList<String>();
		for (String colName : tgtColumnNames) {
			if (srcColumnNames.contains(colName.toLowerCase().trim())) {
				System.out.println("Found=" + colName);
			} else {
				newColumns.add(colName);
			}
		}
		return newColumns;
	}

	public static ArrayList<String> getAllColumnsFromTable(Connection conn, String tableName) throws SQLException {
		ArrayList<String> columnNames = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i).toLowerCase().trim();
			columnNames.add(columnName);
		}
		rs.close();
		st.close();
		return columnNames;
	}

	public static Connection getConnection(String ipaddress, String username, String password, String schema)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + ipaddress + "/" + schema + "?useSSL=false",
				username, password);
		return con;
	}
}
