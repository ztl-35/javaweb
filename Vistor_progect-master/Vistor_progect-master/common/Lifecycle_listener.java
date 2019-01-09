package common;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import History.history;
import History.historyDAO;
import Myvisit.Visitor;
import Myvisit.VisitorDAO;

/**
 * Application Lifecycle Listener implementation class Lifecycle_listener
 *
 */
@WebListener
public class Lifecycle_listener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
	HttpServletRequest request;
    public Lifecycle_listener() {
       
    }
    public void sessionCreated(HttpSessionEvent arg0)  {
    	System.out.println(" sessionCreated");
    	ServletContext application = arg0.getSession().getServletContext();
    	arg0.getSession().setMaxInactiveInterval(5*60);//����session����ʱ����5����
         //���û����ʵ�ʱ�򣬾ͻ����һ��session��������ֻ���ڸ���ҳ�ɼ����������Լ������������û���  ���浽���ݿ���
    	Visitor visitor = new Visitor();
    	//���ֻ����Ϊһ���ÿͣ��ǵò�������userID �� lefttime
    	visitor.setIp(request.getRemoteAddr());
    	visitor.setVisitTime(new Date());
    	visitor.setComefrom(request.getHeader("Referer"));
    	visitor.toString();
    	VisitorDAO vDao;
    	int ID = 0;
		try {
			vDao = new VisitorDAO();
			ID = vDao.saveVisitor(visitor);
			//���ID��Ϊ��ͨ��������������֮���session�����visitor�������Ȼ���Ժ��¼�û���ʱ�򣬸������visitor������������id������userID��
			visitor.setID(ID);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		 /*
		  * ��Ҫ��Ҫ�����ı��������û�����Ϣ��������ֻ�ܱ���һλ��Ϣ,�����ڷ�����һ��ʼ�ͽ����˶�Ӧ��hashmap������
		  */
		 
		 @SuppressWarnings("unchecked")
		 //����ط��ǰѷ�����һ��ʼ�����hashmap�ĵ�ַȡ���������ø�ֵ����ʱ��map��������Ӧ�������û��б�
		 HashMap<String, Visitor> map = (HashMap<String, Visitor>) application.getAttribute("ONLINE_USER");
		 HttpSession session = arg0.getSession();
  	     map.put(session.getId(), visitor);
  	     application.setAttribute("ONLINE_USER", map);
  	     
  	     //����ط�����һ��session����Ϊ�˺����������ʷ���ʼ�¼�����VisitId����ΪVisiotor��ID��VisitId�ǹ�����ϵ��
  	     session.setAttribute("USER", visitor);
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void requestInitialized(ServletRequestEvent arg0)  { 
    	System.out.println("requestInitialized");
    	request = (HttpServletRequest) arg0.getServletRequest();
    	history ht = new history();
    	//ht.setVisitID(((Visitor)request.getSession().getAttribute("USER")).getID());������Ǹ��ڸ��û���ע�����������visitid��
    	HttpSession session = request.getSession();
    	Visitor visitor = (Visitor) session.getAttribute("USER");
    	ht.setVisitID(visitor.getID());
    	ht.setUrl(request.getRequestURL().toString());
    	ht.setVisitTime(new Date());
    	try {
			historyDAO hDao = new historyDAO();
			hDao.saveHistory(ht);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("sessionDestroyed");
         ServletContext application = arg0.getSession().getServletContext();
         @SuppressWarnings("unchecked")
		 HashMap<String, Visitor> map = (HashMap<String, Visitor>) application.getAttribute("ONLINE_USER");
         String sessionid=arg0.getSession().getId();
         Visitor visitor = map.get(sessionid);
         visitor.setLeftTime(new Date());
         VisitorDAO visitorDAO;
         try {
        	visitorDAO = new VisitorDAO();
			visitorDAO.update(visitor);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
         
         //�������ݿ��������ö�Ӧ��session���뿪ʱ�䣬�ٴ�application��ɾ��
         map.remove(sessionid);
         application.setAttribute("ONLINE_USER", map);//��Ϊ֮ǰʹ�õ���application�����ã�����û��Ҫ���н�һ���ĸ��¡�
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }
    
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//contextInitialized��Ӧ�ڷ�����������ʱ�򣬿�ʼ���ļ���������
    	System.out.println("����������");
    	HashMap<String, Visitor> map = new HashMap<String,Visitor>();
    	ServletContext application = arg0.getServletContext();
    	application.setAttribute("ONLINE_USER", map);
    }
}
