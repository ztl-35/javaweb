package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.service.SubjectService;

/*
/**
 * 删除参考书数据访问接口
 */
@WebServlet("/DelSubjectServlet")
public class DelSubjectServlet extends HttpServlet {

	// private static final long serialVersionUID = 8642656559598059856L;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subjectId = request.getParameter("subjectId");

		SubjectService service = new SubjectService();

		try {
			service.deleteSubject(Integer.parseInt(subjectId));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
