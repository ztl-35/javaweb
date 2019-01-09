<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/uploadPic/uploadPreview.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	//随着图片的点击，可以在Ecilipse内置的浏览器里面切换，但是不能在Firefox浏览器里面切换 用一个插件替代下述代码
//	$("[name='image']").change(function(){
//		$("#pic").attr("src",$(this).val());
//	});
	new uploadPreview({UpBtn:"uppic",DivShow:"upimage",ImgShow:"pic"});
	
	
	$("#btn").click(function(){
		var flag = true;
		$.ajax({
			type:"POST",
			async:false,
			url:"${pageContext.request.contextPath}/student/checkOldPwd",
			data:{"student.id":"${STUDENT.id}","student.pwd":$("#oldpwd").val()},
			success: function(data){
				if(data=="0"){
					$("#msg").html("旧密码输入错误!");
					flag = false;
				}
			}
		});
		if(!flag){
			return flag;
		}
		if($.trim($("input[name='student.pwd']").val()).length==0){
			$("#msg").html("新密码输入不能为空!");
			return false;
		}
		if($.trim($("input[name='student.pwd']").val())!=$("#repeat_pwd").val()){
			$("#msg").html("两次密码输入不一致!");
			return false;
		}
		return true;
	});
});
</script>
</head>
<body>
这里是修改用户信息页面
<br/>
<br/>
<s:if test="#session.STUDENT==null">
<!-- 为了防止用户长时间不点击修改信息页面，导致session在服务器中过期-->
<jsp:forward page="${pageContext.request.contextPath}/Login.jsp"></jsp:forward>
</s:if>
<br/>
<br/>
<table>
<tr><td>
<form action="${pageContext.request.contextPath}/student/updateStudent" method="post" enctype="multipart/form-data">
<table>
<tr><td>用户名</td><td><input type="text" name="student.name" value="${STUDENT.name}">
<input type="hidden" name="student.id" value="${STUDENT.id}"></td></tr><!-- 构造一个隐藏变量，到后台之后，可以在数据库中修改对应的位置-->
<tr><td>旧密码</td><td><input type="password" id="oldpwd"></td></tr>
<tr><td>新密码</td><td><input type="password" name="student.pwd" value="${STUDENT.pwd}"></td></tr>
<tr><td>重新输入密码</td><td><input type="password" id="repeat_pwd" value="${STUDENT.pwd}"></td></tr>
<tr>
	<!--因为需要从数据库中读取信息到修改页面，设定为该用户之前已经写好的性别 用Struts2的标签比较方便-->
	<td>性别</td>
    <td>
    	<s:radio list="#{'男':'男','女':'女'}" name="student.sex" value="#session.STUDENT.sex"/>
    </td>
</tr>
<tr>
	<td>年级</td>
    <td>
       <s:select name="student.grade" list="#{'一年级':'一年级','二年级':'二年级','三年级':'三年级','四年级':'四年级'}" value="#session.STUDENT.grade"></s:select>
    </td>
</tr>
<tr><td>手机号码</td><td><input type="text" name="student.phone" value="${STUDENT.phone}"></td></tr>
<tr><td>上传照片</td><td><input type="file" name="image" id="uppic">
<!-- 这里设置一个隐藏变量，是为了显示用户不修改之前照片的信息，把它之前照片信息保存下来，下次继续显示 -->
<input type="hidden" name="student.photo" value="${STUDENT.photo}"></td></tr>
<tr>
    <td colspan="2">
        <div align="center">
            <input type="submit" value="提交" id="btn">
        </div>
    </td>
</tr>
</table>
</form>
</td>
<td>
<div id="upimage">
<s:if test="#session.STUDENT.photo==null">
<img src="images/userPhoto.png" width="223" height="244" id="pic"/>
</s:if>
<s:else>
<img src="${STUDENT.photo}" id="pic" width="223" height="244"/>
</s:else>
</div>
</td>
</tr>
</table>
<div id="msg"></div>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页面</a>
</body>
</html>