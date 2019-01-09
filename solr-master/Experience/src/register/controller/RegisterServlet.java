package register.controller;

import register.service.RegisterService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.action") 
public class RegisterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String remail = request.getParameter("remail");
		String rname = request.getParameter("rname");
		String rpwd = request.getParameter("rpwd");
		System.out.println("remail  "+remail);
		System.out.println("rname  "+  rname);
		System.out.println("rpwd  "+rpwd);
		
		RegisterService service = new RegisterService();
		//�����û������в�ѯ����֤�û�Ψһ�������ֹ���Ա���û�
		if (service.checkUserByUserName(rname)) {
			//true����������û����ˣ�������ע��
			out.write("false");
		}else {
			try {
				service.register(remail,rname,rpwd);
				out.write("true");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
