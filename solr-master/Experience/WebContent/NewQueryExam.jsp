<%@page import="java.text.SimpleDateFormat"%>
<%@page import="exam.pojo.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@page import="exam.dao.ExamDao"%>
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
<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
			var examId = $(e.target).closest("tr").find("#examId").text();
			/* alert(examId); */
			$.ajax({
				url : "http://localhost:8080/Experience/DelExamServlet",
				type : "get",
				datatype : "json",
				data : {
					"examId" : examId
				},
				success : function(msg) {
					if (msg) {
						alert("删除成功");
						location.href = "http://localhost:8080/Experience/QueryExamServlet?op=indexPage"
					} else {
						alert("删除失败");
					}
				},
				error : function(msg) {
					alert('ajax请求出现错误...');
				}
			});
		});
		
		/*不要改为update*/
		$(".updata").on("click", function(e) {
			var exam = $(e.target).closest("tr");
			$("#ExamDiv").css("display", "none");
			$("#PageDiv").css("display", "none");
			$("#UpdataDiv").css("display", "block");
			var updataDiv = $("#UpdataForm");
			updataDiv.find("input[name='examId']").val(exam.find("#examId").text());
			updataDiv.find("input[name='examName']").val(exam.find("#examName").text());
			updataDiv.find("input[name='examDefine']").val(exam.find("#examDefine").text());
			updataDiv.find("input[name='examDesc']").val(exam.find("#examDesc").text());
			updataDiv.find("input[name='examWay']").val(exam.find("#examWay").text());
			updataDiv.find("input[name='examDate']").val(exam.find("#examDate").text());
			updataDiv.find("input[name='examRequirement']").val(exam.find("#examRequirement").text());
			updataDiv.find("input[name='examNote']").val(exam.find("#examNote").text());
		});

		$(".submit").on("click", function(e) {
			var user = $(e.target).closest("#UpdataForm");
			$.ajax({
				url : "http://localhost:8080/Experience/UpdateExamServlet",
				type : "get",
				datatype : "json",
				data : {
					"examId" : $("input[name='examId']").val(),
					"examName" : $("input[name='examName']").val(),
					"examDefine" : $("input[name='examDefine']").val(),
					"examDesc" : $("input[name='examDesc']").val(),
					"examWay" : $("input[name='examWay']").val(),
					"examDate" : $("input[name='examDate']").val(),
					"examRequirement" : $("input[name='examRequirement']").val(),
					"examNote" : $("input[name='examNote']").val(),
				},
				success : function(msg) {
					if (msg) {
						$("#UpdataDiv").css("display", "none");
						alert("更新成功");
						location.href = "http://localhost:8080/Experience/NewQueryExam.jsp"
					} else {
						alert("更新失败");
					}
				}
			});
		});

	});
</script>
<title>等级考试</title>
</head>
<body id="myBody">
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<!-- <div class="col-xs-3 " id="myMenu">        -->
				<ul class="nav nav-tabs nav-stacked affix-top" data-spy="affix"
					data-offset-top="380" style="margin-top: 0px;">
					<li><a href="http://localhost:8080/Experience/">返回首页</a></li>
					<li class="active"><a href="http://localhost:8080/Experience/QueryExamServlet?op=indexPage">全部考试</a></li>
					<li style="display: ${USER.user_flag == 1 ? 'none' : ''};"><a href="AddExam.html">增加考试</a></li>
				</ul>
			</div>
			<div class="col-xs-8" id="ExamDiv">
				<table class="table table-striped table-bordered" border="1">
					<tr>
						<th>等级id号</th>
						<th>等级考试科目</th>
						<th>等级</th>
						<th>等级考试类型</th>
						<th>等级考试方式</th>
						<th>等级考试时间</th>
						<th>等级考试要求</th>
						<th>注意事项</th>
						<th style="display: ${USER.user_flag == 1 ? 'none' : ''};">操作</th>
					</tr>
					<%
						ExamDao examDao = new ExamDao();
						//设置页面显示的记录数,默认是4条
						examDao.setPageSize(4);
						if (session.getAttribute("Exampage") == null) {
							examDao.setPageNo(1);
							session.setAttribute("Exampage", 1);
						} else {
							examDao.setPageNo(Integer.parseInt(session.getAttribute("Exampage").toString()));
						}
						//计算一共多少页
						examDao.computePageCount();
						ArrayList<Exam> examList = examDao.queryPageData();
						for (int i = 0; i < examList.size(); i++) {
							Exam exam = examList.get(i);
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							//在后面的程序中可以设置时间类型
							//String Vtime = simpleDateFormat.format()
					%>
					<tr>
						<td id="examId"><%=exam.getExamId()%></td>
						<td id="examName"><%=exam.getExamName()%></td>
						<td id="examDefine"><%=exam.getExamDefine()%></td>
						<td id="examDesc"><%=exam.getExamDesc()%></td>
						<td id="examWay"><%=exam.getExamWay()%></td>
						<td id="examDate"><%=exam.getExamDate()%></td>
						<td id="examRequirement"><%=exam.getExamRequirement()%></td>
						<td id="examNote"><%=exam.getExamNote()%></td>
						<td style="display: ${USER.user_flag == 1 ? 'none' : ''};">
							<input type="button" value="删除" class="delete" > 
							<input type="button" value="修改" class="updata" >
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>

			<!-- 修改等级考试信息 -->
			<div class="col-md-8"
				style=" display: none;"
				id="UpdataDiv">
				<!-- style=" display: none; position: absolute; left: 40%; top: 30%;" -->
				<form action="Updata" method="post" id="UpdataForm" class="form-horizontal">
				<div class="form-group">
					<!-- <label for="" class="col-sm-2">等级考试ID：</label> -->
					<div class="col-sm-8">
						<input type="hidden" name="examId"><br>
					</div>
				</div>
				<div class="form-group">
						<label for="" class="col-sm-2">等级考试科目：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入科目名" name="examName" class="form-control">
						</div>				
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2">等级：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入等级" name="examDefine" class="form-control">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">等级考试类型：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入等级考试类型" name="examDesc" class="form-control">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">等级考试方式：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入等级考试方式" name="examWay" class="form-control">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">等级考试时间：</label>
						<div class="col-sm-8">
							<input type="text" id="d241" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" placeholder="请输入等级考试时间" name="examDate"/>
						</div>	
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2">等级考试要求：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入等级考试要求" name="examRequirement" class="form-control">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">注意事项：</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入注意事项" name="examNote" class="form-control">
						</div>				
					</div>
					<div class="form-group">				
						<div class="col-sm-8 col-sm-offset-2">
							<input type="button" value="修改课程信息" class="submit">
						</div>		
					</div>
				</form>
			</div>
			
			
			<div class="text-center" id="PageDiv">
			<ul class="pagination">
				<li>
				<a href="http://localhost:8080/Experience/QueryExamServlet?op=indexPage">首页</a>
				</li>
				<li>
				<a href="http://localhost:8080/Experience/QueryExamServlet?op=prev">上一页</a>
				</li>
				<li>
					<a> <%=session.getAttribute("Exampage")%>/<%=examDao.getPageCount()%> </a> 
				</li>
				<li>
				<a href="http://localhost:8080/Experience/QueryExamServlet?op=next">下一页</a>
				</li>
				<li>
				<a href="http://localhost:8080/Experience/QueryExamServlet?op=lastPage">尾页</a>
				</li>
				
			</ul>
			</div>
		</div>
	</div>
</body>
</html>