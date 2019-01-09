<%@page import="myuser.userDAO"%>
<%@page import="myuser.user"%>
<%@page import="java.util.ArrayList,java.text.*,java.util.*"%>
<%@page import="Myvisit.*"%>
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
访客信息记录
</p>'
<a href="Main.jsp">主页面</a>&nbsp;&nbsp;<a href="Online_user.jsp">在线用户页面</a>&nbsp;&nbsp;<a href="Login.jsp">登录页面</a>&nbsp;&nbsp;<a href="History.jsp">历史访客页面</a>
<br></br>
<table id="tb">
  <tr>
  	<th>ID</th>
    <th>用户ID</th>
    <th>访问时间</th>
    <th>离开时间</th>
    <th>IP地址</th>
    <th>原来网页地址</th>
  </tr>
<%
	VisitorDAO visitorDAO = new VisitorDAO();
	ArrayList<Visitor> al = visitorDAO.getVisitor();
	for(int i=0;i<al.size();i++){
		Visitor visitor = al.get(i);
		Date date = visitor.getVisitTime();
		String url = "";
		if(visitor.getComefrom()!=null){
			url = visitor.getComefrom();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String vTime = simpleDateFormat.format(date);
		String Ltime = "";
		if(visitor.getLeftTime()!=null){
			Ltime = simpleDateFormat.format(visitor.getLeftTime());
		}
		userDAO uDao = new userDAO();
		String username = uDao.getNameById(visitor.getUserID());
%>
  <tr>
    <td><%=i+1 %></td>
    <td><%=username %></td>
    <td><%=vTime %></td>
    <td><%=Ltime %></td>
    <td><%=visitor.getIp() %></td>
    <td><%=url %></td>
  </tr>
<%
	}
%>
</table>
</body>
</html>