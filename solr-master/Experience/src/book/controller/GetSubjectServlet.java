package book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import book.pojo.Subject;
import book.service.SubjectService;

/**
 * Servlet implementation class GetSubjectServlet
 */
@WebServlet("/GetSubjectServlet")
public class GetSubjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940326462287371131L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		输出流
		PrintWriter out = response.getWriter();

		String subjectId = request.getParameter("subjectId");

		SubjectService service = new SubjectService();

		try {
			Subject subject = service.querySubjectById(Integer.parseInt(subjectId));
			System.out.println(subject);	
			
//			产生json格式数据
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();
			
			out.print(gson.toJson(subject));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}