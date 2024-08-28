import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PSQLDataWriter {
	public static String tableName = "smallfiledata";
	static int count=0;
	static Connection con=null;
	static Statement stmt=null;
	public static void getPGStmt() throws Exception {
		Class.forName("org.postgresql.Driver");
        con = DriverManager .getConnection("jdbc:postgresql://localhost:5432/meghdata", "postgres", "postgres");
        System.out.println("Opened database successfully");
        stmt = con.createStatement();
	}
	
	public static int saveDataInToDB(String fileName, String filePath, String fileContent) throws Exception
	{
		fileName=fileName.replace("\'", "");
		filePath=filePath.replace("\'", "");
		if(stmt==null) {
			getPGStmt();
		}
		
		long time=System.currentTimeMillis();
		String insertQry = "insert into smallfiledata (id, filename, filepath, filecontent) values ("+time+",'"+fileName+"' , '"+filePath+"','"+fileContent+"')";
//		System.out.println(insertQry);
		int rows = stmt.executeUpdate(insertQry);
		return rows;
	}
	public static void closeAll() throws Exception {
		stmt.close();
		con.close();
	}
	
}
