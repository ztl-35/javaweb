package myuser;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Myvisit.Visitor;
import Myvisit.VisitorDAO;

/**
 * Servlet implementation class ValidUserServlet
 */
@WebServlet("/ValidUserServlet")
public class ValidUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");
		String Pwd = request.getParameter("pwd");
		user u = new user();
		u.setPwd(Pwd);
		u.setUserName(userName);
		userDAO uDao;
		int userID =0;
		try {
			uDao = new userDAO();
			userID = uDao.getUserId(u);
			if (userID==0) {
				response.sendRedirect("Login.jsp");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		//这个visitor是session的引用，里面已经保存了session匿名进来是，保存到visitor数据库里面产生的id，通过这个id重新设置之前匿名没有设置的userID。
		Visitor visitor =(Visitor)request.getSession().getAttribute("USER");
		visitor.setUserID(userID);
		try {
			VisitorDAO vDao = new VisitorDAO();
			vDao.update(visitor);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
