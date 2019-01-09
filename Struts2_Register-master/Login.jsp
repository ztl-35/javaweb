<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$("[type='button']").click(function(){
		if($.trim($("[name='userName']").val())==""){
			$("#msg").html("用户名不能为空!");
			$("[name='userName']").focus();
			$("[name='userName']").select();
			return false;
		}
		if($.trim($("[name='pwd']").val())==""){
			$("#msg").html("密码不能为空!");
			$("[name='pwd']").focus();
			$("[name='pwd']").select();
			return false;
		}
		$.post("user.do?op=checkLogin",{userName:$("[name='userName']").val(),pwd:$("[name='pwd']").val()},function(data){
			if(data=="1"){
				$("#msg").html("登录成功!");
				location.href = "Main.jsp";
			}else{
				$("#msg").html("登录失败，请重新输入!");
			}
		});
	});
});
</script>
</head>
<body>
<form action="" method="post">
<table id="tb">
<tr><th>用户名:</th><td><input type="text" name="userName"/></td></tr>
<tr><th>密码:</th><td><input type="password" name="pwd"/></td></tr>
<tr><td><input type="button" value="登录"/></td><td><div id="msg"></div></td></tr>
</table>
</form>
<a href="Main.jsp">返回主页面</a>
</body>
</html>