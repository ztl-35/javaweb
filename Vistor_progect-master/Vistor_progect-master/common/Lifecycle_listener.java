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
    	arg0.getSession().setMaxInactiveInterval(5*60);//设置session过期时间是5分钟
         //有用户访问的时候，就会产生一个session。但是是只限于该网页可见，而且是自己，不是所有用户。  保存到数据库中
    	Visitor visitor = new Visitor();
    	//如果只是作为一个访客，是得不到他的userID 和 lefttime
    	visitor.setIp(request.getRemoteAddr());
    	visitor.setVisitTime(new Date());
    	visitor.setComefrom(request.getHeader("Referer"));
    	visitor.toString();
    	VisitorDAO vDao;
    	int ID = 0;
		try {
			vDao = new VisitorDAO();
			ID = vDao.saveVisitor(visitor);
			//这个ID是为了通过保存它，更新之后的session里面的visitor这个对象，然后以后登录用户的时候，根据这个visitor对象里面的这个id来更新userID。
			visitor.setID(ID);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		 /*
		  * 主要是要连续的保存在线用户的信息，而不是只能保存一位信息,所以在服务器一开始就建立了对应的hashmap来保存
		  */
		 
		 @SuppressWarnings("unchecked")
		 //这个地方是把服务器一开始申请的hashmap的地址取出来，引用赋值给临时的map。构建对应的在线用户列表
		 HashMap<String, Visitor> map = (HashMap<String, Visitor>) application.getAttribute("ONLINE_USER");
		 HttpSession session = arg0.getSession();
  	     map.put(session.getId(), visitor);
  	     application.setAttribute("ONLINE_USER", map);
  	     
  	     //这个地方设置一个session，是为了后面的设置历史访问记录里面的VisitId，因为Visiotor的ID与VisitId是关联关系。
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
    	//ht.setVisitID(((Visitor)request.getSession().getAttribute("USER")).getID());这个不是该在该用户是注册过的再设置visitid吗
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
         
         //现在数据库里面设置对应的session的离开时间，再从application中删除
         map.remove(sessionid);
         application.setAttribute("ONLINE_USER", map);//因为之前使用的是application的引用，所以没必要进行进一步的更新。
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }
    
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//contextInitialized对应于服务器启动的时候，开始做的监听器工作
    	System.out.println("服务器启动");
    	HashMap<String, Visitor> map = new HashMap<String,Visitor>();
    	ServletContext application = arg0.getServletContext();
    	application.setAttribute("ONLINE_USER", map);
    }
}
