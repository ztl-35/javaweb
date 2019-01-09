<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/jquery-1.11.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Table_style/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Easyui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/Easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	$("[type='submit']").click(function(){
		if($.trim($("input[name='course.name']").val()).length==0){
			$("#info").html("请输入课程名!");
			$("input[name='course.name']").focus();
			return false;
		}
		if($.trim($("input[name='course.type']").val()).length==0){
			$("#info").html("请输入课程类别!");
			$("input[name='course.type']").focus();
			return false;
		}
		if($.trim($("input[name='course.hours']").val()).length==0){
			$("#info").html("请输入课时!");
			$("input[name='course.hours']").focus();
			return false;
		}
		alert("提交成功！")
	});
	$(".delete").click(function(){
		var flag = confirm("你真的要删除这个课程信息吗？");
		if(!flag){
			return;
		}
		$.post("${pageContext.request.contextPath}/course/delete",{"course.id":this.lang},function(data){
			location.href = "${pageContext.request.contextPath}/course/main?page="+$("#page").val();
		});
	});
	$(".modify").click(function(){
		$.post("${pageContext.request.contextPath}/course/getCourses",{"course.id":this.lang},function(data){
			var course = data.split("!");
			<!--用了jQuery中的选择继承关系,不用另外加id来选择了-->
			$("#w [name='course.id']").val(course[0]);
			$("#w [name='course.name']").val(course[1]);
			$("#w [name='course.type']").val(course[2]);
			$("#w [name='course.hours']").val(course[3]);
		});
		$('#w').window('open');
	});
	$("#w [type='submit']").click(function(){
		$.post("${pageContext.request.contextPath}/course/modify",{"course.id":$("#w [name='course.id']").val(),"course.name":$("#w [name='course.name']").val(),"course.type":$("#w [name='course.type']").val(),"course.hours":$("#w [name='course.hours']").val()},function(){
			$('#w').window('close');
			location.href="${pageContext.request.contextPath}/course/main?page="+$("#page").val();
		});
	});
	$("#w").window({
		width:400,
		height:200,
		modal:true,
		iconCls:'icon-ztl'//自己修改弹出窗口的图标（Easyui/themes/icon.css文件修改）
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 为了在进行修改，删除，添加的时候页码要根据情况进行修改，所以需要把当前的页码从action中记录下来，传到要跳转的页面 -->
<input type="hidden" value="${page}" id="page">

<div id="w" class="easyui-window" title="课程信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<input type="hidden" name="course.id">
	课程名:<input type="text" name="course.name"> <br/>
	课程类型:<input type="text" name="course.type"><br/>
	课时:<input type="text" name="course.hours"><br/>
	<input type="submit" value="保存">
</div>

<form method="post" action="${pageContext.request.contextPath}/course/update">
	课程名:<input type="text" name="course.name"> <br/>
	课程类型:<input type="text" name="course.type"><br/>
	课时:<input type="text" name="course.hours"><br/>
	<s:token></s:token>
	<input type="submit" value="提交">
</form>
<div id="info"></div>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>


<p>=============================</p>


<s:if test="#COURSES!=null">
<table id="tb">
<tr><th>序号</th><th>课程名</th><th>课程类型</th><th>课时</th><th>删除课程</th><th>修改课程</th></tr>
<s:iterator value="#COURSES" id="mycourse" status="st">
<!--注意在这个里面是循环，不能用id来标识一个button，会随着循环导致不是唯一的id-->
<tr><td>${st.index+1}</td><td>${mycourse.name}</td><td>${mycourse.type}</td><td>${mycourse.hours}</td><td><input type="button" value="删除" class="delete" lang="${mycourse.id}"></td>
<td><input type="button" value="修改" class="modify" lang="${mycourse.id}"></td></tr>
</s:iterator>
</table>
${NVRBAR}
</s:if>
<s:else>
现在CourseS为空
</s:else>


</body>
</html>