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
		if($.trim($("input[name='teacher.name']").val()).length==0){
			$("#info").html("请输入教师姓名!");
			$("input[name='teacher.name']").focus();
			return false;
		}
		if($.trim($("input[name='teacher.sex']").val()).length==0){
			$("#info").html("请输入教师性别!");
			$("input[name='teacher.sex']").focus();
			return false;
		}
		if($.trim($("input[name='teacher.phone']").val()).length==0){
			$("#info").html("请输入教师电话号码!");
			$("input[name='teacher.phone']").focus();
			return false;
		}
		alert("提交成功！")
	});
	$(".delete").click(function(){
		var flag = confirm("你真的要删除这个教师信息吗？");
		if(!flag){
			return;
		}
		$.post("${pageContext.request.contextPath}/teacher/delete",{"teacher.id":this.lang},function(data){
			location.href = "${pageContext.request.contextPath}/teacher/main?page="+$("#page").val();
		});
	});
	$(".modify").click(function(){
		$.post("${pageContext.request.contextPath}/teacher/getTeachers",{"teacher.id":this.lang},function(data){
			var teacher = data.split("!");
			<!--用了jQuery中的选择继承关系,不用另外加id来选择了-->
			$("#w [name='teacher.id']").val(teacher[0]);
			$("#w [name='teacher.name']").val(teacher[1]);
			$("#w [name='teacher.sex']").val(teacher[2]);
			$("#w [name='teacher.phone']").val(teacher[3]);
		});
		$('#w').window('open');
	});
	$("#w [type='submit']").click(function(){
		$.post("${pageContext.request.contextPath}/teacher/modify",{"teacher.id":$("#w [name='teacher.id']").val(),"teacher.name":$("#w [name='teacher.name']").val(),"teacher.sex":$("#w [name='teacher.sex']").val(),"teacher.phone":$("#w [name='teacher.phone']").val()},function(){
			$('#w').window('close');
			location.href="${pageContext.request.contextPath}/teacher/main?page="+$("#page").val();
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

<div id="w" class="easyui-window" title="教师信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<input type="hidden" name="teacher.id">
	教师姓名:<input type="text" name="teacher.name"> <br/>
	教师性别:<input type="text" name="teacher.sex"><br/>
	联系电话:<input type="text" name="teacher.phone"><br/>
	<input type="submit" value="保存">
</div>

<form method="post" action="${pageContext.request.contextPath}/teacher/update">
	教师姓名:<input type="text" name="teacher.name"> <br/>
	教师性别:<input type="text" name="teacher.sex"><br/>
	联系电话:<input type="text" name="teacher.phone"><br/>
	<s:token></s:token>
	<input type="submit" value="提交">
</form>
<div id="info"></div>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>


<p>=============================</p>


<s:if test="#TEACHERS!=null">
<table id="tb">
<tr><th>序号</th><th>教师姓名</th><th>教师性别</th><th>联系电话</th><th>删除教师</th><th>修改教师</th></tr>
<s:iterator value="#TEACHERS" id="myteacher" status="st">
<!--注意在这个里面是循环，不能用id来标识一个button，会随着循环导致不是唯一的id-->
<tr><td>${st.index+1}</td><td>${myteacher.name}</td><td>${myteacher.sex}</td><td>${myteacher.phone}</td><td><input type="button" value="删除" class="delete" lang="${myteacher.id}"></td>
<td><input type="button" value="修改" class="modify" lang="${myteacher.id}"></td></tr>
</s:iterator>
</table>
${NVRBAR}
</s:if>
<s:else>
现在TeacherS为空
</s:else>


</body>
</html>