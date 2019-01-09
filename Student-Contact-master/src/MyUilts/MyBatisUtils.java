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
			//为了打开数据库，要进行数据库的配置    读入mybatis的主配置文件
            String resource = "mybatis-cfg.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            //为了打开哪个数据库的环境配置    配置文件中的id="user"的环境配置
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in,"user");
            return sessionFactory.openSession();
	}
}

