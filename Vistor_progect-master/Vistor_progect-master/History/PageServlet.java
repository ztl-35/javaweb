package History;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		historyDAO hDao;
		HttpSession session =  request.getSession();
		int page = Integer.parseInt(session.getAttribute("page").toString());
		try {
			hDao = new historyDAO();
			hDao.setPageSize(10);
			hDao.computePageCount();
			if (op.equals("prev")) {
				if (page>1) {
					page--;
				}
			}else {
				if (page<hDao.getPageCount()) {
					page++;
				}
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("page", page);
		response.sendRedirect("History.jsp");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
