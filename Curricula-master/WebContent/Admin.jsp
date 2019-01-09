<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
这里是后台管理页面
<br/>
<br/>
<a href="${pageContext.request.contextPath}/room/main">教室管理Action</a><br/>
<a href="${pageContext.request.contextPath}/teacher/main">教师管理Action</a><br/>
<a href="${pageContext.request.contextPath}/course/main">课程管理Action</a><br/>
<a href="${pageContext.request.contextPath}/init/initmain">课程初始化Action</a><br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>
</body>
</html>