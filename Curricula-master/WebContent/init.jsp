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
	var cell = "";
	$(".teacherName").click(function(){
		cell = this;
		var courseid = this.lang.split("!")[0];
		var coursename = this.lang.split("!")[1];
		$("#tw [name='course.id']").val(courseid);
		<!--
		$.post("${pageContext.request.contextPath}/course/getCourses",{"course.id":this.lang},function(data){
			$("#courseName").html(data.split("!")[1]);
		});
		-->
		$("#courseName").html(coursename);
		$("#tw").window('open');
	});
	$(".teacherRadio").click(function(){
		$("#tw [name='teacher.id']").val(this.lang);
	});
	$("#saveTeacher").click(function(){
		var teacherName="";
		//防止教师姓名的post请求异步执行，导致后面一个post teacherName为空
		$.ajaxSettings.async=false;
		$.post("${pageContext.request.contextPath}/teacher/getTeachers",{"teacher.id":$("#tw [name='teacher.id']").val()},function(data){
			teacherName = data.split("!")[1];
		});
		$.post("${pageContext.request.contextPath}/course/updateTeacher",{"course.id":$("#tw [name='course.id']").val(),"teacher.id":$("#tw [name='teacher.id']").val()},function(data){
			cell.innerHtml = teacherName;
			$("#tw").window('close');
			location.href = "${pageContext.request.contextPath}/init/initmain";
		});
	});
	
	
	$(".roomName").click(function(){
		cell = this;
		var courseid = this.lang.split("!")[0];
		var coursename = this.lang.split("!")[1];
		$("#rw [name='course.id']").val(courseid);
		<!--这是通过post请求方式来得到的courseName
		$.post("${pageContext.request.contextPath}/course/getCourses",{"course.id":this.lang},function(data){
			$("#cName").html(data.split("!")[1]);
		});
		-->
		$("#cName").html(coursename);
		$("#rw").window('open');
	});
	$(".roomRadio").click(function(){
		$("#rw [name='room.id']").val(this.lang);
	});
	$("#saveRoom").click(function(){
		var roomName="";
		$.ajaxSettings.async=false;
		$.post("${pageContext.request.contextPath}/room/getRooms",{"room.id":$("#rw [name='room.id']").val()},function(data){
			roomName = data.split("!")[1];
		});
		$.post("${pageContext.request.contextPath}/course/updateRoom",{"course.id":$("#rw [name='course.id']").val(),"room.id":$("#rw [name='room.id']").val()},function(data){
			cell.innerHtml = roomName;
			$("#rw").window('close');
			location.href = "${pageContext.request.contextPath}/init/initmain";
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


<div id="tw" class="easyui-window" title="教师信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<!-- 
	三个变量的使用说明：
	course.id 和 teacher.id 这两个隐藏变量是为了在用户修改授课教师以后，把数据库里面表的外键东西关联起来，只有这样才能将信息链接起来
	teacher.name是为了在关闭这个修改页面的时候，能够直接将修改过后的老师名字添加到课程页面中，不需要再调用数据库提取老师名字了。
	 -->
	<input type="hidden" name="course.id">
	<input type="hidden" name="teacher.id">
	<!-- teacher.name是用post方式进行提取的 -->
	<input type="hidden" name="teacher.name">
	你正在为课程[<span id="courseName"></span>]设置授课教师。。。。。
	<table id="tb">
	<tr><th>选择</th><th>序号</th><th>教师名</th><th>教师性别</th><th>教师电话号码</th></tr>
	<s:iterator value="#TEACHERS" id="myteacher" status="st">
	<tr><td><input type="radio" name="teacher" class="teacherRadio" lang="${myteacher.id}"></td><td>${st.index+1}</td><td>${myteacher.name}</td><td>${myteacher.sex}</td><td>${myteacher.phone}</td></tr>
	</s:iterator>
	</table>
	<input type="submit" value="保存" id="saveTeacher">
</div>

<div id="rw" class="easyui-window" title="教室信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<input type="hidden" name="course.id">
	<input type="hidden" name="room.id">
    你正在为课程[<span id="cName"></span>]设置授课教室。。。。。
    <table id="tb">
	<tr><th>选择</th><th>序号</th><th>教室名</th><th>教室地址</th></tr>
	<s:iterator value="#ROOMS" id="myroom" status="st">
	<tr><td><input type="radio" name="room" class="roomRadio" lang="${myroom.id}"></td><td>${st.index+1}</td><td>${myroom.name}</td><td>${myroom.address}</td></tr>
	</s:iterator>
	</table>
	<input type="submit" value="保存" id="saveRoom">
</div>

<div id="info"></div>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>


<s:if test="#COURSES!=null">
<table id="tb">
<tr><th>序号</th><th>课程名</th><th>课程类型</th><th>课时</th><th>授课教师[可点击修改]</th><th>授课教室[可点击修改]</th></tr>
<s:iterator value="#COURSES" id="mycourse" status="st">
<!--注意在这个里面是循环，不能用id来标识一个button，会随着循环导致不是唯一的id-->
	<tr>
		<td align="center">${st.index+1}</td>
		<td align="center">${mycourse.name}</td>
		<td align="center">${mycourse.type}</td>
		<td align="center">${mycourse.hours}</td>
		<td align="center" class="teacherName" lang="${mycourse.id}!${mycourse.name}" style="font-style:italic; font-weight:bold; color:#06F;">${mycourse.teacher.name}</td>
		<td align="center" class="roomName" lang="${mycourse.id}!${mycourse.name}"  style="font-style:italic; font-weight:bold; color:#06F;">${mycourse.room.name}</td>
	</tr>
</s:iterator>
</table>
${NVRBAR}
</s:if>
<s:else>
现在CourseS为空
</s:else>


</body>
</html>