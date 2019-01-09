<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="Jquery/jquery-1.11.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
// 	$.post("InitDbServlet",function(data){
// 		alert(data);
// 	});
	$("#btnOnline").click(function(){
		//window.open("Online_user.jsp");
		location.href="Online_user.jsp";
	});
	$("#btnVisitor").click(function(){
		location.href="Visitor.jsp";
	});
	$("#btnHistory").click(function(){
		location.href="History.jsp";
	});
	$("#btnLogin").click(function(){
		location.href="Login.jsp";
	});
});
</script>

</head>
<body>
<input type="button" value="在线用户" id="btnOnline">
<input type="button" value="访客记录" id="btnVisitor">
<input type="button" value="历史记录" id="btnHistory">
<input type="button" value="用户登录" id="btnLogin">
</body>
</html>