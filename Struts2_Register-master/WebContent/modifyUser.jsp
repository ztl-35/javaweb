<%@page import="myuser.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$(".delete").click(function(){
		var str = this.lang.split("!");
		if(!confirm("你真的要删除["+str[1]+"]这张图片吗?")){
			return;
		}
		$.post("${pageContext.request.contextPath }/picture/delete.action",{"picture.id":str[0]},function(){
			location.href="${pageContext.request.contextPath}/user/list";
		});
	});
	$(".display").click(function(){
		$.getJSON("${pageContext.request.contextPath}/picture/getpic.action",{"picture.id":this.lang,"idType":"picture"},function(data){
			alert(data);
			layer.photos({
				photos:data,
				anim:5
			});
	});
});
});
</script>
</head>
<body>
修改用户信息
<br/>
<br/>
<form action="${pageContext.request.contextPath }/user/save.action" method="post">

用户名:<input type="text" readonly name="user.userName" value="<s:property value="user.userName"/>"/>
密码:<input type="text" name="user.pwd" value="<s:property value="user.pwd"/>"/>
<input type="hidden" name="user.id" value="<s:property value="user.id"/>"/>
<input type="submit" value="修改"/>
</form>
<br/>
<br/>
<s:if test="%{#PICTURES.size>0}">
<p style=" font-weight:bold">显示用户对应的照片</p>

<table id="tb">
<tr><th>序号</th><th>照片名</th><th>显示</th><th>删除</th></tr>
<s:iterator value="#PICTURES" id="cpic" status="s">
<tr>
	<td><s:property value="#s.index+1"/></td>
	<td><s:property value="#cpic.name"/></td>
	<td><a href="#" class="display" lang="<s:property value="#cpic.id"/>">显示</a></td>
    <td><a href="#" class="delete" lang="<s:property value="#cpic.id"/>!<s:property value="#cpic.name"/>">删除</a></td>
</tr>
</s:iterator>
</table>
</s:if>
<s:else>
该用户没有照片
</s:else>

<br/>
<br/>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/picture/add">
<table>
<tr><td>选择照片</td><td><input type="file" name="image"/></td></tr>
<tr><td>照片名称</td><td><input type="text" name="pictures[0].name"/></td></tr>
<input type="hidden" name="pictures[0].uid" value="<s:property value="user.id"/>"/>

<tr><td>选择照片</td><td><input type="file" name="image"/></td></tr>
<tr><td>照片名称</td><td><input type="text" name="pictures[1].name"/></td></tr>
<input type="hidden" name="pictures[1].uid" value="<s:property value="user.id"/>"/>

<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>
</table>
</form> 
</body>
</html>