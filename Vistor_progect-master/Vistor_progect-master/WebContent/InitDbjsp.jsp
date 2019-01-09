<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="Jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='button']").click(function(){
		$.post("InitDbServlet",function(data){
			$("#div1").html(data); 
		});
	});
	
});
</script>
</head>
<body>
<input type="button" value="数据局测试">
<a href="Main.jsp">主页</a>
<div id="div1"></div>
</body>
</html>