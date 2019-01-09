package book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class QuerySubjectServlet
 */
@WebServlet("/QuerySubjectServlet")
public class QuerySubjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		�����
		PrintWriter out = response.getWriter();
		
//		ҵ�����
		SubjectService service = new SubjectService();
				
		try {			
			ArrayList<Subject> list = service.queryAllSubject();
			
//			����json��ʽ����
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();
			
			out.print(gson.toJson(list));			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

}