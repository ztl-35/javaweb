<%@page import="myuser.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$(".delete").click(function(){
		layer.msg('zhangtaolin!');
		var str = this.lang.split("!");
		if(!confirm("你真的要删除["+str[1]+"]这个用户吗")){
			return;
		}
		$.post("${pageContext.request.contextPath}/user/delete.action",{"user.id":str[0]},function(){
			location.href = "list.action";
		});
	});
	$(".modify").click(function(){
		location.href = "${pageContext.request.contextPath}/user/modify.action?user.id="+this.lang;
	});
	$(".picture").click(function(){
		alert(this.lang);
		$.getJSON("${pageContext.request.contextPath}/picture/getpic.action",{"picture.uid":this.lang,"idType":"user"},function(data){
			alert(data);
			layer.photos({
				photos:data,
				anim:5
			});
		});
	});
	$(".picture").each(function(index, element) {
        $.post("${pageContext.request.contextPath}/picture/getpicNum.action",{"picture.uid":element.lang},function(data){
			element.innerHTML = element.innerHTML+"("+data+")";
			//$(this).html($(this).html()+"("+data+")"); post对内置的jQuery代码显示是不行的，必须是上面的JavaScript的代码显示
		});
    });
});
</script>
<title>Insert title here</title>
</head>
<body>
<table id="tb">
<tr><th>序号</th><th>用户名</th><th>密码</th><th>照片</th><th>删除</th><th>修改</th></tr>
<s:iterator value="#USERLIST" id="cuser" status="s">
<tr>
	<td><s:property value="#s.index+1"/></td>
	<td><s:property value="#cuser.userName"/></td>
	<td><s:property value="#cuser.pwd"/></td>
    <td><a href="#" class="picture" lang="<s:property value="#cuser.id"/>">显示照片</a></td>
	<td><a href="#" class="delete" lang="<s:property value="#cuser.id"/>!<s:property value="#cuser.userName"/>">删除</a></td>
	<td><a href="#" class="modify" lang="<s:property value="#cuser.id"/>">修改</a></td>
</tr>
</s:iterator>
</table>
</body>
</html>