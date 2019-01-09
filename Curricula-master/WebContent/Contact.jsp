<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	if($("[name='contact.sex']:checked").length==0){
		$("[name='contact.sex']:eq(0)").attr("checked",true);
	}
});
</script>
</head>
<body>
<s:if test="#session.STUDENT==null">
<jsp:forward page="${pageContext.request.contextPath}/Main.jsp"></jsp:forward>
</s:if>
<form action="${pageContext.request.contextPath}/contact/update" method="post">
<!-- 如果不加隐藏变量，会在数据库中重新添加一个紧急联系人数据，以前的联系人数据失效   因为没有id传到后台的hibernate函数saveorupdate会重新申请一个contact -->
<input type="hidden" name="contact.id" value="${STUDENT.contact.id}"/>
<table>
<tr><td>姓名:</td><td><input type="text" name="contact.name" value="${STUDENT.contact.name}"/></td></tr>
<tr><td>性别:</td><td><s:radio list="#{'男':'男','女':'女'}" name="contact.sex" value="#session.STUDENT.contact.sex"/></td></tr>
<tr><td>关系:</td><td><input type="text" name="contact.relation" value="${STUDENT.contact.relation}"/></td></tr>
<tr><td>电话:</td><td><input type="text" name="contact.phone" value="${STUDENT.contact.phone}"/></td></tr>
<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>
</table>
</form>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>
</body>
</html>