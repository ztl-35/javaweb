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

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// �����
		PrintWriter out = response.getWriter();
		// ����������
		
		String commentContent = request.getParameter("commentContent");
		String articleId = request.getParameter("articleId");
		
		
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String commentDate = sdf.format(new java.util.Date()); 
		System.out.println(commentDate);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		System.out.println(user);
		String userName = user.getUser_name();
		
		// ��װ����
		Comment comment = new Comment(commentContent, Integer.parseInt(articleId), userName, Date.valueOf(commentDate));

		// ����ҵ�����
		CommentService service = new CommentService();
		try {
			service.addComment(comment);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("<h1>���۳ɹ�</h1>");
	}

}
