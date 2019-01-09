<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Table_style/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({
		async:false
		})
	$("form").submit(function(){
		var flag;
		$.post("${pageContext.request.contextPath}/user/checkUser",{"user.userName":$("[name='user.userName']").val()},function(data){
			if(data=="1"){
				flag = false;
				alert("用户名已存在!")
			}
		})
		return flag;
	});
});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/addUser" method="post">
<table id="tb">
<tr><td>用户名:</td><td><input type="text" name="user.userName"></td></tr>
<tr><td>密码:</td><td><input type="password" name="user.userPwd"></td></tr>
<tr><td colspan="2">
<div align="center">
  <input type="submit" value="注册">
</div></td></tr>
</table>
</form>
</body>
</html>