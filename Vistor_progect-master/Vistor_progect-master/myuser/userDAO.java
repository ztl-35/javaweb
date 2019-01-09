package myuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import db.Db_Connect;

public class userDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	public userDAO() throws NamingException, SQLException {
		conn = Db_Connect.getConnection();
		sql = "use visitor";
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	public int getUserId(user u) throws NamingException, SQLException {
		if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select ID from users where UserName=? and Pwd=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, u.getUserName());
		ps.setString(2, u.getPwd());
		ResultSet rs=ps.executeQuery();
		int id =0;
		if(rs.next()){
			id = rs.getInt(1);
		}
		conn.close();
		return id;
		
	}
	public String getNameById(int id) throws NamingException, SQLException {
		if(conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select UserName from users where ID=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet resultSet = ps.executeQuery();
		String UserName="";
		if(resultSet.next()) {
			UserName = resultSet.getString(1);
		}
		conn.close();
		return UserName;		
	}
	public int getIdbyName(String username) throws NamingException, SQLException {
		if(conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select id from users where UserName=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet resultSet = ps.executeQuery();
		int id =0;
		if(resultSet.next()) {
			id = resultSet.getInt(1);
		}
		conn.close();
		return id;		
	}
}
