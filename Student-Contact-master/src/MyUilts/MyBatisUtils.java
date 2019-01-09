package MyUilts;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	public static SqlSession openSession() throws IOException
	{
			//Ϊ�˴����ݿ⣬Ҫ�������ݿ������    ����mybatis���������ļ�
            String resource = "mybatis-cfg.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            //Ϊ�˴��ĸ����ݿ�Ļ�������    �����ļ��е�id="user"�Ļ�������
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in,"user");
            return sessionFactory.openSession();
	}
}

