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
	$(".selectIt").each(function(index, element) {
		$.ajax({async:false});
        $.post("${pageContext.request.contextPath}/course/checkSelect",{"course.id":element.lang},function(data){
			eval("element.checked = "+data);
		});
    });
	$(".selectIt").click(function(){
		$.post("${pageContext.request.contextPath}/course/checkIt",{"course.id":this.lang},function(data){
			var delOrAdd = data.split("!")[0];
			var MycourseName = data.split("!")[1];
			if(delOrAdd=="0"){
				alert("您已经取消了["+MycourseName+"]这门课!");
			}else{
				alert("您已经选择了["+MycourseName+"]这门课!");
			}
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

<div id="showSelected" class="easyui-window" title="教室信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
</div>

<div id="info"></div>
<br/>
<a href="${pageContext.request.contextPath}/Main.jsp">返回主页</a>


<s:if test="#COURSES!=null">
<table id="tb">
<tr><th>序号</th><th>课程名</th><th>课程类型</th><th>课时</th><th>授课教师</th><th>授课教室</th><th>选择</th></tr>
<s:iterator value="#COURSES" id="mycourse" status="st">
<!--注意在这个里面是循环，不能用id来标识一个button，会随着循环导致不是唯一的id-->
	<tr>
		<td align="center">${st.index+1}</td>
		<td align="center">${mycourse.name}</td>
		<td align="center">${mycourse.type}</td>
		<td align="center">${mycourse.hours}</td>
		<td align="center">${mycourse.teacher.name}</td>
		<td align="center">${mycourse.room.name}</td>
		<td><input type="checkbox" class="selectIt" lang="${mycourse.id}"></td>
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