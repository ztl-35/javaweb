package History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.NamingException;

import db.Db_Connect;

public class historyDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;
	int pageNo;
	int pageSize;
	int pageCount;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public historyDAO() throws NamingException, SQLException {
		conn = Db_Connect.getConnection();
		sql = "use visitor";
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	public ArrayList<history> getHistory() throws SQLException, NamingException{
		if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select * from history";
    	ps = conn.prepareStatement(sql);
    	ArrayList<history> al = new ArrayList<history>();
    	ResultSet rSet = ps.executeQuery();
    	while(rSet.next()) {
    		history ht = new history();
    		ht.setID(rSet.getInt(1));
    		ht.setVisitID(rSet.getInt(2));
    		ht.setVisitTime(rSet.getTimestamp(3));
    		ht.setUrl(rSet.getString(4));
    		al.add(ht);
    	}
    	conn.close();
    	return al;
	}
	//根据当前页，来提取数据，不是提取所有history数据
	public ArrayList<history> getPageData() throws SQLException, NamingException{
		if (conn.isClosed()) {
			conn = Db_Connect.getConnection();
		}
		sql = "select * from history limit "+(pageNo-1)*pageSize+","+pageSize;//limit后面是   起始点和每页多少条数
    	ps = conn.prepareStatement(sql);
    	ArrayList<history> al = new ArrayList<history>();
    	ResultSet rSet = ps.executeQuery();
    	while(rSet.next()) {
    		history ht = new history();
    		ht.setID(rSet.getInt(1));
    		ht.setVisitID(rSet.getInt(2));
    		ht.setVisitTime(rSet.getTimestamp(3));
    		ht.setUrl(rSet.getString(4));
    		al.add(ht);
    	}
    	conn.close();
    	return al;
	}
	 public void saveHistory(history ht) throws SQLException, NamingException {
		 	if (conn.isClosed()) {
				conn = Db_Connect.getConnection();
			}
			sql = "insert into history (VisitID,VisitTime,Url) values(?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,ht.getVisitID());
			ps.setTimestamp(2, new Timestamp(ht.getVisitTime().getTime()));
			ps.setString(3, ht.getUrl());
			ps.executeUpdate();
			conn.close();
		}
	 public void computePageCount() throws SQLException, NamingException {
		 if (conn.isClosed()) {
				conn = Db_Connect.getConnection();
			}
			sql = "select count(*) from history";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pageCount = rs.getInt(1);
			if (pageCount % pageSize ==0) {
				pageCount = pageCount/pageSize;
			}else {
				pageCount = pageCount/pageSize+1;
			}
			conn.close();
	}
}
