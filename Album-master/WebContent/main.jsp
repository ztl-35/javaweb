<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:if test="#session.USER==null">未登录</s:if>
<s:else>您当前的登录名是[<s:property value="#session.USER.userName"/>]<br/>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
</s:else>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/register.jsp">注册</a><br/><br/>
<a href="${pageContext.request.contextPath}/login.jsp">登录</a><br/><br/>
<a href="${pageContext.request.contextPath}/album.jsp">照片</a><br/><br/>
</body>
</html>