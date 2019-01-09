<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jsLib/jquery-1.11.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
$(function(){
	//如果用户名失去焦点，判断是否重复
	$("[name='userName']").blur(function(){
		if($.trim($(this).val())==""){
			$("#msg").html("用户名不能为空!");
			$("[name='userName']").focus();
			$("[name='userName']").select();
			return false;
		}
		//不为空，就会异步调用action处理。
		$.post("user.do?op=CheckExists",{userName:$("[name='userName']").val(),pwd:$("[name='pwd']").val()},function(data){
			$("[name='hidden_data']").val(data);
			if(data=="1"){
				$("#msg").html("该用户已经注册!");
				$("[name='userName']").focus();
				$("[name='userName']").select();
				return false;
			}
			$("#msg").html("");//如果用户名正确，会停留上次用户名注册错误的信息。
		});
	});
	$("form").submit(function(){
		if($.trim($("[name='userName']").val())==""){
			$("#msg").html("用户名不能为空!");
			$("[name='userName']").focus();
			$("[name='userName']").select();
			return false;
		}
		
		if($("[name='hidden_data']").val()=="1"){
			$("#msg").html("该用户已经注册!");
			$("[name='userName']").focus();
			$("[name='userName']").select();
			return false;
		}
		
		if($.trim($("[name='pwd']").val())==""){
			$("[name='pwd']").focus();
			$("#msg").html("密码不能为空!");
			return false;
		}
		return true; 
	});	
});

</script>
</head>
<body>
用户注册
<br/>
<br/>
<form action="user.do?op=add" method="post">
<table id="tb">
<tr><th>用户名</th><th>密码</th></tr>
<tr><td>用户名:</td><td><input type="text" name="userName"/></td></tr>
<tr><td>密码:</td><td><input type="password" name="pwd"/></td></tr>
<input type="hidden" name="hidden_data" value="0" />
<tr><td><input type="submit" value="提交"/></td><td><div id="msg"></div></td></tr>
</table>
</form>
<a href="Main.jsp">返回主页面</a>
</body>
</html>