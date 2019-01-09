package book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.pojo.Subject;
import book.service.SubjectService;

/*
/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateSubjectServlet")
public class UpdateSubjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String subjectId = request.getParameter("subjectId");
		String subjectName = request.getParameter("subjectName");
		String subjectDesc = request.getParameter("subjectDesc");
	

		// 封装数据
		Subject subject = new Subject(Integer.parseInt(subjectId),subjectName,subjectDesc);

		// 创建业务对象
		SubjectService service = new SubjectService();

		try {
			service.updateSubject(subject);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("修改成功！");

	}

}
