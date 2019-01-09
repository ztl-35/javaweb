<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
这里是登录页面
<br/>
<br/>
<form action="${pageContext.request.contextPath}/student/loginStudent" method="post">
<table>
<tr><td>用户名</td><td><input type="text" name="student.name"></td></tr>
<tr><td>密码</td><td><input type="text" name="student.pwd"></td></tr>
<tr>
  <td colspan="2"><div align="center">
    <input type="submit" value="提交">
  </div></td></tr>
</table>
</form>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页面</a>
</body>
</html>