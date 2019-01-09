<%@page import="java.util.ArrayList,java.text.*,java.util.*"%>
<%@page import="History.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Link_biaoge.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p style="margin-left:100px">
历史浏览信息记录
</p>
<a href="Main.jsp">主页面</a>&nbsp;&nbsp;<a href="Visitor.jsp">访客页面</a>&nbsp;&nbsp;<a href="Login.jsp">登录页面</a>&nbsp;&nbsp;<a href="Online_user.jsp">在线用户页面</a>
<br></br>
<table id="tb">
  <tr>
  	<th>ID</th>
    <th>用户ID</th>
    <th>访问时间</th>
    <th>原来网页地址</th>
  </tr>
<%
	historyDAO hDAO = new historyDAO();
	hDAO.setPageSize(10);//首先设置每页显示10条记录
	hDAO.computePageCount();//为了后面调用总页数，需要先计算一下总页数。
	
	//对于PageNo的考虑，session可以记录历史的PageNo的页码，不用每次都是从第一页开始.
	if(session.getAttribute("page")==null){
		hDAO.setPageNo(1);
		session.setAttribute("page", 1);
	}else{
		hDAO.setPageNo(Integer.parseInt(session.getAttribute("page").toString()));
	}

	ArrayList<history> al = hDAO.getPageData();
	for(int i=0;i<al.size();i++){
		history ht = al.get(i);
		Date date = ht.getVisitTime();
		String url = "";
		if(ht.getUrl()!=null){
			url = ht.getUrl();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String vTime = simpleDateFormat.format(date);
%>
  <tr>
    <td><%=i+1 %></td>
    <td><%=ht.getVisitID() %></td>
    <td><%=vTime %></td>
    <td><%=url %></td>
  </tr>
<%
	}
%>
</table>
<a href="PageServlet?op=prev">上一页</a><%=hDAO.getPageNo() %>/<%=hDAO.getPageCount() %><a href="PageServlet?op=next">下一页</a>
</body>
</html>