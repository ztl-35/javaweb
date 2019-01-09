<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="Jquery/jquery-1.11.1.js"></script>
<link rel="stylesheet" type="text/css" href="Link_biaoge.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$("#btn_submit").click(function(){
		var info="";
		$.ajaxSetup({async:false});
		$.post("CheckLogin",{username:$("input[type='text'][name='username']").val()},function(data){
			info = data;
        });
		if(info.length>0){
			alert(info);
			return false;
		}
	});
});
</script>
</head>
<body>
<form action="ValidUserServlet" method="post">
<table id="tb">
<tr><th colspan="2">用户登录信息</th></tr>
<tr><td>用户名<td><input type="text" name="username"></td></tr>
<tr><td>密码</td><td><input type="text" name="pwd"></td></tr>
<tr><td colspan="2"><input type="submit" value="登录" id="btn_submit"></td></tr>
</table>
</form>
</body>
</html>