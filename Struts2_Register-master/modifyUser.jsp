<%@page import="myuser.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
修改用户信息
<br/>
<br/>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	UserDAO userDao = new UserDAO();
	System.out.println("id:"+id);
	User user = userDao.getUserById(id);
%>
<form action="user.do?op=modify" method="post">
用户名:<input type="text" readonly name="userName" value="<%=user.getUserName() %>"/>
密码:<input type="text" name="pwd" value="<%=user.getPwd() %>"/>
<input type="hidden" name="id" value="<%=id%>"/>
<input type="submit" value="修改"/>
</form>

</body>
</html>