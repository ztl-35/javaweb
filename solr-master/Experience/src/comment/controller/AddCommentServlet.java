package comment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.pojo.User;
import comment.pojo.Comment;
import comment.service.CommentService;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {

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
		response.setCharacterEncoding("utf-8");
		// 输出流
		PrintWriter out = response.getWriter();
		// 获得请求参数
		
		String commentContent = request.getParameter("commentContent");
		String articleId = request.getParameter("articleId");
		
		
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String commentDate = sdf.format(new java.util.Date()); 
		System.out.println(commentDate);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		System.out.println(user);
		String userName = user.getUser_name();
		
		// 封装数据
		Comment comment = new Comment(commentContent, Integer.parseInt(articleId), userName, Date.valueOf(commentDate));

		// 创建业务对象
		CommentService service = new CommentService();
		try {
			service.addComment(comment);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("<h1>评论成功</h1>");
	}

}
