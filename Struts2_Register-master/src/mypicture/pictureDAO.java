package mypicture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.dbLib;

public class pictureDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	public pictureDAO() {
		conn = dbLib.getConn();
	}
	public void addPicture(Picture picture) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "insert into picture(uid,name,url) values(?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, picture.uid);
		ps.setString(2, picture.name);
		ps.setString(3, picture.url);
		ps.executeUpdate();
		conn.close();
	}
	public ArrayList<Picture> getPictures(int id,String idType) throws SQLException{
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		String filedName="";
		if (idType.equals("user")) {
			filedName = "uid";
		}else {
			filedName = "id";
		}
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		sql = "select * from picture where "+filedName+"=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Picture picture = new Picture();
			picture.id = rs.getInt(1);
			picture.uid = rs.getInt(2);
			picture.name = rs.getString(3);
			picture.url = rs.getString(4);
			pictures.add(picture);
		}
		System.out.println("dao:picture::::"+pictures);
		conn.close();
		return pictures;
	}
	public int getpictureNumber(int uid) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		int num=0;
		sql="select count(*) from picture where uid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rSet = ps.executeQuery();
		rSet.next();
		num = rSet.getInt(1);
		conn.close();
		return num;
	}
	public void deletePicture(int id) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "delete from picture where id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}
	public String getURL(int id) throws SQLException {
		if (conn.isClosed()) {
			conn = dbLib.getConn();
		}
		sql = "select url from picture where id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet resultSet = ps.executeQuery();
		resultSet.next();
		String url = resultSet.getString(1);
		conn.close();
		return url;
	}
}
