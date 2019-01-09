<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:if test="#session.user==null">
用户未登录
</s:if>
<s:else>
当前登录的用户是:<s:property value="#session.user.userName"/>
</s:else>
<br/>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Register.jsp">注册</a>
<a href="${pageContext.request.contextPath}/Login.jsp" style="margin-left:20px">登录</a>
<a href="${pageContext.request.contextPath}/user/list" style="margin-left:20px">用户列表</a>

</body>
</html>