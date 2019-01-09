<%@page import="java.text.SimpleDateFormat"%>
<%@page import="course.pojo.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="course.dao.CourseDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery.js"></script>
<!-- 视口设置 -->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<!-- 引入bootstrap的css文件 -->
<link rel="stylesheet" type="text/css"
	href="js/bootstrap/css/bootstrap.css">
<!-- 引入css文件 -->
<link rel="stylesheet" type="text/css" href="styles/base.css">
<link rel="stylesheet" href="styles/index.css">
<!-- 引入jquery的js文件 -->
<script type="text/javascript" src="js/bootstrap/js/jquery.js"></script>
<!-- 引入bootstrap的js文件 -->
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
<!-- 引入angularjs的js文件 -->
<script type="text/javascript" src="js/angularjs/angular.js"></script>
<!-- 引入全局配置js -->
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/*首先判断session中是否有值，如果没有，就隐藏整个HTML的body标签，其实body内容已经显示，只不过设置为hidden  */
		document.getElementById("myBody").style.visibility="hidden";
		var user="<%=session.getAttribute("USER")%>"; 
	   　　if(user=="null"){
		   alert("您好,请先在首页登录!");
		   location.href = "http://localhost:8080/Experience"
	   　　}else{
		   document.getElementById("myBody").style.visibility="visible";
	   　　}
		
		$(".delete").on('click', function(e) {
			var courseId = $(e.target).closest("tr").find("#courseId").text();
			$.ajax({
				url : "http://localhost:8080/Experience/DelCourseServlet",
				type : "get",
				datatype : "json",
				data : {
					"courseId" : courseId
				},
				success : function(msg) {
					if (msg) {
						alert("删除成功");
						location.href = "http://localhost:8080/Experience/NewQueryCourse.jsp"
					} else {
						alert("删除失败");
					}
				},
				error : function(msg) {
					alert('ajax请求出现错误...');
				}
			});
		});



		var oldCourse;
		$(".updata").on("click", function(e) {
			var user = $(e.target).closest("tr");
			oldCourse = user;
			
			$("#CourseDiv").css("display", "none");
			$("#PageDiv").css("display", "none");
			$("#UpdataDiv").css("display", "block");
			var updataDiv = $("#UpdataForm");
			updataDiv.find("input[name='courseId']").val(user.find("#courseId").text());
			updataDiv.find("input[name='courseName']").val(user.find("#courseName").text());
			updataDiv.find("input[name='courseCredit']").val(user.find("#courseCredit").text());
			updataDiv.find("input[name='courseTeacher']").val(user.find("#courseTeacher").text());
			updataDiv.find("input[name='courseAddress']").val(user.find("#courseAddress").text());
			updataDiv.find("input[name='courseTime']").val(user.find("#courseTime").text());
			updataDiv.find("input[name='courseLong']").val(user.find("#courseLong").text());
			updataDiv.find("input[name='courseFeature']").val(user.find("#courseFeature").text());
			updataDiv.find("input[name='courseType']").val(user.find("#courseType").text());
		});

		$(".submit").on("click", function(e) {
			var user = $(e.target).closest("#UpdataForm");
			$.ajax({
				url : "http://localhost:8080/Experience/UpdateCourseServlet",
				type : "get",
				datatype : "json",
				data : {
					"courseId" : $("input[name='courseId']").val(),
					"courseName" : $("input[name='courseName']").val(),
					"courseCredit" : $("input[name='courseCredit']").val(),
					"courseTeacher" : $("input[name='courseTeacher']").val(),
					"courseAddress" : $("input[name='courseAddress']").val(),
					"courseTime" : $("input[name='courseTime']").val(),
					"courseLong" : $("input[name='courseLong']").val(),
					"courseFeature" : $("input[name='courseFeature']").val(),
					"courseType" : $("input[name='courseType']").val(),
				},
				success : function(msg) {
					if (msg) {
						oldCourse.find("#courseId").text(user.find("input[name='courseId']").val());
						oldCourse.find("#courseName").text(user.find("input[name='courseName']").val());
						oldCourse.find("#courseCredit").text(user.find("input[name='courseCredit']").val());
						oldCourse.find("#courseTeacher").text(user.find("input[name='courseTeacher']").val());
						oldCourse.find("#courseAddress").text(user.find("input[name='courseAddress']").val());
						oldCourse.find("#courseTime").text(user.find("input[name='courseTime']").val());
						oldCourse.find("#courseLong").text(user.find("input[name='courseLong']").val());
						oldCourse.find("#courseFeature").text(user.find("input[name='courseFeature']").val());
						oldCourse.find("#courseType").text(user.find("input[name='courseType']").val());
						$("#UpdataDiv").css("display", "none");
						alert("更新成功");
						location.href = "http://localhost:8080/Experience/NewQueryCourse.jsp"
					} else {
						alert("更新失败");
					}
				}
			});
		});

	});
</script>
<title>课程信息</title>
</head>
<body id="myBody">
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<!-- <div class="col-xs-3 " id="myMenu">        -->
				<ul class="nav nav-tabs nav-stacked affix-top" data-spy="affix"
					data-offset-top="380" style="margin-top: 0px;">
					<li><a href="http://localhost:8080/Experience/">返回首页</a></li>
					<li class="active"><a href="NewQueryCourse.jsp">全部课程</a></li>
					<li style="display: ${USER.user_flag == 1 ? 'none' : ''};"><a href="AddCourse.html">增加课程</a></li>
				</ul>
			</div>
			<div class="col-xs-8" id="CourseDiv">
				<table class="table table-striped table-bordered" border="1">
					<tr>
						<th>课程id号</th>
						<th>课程名</th>
						<th>课程学分</th>
						<th>授课教师</th>
						<th>授课教室</th>
						<th>上课时间</th>
						<th>授课时长</th>
						<th>课程类型</th>
						<th>考察类型</th>
						<th style="display: ${USER.user_flag == 1 ? 'none' : ''};">操作</th>
					</tr>
					<%
						CourseDao courseDao = new CourseDao();
						//设置页面显示的记录数,默认是4条
						courseDao.setPageSize(4);
						if (session.getAttribute("Coursepage") == null) {
							courseDao.setPageNo(1);
							session.setAttribute("Coursepage", 1);
						} else {
							courseDao.setPageNo(Integer.parseInt(session.getAttribute("Coursepage").toString()));
						}
						//计算一共多少页
						courseDao.computePageCount();
						ArrayList<Course> courList = courseDao.queryPageData();
						for (int i = 0; i < courList.size(); i++) {
							Course course = courList.get(i);
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							//在后面的程序中可以设置时间类型
							//String Vtime = simpleDateFormat.format()
					%>
					<tr>
						<td id="courseId"><%=course.getCourseId()%></td>
						<td id="courseName"><%=course.getCourseName()%></td>
						<td id="courseCredit"><%=course.getCourseCredit()%></td>
						<td id="courseTeacher"><%=course.getCourseTeacher()%></td>
						<td id="courseAddress"><%=course.getCourseAdd()%></td>
						<td id="courseTime"><%=course.getCourseTime()%></td>
						<td id="courseLong"><%=course.getCourseLong()%></td>
						<td id="courseFeature"><%=course.getCourseFeature()%></td>
						<td id="courseType"><%=course.getCourseType()%></td>
						<td style="display: ${USER.user_flag == 1 ? 'none' : ''};"><input type="button" value="删除" class="delete"> <input
							type="button" value="修改" class="updata"></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>

			<!-- 修改课程信息 -->
			<div class="col-md-8"
				style=" display: none;"
				id="UpdataDiv">
				<!-- style=" display: none; position: absolute; left: 40%; top: 30%;" -->
				<form action="Updata" method="post" id="UpdataForm" class="form-horizontal">
				<div class="form-group">
					<!-- <label for="" class="col-sm-2">课程ID：</label> -->
					<div class="col-sm-8">
						<input type="hidden" name="courseId"><br>
					</div>
				</div>
				<div class="form-group">
						<label for="" class="col-sm-2">课程名：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入课程名" name="courseName" class="form-control" ng-model="courseName">
						</div>				
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2">课程学分：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入学分" name="courseCredit" class="form-control" ng-model="courseCredit">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">课程教师：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入老师姓名" name="courseTeacher" class="form-control" ng-model="courseTeacher">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">授课教室：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入教室" name="courseAddress" class="form-control" ng-model="courseAdd">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">授课时间：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入上课时间" name="courseTime" class="form-control" ng-model="courseTime">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">上课时长：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入周次" name="courseLong" class="form-control" ng-model="courseLong">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">课程类型：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入课程属性" name="courseFeature" class="form-control" ng-model="courseFeature">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">考察类型：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入考试类型" name="courseType" class="form-control" ng-model="courseType">
						</div>				
					</div>	
					<div class="form-group">				
						<div class="col-sm-8 col-sm-offset-2">
							<input type="button" value="修改课程信息" class="submit" ng-click="update();">
						</div>				
					</div>
<!-- 					 课程名：<input	type="text" name="courseName"><br> 
						课程学分：<input type="text" name="courseCredit"><br> 
						课程教师：<input type="text" name="courseTeacher"><br> 
						授课教室：<input type="text" name="courseAddress"><br>
						授课时间：<input type="text" name="courseTime"><br> 
						上课时长：<input type="text" name="courseLong"><br> 
						课程类型：<input type="text" name="courseFeature"><br> 
						考察类型：<input type="text" name="courseType"><br>
						 <input type="button" value="提交" class="submit"> -->
				</form>
			</div>
			
			
			<div class="text-center" id="PageDiv">
			<ul class="pagination">
				<li>
					<a href="http://localhost:8080/Experience/QueryCourseServlet?op=indexPage">首页
				</a>
				</li>
				<li>
				<a href="http://localhost:8080/Experience/QueryCourseServlet?op=prev">上一页
				</a>
				<li>
					<a> <%=session.getAttribute("Coursepage")%>/<%=courseDao.getPageCount()%> </a> 
				</li>
				<li>
				<a href="http://localhost:8080/Experience/QueryCourseServlet?op=next">下一页
				</a>
				
				</li>
				<li>
					<a href="http://localhost:8080/Experience/QueryCourseServlet?op=lastPage">尾页
				</a>
				</li>
			
			</ul>
			</div>
		</div>
	</div>
</body>
</html>