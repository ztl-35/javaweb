package Myvisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.NamingException;

import db.Db_Connect;

public class VisitorDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
    public VisitorDAO() throws NamingException, SQLException {
		conn = Db_Connect.getConnection();
		sql="use visitor;";
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
    public int saveVisitor(Visitor visitor) throws SQLException, NamingException {
		sql = "insert into visitors (UserID,VisitTime,LeftTime,ip,comefrom) values(?,?,?,?,?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1,visitor.getUserID());
		ps.setTimestamp(2,new Timestamp(visitor.getVisitTime().getTime()));
		if (visitor.getLeftTime()!=null) {
			ps.setTimestamp(3,new Timestamp(visitor.getLeftTime().getTime()));
		}else {
			ps.setTimestamp(3,null);
		}
		ps.setString(4, visitor.getIp());
		ps.setString(5, visitor.getComefrom());
		ps.executeUpdate();
		
		if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select max(ID) from visitors";
		ps = conn.prepareStatement(sql);
		ResultSet rSet = ps.executeQuery();
		rSet.next();
		int id = rSet.getInt(1);
		conn.close();
		return id;
	}
    public ArrayList<Visitor> getVisitor() throws SQLException, NamingException{
    	if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
    	sql = "select * from visitors";
    	ps = conn.prepareStatement(sql);
    	ArrayList<Visitor> al = new ArrayList<Visitor>();
    	ResultSet rSet = ps.executeQuery();
    	while(rSet.next()) {
    		Visitor visitor = new Visitor();
    		visitor.setID(rSet.getInt(1));
    		visitor.setUserID(rSet.getInt(2));
    		visitor.setVisitTime(rSet.getTimestamp(3));
    		visitor.setLeftTime(rSet.getTimestamp(4));
    		visitor.setIp(rSet.getString(5));
    		visitor.setComefrom(rSet.getString(6));
    		al.add(visitor);
    	}
    	conn.close();
    	return al;
    	
    }
    public void update(Visitor visitor) throws SQLException, NamingException {
    	if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "update visitors set LeftTime=?,UserID=? where ID=?";
		ps = conn.prepareStatement(sql);
		if (visitor.getLeftTime()==null) {
			ps.setTimestamp(1,null);
		}else {
			ps.setTimestamp(1,new Timestamp(visitor.getLeftTime().getTime()));//你运行到这里没报错吗？(Timestamp) visitor.getLeftTime()
		}
//		Timestamp timestamp=(Timestamp) visitor.getLeftTime();
//		System.out.println("检测："+timestamp);
		ps.setInt(2, visitor.getUserID());
		ps.setInt(3, visitor.getID());
		ps.executeUpdate();
		conn.close();
    }
}
