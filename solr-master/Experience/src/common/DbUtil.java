package common;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * ���ݿ⹤����
 * 
 * @author Administrator
 *
 */
public class DbUtil {

	/**
	 * �������ݿ�����
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException {
		  try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (ClassNotFoundException e) {  
	            System.out.println("δ�ܳɹ������������������Ƿ�����������");  
	            //���һ��println��������������쳣������Ƿ����������������������ַ����Ƿ����  
	            e.printStackTrace();  
	        }  
		String url = "jdbc:mysql://localhost:3306/experience?characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		try{
			Connection conn =(Connection) DriverManager.getConnection(url , username , password ) ;
			return conn;
		}catch(SQLException se){
			System.out.println("���ݿ�����ʧ�ܣ�");
			se.printStackTrace();
			return null;
		}
		
	}

}
