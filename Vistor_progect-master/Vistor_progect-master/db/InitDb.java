package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import javax.naming.NamingException;

public class InitDb {
	public static void Initialize(String sqlscriptFile) throws NamingException, SQLException, IOException {
		Connection conn = Db_Connect.getConnection();
		Statement st = conn.createStatement();
		BufferedReader bf = new BufferedReader(new FileReader(sqlscriptFile));
		String sql = "";
		String line = "";
		while((line=bf.readLine())!=null) {
			if (!line.endsWith(";")) {
				sql += line;
				continue;
			}
			sql +=line;
			st.addBatch(sql);
			sql="";
		}
		st.executeBatch();
		bf.close();
	}
}
