<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布评论</title>
	<!-- 视口设置 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<!-- 引入bootstrap的css文件 -->
<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css">
<!-- 引入datepicker插件css -->
<link rel="stylesheet" type="text/css" href="js/datepicker/jquery.datetimepicker.css">
<!-- 引入css文件 -->
<link rel="stylesheet" type="text/css" href="styles/style.css">
<!-- 引入jquery的js文件 -->
<script type="text/javascript" src="js/jquery.js"></script>
<!-- 引入angularjs的js文件 -->
<script type="text/javascript" src="js/angularjs/angular.js"></script>
<!-- 引入layer.js插件 -->
<script type="text/javascript" src="js/layer/layer.js"></script>
<!-- 引入全局配置js  -->
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".submit").on('click',function(e){
		var articleId = $("input[id='articleId']").val();
		var currentArticlePage=$("#currentArticlePage").val();
		
        $.ajax({
	        url:"http://localhost:8080/Experience/AddCommentServlet",
	        type:"get",
	        datatype:"json",
	        data:{
	        	 "articleId": $("#articleId").val(),
	             "commentContent":$("#commentContent").val(),
	             "currentArticlePage":$("#currentArticlePage").val(),
	        },
	        success:function(msg){
	            if(msg){
	            	alert("发表评论成功");
                    location.href="http://localhost:8080/Experience/QueryArticleServlet?articleId="+articleId+"&currentArticlePage="+currentArticlePage;
	            }
	            else{
	            	 alert('ajax请求出现错误...');  
	            }
	        }
	    });
	});
});

</script>
</head>
<body>
	
	<%
		String articleId = request.getParameter("articleId");
		String currentArticlePage = request.getParameter("currentArticlePage");
	%>
		
	<!-- 网页头部开始 -->
	<div ng-include="'inc/header.jsp'"></div>
	<!-- 网页头部结束 -->

	<div class="container">		
		<div class="row">			
			<!-- 左侧区域开始 -->
			<div class="col-md-3">				
			</div>
			<!-- 左侧区域结束 -->
			<!-- 右侧区域开始 -->
			<div class="col-md-8">
				<h3 class="page-header">发表评论</h3>
				<!-- 表单开始 -->
				<form class="form-horizontal" id="addForm" >
					<div class="form-group">
						<label for="" class="col-sm-2"></label>
						<div class="col-sm-8">
							<input type="hidden"  id="articleId" class="form-control" value="<%=articleId%>">
							<input type="hidden"  id="currentArticlePage" class="form-control" value="<%=currentArticlePage%>">
						</div>				
					</div>	
					<div class="form-group">
						<label for="" class="col-sm-2">评论内容</label>
						<div class="col-sm-8" >
							<textarea placeholder="请在此处输入内容" rows="8" id="commentContent" class="content"  ></textarea>
						</div>				
					</div>
					<div class="form-group">				
						<div class="col-sm-8 col-sm-offset-2">
							<input type="button" value="发表评论" class="btn btn-primary submit" >
						</div>				
					</div>
				</form>
				<!-- 表单结束 -->
			</div>
			<!-- 右侧区域结束 -->
		</div>
	</div>
	


	
</body>
</html>