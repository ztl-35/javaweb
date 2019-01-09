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
<s:if test="#session.USER==null">
<jsp:forward page="/main.jsp"></jsp:forward>
</s:if>
<s:else>
<a href="${pageContext.request.contextPath}/album/newFile.jsp">创建[<s:property value="#session.USER.userName"/>]用户相册</a>
</s:else>
</body>
</html>