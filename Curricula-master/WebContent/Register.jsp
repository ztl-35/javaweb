<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		var pwd = $("input[name='student.pwd']").val();
		var repeatPwd = $("#repeat_pws").val();
		if(pwd!=repeatPwd){
			alert("您的密码输入不一致，请重新输入！");
			return false;
		}
	});
});
</script>
</head>
<body>
这里是注册页面
<br/>
<br/>
<form action="${pageContext.request.contextPath}/student/addStudent" method="post">
<table>
<tr><td>用户名</td><td><input type="text" name="student.name"></td></tr>
<tr><td>密码</td><td><input type="password" name="student.pwd"></td></tr>
<tr><td>重复密码</td><td><input type="password" id="repeat_pws"></td></tr>
<tr>
	<td>性别</td>
    <td>
    	<input type="radio" value="男" checked name="student.sex">男
        <input type="radio" value="女" name="student.sex">女
    </td>
</tr>
<tr>
	<td>年级</td>
    <td >
        <select name="student.grade">
            <option value="一年级">一年级</option>
            <option value="二年级">二年级</option>
            <option value="三年级">三年级</option>
            <option value="四年级">四年级</option>
        </select>
     </td>
</tr>
<tr>
    <td colspan="2">
        <div align="center">
            <input type="submit" value="提交" id="btn">
        </div>
    </td>
</tr>
</table>
</form>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页面</a>
</body>
</html>