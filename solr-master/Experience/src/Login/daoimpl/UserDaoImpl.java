package Login.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Login.dao.UserDao;
import Login.pojo.User;
import common.DbUtil;
 
public class UserDaoImpl implements UserDao {

//	登录查找
	@Override
	public User select(String uname,String upwd) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();		
		sb.append("SELECT * ");
		sb.append("FROM student where user_name = ? and user_password = ? ");
		System.out.println("登录查询语句："+sb);
		PreparedStatement pstm=conn.prepareStatement(sb.toString());
		pstm.setString(1,uname);
		pstm.setString(2,upwd);
		ResultSet rs=pstm.executeQuery();
		User user=null;
//		ArrayList<User> list=new ArrayList<User>();
		if(rs.next()){
			user=new User();
			user.setUser_id(rs.getInt(1));
			user.setUser_name(rs.getString(2));
			user.setUser_password(rs.getString(3));
			user.setUser_email(rs.getString(4));
			user.setUser_flag(rs.getInt(5));
//			list.add(user);
		}
		return user;
	}
	
//	注册
	@Override
	public Boolean register(String remail, String rname,String rpwd) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();		
		sb.append("SELECT * ");
		sb.append("FROM student where user_name = ?");
		
		PreparedStatement pstm=conn.prepareStatement(sb.toString());
		pstm.setString(1,rname);
		ResultSet rs=pstm.executeQuery();
		Boolean result = null;
		
		if(rs.next()){
			result = false;
		}else {
			sb = new StringBuffer();		
			sb.append("insert into student(user_name,user_password,user_email,user_flag) ");
			sb.append("values(?,?,?,?)");
			
			pstm=conn.prepareStatement(sb.toString());
			System.out.println(sb.toString());
			pstm.setString(1,rname);
			pstm.setString(2,rpwd);
			pstm.setString(3,remail);
			//注册的全部都默认为用户，设置flag为1。管理员通过数据库设置为0，不允许用户操作。
			pstm.setInt(4, 1);
			pstm.executeUpdate();
			result = true;
		}
		return result;
	}

	@Override
	public boolean checkUserByUserName(String userName) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select count(*) from student where user_name=?";
			PreparedStatement pstm=conn.prepareStatement(sql.toString());
			pstm.setString(1,userName);
			ResultSet rs=pstm.executeQuery();
			int rowCount=0;
			while (rs.next()) {
				rowCount = rs.getInt(1);				
			}
			if (rowCount>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//代表没有这个用户名
		return false;
	}

	@Override
	public boolean checkAdmin(String userName) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from student where user_name=?";
			PreparedStatement pstm=conn.prepareStatement(sql.toString());
			pstm.setString(1,userName);
			ResultSet rs=pstm.executeQuery();
			
			//因为注册保证用户名唯一，所以这个里面最多就只有一条记录
			while (rs.next()) {
				User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				if (user.getUser_flag()==0) {
					//是管理员
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
