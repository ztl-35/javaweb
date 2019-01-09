<%@page import="myuser.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$(".deleteUser").each(function(index, element) {
        $(this).click(function(){
			if(!confirm("你真的要删除["+element.lang+"]这个用户吗？")){
				return;
			}else{
				$.post("user.do?op=delete",{userName:element.lang,pwd:""},function(data){
					location.href="UserList.jsp";
				});
			}
		});
    });
	
	$(".modifyUser").each(function(index, element) {
        $(this).click(function(data){
			location.href = "modifyUser.jsp?id="+element.lang;
		});
    });
});
</script>
</head>
<body>
用户列表
<%
	pageContext.setAttribute("userList", new UserDAO().getusers());
%>
<br/>
<br/>
<table id="tb">
<tr><th>序号</th><th>用户名</th><th>密码</th><th>删除</th><th>修改</th></tr>
<!-- 这是Struts1的循环标签 -->
<logic:iterate id="user" name="userList" type="myuser.User" indexId="number">       
<tr>
	<td>${number+1}</td>
    <td>${user.userName}</td><!--这里得到属性有两种方式，一种是EL表达式，一种是Struts框架的写法 -->
    <td><bean:write name="user" property="pwd"/></td>
    <td><a href="#" lang="${user.userName}" class="deleteUser">删除</a></td><!-- href="#"表示不跳转     lang属性是表示携带的元素的属性 -->
    <td><a href="#" lang="${user.id}" class="modifyUser">修改</a></td>
</tr>     
</logic:iterate>
</table>
<br/>
<br/>
<a href="Main.jsp">返回主页面</a>
</body>
</html>