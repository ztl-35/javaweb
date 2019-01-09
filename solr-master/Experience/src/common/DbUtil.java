package common;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 数据库工具类
 * 
 * @author Administrator
 *
 */
public class DbUtil {

	/**
	 * 返回数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException {
		  try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (ClassNotFoundException e) {  
	            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");  
	            //添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误  
	            e.printStackTrace();  
	        }  
		String url = "jdbc:mysql://localhost:3306/experience?characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		try{
			Connection conn =(Connection) DriverManager.getConnection(url , username , password ) ;
			return conn;
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace();
			return null;
		}
		
	}

}
