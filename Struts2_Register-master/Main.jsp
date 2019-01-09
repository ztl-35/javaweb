<%@page import="db.dbLib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br/>
<logic:empty name="user">
用户未登录
</logic:empty>
<logic:present name="user">
当前登录的用户是：${sessionScope.user.userName}
</logic:present>
<br/>
<br/>
<a href="Register.jsp">注册</a>
<a href="Login.jsp" style="margin-left:20px">登录</a>
<a href="UserList.jsp" style="margin-left:20px">用户列表</a>

</body>
</html>