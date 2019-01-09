package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import notice.pojo.Notice;
import notice.service.NoticeService;

/**
 * Servlet implementation class GetNoticeServlet
 */
@WebServlet("/GetNoticeServlet")
public class GetNoticeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				
				//输出流
				PrintWriter out= response.getWriter();
		
		
			String noticeId = request.getParameter("noticeId");
			
			//创建业务对象
			NoticeService service = new NoticeService();
			try {
				Notice notice = service.queryNoticeById(Integer.parseInt(noticeId));
				
//				产生json格式数据
				Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd")  
				  .create();
				
				out.print(gson.toJson(notice));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}

}
