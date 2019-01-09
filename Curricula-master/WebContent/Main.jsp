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
这里是主页面
<br/>
<br/>
<s:if test="#session.STUDENT==null">
用户未登录
</s:if>
<s:else>
当前登录的用户是:<s:property value="#session.STUDENT.name"/>
<a href="${pageContext.request.contextPath}/Contact.jsp">紧急联系人</a>
<br/>
<a href="${pageContext.request.contextPath}/modify.jsp" style="font-size:18px">修改完善个人信息</a>
</s:else>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Admin.jsp">进入后台管理页面</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Register.jsp">进入注册页面</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Login.jsp">进入登录页面</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/course/cmain">进入选课页面</a>
</body>
</html>