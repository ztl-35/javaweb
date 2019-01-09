package myuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.dbLib;

public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	public UserDAO() {
		conn = dbLib.getConn();
	}
	public void addUser(User user) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "insert into users(UserName,Pwd) values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.userName);
		ps.setString(2, user.pwd);
		ps.executeUpdate();
		conn.close();
	}
	public ArrayList<User> getusers() throws SQLException{
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		ArrayList<User> arrayList = new ArrayList<User>();
		sql = "select * from users";
		ps = conn.prepareStatement(sql);
		ResultSet rSet = ps.executeQuery();
		while(rSet.next()) {
			User user = new User();
			user.id = rSet.getInt(1);
			user.userName = rSet.getString(2);
			user.pwd = rSet.getString(3);
			arrayList.add(user);
		}
		conn.close();
		return arrayList;
	}
	public boolean checkExists(User user) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "select * from users where userName=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.userName);
		ResultSet resultSet = ps.executeQuery();
		boolean flag = false;
		if(resultSet.next()) {
			flag = true;
		}
		conn.close();
		System.out.println("flag:"+flag);
		return flag;
	}
	public void deleteUser(User user) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "delete from users where UserName=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.userName);
		ps.executeUpdate();
		conn.close();
	}
	public void modifyUser(User user) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "update users set UserName=?,Pwd=? where ID=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.userName);
		ps.setString(2, user.pwd);
		ps.setInt(3, user.id);
		ps.executeUpdate();
		conn.close();
	}
	public User getUserById(int id) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "select * from users where ID=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet resultSet = ps.executeQuery();
		User user = new User();
		resultSet.next();
		user.id = resultSet.getInt(1);
		user.userName = resultSet.getString(2);
		user.pwd = resultSet.getString(3);
		conn.close();
		return user;
	}
	public boolean checkUserLogin (User user) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "select * from users where UserName=? and Pwd=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.userName);
		ps.setString(2, user.pwd);
		ResultSet resultSet = ps.executeQuery();
		boolean flag = true;
		if(resultSet.next()) {
			flag = true;
		}else {
			flag = false;
		}
		conn.close();
		System.out.println("flag::::"+flag);
		return flag;
	}
}
